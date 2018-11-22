package cn.rivamed.springbootmybatiswithredis.service.impl;


import cn.rivamed.springbootmybatiswithredis.dao.domain.Book;
import cn.rivamed.springbootmybatiswithredis.dao.mapper.BookMapper;
import cn.rivamed.springbootmybatiswithredis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
  @Autowired
  private BookMapper bookRepository;
  public ResponseEntity<Book> addBook(final Book book) {
    int num = bookRepository.addBook(book);
    return ResponseEntity.ok(book);
  }
}
