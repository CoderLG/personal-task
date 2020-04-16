package com.utils;

import java.sql.*;

/**
 * author: LG
 * date: 2019-11-14 08:56
 * desc:
 */
public class MysqlUtils {

//   private String driver ="com.mysql.jdbc.Driver";
//    private String url="jdbc:mysql://192.168.4.141:3306/terminaldb";
//    private String name="root";
//    private String pwd="123456";
//    Connection conn=null;


    public static Connection  getconn(String url,String name,String password){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url,name,password);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static PreparedStatement prepareStatement(Connection conn, String sql, Object []ob){
        PreparedStatement ps=null;
        try {
            int index=1;
            ps = conn.prepareStatement(sql);
            if(ps!=null&&ob!=null){
                for (int i = 0; i < ob.length; i++) {
                    ps.setObject(index, ob[i]);
                    index++;
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return ps;
    }


    /***
     *
     *  增删改方法
     *  接受 参数为 SQL语句 和 对象数组
     * @return 返回受影响行数
     */
    public static int executeUpdate(Connection conn,String sql ,Object []ob){

        PreparedStatement ps=null;
        try {
            ps=prepareStatement(conn,sql,ob);
            int i=ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //	e.printStackTrace();
            return 0;
        }finally{
            closeAll(conn, ps, null);
        }

    }



    public static void closeAll(Connection conn ,PreparedStatement ps,ResultSet rs){
        if(rs!=null)
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
