package com.pruvn.rms.validator;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pruvn.rms.model.UploadItem;


public class FileUploadValidator implements Validator{
	 
	@Override
	public boolean supports(Class clazz) {
		//just validate the FileUpload instances
		return FileUpload.class.isAssignableFrom(clazz);
	}
 
	@Override
	public void validate(Object target, Errors errors) {
 
		UploadItem file = (UploadItem)target;
		if(file.getFileData().getSize()==0){
			errors.rejectValue("fileData", "required.fileUpload");
		}
	}
}
