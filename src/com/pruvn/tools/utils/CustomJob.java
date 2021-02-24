package com.pruvn.tools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.pruvn.tools.printserver.UsermasterDAO;
public class CustomJob extends  QuartzJobBean {
	private static final Logger logger = Logger.getLogger(CustomJob.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private UsermasterDAO usermasterDAO;
	@Override
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		try {
			Date lastDateRun  = ctx.getPreviousFireTime();
			if (lastDateRun != null) {
				logger.info("Last date run: " + sdf.format(lastDateRun));
				int refireCount = ctx.getRefireCount();
				if (refireCount > 0) {
					logger.info("Total attempts: " + refireCount);
				}
			}
			else {
				logger.info("Job is run for the first time");
			}
			logger.info("Delegating work to worker");
			usermasterDAO.excuteJobLockUser();
			String nextDateRun = sdf.format(ctx.getNextFireTime());
			logger.info("Next date run: " + nextDateRun);

		} 
		catch (Exception e) {
			logger.info("Unexpected exception" , e);
			throw new JobExecutionException("Unexpected exception", e, true);
		}
	}
	
	public UsermasterDAO getUsermasterDAO() {
		return usermasterDAO;
	}
	@Autowired
	public void setUsermasterDAO(UsermasterDAO usermasterDAO) {
		this.usermasterDAO = usermasterDAO;
	}

	/**
	 * The worker
	 * <p> 
	 * This is required so that Spring's {@link JobDetailsBean} will
	 * automatically inject the values
	 */ 
	
}
