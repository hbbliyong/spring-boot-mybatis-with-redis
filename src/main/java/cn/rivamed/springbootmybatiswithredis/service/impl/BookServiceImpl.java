package cn.rivamed.springbootmybatiswithredis.service.impl;


import cn.rivamed.springbootmybatiswithredis.dao.domain.Book;
import cn.rivamed.springbootmybatiswithredis.dao.mapper.BookMapper;
import cn.rivamed.springbootmybatiswithredis.service.BookService;
import cn.rivamed.springbootmybatiswithredis.util.RedisCacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
  @Autowired
  private BookMapper bookRepository;

  @Autowired
  private RedisCacheHelper redisCacheHelper;
  private static  final String keyHead="mysql:get:book:";
  public ResponseEntity<Book> addBook(final Book book) {
    int num = bookRepository.addBook(book);
    return ResponseEntity.ok(book);
  }

  @Override
  public Book getBookById(String id) {
    Book book=redisCacheHelper.get(keyHead+id,Book.class);
    if(book==null){
     book= bookRepository.getBook(id);
     if(book!=null){
       redisCacheHelper.add(keyHead+id,30L,book);
     }
    }
    return book;
  }
}
