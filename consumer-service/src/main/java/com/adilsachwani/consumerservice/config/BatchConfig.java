package com.adilsachwani.consumerservice.config;

import com.adilsachwani.consumerservice.batch.CricketerProcessor;
import com.adilsachwani.consumerservice.model.Cricketer;
import com.adilsachwani.consumerservice.model.CricketerCsv;
import com.adilsachwani.consumerservice.repository.CricketerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CricketerRepository cricketerRepository;

    @Bean
    public FlatFileItemReader<CricketerCsv> reader(){
        return new FlatFileItemReaderBuilder<CricketerCsv>()
                .name("csvReader")
                .resource(new ClassPathResource("cricketers.csv"))
                .linesToSkip(1)
                .delimited()
                .names("Player", "Country")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                    setTargetType(CricketerCsv.class);
                }})
                .build();
    }

    @Bean
    public CricketerProcessor processor(){
        return new CricketerProcessor();
    }

    @Bean
    public MongoItemWriter<Cricketer> writer(MongoTemplate mongoTemplate){
        return new MongoItemWriterBuilder<Cricketer>()
                .template(mongoTemplate)
                .collection("cricketer")
                .build();
    }

    @Bean
    public Step cricketerStep(MongoItemWriter<Cricketer> writer){
        return stepBuilderFactory
                .get("importCricketersStep")
                .<CricketerCsv, Cricketer>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job job(Step cricketerStep){
        return jobBuilderFactory
                .get("importCricketers")
                .flow(cricketerStep)
                .end()
                .build();
    }

}
