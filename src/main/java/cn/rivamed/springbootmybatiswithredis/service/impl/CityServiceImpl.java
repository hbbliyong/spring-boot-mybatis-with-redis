package cn.rivamed.springbootmybatiswithredis.service.impl;

import cn.rivamed.springbootmybatiswithredis.dao.domain.City;
import cn.rivamed.springbootmybatiswithredis.dao.mapper.CityMapper;
import cn.rivamed.springbootmybatiswithredis.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CityServiceImpl implements CityService{

    private static final Logger LOGGER= LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据城市 ID,查询城市信息
     *  获取城市逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     * @param id
     * @return
     */
    @Override
    public City findCityById(Long id) {
        //从缓存中获取城市信息
        String key="city_"+id;
        ValueOperations<String,City> operations=redisTemplate.opsForValue();

        //缓存存在
        boolean hasKey=redisTemplate.hasKey(key);
        if(hasKey){
            City city=operations.get(key);

            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }

        //从DB中获取城市信息
        City city=cityMapper.findById(id);
        //插入缓存数据
        operations.set(key,city,10, TimeUnit.SECONDS);
        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());
        return city;
    }

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    @Override
    public Long saveCity(City city) {
        return cityMapper.saveCity(city);
    }

    /**
     * 更新城市信息
     * 更新城市逻辑：
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     * @param city
     * @return
     */
    @Override
    public Long updateCity(City city) {
        Long ret=cityMapper.updateCity(city);

        //如果缓存存在，删除缓存
        String key="city_"+city.getId();
        boolean hasKey=redisTemplate.hasKey(key);
        if(hasKey){
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString());
        }
        return ret;
    }

    /**
     * 根据城市 ID,删除城市信息
     *
     * @param id
     * @return
     */
    @Override
    public Long deleteCity(Long id) {
        Long ret = cityMapper.deleteCity(id);

        // 缓存存在，删除缓存
        String key = "city_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);

            LOGGER.info("CityServiceImpl.deleteCity() : 从缓存中删除城市 ID >> " + id);
        }
        return ret;
    }
}
