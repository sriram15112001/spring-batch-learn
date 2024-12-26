package com.deepak.spring_batch_learn.config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

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

}
