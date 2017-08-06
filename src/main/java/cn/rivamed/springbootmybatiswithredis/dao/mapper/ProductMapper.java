package cn.rivamed.springbootmybatiswithredis.dao.mapper;

import cn.rivamed.springbootmybatiswithredis.dao.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {
    Product select(@Param("id") long id);
    void update(Product product);
}
