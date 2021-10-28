package com.zzzfyrw.system.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class ModelProcessorAdapter {


    private List<IModelProcessor> list;


    public ModelProcessorAdapter(List<IModelProcessor> list) {
        this.list = list;
    }



}
