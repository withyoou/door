package com.withyou.services.demo;


import com.withyou.services.demo.domain.entity.Demo;
import com.withyou.services.demo.mapper.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

    private Logger log = LoggerFactory.getLogger(DemoTest.class);

    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void test() {
        log.info("---- start test ----");
        Demo demo = new Demo();
        demo.setName("jeremy");
        demoMapper.insert(demo);
        List<Demo> demos = demoMapper.queryDemos();
        log.info("get demos from db: {}", demos.toString());
    }
}
