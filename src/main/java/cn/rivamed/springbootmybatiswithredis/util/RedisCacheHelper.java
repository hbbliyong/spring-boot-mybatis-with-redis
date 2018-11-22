package cn.rivamed.springbootmybatiswithredis.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisCacheHelper {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public <T> void add(String key,Long time,T t){
        Gson gson=new Gson();
        redisTemplate.opsForValue().set(key,gson.toJson(t),time, TimeUnit.MINUTES);
    }

    public <T> void add(String key,Long time,List<T> t){
        Gson gson=new Gson();
        redisTemplate.opsForValue().set(key,gson.toJson(t),time, TimeUnit.MINUTES);
    }

    public <T> T get(String key,Class<T> clazz){
        Gson gson=new Gson();
       // Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String json=redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(json)){
            return gson.fromJson(json,clazz);
        }
        return null;
    }
    public <T> List<T> getList(String key){
        Gson gson=new Gson();
        String json=redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(json)){
            return gson.fromJson(json,new TypeToken<List<T>>(){}.getType());
        }
        return null;
    }

    public void delete(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}
