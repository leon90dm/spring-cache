package hello;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@CacheConfig(cacheNames = "books") //如果这个类方法中@Cacheable未设置value,那么就使用这个cacheName
public class SimpleBookRepository implements BookRepository {

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    @Override
    @Cacheable("bookNames") //由于@Cacheable设置了value,那么就会使用新设置的,而非「books」
    public String getTitleByIsbn(String isbn) {
        simulateSlowService();
        return isbn + "'book";
    }

    @Override
    @Cacheable("bookValues")
    //如果使用的是redis,那么所有存储其实是和key有关系,比如下面的方法,只有一个参数,但是同上面的相比
    //        bookRepository.getTitleByIsbn("isbn-1234"));
    //        bookRepository.getNameByIsbn("isbn-1234"));
    //虽然方法不一样,但是key一样,因此两个方法如果走缓存,都会得到方法先调用设置到缓存里的值。要特别注意。
    public String getNameByIsbn(String isbn) {
        simulateSlowService();
        return isbn + "'value";
    }

    @Override
    @Cacheable
    //如果参数为一个类的实例,会以实例序列化的数据做为key,最好要重新equals和hashcode
    public String getNameByBookIsbn(Book book) {
        simulateSlowService();
        return book.getIsbn() + "'bookInstance";
    }

    @Override
//    @Cacheable( key = "#root.methodName")
    //Spring默认的key是通过参数名来生成的,所以像这样没有参数的方法,那么返回的值是一样的。
    //要想区分二者存储的,则需要加不同的key或者使用自定制的keyGenerator
    @Cacheable
    public List<Book> findAllBook1() {
        simulateSlowService();
        Book book = new Book("findAll1", "findAll1=book");
        return Arrays.asList(book);
    }

    @Override
//    @Cacheable(key = "#root.methodName")
    @Cacheable
    public List<Book> findAllBook2() {
        simulateSlowService();
        Book book = new Book("findAll2", "findAll2=book");
        return Arrays.asList(book);
    }

    @Override
    @Cacheable //如上面所说,如果这里加了@Cacheable,因为默认key通前面一样,所以返回的实际是 List<Book> ,这是很恐怖的
    public List<String> findAllBookNames() {
        simulateSlowService();
        return Arrays.asList("findAllBookNames");
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 2000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
