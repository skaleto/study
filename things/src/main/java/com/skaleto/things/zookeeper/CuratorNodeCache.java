package com.skaleto.things.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorNodeCache {

    private CuratorFramework client;

    private String connectString = "127.0.0.1:2181";

    private String nodePrefix = "/nodecache";

    public void initialize() {
        client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();

        client.start();
    }

    public void createNodeCache() throws Exception {
        NodeCache cache = new NodeCache(client, nodePrefix);
        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("nodeChanged:");
                System.out.println("path:" + cache.getPath());
                System.out.println("data" + new String(cache.getCurrentData().getData(), "utf-8"));
            }
        });
        cache.start();
    }


    public static void main(String[] args) throws Exception {
        CuratorNodeCache curatorNodeCache = new CuratorNodeCache();
        curatorNodeCache.initialize();
        curatorNodeCache.createNodeCache();
        Thread.sleep(Integer.MAX_VALUE);
    }


}
