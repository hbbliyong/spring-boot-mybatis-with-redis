package cn.rivamed.springbootmybatiswithredis.dao.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Book {

  private Integer bookId;

  private Integer pageNum;

  private String bookName;

  private BookType BookType;

  private String bookDesc;

  private Double bookPrice;

  private LocalDateTime createTime;

  private LocalDateTime modifyTime;
}
