package com.batchexample.bootbatch.cconfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotficationImpl implements JobExecutionListener {

    private Logger logger = LoggerFactory.getLogger(JobCompletionNotficationImpl.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("job started");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("job completed");
        }
    }






}
