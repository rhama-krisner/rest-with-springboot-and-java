package application.app.controller;

import application.app.model.Book;
import application.app.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Find all", description = "Find all books",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Book.class)))
                    }),
                    @ApiResponse(description = "BAD REQUEST", responseCode = "400", content = @Content),
                    @ApiResponse(description = "UNATHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public List<Book> findAll() {
        return service.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Find book by id", description = "Finds book by ID",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Book.class))),
                    @ApiResponse(description = "NO CONTENT", responseCode = "204", content = @Content),
                    @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public Book findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @CrossOrigin(origins = {"http://localhost:8080"})
    @PostMapping
    @Operation(summary = "Add new book", description = "Add new book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Book.class))),
                    @ApiResponse(description = "BAD REQUEST", responseCode = "204", content = @Content),
                    @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public Book create(@RequestBody Book book) {
        return service.create(book);
    }

    @PutMapping
    @Operation(summary = "Updated", description = "Update book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Book.class))),
                    @ApiResponse(description = "BAD REQUEST", responseCode = "204", content = @Content),
                    @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public Book update(@RequestBody Book book) {
        return service.update(book);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete book", description = "Add new book",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "NO CONTENT", responseCode = "204",content = @Content),
                    @ApiResponse(description = "BAD REQUEST", responseCode = "204", content = @Content),
                    @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }

}
