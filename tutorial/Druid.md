## 1.使用说明
## 2.开启Druid的监控功能
 开启监控只需要一个监控服务器以及一个过滤器,监控服务器设定了访问监控口太的连接地址,设定了访问数据库的黑名单,白名单,以及配置
 访问的用户名以及密码
 ```java
@Configuration
public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean statViewService(){
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new StatViewServlet(),"/druid/**");
        //IP白名单
        servletRegistrationBean.addInitParameter("allow","192.168.2.183,192.168.2.149,127.0.0.1");
        //IP黑名单
        servletRegistrationBean.addInitParameter("deny","192.168.1.100");
        servletRegistrationBean.addInitParameter("loginUsername","druid");
        servletRegistrationBean.addInitParameter("loginPassword","12345678");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的格式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,/druid/**");
        return filterRegistrationBean;
    }
}

```

运行应用后,我们可以通过http://localhost/druid/index.html打开控制台.
在监控后台,可以实时查看数据库连接池的情况,每一个被执行SQL语句的次数以及花费的时间,并发数等,以及一个URI的请求次数,时间和并发情况.这些都是我们分析数据库的情况和性能提供了可靠的,详细的数据