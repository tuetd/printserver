package com.pruvn.rms.utils.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Calendar;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbSession;

import org.apache.log4j.Logger;

import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.DateUtils;

public class ShareFileTransfer extends BaseFileTransfer implements IFileTransfer {
	private static final Logger logger = Logger.getLogger(ShareFileTransfer.class);
	
	/**
	 * Upload a file to a FTP server. A FTP URL is generated with the
	 * following syntax:
	 * ftp://user:password@host:port/filePath;type=i.
	 * 
	 * @param ftpServer , FTP server address (optional port ':portNumber').
	 * @param user , Optional user name to login.
	 * @param password , Optional password for user.
	 * @param fileName , Destination file name on FTP server (with optional
	 *            preceding relative path, e.g. "myDir/myFile.txt").
	 * @param source , Source file to upload.
	 * @throws MalformedURLException, IOException on error.
	 * @throws ParseException 
	 */
	public void upload( String fileName, String source, String partnerbank ) throws MalformedURLException,
	IOException, ParseException
	{
		if (host != null && fileName != null && source != null)
		{
			if (isCreatedSubdirectory) {
				File file = new File(host + '\\' + baseDirectory + '\\' + DateUtils.dateToString(Calendar.getInstance().getTime(), DateUtils.yyyymmdd));
				
				if (!file.exists()) {
					file.mkdirs();
				}
			} else {
				File file = new File(host + '\\' + baseDirectory + '\\' + partnerbank);
				
				if (!file.exists()) {
					file.mkdirs();
				}
			}
			
//			File file = new File(host + '\\' + baseDirectory + '\\' + partnerbank+ '\\' + fileName);
//			
//			if (!file.exists()) {
//				file.mkdirs();
//			}
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			
			if (isCreatedSubdirectory) {
				fos = new FileOutputStream(host + '\\' + baseDirectory + '\\' + partnerbank + '\\' + DateUtils.dateToString(Calendar.getInstance().getTime(), DateUtils.yyyymmdd) + '\\' + fileName);
			} else {
				fos = new FileOutputStream(host + '\\' + baseDirectory + '\\' + partnerbank + '\\' + fileName);
			}
			
			fis = new FileInputStream(source);
			
			// Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = fis.read(buf)) > 0) {
            	fos.write(buf, 0, len);
            }
            fis.close();
            fos.close();
		}
		else
		{
			logger.info( "Input not available." );
		}
	}

	/**
	 * Download a file from a FTP server. A FTP URL is generated with the
	 * following syntax:
	 * ftp://user:password@host:port/filePath;type=i.
	 * 
	 * @param ftpServer , FTP server address (optional port ':portNumber').
	 * @param user , Optional user name to login.
	 * @param password , Optional password for user.
	 * @param fileName , Name of file to download (with optional preceeding
	 *            relative path, e.g. one/two/three.txt).
	 * @param destination , Destination file to save.
	 * @throws MalformedURLException, IOException on error.
	 */
	public void download( String fileName, String destination, String partnerbank ) throws MalformedURLException,
	IOException
	{
		if (host != null && fileName != null && destination != null)
		{
			FileInputStream fis = null;
			FileOutputStream fos = new FileOutputStream(destination);

			fis = new FileInputStream(host + '\\' + baseDirectory + '\\' + fileName);
			
			// Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = fis.read(buf)) > 0) {
            	fos.write(buf, 0, len);
            }
            fis.close();
            fos.close();
		}
		else
		{
			logger.info( "Input not available" );
		}
	}
}
