package com.deepak.spring_batch_learn.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

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

}
