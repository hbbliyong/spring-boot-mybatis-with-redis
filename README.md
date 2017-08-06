# Java Web现代化开发：Spring Boot + Mybatis + Redis二级缓存
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