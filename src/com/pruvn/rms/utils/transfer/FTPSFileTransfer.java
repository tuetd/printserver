package com.pruvn.rms.utils.transfer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.ParseException;
import java.util.Calendar;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.util.TrustManagerUtils;
import org.apache.log4j.Logger;

import com.pruvn.rms.utils.DateUtils;

public class FTPSFileTransfer extends BaseFileTransfer implements IFileTransfer {
	private static final Logger logger = Logger.getLogger(FTPSFileTransfer.class);

	/* (non-Javadoc)
	 * @see com.pvfc.jobs.transfer.IFileTransfer#upload(java.lang.String, java.lang.String)
	 */

	public void upload(String fileName, String source) throws MalformedURLException,
	IOException, ParseException,  KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException
	{
		if (host != null && fileName != null && source != null)
		{
			
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(null, null);
			KeyManager km = kmf.getKeyManagers()[0];
			
			FTPSClient client = new FTPSClient(protocol,isImplicit);
			client.setTrustManager(TrustManagerUtils.getValidateServerCertificateTrustManager());
			client.setKeyManager(km);
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
			
			client.execPROT("P");
			client.setFileType(FTPSClient.BINARY_FILE_TYPE);
			client.enterLocalPassiveMode();
			client.execPBSZ(0L);
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
	public void download( String fileName, String destination ) throws MalformedURLException,
	IOException
	{
		if (host != null && fileName != null && destination != null)
		{
			FTPSClient client = new FTPSClient(true);
			client.setTrustManager(TrustManagerUtils.getAcceptAllTrustManager());
			if (keepAliveTimeout != null)
			{
				client.setControlKeepAliveTimeout(keepAliveTimeout);
			}
			
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
