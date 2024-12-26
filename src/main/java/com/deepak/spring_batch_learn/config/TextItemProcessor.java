package com.deepak.spring_batch_learn.config;

import org.springframework.batch.item.ItemProcessor;

public class TextItemProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {
        return item.replaceAll("\\d", "*");
    }
}
