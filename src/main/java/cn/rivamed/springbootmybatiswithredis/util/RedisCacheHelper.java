package cn.rivamed.springbootmybatiswithredis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCacheHelper {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

}
