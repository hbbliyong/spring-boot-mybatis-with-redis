package cn.rivamed.springbootmybatiswithredis.service;

import cn.rivamed.springbootmybatiswithredis.dao.domain.Book;
import org.springframework.http.ResponseEntity;

public interface BookService {
     ResponseEntity<Book> addBook(final Book book);
}
