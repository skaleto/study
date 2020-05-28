package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;


public class DistributedIDGenerator {

    private CuratorFramework client;

    private String connectString = "127.0.0.1:2181";

    private String nodePrefix = "/distributedID/ID-";

    public void initialize() {
        client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        client.start();
    }

    public void registerWatch() throws Exception {
        client.getData().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("watchedEvent:"+watchedEvent);
            }
        }).forPath("/distributedID");
    }

    public String createID() throws Exception {
        return client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath(nodePrefix);
    }


    public static void main(String[] args) throws Exception {
        DistributedIDGenerator generator = new DistributedIDGenerator();
        generator.initialize();
        for (int i = 0; i < 10; i++) {
            System.out.println(generator.createID());
        }
    }


}
