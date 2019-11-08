package com.withyou.services.demo.mapper;

import com.withyou.services.demo.domain.entity.Demo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author jeremy.zhao
 */
@Mapper
public interface DemoMapper {

    @Insert("insert into demo (name) values (#{name})")
    void insert(Demo demo);

    @Select("select * from demo")
    List<Demo> queryDemos();
}
