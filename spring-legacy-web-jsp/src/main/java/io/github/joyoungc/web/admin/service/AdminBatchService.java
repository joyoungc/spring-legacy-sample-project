/**
 * Admin Batch Service
 * 
 * @author	Joyoungc
 * @date	2017.10.20
 */
package io.github.joyoungc.web.admin.service;

import org.joda.time.DateTime;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.github.joyoungc.web.common.util.BatchUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminBatchService {
	
	@Autowired
	private BatchUtils batchUtils;
	
	public JobExecution executeBatch() throws Exception {
		
		JobParametersBuilder builder = new JobParametersBuilder();
		// target 데이트 포맷 양식 : yyyyMMdd_HH
		DateTime today = new DateTime();
		String fileName = "file:c:/test/batch/" + today.toString("yyyyMMdd") + "/admin_info_"
				+ today.toString("yyyyMMddHHmmss") + ".txt";
		
		log.info("fileName : {}", fileName);

		builder.addString("outputFile", fileName);
		builder.addLong("currentTime", System.currentTimeMillis());
		
		JobExecution result = batchUtils.runBatchJob("adminJob", builder.toJobParameters());
		
		return result;
	}
	
	/**
	 * 관리자 정보 파일생성 배치 Scheduler
	 * @author 정조영 (2017-10-25)
	 */ 
	@Scheduled(cron="0 0/10 * * * ?") // 매시 10분마다 실행 
	public void executeAdminBatchScheduler() {
		JobParametersBuilder builder = new JobParametersBuilder();
		// target 데이트 포맷 양식 : yyyyMMdd_HH
		DateTime today = new DateTime();
		String fileName = "file:c:/test/batch/" + today.toString("yyyyMMdd") + "/admin_info_"
				+ today.toString("yyyyMMddHHmmss") + ".txt";
		log.info("fileName : {}", fileName);
		
		builder.addString("outputFile", fileName);
		builder.addLong("currentTime", System.currentTimeMillis());
		
		batchUtils.runBatchJob("adminJob", builder.toJobParameters());
	}

}
