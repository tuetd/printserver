package com.pruvn.tools.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FileTransferClient;

import cz.dhl.ftp.Ftp;
import cz.dhl.ftp.FtpFile;
import cz.dhl.ftp.FtpOutputStream;

public class FTPProcessor {
	public static FileTransferClient connectToFTPServer(Connection conn, Integer ftpConfigID) throws SQLException, FTPException, IOException {
		FileTransferClient result = null;
		
		PreparedStatement pstmt = conn.prepareStatement("SELECT HOST, USERNAME, PASSWORD " +
														" FROM FTPCONFIG WHERE ID = ?");
		pstmt.setObject(1, ftpConfigID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			result = new FileTransferClient();
			result.setRemoteHost(rs.getString("HOST"));
			result.setUserName(rs.getString("USERNAME"));
			result.setPassword(rs.getString("PASSWORD"));
			result.connect();
		}
		
		rs.close();
		pstmt.close();
		return result;
	}
	
	public static FileTransferClient connectToFTPServer(String host, Integer port, String username, String password) throws FTPException, IOException {		
		FileTransferClient result = new FileTransferClient();		
		result.setRemoteHost(host);
		if (null != port) {
			result.setRemotePort(port.intValue());
		}
		if (null != username) {
			result.setUserName(username);
		}
		if (null != password) {
			result.setPassword(password);
		}
		result.setTimeout(20000000);
		result.connect();		
		return result;
	}
	
	public static void disconnect(FileTransferClient ftp) {
		try {
			ftp.disconnect();
		} catch (FTPException e) {		
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Ftp connect(String host, String username, String password) throws IOException {
		Ftp ftp = new Ftp();
		ftp.connect(host, Ftp.PORT);
		ftp.login(username, password);
		return ftp;
	}
	
	public static void ftpdisconnect(Ftp ftp) {
		if (null != ftp) {
			ftp.disconnect();
		}
	}
	
	public static void upploadFile(String localfile, String remotefile, Ftp ftp) throws IOException {
		FtpFile ftpFile = new FtpFile(remotefile, ftp);
		FtpOutputStream os = new FtpOutputStream(ftpFile);
		FileInputStream fis = new FileInputStream(localfile);
		byte[] buffer = new byte[4096];
	    int bytesRead;
	    while ((bytesRead = fis.read(buffer)) != -1) {
	    	os.write(buffer, 0, bytesRead);
	    }
	    fis.close();
	    os.close();
	}
	
	public static void upploadFile(File localfile, String remotefile, Ftp ftp) throws IOException {
		FtpFile ftpFile = new FtpFile(remotefile, ftp);
		FtpOutputStream os = new FtpOutputStream(ftpFile);
		FileInputStream fis = new FileInputStream(localfile);
		byte[] buffer = new byte[4096];
	    int bytesRead;
	    while ((bytesRead = fis.read(buffer)) != -1) {
	    	os.write(buffer, 0, bytesRead);
	    }
	    fis.close();
	    os.close();
	}
	
}
