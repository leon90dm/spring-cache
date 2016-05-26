package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ImportResource(locations = "classpath:spring-context.xml")
//@EnableCaching
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Component
    static class Runner implements CommandLineRunner {
        @Autowired
        private BookRepository bookRepository;

        @Override
        public void run(String... args) throws Exception {

            //结论请参见SimpleBookRepository
            log.info(".... Fetching books");
            //            log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
            //            log.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
            //            log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
            //            log.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
            //            log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
            //            log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
            //            log.info("isbn-1234 -->" + bookRepository.getTitleByIsbn("isbn-1234"));
            //            log.info("isbn-1234 -->" + bookRepository.getNameByIsbn("isbn-1234"));
            //            log.info("isbn-1234 -->" + bookRepository.getTitleByIsbn("isbn-1234"));
            //            log.info("isbn-1234 -->" + bookRepository.getNameByIsbn("isbn-1234"));

//            log.info("isbn-1234 -->" + bookRepository.getNameByBookIsbn(new Book("isbn-1234", "title")));
//            log.info("isbn-4567 -->" + bookRepository.getNameByBookIsbn(new Book("isbn-4567", "title")));
//            log.info("isbn-1234 -->" + bookRepository.getNameByBookIsbn(new Book("isbn-1234", "title")));
//            log.info("isbn-4567 -->" + bookRepository.getNameByBookIsbn(new Book("isbn-4567", "title")));
            System.out.println(bookRepository.findAllBook1());
            System.out.println(bookRepository.findAllBook2());
            System.out.println(bookRepository.findAllBook1());
            System.out.println(bookRepository.findAllBook2());
            System.out.println(bookRepository.findAllBookNames());
            System.out.println(bookRepository.findAllBookNames());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
