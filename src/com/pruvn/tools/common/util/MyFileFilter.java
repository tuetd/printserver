package com.pruvn.tools.common.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter{
	private String fileExt = null;
	
	public MyFileFilter(String fileExt) {
		this.fileExt = fileExt;
	}
	
	 public boolean accept(File file) {
         String filename = file.getName();
         return filename.endsWith(fileExt);
     }
     public String getDescription() {
         return "*" + fileExt;
     }

}
