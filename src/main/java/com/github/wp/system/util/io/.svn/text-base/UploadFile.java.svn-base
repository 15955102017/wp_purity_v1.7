package com.github.wp.system.util.io;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 上传文件接口
 * @author wangping
 * @version 1.0
 * @since 2015年8月14日, 上午10:33:26
 */
public interface UploadFile extends IO {
	
	/**
	 * 执行上传文件操作
	 * @param request
	 * @throws Exception
	 * @author wangping
	 */
	public void doUpload(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取保存文件的详细信息
	 * @return
	 * @author wangping
	 */
	public List<SuperFileItem> getSuperFileItem();
	
	/**
	 * 获取文件的最大上传尺寸
	 * @return
	 * @author wangping
	 */
	public Long getFileMaxSize();
	
	/**
	 * 设置上传文件的最大尺寸，默认值为10M
	 * @param size
	 * @author wangping
	 */
	public void setFileMaxSize(Long size);
	
}
