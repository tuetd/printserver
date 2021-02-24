package com.pruvn.rms.utils.transfer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.ParseException;

public interface IFileTransfer {

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
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 * @throws UnrecoverableKeyException 
	 */
	public abstract void upload(String fileName, String source)
			throws MalformedURLException, IOException, ParseException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException;

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
	public abstract void download(String fileName, String destination)
			throws MalformedURLException, IOException;

}