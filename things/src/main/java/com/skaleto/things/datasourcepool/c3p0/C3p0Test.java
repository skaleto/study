package com.skaleto.things.datasourcepool.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author : ybyao
 * @Create : 2019-09-05 11:06
 */
public class C3p0Test {

    public void test(){

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setInitialPoolSize(3);
        comboPooledDataSource.setAutoCommitOnClose(true);
        comboPooledDataSource.setMaxPoolSize(15);
        comboPooledDataSource.setMinPoolSize(3);
        comboPooledDataSource.setAcquireRetryAttempts(0);
        comboPooledDataSource.setTestConnectionOnCheckin(false);
        comboPooledDataSource.setTestConnectionOnCheckout(false);

    }
}
