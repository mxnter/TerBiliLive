package com.TerBiliLive.Utiliy;

import java.io.*;


public class FileUtil {

    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static String readFile(String filePath){
        try {
            String encoding="UTF-8";
            String readText="";
            File file=new File("Ter/data/"+filePath+".terda");
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    //System.out.println(lineTxt);
                    readText=readText+lineTxt;
//                    return lineTxt;
                }

                read.close();
                return readText;
            }else{
                return "";
                //System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {

            // System.out.println("读取文件内容出错");
            e.printStackTrace();
            return "";
        }

    }

    public static void writeFile(final String strFilename, final String strBuffer)
    {
        try
        {
            // 创建文件对象
            File fileText = new File(strFilename);
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter("Ter/data/"+fileText+".terda");

            // 写文件
            fileWriter.write(strBuffer);
            // 关闭
            fileWriter.close();
        }
        catch (IOException e)
        {
            //
            e.printStackTrace();
        }
    }
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            //      System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            //   System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            //      System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }

}

