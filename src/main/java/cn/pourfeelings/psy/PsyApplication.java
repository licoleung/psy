package cn.pourfeelings.psy;

import cn.pourfeelings.psy.chat.WebServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "cn.pourfeelings.psy.mapper")
@SpringBootApplication
public class PsyApplication {

    public static void main(String[] args) throws Exception{

        SpringApplication.run(PsyApplication.class, args);
        new WebServer().run();
    }

}
