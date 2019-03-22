package org.java.controller;


import org.java.service.WebMagicService;
import org.java.service.impl.WebMagicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebMagicontroller implements PageProcessor {
    @Autowired
    private  WebMagicService service;

   private static Map<String, String> titles = new HashMap<>(100);//用于保存爬取的内容

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .addHeader("Accept", " text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
            .addHeader(" Accept-Encoding", "gzip, deflate")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive")
            .addHeader("Cookie", "BAIDUID=12C4D93A70C569FB32FEB167200343E6:FG=1; LOCALGX=%u6B66%u6C49%7C%34%38%31%32%7C%u6B66%u6C49%7C%34%38%31%32; Hm_lvt_e9e114d958ea263de46e080563e254c4=1553052323; Hm_lpvt_e9e114d958ea263de46e080563e254c4=1553052596")
            .addHeader("Host", "news.baidu.com")
            .addHeader("Upgrade-Insecure-Requests", "1")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(http://news\\.baidu\\.com/\\w+/\\w+)").all());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }

        titles.put(page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[1]/strong/a/text()").toString(), page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[1]/strong/a/@href").toString());
        titles.put(page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[2]/strong/a/text()").toString(), page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[2]/strong/a/@href").toString());
        titles.put(page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[3]/strong/a[1]/text()").toString(), page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[3]/strong/a[1]/@href").toString());
        titles.put(page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[3]/strong/a[2]/text()").toString(), page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[3]/strong/a[2]/@href").toString());
        titles.put(page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[4]/strong/a/text()").toString(), page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[4]/strong/a/@href").toString());
        titles.put(page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[5]/strong/a/text()").toString(), page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[5]/strong/a/@href").toString());
        titles.put(page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[6]/strong/a[1]/text()").toString(), page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[6]/strong/a[1]/@href").toString());
        titles.put(page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[6]/strong/a[2]/text()").toString(), page.getHtml().xpath("//*[@id=\"pane-news\"]/div/ul/li[6]/strong/a[2]/@href").toString());

        List<Selectable> nodes = page.getHtml().xpath("//*[@id=\"pane-news\"]/ul").nodes();//保存有规律的内容
        for (Selectable div : nodes) {
            titles.put(div.xpath("//li[1]/a/text()").toString(), div.xpath("//li[1]/a/@href").toString());
            titles.put(div.xpath("//li[2]/a/text()").toString(), div.xpath("//li[2]/a/@href").toString());
            titles.put(div.xpath("//li[3]/a/text()").toString(), div.xpath("//li[3]/a/@href").toString());
            titles.put(div.xpath("//li[4]/a/text()").toString(), div.xpath("//li[4]/a/@href").toString());
            titles.put(div.xpath("//li[5]/a/text()").toString(), div.xpath("//li[5]/a/@href").toString());
            titles.put(div.xpath("//li[6]/a/text()").toString(), div.xpath("//li[6]/a/@href").toString());
        }
    }

    public Map ret(){
        /*for (Map.Entry<String,String> m:titles.entrySet()) {
            System.out.println( m.getKey()+ m.getValue());
        }*/
            return titles;
    }

    @Override
    public Site getSite() {
        return site;
    }



}

