package com.github.wp.system.util.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载处理程序
 * 
 * @author wangping
 * @version 1.0
 * @since 2015年8月14日, 上午10:59:57
 */
public class DownloadHandler implements DownloadFile {

	private String filePath;

	@Override
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void doDownload(HttpServletResponse response, String filePath, String fileName, boolean isOnline)
			throws Exception {
		this.filePath = filePath;
		File file = new File(this.filePath);
		if (!file.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(
				file));
		byte[] buf = new byte[1024];
		int len = 0;

		response.reset(); // 非常重要
		if (isOnline) { // 在线打开方式
			URL u = new URL("file:///" + filePath);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition",
					"inline; filename="+fileName);
			// 文件名应该编码成UTF-8
		}
		else { // 纯下载方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition",
					"attachment; filename="+fileName);
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
	}

}

