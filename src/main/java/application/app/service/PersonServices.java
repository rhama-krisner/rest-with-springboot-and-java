package application.app.service;

import application.app.exceptions.ResourceNotFoundException;
import application.app.model.Person;
import application.app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
    public class PersonServices {
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    final
    PersonRepository repository;

    public PersonServices(PersonRepository repository) {
        this.repository = repository;
    }

    public Person findById(Long id) {
        logger.info("Buscando pessoa");
        Optional<Person> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Person.class.getName()));
    }


    public List<Person> findAll() {
        logger.info("Buscando todas as pessoas");

        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Criando uma pessoa.");

        return repository.save(person);
    }

    //PUT padrão

//    public Person update(Long id, Person person) {
//        logger.info("Atualizado pessoa" + person.getId());
//        return repository.findById(id)
//                .map(recordFound -> {
//                    recordFound.setFirstName(person.getFirstName());
//                    recordFound.setLastName(person.getLastName());
//                    recordFound.setAddress(person.getAddress());
//                    recordFound.setGender(person.getGender());
//                    return repository.save(recordFound);
//                }).orElseThrow(() ->
//                        new ResourceNotFoundException("Não foi possivel encontrar a pessoa especificada" + id));
//    }

    //PUT sem precisar passar id na URI

    public Person update(Person person){
        logger.info("Atualizado pessoa " + person.getId());

        var repo = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Não foi encontrado pessoas com esse ID"));

        repo.setFirstName(person.getFirstName());
        repo.setLastName(person.getLastName());
        repo.setAddress(person.getAddress());
        repo.setGender(person.getGender());

        return repository.save(repo);
    }



    public void delete(Long id) {
        logger.info("Excluindo uma pessoa");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem dados encontrados nesse ID"));
        repository.delete(entity);

    }

}
