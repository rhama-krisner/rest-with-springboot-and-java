package application.app.service;

import application.app.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findByID(String id){
        logger.info("Buscando uma pessoa");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Rhama");
        person.setLastName("Krisner");
        person.setAddress("Betim, Minas Gerais");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i <= 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    private Person mockPerson(int i) {
        logger.info("Buscando todas as pessoas.");
        logger.info("Buscando uma pessoa");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Pessoa de nome " + i);
        person.setLastName("Sobrenome");
        person.setAddress("Betim, Minas Gerais");
        person.setGender("Male");

        return person;
    }

}
