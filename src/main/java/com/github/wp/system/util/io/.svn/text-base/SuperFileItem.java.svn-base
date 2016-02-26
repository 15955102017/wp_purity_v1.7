package com.github.wp.system.util.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;

/**
 * 文件详细信息类
 * @author wangping
 * @version 1.0
 * @since 2015年8月14日, 下午4:42:47
 */
public class SuperFileItem implements FileItem {

	/** {field's description} */
	private static final long serialVersionUID = 1L;
	public FileItem  diskFileItem;
	
	private String savedFilePath;
	private String savedFileName;
	
    public SuperFileItem(FileItem fileItem){
    	super();
    	this.diskFileItem = fileItem;
    }
	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#delete()
	 */
	@Override
	public void delete() {
		diskFileItem.delete();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#get()
	 */
	@Override
	public byte[] get() {
		return diskFileItem.get();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#getContentType()
	 */
	@Override
	public String getContentType() {
		return diskFileItem.getContentType();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#getFieldName()
	 */
	@Override
	public String getFieldName() {
		return diskFileItem.getFieldName();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return diskFileItem.getInputStream();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#getName()
	 */
	@Override
	public String getName() {
		return diskFileItem.getName();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#getOutputStream()
	 */
	@Override
	public OutputStream getOutputStream() throws IOException {
		return diskFileItem.getOutputStream();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#getSize()
	 */
	@Override
	public long getSize() {
		return diskFileItem.getSize();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#getString()
	 */
	@Override
	public String getString() {
		return diskFileItem.getString();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#getString(java.lang.String)
	 */
	@Override
	public String getString(String arg0) throws UnsupportedEncodingException {
		return diskFileItem.getString(arg0);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#isFormField()
	 */
	@Override
	public boolean isFormField() {
		return diskFileItem.isFormField();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#isInMemory()
	 */
	@Override
	public boolean isInMemory() {
		return diskFileItem.isInMemory();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#setFieldName(java.lang.String)
	 */
	@Override
	public void setFieldName(String arg0) {
		diskFileItem.setFieldName(arg0);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#setFormField(boolean)
	 */
	@Override
	public void setFormField(boolean arg0) {
		diskFileItem.setFormField(arg0);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.fileupload.FileItem#write(java.io.File)
	 */
	@Override
	public void write(File arg0) throws Exception {
		diskFileItem.write(arg0);
	}

	public FileItem getDiskFileItem() {
		return diskFileItem;
	}

	public void setDiskFileItem(FileItem diskFileItem) {
		this.diskFileItem = diskFileItem;
	}

	public String getSavedFilePath() {
		return savedFilePath;
	}

	public void setSavedFilePath(String savedFilePath) {
		this.savedFilePath = savedFilePath;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

	@Override
	public FileItemHeaders getHeaders() {
		return diskFileItem.getHeaders();
	}

	@Override
	public void setHeaders(FileItemHeaders arg0) {
		diskFileItem.setHeaders(arg0);
		
	}

}

