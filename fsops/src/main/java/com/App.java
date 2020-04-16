package com;

import com.utils.FileUtils;
import com.utils.MysqlUtils;
import com.utils.TimeUtils;

import java.sql.Connection;
import java.util.Date;


public class App {
    public static void main(String[] args) {



        switch (args[0]){
            case "mkdir":
                if(args.length > 2){
                    for(int i=1;i<args.length;i++)
                        FileUtils.createDir(args[i]);
                }else {
                    FileUtils.createDir(args[1]);
                }
                break;
            case "mfile":
                FileUtils.createFile(args[1]);
                break;
            case "rm":
                FileUtils.deleteDir(args[1]);
                break;
            case "ls":
                FileUtils.detail(args[1]);
                break;
            case "-logdb":
                intserDB(args[1],args[2],args[3],args[4],args[5]);
                break;
            case "help":
                System.out.println("显示该文件操作程序的简要帮助手册");
                break;
            case "self":
                System.out.println("显示本软件的软件名称、版本、开发者和编写日期");
                break;
        }


    }

    public static void intserDB(String url,String user,String pass,String act,String file){

        String str="INSERT INTO `filesyslog`(hostname,operation,date) VALUE(?,?,?)";
        String hostname = System.getenv("COMPUTERNAME");
        String action = act ;
        String format = TimeUtils.format(new Date());
        String dbUrl = url.substring(1,url.length()-1);
        String dbUser = user.substring(1,user.length()-1);
        String dbPass = pass.substring(1,pass.length()-1);
        if(hostname == null){
            hostname="获取主机名错误";
        }
        Connection conn = null;
       try {
            conn = MysqlUtils.getconn(dbUrl,dbUser,dbPass);
            if(conn == null){
                System.out.println("连接错误，请检查连接信息！");
                return;
            }
       }catch (Exception e){
           System.out.println("连接错误，请检查连接信息！");
       }
       try {
           int index = MysqlUtils.executeUpdate(conn, str, new Object[]{hostname, action, format});
       }catch (Exception e ){
           System.out.println("执行sql错误，请检查输入信息！");
       }

    }


}