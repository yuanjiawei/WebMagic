package org.java.service;

import java.util.List;
import java.util.Map;

public interface WebMagicService {
    //查询数据库中的新闻信息
    List<Map> show();

    //把爬取到的数据存入数据库
    void add(Map map);
}
