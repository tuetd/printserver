package com.pruvn.rms.utils.transfer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.ParseException;

import org.apache.log4j.Logger;

public class BaseFileTransfer implements IFileTransfer {
	private static final Logger logger = Logger.getLogger(BaseFileTransfer.class);
	protected String host;
	protected String port;
	protected String user;
	protected String password;
	protected String baseDirectory;
	protected Long keepAliveTimeout;
	protected Boolean isCreatedSubdirectory;
	protected String protocol;
	protected boolean isImplicit;
	
	
	public boolean getIsImplicit() {
		return isImplicit;
	}

	public void setIsImplicit(boolean isImplicit) {
		this.isImplicit = isImplicit;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the isCreatedSubdirectory
	 */
	public Boolean getIsCreatedSubdirectory() {
		return isCreatedSubdirectory;
	}

	/**
	 * @param isCreatedSubdirectory the isCreatedSubdirectory to set
	 */
	public void setIsCreatedSubdirectory(Boolean isCreatedSubdirectory) {
		this.isCreatedSubdirectory = isCreatedSubdirectory;
	}

	/**
	 * @return the keepAliveTimeout
	 */
	public Long getKeepAliveTimeout() {
		return keepAliveTimeout;
	}

	/**
	 * @param keepAliveTimeout the keepAliveTimeout to set
	 */
	public void setKeepAliveTimeout(Long keepAliveTimeout) {
		this.keepAliveTimeout = keepAliveTimeout;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the baseDirectory
	 */
	public String getBaseDirectory() {
		return baseDirectory;
	}

	/**
	 * @param baseDirectory the baseDirectory to set
	 */
	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}

	/* (non-Javadoc)
	 * @see com.pvfc.jobs.transfer.IFileTransfer#upload(java.lang.String, java.lang.String)
	 */
	public void upload( String fileName, String source) throws MalformedURLException, IOException, ParseException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {};

	/* (non-Javadoc)
	 * @see com.pvfc.jobs.transfer.IFileTransfer#download(java.lang.String, java.lang.String)
	 */
	public void download( String fileName, String destination) throws MalformedURLException, IOException {};
	
}
