package com.pruvn.rms.utils.transfer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.log4j.Logger;

import com.pruvn.rms.utils.DateUtils;


public class FTPFileTransfer extends BaseFileTransfer implements IFileTransfer {
	private static final Logger logger = Logger.getLogger(FTPFileTransfer.class);

	/* (non-Javadoc)
	 * @see com.pvfc.jobs.transfer.IFileTransfer#upload(java.lang.String, java.lang.String)
	 */
	public void upload( String fileName, String source) throws MalformedURLException,
	IOException, ParseException
	{
		if (host != null && fileName != null && source != null)
		{
			FTPClient client = new FTPClient();
			if (keepAliveTimeout != null)
			{
				client.setControlKeepAliveTimeout(keepAliveTimeout);
			}
			
			FileInputStream fis = null;

			if (StringUtils.isNotEmpty(port)) {
				client.connect(host, new Integer(port));
			} else {
				client.connect(host);
			}

			client.login(user, password);

			if (StringUtils.isNotEmpty(baseDirectory)) {
				client.changeWorkingDirectory(baseDirectory);
			}
			
			
			if (isCreatedSubdirectory) {
				client.makeDirectory(DateUtils.dateToString(Calendar.getInstance().getTime(), DateUtils.yyyymmdd));
				client.changeWorkingDirectory(DateUtils.dateToString(Calendar.getInstance().getTime(), DateUtils.yyyymmdd));
			}
//			client.execPROT("P");
			client.setFileType(FTPSClient.BINARY_FILE_TYPE);
//			client.execPBSZ(0L);
			fis = new FileInputStream(source);
			client.storeFile(fileName, fis);
			client.logout();
			fis.close();
		}
		else
		{
			logger.info( "Input not available." );
		}
	}

	/* (non-Javadoc)
	 * @see com.pvfc.jobs.transfer.IFileTransfer#download(java.lang.String, java.lang.String)
	 */
	public void download( String fileName, String destination) throws MalformedURLException,
	IOException
	{
		if (host != null && fileName != null && destination != null)
		{
			FTPClient client = new FTPClient();
			FileOutputStream fos = null;

			if (StringUtils.isNotEmpty(port)) {
				client.connect(host, new Integer(port));
			} else {
				client.connect(host);
			}
			client.login(user, password);

			if (StringUtils.isNotEmpty(baseDirectory)) {
				client.changeWorkingDirectory(baseDirectory);
			}

			fos = new FileOutputStream(destination);

			client.retrieveFile(fileName, fos);
			fos.close();
			client.disconnect();
		}
		else
		{
			logger.info( "Input not available" );
		}
	}
}
