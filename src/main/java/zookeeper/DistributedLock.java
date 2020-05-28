package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class DistributedLock implements Lock {

    private CuratorFramework client;

    private String connectString = "127.0.0.1:2181";

    private String lockNode = "/lock";

    private String nodePrefix = "/lock/seq-";

    private String prevNode = null;

    private String currentNode = null;

    private AtomicInteger reentrantCount = new AtomicInteger();

    public DistributedLock() throws Exception {
        client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        client.start();

        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(lockNode);
    }

    public void lock() {
        boolean locked;
        synchronized (this) {
            if (reentrantCount.get() == 0) {
                locked = tryLock();
                if (locked) {
                    return;
                }
            } else {
                reentrantCount.incrementAndGet();
                return;
            }
        }

        while (!locked) {
            try {
                //阻塞直到接收到watch事件
                await();
                //（2）获取等待列表
                List<String> childrenList = client.getChildren().forPath(lockNode);
                locked = checkLocked(childrenList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void unlock() throws Exception {
        if (reentrantCount.get() == 0) {
            //删除当前节点
            client.delete().forPath(currentNode);
        } else {
            reentrantCount.decrementAndGet();
        }
    }

    private void await() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        //监听前一个节点的变化情况
        //方法1.使用watcher
        client.getData().usingWatcher((Watcher) watchedEvent -> {
            //其实这里应该处理一下，判断一下监听到的事件是否是前一个节点被删除
            latch.countDown();
        }).forPath(prevNode);

        //方法2.使用cache，略

        //阻塞直到接收到前一个节点的变化情况
        latch.await();
    }

    public boolean tryLock() {
        //（1）创建顺序节点
        try {
            currentNode = client.create()
                    .creatingParentsIfNeeded()
                    .withProtection()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(nodePrefix);

            //（2）获取等待列表
            List<String> childrenList = client.getChildren().forPath(lockNode);

            if (checkLocked(childrenList)) {
                return true;
            }
            //（4）当前节点不是最小的节点，则获得当前节点的前面一个节点，并注册对他的watch
            int index = Collections.binarySearch(childrenList, currentNode);
            prevNode = childrenList.get(index - 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkLocked(List<String> childrenList) {
        Collections.sort(childrenList);
        //（3）当前节点是最小的节点，则说明加锁成功
        return !CollectionUtils.isEmpty(childrenList) && currentNode.equals(childrenList.get(0));
    }



}
