package com.github.wp.test;

import java.io.File;

import com.github.wp.system.util.MD5Check;

public class testFileMD5 {

	public static void main(String[] args) {
		File file = new File("E:\\王平\\资料\\我的\\观测产品数据表.xls");
		File file1 = new File("C:\\观测产品数据表.xls");
		MD5Check md5 = new MD5Check();
		try {
		    System.out.println(md5.getFileMD5String(file));
		    System.out.println(md5.getFileMD5String(file1));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
