package com.skaleto.things.datasourcepool.tradition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author : ybyao
 * @Create : 2019-09-05 10:18
 */
public class JDBC {

    private Connection conn;

    public void init() throws ClassNotFoundException, SQLException {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.连接数据库URL
        String url = "jdbc:mysql://localhost:3306/test?" + "user=root&password=root";
        //3.获取数据库连接
        conn = DriverManager.getConnection(url);

    }
}
