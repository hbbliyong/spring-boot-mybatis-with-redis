# Java Web现代化开发：Spring Boot + Mybatis + Redis一级、二级缓存
## 环境
+ 开发环境：mac 10.11
+ ide：Intellij 2017.1
+ jdk：1.8
+ Spring-Boot：1.5.3.RELEASE
+ Redis：3.2.9
+ Mysql：5.7
## 基本信息
[github](https://github.com/Lovelcp/spring-boot-mybatis-with-redis/tree/master)  

[教程](https://juejin.im/post/592c08292f301e006c60cae2)


## Redis
pom.xml中已经引入了spring-boot-starter-data-redis库，所以Spring Boot会帮我们自动加载Redis的连接，
具体的配置类org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration。通过该配置类，
我们可以发现底层默认使用Jedis库，并且提供了开箱即用的redisTemplate和stringTemplate。

### 将Redis作为二级缓存
Mybatis的二级缓存可以自动地对数据库的查询做缓存，并且可以在更新数据时同时自动地更新缓存。

实现Mybatis的二级缓存很简单，只需要新建一个类实现org.apache.ibatis.cache.Cache接口即可。

该接口共有以下五个方法：

+ String getId()：mybatis缓存操作对象的标识符。一个mapper对应一个mybatis的缓存操作对象。
+ void putObject(Object key, Object value)：将查询结果塞入缓存。
+ Object getObject(Object key)：从缓存中获取被缓存的查询结果。
+ Object removeObject(Object key)：从缓存中删除对应的key、value。只有在回滚时触发。
一般我们也可以不用实现，具体使用方式请参考：org.apache.ibatis.cache.decorators.TransactionalCache。
+ void clear()：发生更新时，清除缓存。
+ int getSize()：可选实现。返回缓存的数量。
+ ReadWriteLock getReadWriteLock()：可选实现。用于实现原子性的缓存操作。

#### 在mapper的配置文件中开启二级缓存
````
   <!-- 开启基于redis的二级缓存 -->
    <cache type="cn.rivamed.springbootmybatiswithredis.util.RedisCache"/>
````
### mybatis输出sql日志
````
logging:
  level:
    cn.rivamed.springbootmybatiswithredis.dao.mapper: TRACE
````
## 开启一级缓存
### 一级缓存策略
> 这里我们使用的是 Cache Aside 策略，从三个维度：（摘自 耗子叔叔博客）
  失效：应用程序先从cache取数据，没有得到，则从数据库中取数据，成功后，放到缓存中。
  命中：应用程序从cache中取数据，取到后返回。
  更新：先把数据存到数据库中，成功后，再让缓存失效。
  大致流程如下：
  获取商品详情举例
  a. 从商品 Cache 中获取商品详情，如果存在，则返回获取 Cache 数据返回。
  b. 如果不存在，则从商品 DB 中获取。获取成功后，将数据存到 Cache 中。则下次获取商品详情，就可以从 Cache 就可以得到商品详情数据。
  c. 从商品 DB 中更新或者删除商品详情成功后，则从缓存中删除对应商品的详情缓存
## issue
### 1.返回List
````
    <!--配置一个resultMap 指定返回的类型 -->
    <resultMap id="departMent" type="Department">
        <id column="dp_id" property="dp_id" />
        <result column="dp_name" property="dp_name" />
        <result column="cost_center" property="cost_center" />
    </resultMap>

    <!-- 返回一个list的写法 ,这里是resultMap 不是resultType-->
    <select id="queryAllDepartment"  resultMap="departMent" >
        select * from t_department
    </select>
````      