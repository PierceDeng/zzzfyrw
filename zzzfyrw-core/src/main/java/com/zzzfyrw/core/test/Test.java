package com.zzzfyrw.core.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzzfyrw.common.thread.ZThreadPoolManager;
import com.zzzfyrw.core.repository.entity.SysDictEntity;
import com.zzzfyrw.core.repository.mapper.SysDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/config")
@RefreshScope
public class Test {

    private String test;

    @Autowired
    private SysDictMapper sysDictMapper;

    @GetMapping("/getKey")
    public String getKey(){
        return test;
    }

    @GetMapping("/dict")
    public List<SysDictEntity> dict(){
        SysDictEntity sysDictEntity = new SysDictEntity();
//        sysDictMapper.insert(sysDictEntity);
//        ZThreadPoolManager.getInstance().asyncExecute(() ->{
//            SysDictEntity s2 = new SysDictEntity();
//            sysDictMapper.insert(s2);
//        });
        List<SysDictEntity> sysDictEntities = sysDictMapper.selectList(new LambdaQueryWrapper<SysDictEntity>());
        for (SysDictEntity dictEntity : sysDictEntities) {
            dictEntity.setDate(LocalDate.now());
            dictEntity.setDateTime(LocalDateTime.now());
        }
        return sysDictEntities;
    }


}
