package com.TerBiliLive.Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;


public class FileUtil {

    /**
     * 读取文本类文件
     *
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        try {
            String encoding = "UTF-8";
            StringBuilder readText = new StringBuilder();
            File file = new File("Ter/data/" + filePath + ".terda");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    readText.append(lineTxt);
//                    return lineTxt;
                }

                read.close();
                return readText.toString();
            } else {
                InOutPutUtil.outPut("找不到指定的文件");
                return "";
            }
        } catch (Exception e) {
            InOutPutUtil.outPut("读取文件内容出错");
            e.printStackTrace();
            return "";
        }

    }

    public static void writeFile(final String strFilename, final String strBuffer) {
        try {
            // 创建文件对象
            File fileText = new File(strFilename);
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter("Ter/data/" + fileText + ".terda");
            // 写文件
            fileWriter.write(strBuffer);
            // 关闭
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            InOutPutUtil.outPut("创建目录" + destDirName + "警告，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            InOutPutUtil.outPut("创建目录" + destDirName + "成功！");
            return true;
        } else {
            InOutPutUtil.outPut("创建目录" + destDirName + "失败！");
            return false;
        }
    }


    public static boolean auditFile(String filePath){
        File file = new File(filePath);
        return file.exists()||file.isFile();
    }
    public static File copyInternalFiles(String filePath,String copyFilePath){
        File file = new File(filePath);
        URL plugin = FileUtil.class.getResource(copyFilePath);
        try (InputStream in = plugin.openStream()) {
            FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(in));
            InOutPutUtil.outPut("拷贝"+copyFilePath+"内部文件到"+filePath+"成功！");
        } catch (Exception e) {
            InOutPutUtil.outPut(e.getMessage());;
        }
        return file;
    }


}

