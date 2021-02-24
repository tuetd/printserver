package com.pruvn.rms.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 */
public class UploadItem
{
	@NotNull
	private CommonsMultipartFile fileData;
	
	private List<String> result;

	private String uploadType;
	/**
	 * @return the result
	 */
	public List<String> getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(List<String> result) {
		this.result = result;
	}

	public CommonsMultipartFile getFileData()
	{
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData)
	{
		this.fileData = fileData;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	
}