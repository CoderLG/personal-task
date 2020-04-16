package com.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;

/**
 * author: LG
 * date: 2019-11-07 16:42
 * desc:
 */

public class FileUtils {
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }

    }

    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if(file.exists()) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if(!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，正在创建它！");
            if(!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }

    }

    public static boolean deleteDir(String destFileName) {
        File dir = new File(destFileName);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(dir.getPath()+"\\" + children[i]);
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }


    public static void detail(String destFileName) {
        File dir = new File(destFileName);
        Calendar cal = Calendar.getInstance();
        if (dir.isDirectory()) {
            System.out.println("Directory " + destFileName);
            System.out.println("------------------------------");
            System.out.println("Name\tTime created\tTime modified\tType");

            //String[] children = dir.list();
            File[] files = dir.listFiles();
            File file = null;
            FileTime time = null;
            for (int i =0; i<files.length;i++) {
                file = files[i];
                if (file.isDirectory()) {
                    System.out.print(file.getName() + "\t");
                    try {
                        time = Files.readAttributes(Paths.get(files[i].getAbsolutePath()), BasicFileAttributes.class).creationTime();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println(TimeUtils.format(time.toInstant()) + "\t" + TimeUtils.format(file.lastModified()) + "\t" + "d");

                } else {
                    System.out.print(file.getName() + "\t");
                    try {
                        time = Files.readAttributes(Paths.get(files[i].getAbsolutePath()), BasicFileAttributes.class).creationTime();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(TimeUtils.format(time.toInstant()) + "\t" + TimeUtils.format(file.lastModified()) + "\t" + "f");
                }
            }
        }

    }

    public static boolean fileExist(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {

        }

        return false;
    }

}
