package hello;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

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

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
