package com.pruvn.rms.validator;

import org.springframework.validation.Errors;

import com.pruvn.rms.model.UploadItem;


public class FileExcelUploadValidator extends FileUploadValidator{
	 
	
	@Override
	public void validate(Object target, Errors errors) {
		super.validate(target, errors);
		UploadItem file = (UploadItem)target;
		if(!( ("application/vnd.ms-excel".equalsIgnoreCase(file.getFileData().getContentType())
			 || "application/xls".equalsIgnoreCase(file.getFileData().getContentType()) 
			 || "application/octet-stream".equalsIgnoreCase(file.getFileData().getContentType())
		      ))
		   ){
		  errors.rejectValue("fileData", "invalidExcelFile.fileUpload");
		}
	}
}
