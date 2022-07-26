package com.adilsachwani.consumerservice.listener;

import com.adilsachwani.consumerservice.config.MessagingQueueConfig;
import com.adilsachwani.consumerservice.model.Cricketer;
import com.adilsachwani.consumerservice.repository.CricketerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CricketerListener {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private CricketerRepository cricketerRepository;

    @RabbitListener(queues = MessagingQueueConfig.CRICKETER_QUEUE)
    public void addCricketerListener(Cricketer cricketer){
        log.info("--- Starting addCricketerListener() ---");
        cricketerRepository.save(cricketer);
        log.info("--- Ending addCricketerListener() ---");
    }

    @RabbitListener(queues = MessagingQueueConfig.IMPORT_CRICKETERS_QUEUE)
    public void importCricketersListener(String country){
        log.info("--- Starting importCricketersListener() ---");
        JobParameters parameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(job, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("--- Ending importCricketersListener() ---");
    }

}
