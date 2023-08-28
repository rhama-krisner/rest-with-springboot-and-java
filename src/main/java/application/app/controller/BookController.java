package application.app.controller;

import application.app.model.Book;
import application.app.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Books", description = "Endpoints for managing books.")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }
    @GetMapping(value = {"", "/"})
    public List<Book> findAll(){
        return service.findAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book findById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }
    @PostMapping
    public Book create(@RequestBody Book book){
        return service.create(book);
    }
    @PutMapping
    public Book update(@RequestBody Book book){
        return service.update(book);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.notFound().build();
    }

}
