package com.yuding.www;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileApplication {
	
	private static final String FOLDER = "C:\\jeecg\\csms\\csms-crew-server\\src\\main\\java\\com\\mims\\csms\\crew\\domain";
//	private static final String TABLE_MATCHER ="@Table.*?name\\s{0,}=\\s{0,}\"(.*?)\"\\)";//匹配表名
	private static final String TABLE_MATCHER ="@Table.*?name\\s{0,}=\\s{0,}\"(.*?)\",";//匹配表名
	/**
	 * 匹配类型1
	 */
	private static final String CONTENT_MATCHER ="\\s+\\*\\s(.*?)\\s+\\*\\/\\s+@Column.*?\"(.*?)\"";//匹配字段
	
//	private static final String CONTENT_MATCHER ="\\s+@Column.*?\"(.*?)\"[0-9a-zA-Z\\s,=)]+\\s.*?//\\s{0,}(.*?)\\n";//匹配字段 //匹配类型2
	
	private static final Integer TYPE = 1; //输出类型
	
	public static void main(String[] args) {
		File root = new File(FOLDER);
		List<File>flist = new ArrayList<>();
		listPath(flist,root);
		for(File file:flist) {
			int c=0;
			StringBuffer sb = new StringBuffer();
			try{
				FileReader reader=new FileReader(file);
				c=reader.read();
				while(c!=-1){
					sb.append((char)c);
					c=reader.read();
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			String filetext = sb.toString();
			
			Pattern p = Pattern.compile(TABLE_MATCHER);
			Matcher matcher = p.matcher(filetext);
			if(!matcher.find()) {
				continue;
			}
			//---------------------------------------------------
//			if(matcher.group(1).contains("schema")) {
//				continue;
//			}
			//---------------------------------------------------
			p = Pattern.compile(CONTENT_MATCHER);
			matcher = p.matcher(filetext);
			if(!matcher.find()) {
				continue;
			}
			p = Pattern.compile(TABLE_MATCHER);
			matcher = p.matcher(filetext);
			while(matcher.find()) {
				System.out.println("☆☆☆☆☆"+matcher.group(1));
			}
			p = Pattern.compile(CONTENT_MATCHER);
			matcher = p.matcher(filetext);
			while(matcher.find()) {
				if(TYPE==1) {
					System.out.println(matcher.group(2)+"	"+matcher.group(1));
				}else if(TYPE==2) {
					System.out.println(matcher.group(1)+"	"+matcher.group(2));
				}
			}
			System.out.println();
		}
	}

	/**
	 * @param args
	 * 
	 * 实现功能:
	 *     检索指定路径下的所有的下属文件夹和文件
	 */
 
	public static void listPath(List<File>flist,File url) {
		String files[] = url.list();
		for (String file : files) {
			File f = new File(url, file);
			// 判断得到的文件对象是否是文件路径
			if (f.isDirectory()) {
//				System.out.println("这是文件路径:" + f);
				listPath(flist,f);
			}
			// 判断得到的是否是文件
			if (f.isFile()&&f.getPath().contains("domain")) {
//				System.out.println("这是文件:" + f);
				flist.add(f);
			}
		}
	}

}
