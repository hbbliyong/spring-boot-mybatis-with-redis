package cn.rivamed.springbootmybatiswithredis.controller;

import cn.rivamed.springbootmybatiswithredis.dao.domain.Book;
import cn.rivamed.springbootmybatiswithredis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/getBook")
    public Book getBook(String id){
        return bookService.getBookById(id);
    }
}
