package com.TerBiliLive.Utiliy;
import java.io.*;

public class AgreementUtil {

	public static String readFile(){
		try {
			String encoding="UTF-8";
			String readText="";
			File file=new File("Ter/" + "用户协议"+ ".text");
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
				return "NO";
				//System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {

			// System.out.println("读取文件内容出错");
			e.printStackTrace();
			return "NO";
		}

	}

	// 输出协议
	public static void putAgreement(String Data) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("Ter/" + "用户协议"+ ".text");
			fw = new FileWriter(f, true);

		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(Data);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 输出协议
	public static void putAgreement(String Day, String Date, String Log ,String ProjectName,String path ) {
		FileUtil.createDir("Ter/log/"+path);
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("Ter/log/" +path+"/"+ Day+ProjectName+ ".terlog");
			fw = new FileWriter(f, true);

		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(Date + ":" + Log);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}

