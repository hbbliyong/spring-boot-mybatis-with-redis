package cn.rivamed.springbootmybatiswithredis.controller;

import cn.rivamed.springbootmybatiswithredis.dao.domain.Product;
import cn.rivamed.springbootmybatiswithredis.dao.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @PostMapping("/save")
    public void save(@RequestBody Product product){
        productMapper.save(product);
    }
    @GetMapping("/{id}")
    public Product getProductInfo(
            @PathVariable("id")
                Long productId){
        return productMapper.select(productId);
    }

    @GetMapping("/list/{name}")
    public List<Product> getProductByName(@PathVariable("name") String name){
                List<Product> products=productMapper.findByName(name);
                return products;
    }
    @PutMapping("/{id}")
    public Product ProductupdateProduct(
            @PathVariable("id")
                    Long productId,
            @RequestBody
                    Product newProduct){
        Product product = productMapper.select(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        productMapper.update(product);
        return product;
    }
}
