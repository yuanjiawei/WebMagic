package org.java.service.impl;

import org.java.dao.WebMagicMapper;
import org.java.service.WebMagicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WebMagicServiceImpl implements WebMagicService {
    @Autowired
    private WebMagicMapper mapper;
    @Override
    public List<Map> show() {
        return mapper.show();
    }

    @Override
    public void add(Map map) {
         mapper.add(map);
    }

}

