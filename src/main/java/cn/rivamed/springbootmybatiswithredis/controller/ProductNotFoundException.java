package cn.rivamed.springbootmybatiswithredis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
 class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException(Long productId) {
        super("Couldn't find product '" + productId + "'.");
    }
}
