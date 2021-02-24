package com.pruvn.rms.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpStatus;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.pruvn.rms.dao.ICMDAO;
import com.pruvn.rms.domain.Icm;
import com.pruvn.rms.dto.IBMCMDto;
import com.pruvn.rms.utils.SqlConstant;

import oracle.jdbc.OracleTypes;

/**
 * <p>Hibernate DAO layer for PvfcBsColl</p>
 * <p>Generated at Mon Jul 11 14:56:25 ICT 2011</p>
 *
 * @author Salto-db Generator v1.0.16 / EJB3 + Spring/Hibernate DAO
 * @see http://www.hibernate.org/328.html
 */
public class ICMHibernateDAO extends
		AbstractHibernateDAO<Icm, String> implements
		ICMDAO {

	@Override
	public boolean saveCall(Icm data) {
		// TODO Auto-generated method stub
		if(data != null && data.getLoanNo() != null) {
			Icm call = new Icm();
			saveOrUpdate(call);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteData() {
		return (boolean) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(final Session session) throws HibernateException, SQLException {
            	StringBuffer sqlQuery  = new StringBuffer(SqlConstant.DELETE_ICM_DOCUMENT);
        		SQLQuery query = session.createSQLQuery(
        				sqlQuery.toString());
        		query.executeUpdate();
        		return true;
            }
        });
	}
	List<IBMCMDto> lst = new ArrayList<IBMCMDto>();
	@SuppressWarnings("unchecked")
	@Override
	public List<IBMCMDto> zipAgreementNo() {
		// TODO Auto-generated method stub
		try {
			return (List<IBMCMDto>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
				public Object doInHibernate(final Session session) throws HibernateException, SQLException {
	            	session.doWork(new Work() {
	    				@Override
	    				public void execute(final Connection arg0) throws SQLException {
							try {
								String sql = "CALL SP_GET_CREDITSHIELD_DOCUMENT(?)";
								lst = new ArrayList<IBMCMDto>();
		    					CallableStatement cs;
		    					cs = arg0.prepareCall(sql);
								int index = 1;						
								cs.registerOutParameter(index, OracleTypes.CURSOR);
		    					cs.execute();
		    					ResultSet rs = (ResultSet) cs.getObject(index);
		    					String filePath = null;
		    					String agreementNo = null;
		    					while(rs.next()){
		    						 filePath = rs.getString("DOCREFID");
		    						 agreementNo = rs.getString("LOANAGRNO");
		    						 IBMCMDto dto = new IBMCMDto();
		    						 dto.setAgreementNo(agreementNo);
		    						 dto.setDocRefId(filePath);
		    						// cacheFile = new File(folderName + agreementNo + ".pdf");
		    						 lst.add(dto);
		    						 /*if(cacheFile.exists()) {
		    							simpleHeader = new SimpleDateFormat(
		    									SqlConstant.CONST_API_FORMAT_DATE_HEADER);
		    							cacheFile = new File(folderName + agreementNo+ "_" + simpleHeader.format(new Date()) + ".pdf");
		    						}
		    						filePath = host + filePath;
		    						fos = new FileOutputStream(cacheFile);
		    						fos.write(getImageFromUrl(filePath));
		    						fos.flush();*/
		    					}
		    					//zipFolder(sourceZip, targetZip);
							} catch (Exception e) {
								e.printStackTrace();
							}
	    				}
	    			});
	            	return lst;
	            }
	        });
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static byte[] getImageFromUrl(String url) {
		byte[] result = null;
		// Create an instance of HttpClient.
		HttpClient httpClient = new HttpClient();
		// Create a method instance.
		GetMethod method = new GetMethod(url);
		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		try {
			// Execute the method.
			int statusCode = httpClient.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.out.println("Method failed: " + method.getStatusLine());
			} else {
				result = method.getResponseBody();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	private static void zipFolder(String source, String target) {
		try {
	        FileOutputStream fos = new FileOutputStream(target);
	        ZipOutputStream zipOut = new ZipOutputStream(fos);
	        File fileToZip = new File(source);
	        zipFile(fileToZip, fileToZip.getName(), zipOut);
	        zipOut.close();
	        fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
}
