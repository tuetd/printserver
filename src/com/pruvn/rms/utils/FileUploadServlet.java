package com.pruvn.rms.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import twitter4j.org.json.JSONException;
import twitter4j.org.json.JSONObject;

public class FileUploadServlet extends HttpServlet{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] fileArrayExt = {"doc", "docx", "xls", "xlsx", "pdf"};
	protected String urlBackup;
	//	
	public  void init(ServletConfig servletConfig) throws ServletException{
		super.init(servletConfig);
		this.urlBackup = servletConfig.getInitParameter("urlBackup");
	  }
//	
	public String getUrlBackup() {
		return urlBackup;
	}

	public void setUrlBackup(String urlBackup) {
		this.urlBackup = urlBackup;
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
		// Check that we have a file upload request
		String error= "";
		String msg= "";
		String url= "";
		String fileName = "";
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			error = "Error:";
			msg = "No multipart content.";
		}
		else{
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			try {
				List items = upload.parseRequest(request);
				if(items.isEmpty()){
					error = "No files Found";
					msg = "No files Found";
				} else{
					FileItem fileItem = (FileItem) items.get(0);
					String contentType = fileItem.getContentType();
					System.out.println(contentType);
					String realPath = getServletContext().getRealPath("uploadfiles");
					String relativeDir = "";
					String type = fileItem.getFieldName();
					if(type.indexOf("file_Upload_") >=0){
						type = type.replaceAll("file_Upload_", "");
					}
					System.out.println(type);
					fileName = getFileName(fileItem.getName());
					System.out.println(fileName);
                    int dot = getFileName(fileItem.getName()).lastIndexOf('.');
                    String fileExt = getFileName(fileItem.getName()).substring(dot + 1);
                    System.out.println(fileExt);
                    fileName = UUID.randomUUID().toString() + getFileName(fileItem.getName()).substring(dot);
					if("file".equals(type)){
						if (!isValidFileExt(fileExt)) {
							error = "Not support file type [" + fileExt + "].";
							msg = "Not support file type [" + fileExt + "].";
						} else {
							if(contentType.indexOf("application/msword") != -1 
									|| (contentType.indexOf("application/pdf") != -1)
									|| (contentType.indexOf("application/vnd.ms-excel") != -1)
									|| (contentType.indexOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") != -1)
								|| contentType.indexOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document") != -1){
								relativeDir = File.separator+"file";
							}
							else{
								error = "Not support file type [" + contentType + "].";
								msg = "Not support file type [" + contentType + "].";
							}
						}
					}
					else if("attachment".equals(type)){
						if (!isValidFileExt(fileExt)) {
							error = "Not support file type [" + fileExt + "].";
							msg = "Not support file type [" + fileExt + "].";
						} else {
							if(contentType.indexOf("application/pdf") != -1 
									|| contentType.indexOf("application/msword") != -1
									|| contentType.indexOf("application/vnd.ms-excel") != -1
									|| contentType.indexOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") != -1
									|| contentType.indexOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document") != -1
									|| contentType.indexOf("application/zip") != -1){
								relativeDir = File.separator+"attachments";
							}
							else {
								error = "Not support file type [" + contentType + "].";
								msg = "Not support file type [" + contentType + "].";
							}
						}
					}
					else{
						if(contentType.indexOf("application/x-shockwave-flash") != -1){
								relativeDir =File.separator + "flash";
						}
						else if(contentType.indexOf("image/") != -1){
							relativeDir = File.separator+"image";
						}
						else if(contentType.indexOf("application/pdf") != -1){
							relativeDir = File.separator+"pdf";
						}
						else {
							error = "Not support file type [" + contentType + "].";
							msg = "Not support file type [" + contentType + "].";
						}
					}

					if("".equals(error) ){
						//folder backup
						String filePathBackup = urlBackup +relativeDir+ File.separator + fileName;
						checkExist(urlBackup);
						FileOutputStream osBackup = new FileOutputStream(filePathBackup);
						osBackup.write(fileItem.get());
						osBackup.flush();
						osBackup.close();
						// folder temp;
						String folder = realPath + File.separator  + relativeDir;
						checkExist(folder);
						String filePath = folder + File.separator + fileName;
						FileOutputStream os = new FileOutputStream(filePath);
						os.write(fileItem.get());
						os.flush();
						os.close();
//						url = getServletContext().getContextPath() + "/uploadfiles" +relativeDir + "/" + fileName;
						//TODO
						//url = getServletContext().getContextPath() + "/viewImage.html?namefile=" + fileName;
					}

                  
                }
			} catch (Exception e) {
				e.printStackTrace();
				String message = e.getMessage();
				error = "ERROR:" + message;
				msg = message;
			}
		}
		JSONObject obj= new JSONObject();
		try {
			 obj.put("error", error);
			obj.put("msg", JSONObject.stringToValue(msg));
			obj.put("url", JSONObject.stringToValue(url));
			obj.put("fileName", fileName);
		} catch (JSONException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		response.getWriter().print(obj.toString());
	}

	private void checkExist(String folder) throws IOException {
		File file = new File(folder);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	private String getFileName(String name) {
		if(name != null){
			int index = name.lastIndexOf("\\");
			if(index >= 0 && index < name.length()){
				name = name.substring(index + 1);
			}
			return name;
		}
		return null;
	}

	private BufferedImage scaleToSize(BufferedImage uploadImage)
    {
        AffineTransform atx = new AffineTransform();
        atx.scale(200d / uploadImage.getWidth(), 200d / uploadImage.getHeight());
        AffineTransformOp afop = new AffineTransformOp(atx, AffineTransformOp.TYPE_BILINEAR);
        uploadImage = afop.filter(uploadImage, null);
        return uploadImage;
    }
	
	private boolean isValidFileExt(String ext) {
		boolean matched = false;
		for (String item : fileArrayExt) {
			if (item.equalsIgnoreCase(ext)) {
				matched = true;
			}
		}
		
		if (!matched) {
			return false;
		}
		
		return true;
	}


}