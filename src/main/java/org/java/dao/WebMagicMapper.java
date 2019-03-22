package org.java.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WebMagicMapper {
    //查询数据库中的新闻信息
    List<Map> show();

    //把爬取到的数据存入数据库
    void add(@Param("map") Map map);

}
