package cn.rivamed.springbootmybatiswithredis.dao.mapper;



import cn.rivamed.springbootmybatiswithredis.dao.domain.Book;

import java.util.List;

public interface BookMapper {
  int addBook(Book book);
  int updateBook(Book book);
  int deleteBook(int id);
  Book getBook(int id);
  List<Book> getBooks(Book book);
}
