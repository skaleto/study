package com.skaleto.things.redis;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yaoyibin
 */
public class TestRedisson {

    @Transactional
    public void testRedLock() {
        Config config1 = new Config();
        config1.useSingleServer().setAddress("com.skaleto.things.redis://127.0.0.1:6379").setDatabase(0).setPassword("hziflytek@2020");
        RedissonClient redisson1 = Redisson.create(config1);
        RLock rLock1 = redisson1.getLock("test");

        Config config2 = new Config();
        config2.useSingleServer().setAddress("com.skaleto.things.redis://127.0.0.1:6380").setDatabase(0).setPassword("hziflytek@2020");
        RedissonClient redisson2 = Redisson.create(config2);
        RLock rLock2 = redisson2.getLock("test");

        Config config3 = new Config();
        config3.useSingleServer().setAddress("com.skaleto.things.redis://127.0.0.1:6381").setDatabase(0).setPassword("hziflytek@2020");
        RedissonClient redisson3 = Redisson.create(config3);
        RLock rLock3 = redisson3.getLock("test");

        RedissonRedLock redissonRedLock = new RedissonRedLock(rLock1, rLock2, rLock3);
        boolean success = redissonRedLock.tryLock();

        redissonRedLock.unlock();


    }
}
