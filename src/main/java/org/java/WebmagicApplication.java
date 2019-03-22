package org.java;

import org.java.controller.WebMagicontroller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.codecraft.webmagic.Spider;

@SpringBootApplication
public class WebmagicApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebmagicApplication.class, args);
        Spider.create(new WebMagicontroller()).addUrl("http://news.baidu.com/?qq-pf-to=pcqq.c2c").thread(5).run();
    }

}
