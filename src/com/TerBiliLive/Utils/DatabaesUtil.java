package com.TerBiliLive.Utils;


import com.TerBiliLive.Info.ConfInfo;
import org.apache.commons.lang.StringEscapeUtils;
import org.omg.CORBA.INTERNAL;

import java.sql.*;
import java.util.List;

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
            InOutPutUtil.outPut("数据库连接失败",false);
            e.printStackTrace();
        }
    }

    public ResultSet select (String sql){
        if(statement==null){
            InOutPutUtil.outPut("数据库未连接",false);
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
            InOutPutUtil.outPut("数据库未连接",false);
            ConfInfo.databaesUtil = new DatabaesUtil();
            return 0;
        }else{
            int rs = 0;
            try {
                InOutPutUtil.outPut("数据库语句："+sql,false);
               rs = statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;
        }
    }

    public int executeUpdate (String sql, List<String> value){
        if(statement==null){
            InOutPutUtil.outPut("数据库未连接",false);
            ConfInfo.databaesUtil = new DatabaesUtil();
            return 0;
        }else{
            int rs = 0;
            try {
                InOutPutUtil.outPut("数据库语句："+sql,false);
                PreparedStatement pstmt = connection.prepareStatement(sql);
                for(int i = 0; i<value.size();i++){
                    pstmt.setString(i+1,value.get(i));
                }
//                pstmt.addBatch();//最主要是就是这2行
//                pstmt.executeBatch();//执行
                rs = pstmt.executeUpdate();

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

    public static String escapeSql(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char src = str.charAt(i);
            switch (src) {
                case '\'':
                    sb.append("''");// hibernate转义多个单引号必须用两个单引号
                    break;
                case '\"':
                case '\\':
                    sb.append('\\');
                default:
                    sb.append(src);
                    break;
            }
        }
        return sb.toString();
    }

}
