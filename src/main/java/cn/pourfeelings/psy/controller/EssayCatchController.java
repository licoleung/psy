package cn.pourfeelings.psy.controller;

import cn.pourfeelings.psy.pojo.Essay;
import cn.pourfeelings.psy.service.EssayService;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Date;

/**
 * @author LicoLeung
 * @create 2019-04-11 19:23
 */
@Controller
public class EssayCatchController {
    @Autowired
    EssayService essayService;

    @RequestMapping("/pa")
    public String addEssay(Essay essay){
        run();
        return "redirect:/essays";
    }

    public void run() {
        //创建浏览器对象

        HttpClient httpClient = new DefaultHttpClient();
        //设置响应时间，设置代理服务器
/*        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000).//连接超时
                setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000).//传输超时
                setParameter(ConnRouteParams.DEFAULT_PROXY, new HttpHost("121.239.8.151",3128));*/ //代理服务器（防止别人把自己的IP加入黑名单） 百度代理服务器

        //创建get请求
        HttpGet hget = new HttpGet("https://www.xinli001.com/");
        try {
            //发送请求
            HttpResponse response = httpClient.execute(hget);
            //获取内容（实体）
            String s = EntityUtils.toString(response.getEntity(), "utf-8");
            /*System.out.println(s);*/

            //Jsoup解析
            Document document = Jsoup.parse(s);
            Elements elements = document.select("div[class='content']");

            //最大不可以超过32
            for (int i=0;i<32;i++) {
                Essay essay = new Essay();
                Element element = elements.get(i);

                Elements title = element.select("a[class='common-a']");
                //获取图片
                //System.out.println(title.select("img").attr("src"));
                essay.setPicture(title.select("img").attr("src"));

//                System.out.println(title);

                //标题
                //System.out.println(title.text());
                essay.setTitle(title.text());
                //标题下的说明
                //System.out.println(element.select("p[class='small-title']").text());
                essay.setIntroduction(element.select("p[class='small-title']").text());
                //超链接
                //System.out.println(title.attr("href"));

                //获取内容
                //System.out.println(context(title.attr("href")));
                essay.setContent(context(title.attr("href")));
                essay.setReadnum(0);
                essay.setLikenum(0);
                essay.setTime(new Date());
                essayService.addEssay(essay);
            }



        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String context(String href) {
        //创建浏览器对象
        HttpClient httpClient = new DefaultHttpClient();
        //创建get请求
/*        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000).
                setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000).
                setParameter(ConnRouteParams.DEFAULT_PROXY, new HttpHost("58.210.94.242",50514));*/
        HttpGet hget = new HttpGet(href);
        try {
            //发送请求
            HttpResponse response = httpClient.execute(hget);
            //获取内容
            String s = EntityUtils.toString(response.getEntity(), "utf-8");
//            System.out.println(s);

            Document document = Jsoup.parse(s);
            Elements p = document.select("div[class='yxl-editor-article']");
            return p.html();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
