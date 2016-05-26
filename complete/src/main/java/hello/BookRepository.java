package hello;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface BookRepository {

    Book getByIsbn(String isbn);

    String getTitleByIsbn(String isbn);

    String getNameByIsbn(String isbn);

    String getNameByBookIsbn(Book book);

    List<Book> findAllBook1();

    List<Book> findAllBook2();

    List<String> findAllBookNames();
}
