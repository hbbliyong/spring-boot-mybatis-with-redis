package cn.rivamed.springbootmybatiswithredis.dao.mapper;

import cn.rivamed.springbootmybatiswithredis.dao.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    void  save(Product product);
    Product select(@Param("id") long id);
    List<Product> findByName(@Param("name") String name);
    void update(Product product);
}
