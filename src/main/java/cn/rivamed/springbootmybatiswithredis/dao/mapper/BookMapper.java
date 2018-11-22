package cn.rivamed.springbootmybatiswithredis.dao.mapper;



import cn.rivamed.springbootmybatiswithredis.dao.domain.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
  int addBook(Book book);
  int updateBook(Book book);
  int deleteBook(int id);
  Book getBook(String id);
  List<Book> getBooks(Book book);
}
