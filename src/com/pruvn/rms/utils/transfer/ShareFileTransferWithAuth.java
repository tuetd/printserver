package com.pruvn.rms.utils.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.PrivilegedAction;
import java.text.ParseException;
import java.util.Calendar;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbSession;

import org.apache.log4j.Logger;

import com.pruvn.rms.utils.Constant;
import com.pruvn.rms.utils.DateUtils;

public class ShareFileTransferWithAuth {
	private static Logger logger = Logger.getLogger(ShareFileTransferWithAuth.class);
	
	@SuppressWarnings("unchecked")
	public static Boolean transfer(Boolean isCreatedSubdirectory, String host,
			String baseDirectory, String source, String fileName,
			String partnerBank, String username, String password) throws UnknownHostException, SmbException {
		java.net.InetAddress i = java.net.InetAddress.getLocalHost();		
		UniAddress dc = UniAddress.getByName(i.getHostAddress());        
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(Constant.DOMAIN_NAME, username, password);        
		SmbSession.logon( dc, auth );	
		  logger.info("Transfer Successful");
		  return true;
	}
}

@SuppressWarnings("rawtypes")
class WriteFileAction implements PrivilegedAction {
	private static Logger logger = Logger.getLogger(ShareFileTransferWithAuth.class);
	private Boolean isCreatedSubdirectory;
	private String host;
	private String baseDirectory;
	private String source;
	private String fileName;
	private String partnerBank;

	public WriteFileAction(Boolean isCreatedSubdirectory, String host,
			String baseDirectory, String source, String fileName,
			String partnerBank) {
		super();
		this.isCreatedSubdirectory = isCreatedSubdirectory;
		this.host = host;
		this.baseDirectory = baseDirectory;
		this.source = source;
		this.fileName = fileName;
		this.partnerBank = partnerBank;
	}

	public Object run() {
		try {
			File file = new File(host + '\\' + baseDirectory + '\\' + this.partnerBank);

			if (!file.exists()) {
				file.mkdirs();
			}

			FileInputStream fis = null;
			FileOutputStream fos = null;

			if (isCreatedSubdirectory) {
				fos = new FileOutputStream(host + '\\' + baseDirectory + '\\' + this.partnerBank + '\\'
						+ DateUtils.dateToString(Calendar.getInstance().getTime(), DateUtils.yyyymmdd) + '\\' + fileName);
			} else {
				fos = new FileOutputStream(host + '\\' + baseDirectory + '\\' + this.partnerBank + '\\' + fileName);
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
		} catch (IOException ioException) {
			logger.error(ioException.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

		return null;

	}
}
