package com.github.wp.system.util.io;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传处理类
 * @author wangping
 * @version 1.0
 * @since 2015年8月14日, 下午4:02:51
 */
public class UploadHandler implements UploadFile {

	private String filePath;//用户指定的文件存储路径
	
	private String savedFilePath;//文件的最终存储路径
	
	private List<SuperFileItem> superFileItem;//上传成功后文件详细信息
	
	private Long fileMaxSize = 10*1024*1024l;//初始化上传文件的文件大小为10M

	public UploadHandler(){
		
	}
	
	/** 
	 * Constructor for UploadHandler
	 * @param filePath 文件的保存路径
	 */
	public UploadHandler(String filePath){
		this.filePath = filePath;
	}
	
    /**
     * 获取文件最大文件大小
     * @return
     * @author wangping
     */
    public Long getFileMaxSize() {
		return fileMaxSize;
	}

    
    /**
     * 通过request获取上传文件信息
     * @param request
     * @return
     * @throws Exception
     * @author wangping
     */
	private List<SuperFileItem> getFile(HttpServletRequest request) throws Exception{
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<SuperFileItem> items = new ArrayList<SuperFileItem>();
		try {
			List<FileItem> diskItem = upload.parseRequest(request);
			for(FileItem item : diskItem) {
				SuperFileItem superFileItem = new SuperFileItem(item);
				superFileItem.setDiskFileItem(item);
				items.add(superFileItem);
			}
			return items;
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
    
    /**
     * 获取保存的文件名称
     * @param request
     * @return
     * @author wangping
     */
	private String getSavedFileName(FileItem item) {
		String last = item.getName().substring(item.getName().lastIndexOf(".")+1);
        String uuid = UUID.randomUUID().toString();
		return uuid + "." + last;
	}

    /**
     * 生成文件目录
     * @param request
     * @return
     * @author wangping
     */
	private File generatorFileDirectory() {
		File headPath = new File(filePath + "\\" + new Date(new java.util.Date().getTime()).toString());//获取文件夹路径
        if(!headPath.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
        	headPath.mkdirs();
        }
		return headPath;
	}

	/**
     * 获取保存的文件详细信息
     * @return
     * @author wangping
     */
	@Override
	public List<SuperFileItem> getSuperFileItem() {
		return superFileItem;
	}
	
	/**
	 * 设置上传文件的最大文件大小
	 * @param fileMaxSize
	 * @author wangping
	 */
	public void setFileMaxSize(Long fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}

	/**
     * 设置文件保存路径
     * @param filePath
     * @return
     * @author wangping
     */
	@Override
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
     * 上传文件
     * @param request
     * @return
     * @throws Exception
     * @author wangping
     */
	@Override
	public void doUpload(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		superFileItem = new ArrayList<SuperFileItem>();
		try {
			List<SuperFileItem> items = getFile(request);
			Iterator<SuperFileItem> itr = items.iterator();
			while (itr.hasNext()) {
				SuperFileItem item = (SuperFileItem) itr.next();
				if (item.isFormField()) {
					System.out.println("表单参数名:" + item.getFieldName()
							+ "，表单参数值:" + item.getString("UTF-8"));
				}
				else {
					if (item.getName() != null && !item.getName().equals("")) {
						System.out.println("上传文件的大小:" + item.getSize());
						System.out.println("上传文件的类型:" + item.getContentType());
						// item.getName()返回上传文件在客户端的完整路径名称
						System.out.println("上传文件的名称:" + item.getName());
                        //判断文件尺寸是否在指定范围内
						if(fileMaxSize < item.getSize()) {
							request.setAttribute("upload.message", "文件大小超过" + fileMaxSize);
							return;
						}
						//生成保存文件目录
						File headPath = generatorFileDirectory();
						//获得保存文件名
				        String savedFileName = getSavedFileName(item);
				        //设置保存文件的完全路径名
				        savedFilePath = headPath.getPath() + "\\" + savedFileName;
				        //将文件的保存名称与保存路径添加到文件信息对象中
				        item.setSavedFileName(savedFileName);
				        item.setSavedFilePath(savedFilePath);
						// 上传文件的保存路径
						File file = new File(headPath, savedFileName);
						item.write(file);
						superFileItem.add(item);
						request.setAttribute("upload.message", "上传文件成功！");
					}
					else {
						request.setAttribute("upload.message", "没有选择上传文件！");
					}
				}
			}
		}
		catch (FileUploadException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message", "上传文件失败！");
		}
	}

}

