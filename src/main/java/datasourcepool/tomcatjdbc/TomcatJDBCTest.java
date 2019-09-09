package datasourcepool.tomcatjdbc;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : ybyao
 * @Create : 2019-09-05 11:44
 */
public class TomcatJDBCTest {

    private DataSource dataSource;

    public static void main(String[] args) throws SQLException {
        TomcatJDBCTest test=new TomcatJDBCTest();
        test.init();
        test.getConnection();
    }

    public void init() {
        PoolProperties poolProperties = new PoolProperties();
        // setDefault
        poolProperties.setInitialSize(3);
        poolProperties.setTimeBetweenEvictionRunsMillis(1000);
        poolProperties.setDefaultAutoCommit(true);
        poolProperties.setLogAbandoned(false);
        poolProperties.setMaxActive(15);
        poolProperties.setMinIdle(3);
        poolProperties.setTestOnBorrow(false);
        poolProperties.setTestOnReturn(false);
        poolProperties.setUrl("jdbc:mysql://10.4.111.250:3306/stc_manage?characterEncoding=UTF8&useSSL=false");
        poolProperties.setUsername("root");
        poolProperties.setPassword("hziflytek@M2017");
        poolProperties.setDriverClassName("com.mysql.jdbc.Driver");
        poolProperties.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        dataSource = new DataSource(poolProperties);

    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
