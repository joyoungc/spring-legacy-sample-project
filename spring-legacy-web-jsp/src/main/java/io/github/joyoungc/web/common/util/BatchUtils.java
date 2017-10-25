/**
 * Batch Utilities
 * 
 * @author	Joyoungc
 * @date	2017.10.25
 */
package io.github.joyoungc.web.common.util;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BatchUtils {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	public JobExecution runBatchJob(String jobId, JobParameters jobParameters) {
		try {
			return jobLauncher.run(context.getBean(jobId, Job.class), jobParameters);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
