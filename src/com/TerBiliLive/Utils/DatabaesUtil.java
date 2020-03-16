package com.TerBiliLive.Utils;


import com.TerBiliLive.Info.ConfInfo;
import org.omg.CORBA.INTERNAL;

import java.sql.*;

public class DatabaesUtil {
    private String JDBC_URL = ConfInfo.JDBC_URL;
    private String JDBC_USERNAME = ConfInfo.JDBC_USERNAME;
    private String JDBC_PASSWORD = ConfInfo.JDBC_PASSWORD;
    private String JDBC_DRIVER = ConfInfo.JDBC_DRIVER;
    private Connection connection = null;
    private Statement statement = null;

    public DatabaesUtil() {
        try {
            try {
                Class.forName(JDBC_DRIVER);// 加载驱动,连接sqlite的jdbc
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(JDBC_URL);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
        } catch (SQLException e) {
            InOutPutUtil.outPut("数据库连接失败");
            e.printStackTrace();
        }
    }

    public ResultSet select (String sql){
        if(statement==null){
            InOutPutUtil.outPut("数据库未连接");
            ConfInfo.databaesUtil = new DatabaesUtil();
            return null;
        }else{
            ResultSet rs = null;
            try {
                rs = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;
        }
    }

    public int executeUpdate (String sql){
        if(statement==null){
            InOutPutUtil.outPut("数据库未连接");
            ConfInfo.databaesUtil = new DatabaesUtil();
            return 0;
        }else{
            int rs = 0;
            try {
               rs = statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;
        }
    }

    public void close() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
