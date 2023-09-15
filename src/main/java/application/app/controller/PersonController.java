package application.app.controller;

import application.app.model.Person;
import application.app.repository.PersonRepository;
import application.app.service.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static application.app.util.MediaType.APPLICATION_YAML;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people.")
public class PersonController {

    @Autowired
    PersonServices service;

    @Autowired
    PersonRepository repository;

    //GET all
    @GetMapping(value = {"", "/"},
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    APPLICATION_YAML

            })
    @Operation(summary = "Find all", description = "Find all people",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "SUCCESS", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(
                                                    schema = @Schema(implementation = Person.class))
                                    )}),
                    @ApiResponse(description = "BAD REQUEST", responseCode = "400", content = @Content),
                    @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "NOT FOUND", responseCode = "404", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public List<Person> findAll() {
        List<Person> personList = repository.findAll();

        for (Person pessoa : personList) {
            long id = pessoa.getId();
            pessoa.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        }

        return personList;
    }

    //GET by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    APPLICATION_YAML
            })
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
        Optional<Person> personOptional = repository.findById(id);

        personOptional.get().add(linkTo(methodOn(PersonController.class).findAll())
                .withRel("Listar todas as Pessoas"));

        return new ResponseEntity<Person>(personOptional.get(), HttpStatus.OK).getBody();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            APPLICATION_YAML
    })
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


    @PutMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    APPLICATION_YAML})
    public Person update(@RequestBody Person person) { // Para adicionar o put padrão, so é necessário
        return service.update(person);                 // adicionar o @PathVariable Long id
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete person", description = "Add new person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "NO CONTENT", responseCode = "204", content = @Content),
                    @ApiResponse(description = "BAD REQUEST", responseCode = "204", content = @Content),
                    @ApiResponse(description = "UNAUTHORIZED", responseCode = "401", content = @Content),
                    @ApiResponse(description = "INTERNAL ERROR", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}

