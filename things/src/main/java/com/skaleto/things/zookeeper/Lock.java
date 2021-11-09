package com.skaleto.things.zookeeper;

public interface Lock {

    void lock();

    void unlock() throws Exception;

}
