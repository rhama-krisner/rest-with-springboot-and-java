package application.app.service;

import application.app.exceptions.ResourceNorFoundException;
import application.app.model.Book;
import application.app.model.Person;
import application.app.repository.BookRepository;
import io.micrometer.observation.GlobalObservationConvention;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.hibernate.query.results.Builders.entity;

@Service
public class BookService {
    private final Logger logger = Logger.getLogger(BookService.class.getName());

    final BookRepository repository;
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book findById(Long id){
        logger.info("Buscando Livro");

        return repository.findById(id).orElseThrow(()-> new ResourceNorFoundException("Não foi encontrado com esse id."));
    }

    public List<Book> findAll(){
        logger.info("Buscando todos os livros.");

        return repository.findAll();
    }

    public Book create(Book book){
        logger.info("Adicionado livro.");
        return repository.save(book);
    }

    public Book update(Book book){
        logger.info("Atualizando livro.");

        Book entity = repository.findById(book.getId()).orElseThrow(()-> new ResourceNorFoundException("Não foi encontrado dados para esse id."));
        entity.setAuthor(book.getAuthor());
        entity.setTitle(book.getTitle());
        entity.setLaunch_date(book.getLaunch_date());
        entity.setPrice(book.getPrice());
        entity.setLaunch_date(book.getLaunch_date());

        return repository.save(book);
    }

    public void delete(Long id){
        logger.info("Livro excluído.");

        Book entity = repository.findById(id).orElseThrow(()->new ResourceNorFoundException("Sem dado encontrado nesse ID."));
        repository.delete(entity);
    }
}
