package cn.rivamed.springbootmybatiswithredis;

import cn.rivamed.springbootmybatiswithredis.util.CglibDemo.EasyClass;
import cn.rivamed.springbootmybatiswithredis.util.CglibDemo.EasyIntercepter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Enhancer;


@SpringBootApplication
public class SpringBootMybatisWithRedisApplication {

    public static void main(String[] args) {
       // cglibTest();

        SpringApplication.run(SpringBootMybatisWithRedisApplication.class, args);
    }

     static void cglibTest() {
        EasyClass easyClass = (EasyClass) Enhancer.create(
                EasyClass.class,
                null,
                new EasyIntercepter()
        );
        easyClass.easyMethod();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EasyClass.class);
        enhancer.setCallback(
                new EasyIntercepter()
        );
        ((EasyClass) enhancer.create()).easyMethod();
    }
}
