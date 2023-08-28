package application.app.controller;

import application.app.model.Person;
import application.app.service.PersonServices;
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
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people.")
public class PersonController {

    private final PersonServices service;

    public PersonController(PersonServices service) {
        this.service = service;
    }

    @GetMapping(value = {""})
    @Operation(summary = "Find all", description = "Find all people",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Person.class))
                                    )}),
                    @ApiResponse(description = "BAD REQUEST", responseCode = "400", content = @Content),
                    @ApiResponse(description = "UNATHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public List<Person> findAll() {
        return service.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Find person by id", description = "Finds person by ID",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Person.class))),
                    @ApiResponse(description = "NO CONTENT", responseCode = "204", content = @Content),
                    @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public Person findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br"})
    @PostMapping
    @Operation(summary = "Add new person", description = "Add new person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Person.class))),
                    @ApiResponse(description = "BAD REQUEST", responseCode = "204", content = @Content),
                    @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public Person create(@RequestBody Person person) {
        return service.create(person);
    }

    @PutMapping
    @Operation(summary = "Updated", description = "Update person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Person.class))),
                    @ApiResponse(description = "BAD REQUEST", responseCode = "204", content = @Content),
                    @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public Person update(@RequestBody Person person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete person", description = "Add new person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "NO CONTENT", responseCode = "204",content = @Content),
                    @ApiResponse(description = "BAD REQUEST", responseCode = "204", content = @Content),
                    @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}

