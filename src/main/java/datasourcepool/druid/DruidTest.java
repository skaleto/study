package datasourcepool.druid;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author : ybyao
 * @Create : 2019-09-06 17:52
 */
public class DruidTest {

    public void init() {
        DruidDataSource dataSource = new DruidDataSource();

        // setDefault
        dataSource.setInitialSize(3);
//        dataSource.acquireIncrement = 3;
        dataSource.setTimeBetweenConnectErrorMillis(1000);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setLogAbandoned(false);
        dataSource.setMaxActive(15);
        dataSource.setMinIdle(3);
        dataSource.setTestOnReturn(false);
        dataSource.setTestOnBorrow(false);
    }
}
