package hello;

public interface BookRepository {

    Book getByIsbn(String isbn);

    String getTitleByIsbn(String isbn);

    String getNameByIsbn(String isbn);

    String getNameByBookIsbn(Book book);
}
