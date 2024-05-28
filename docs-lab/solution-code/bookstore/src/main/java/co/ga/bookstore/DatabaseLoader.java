package co.ga.bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DatabaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        log.info("Saved {}", bookRepository.save(new Book("Rainbow Six")));
        log.info("Saved {}", bookRepository.save(new Book("The Hunt For Red October")));
        log.info("Saved {}", bookRepository.save(new Book("Without Remorse")));
        log.info("Saved {}", bookRepository.save(new Book("Red Storm Rising")));
        log.info("Saved {}", bookRepository.save(new Book("Debt of Honor")));
        log.info("Saved {}", bookRepository.save(new Book("Clear and Present Danger")));
        log.info("Saved {}", bookRepository.save(new Book("Red Rabbit")));
    }
}