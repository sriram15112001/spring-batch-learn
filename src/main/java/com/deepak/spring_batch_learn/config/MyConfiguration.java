package com.deepak.spring_batch_learn.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MyConfiguration {
    @Bean
    public FlatFileItemReader<String> reader() {
        return new FlatFileItemReaderBuilder<String>()
                .resource(new ClassPathResource("username.csv"))
                .name("csv-reader")
                .lineMapper((line, lineNumber) -> {
                    return line;
                })
                .build();
    }

    @Bean
    public FlatFileItemWriter<String> writer() {
        return new FlatFileItemWriterBuilder<String>()
                .resource(new FileSystemResource("src/main/resources/masked-data.csv"))
                .name("csv-writer")
                .lineAggregator(line -> line)
                .build();
    }

    @Bean
    public TextItemProcessor textItemProcessor() {
        return new TextItemProcessor();
    }

    @Bean
    public Step maskingStep(JobRepository jobRepository,
                            PlatformTransactionManager transactionManager,
                            FlatFileItemReader<String> reader,
                            TextItemProcessor textItemProcessor,
                            FlatFileItemWriter<String> writer) {
        return new StepBuilder("mask-step", jobRepository)
                .<String, String> chunk(3, transactionManager)
                .reader(reader)
                .processor(textItemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job maskingJob(JobRepository jobRepository, Step maskingStep){
        return new JobBuilder("masking-job", jobRepository)
                .start(maskingStep)
                .build();
    }

}
