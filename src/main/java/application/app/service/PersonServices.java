package application.app.service;

import application.app.exceptions.ResourceNorFoundException;
import application.app.model.Person;
import application.app.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

     final PersonRepository repository;

    public PersonServices(PersonRepository repository) {
        this.repository = repository;
    }

    public Person findById(Long id) {
        logger.info("Buscando uma pessoa");

        return repository.findById(id).orElseThrow(() -> new ResourceNorFoundException("Não foram encontrado dados para esse ID"));
    }

    public List<Person> findAll() {
        logger.info("Buscando todas as pessoas");

        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Criando uma pessoa.");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Atualizado pessoa.");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNorFoundException("Não foram encontrado dados para esse ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {
        logger.info("Excluindo uma pessoa");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNorFoundException("Sem dados encontrados nesse ID"));
        repository.delete(entity);
    }

}
