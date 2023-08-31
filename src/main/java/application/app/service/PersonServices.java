package application.app.service;

import application.app.controller.PersonController;
import application.app.data.vo.v1.PersonVO;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import application.app.exceptions.ResourceNorFoundException;
import application.app.mapper.DozerMapper;
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

    public PersonVO findById(Long id) {
        logger.info("Buscando uma pessoa");

        var entity = repository.findById(id).orElseThrow(() ->
                new ResourceNorFoundException("Não foram encontrado dados para esse ID"));

        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Buscando todas as pessoas");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Criando uma pessoa.");

        var entity = DozerMapper.parseObject(person, Person.class);

        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        logger.info("Atualizado pessoa.");

        Person entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNorFoundException("Não foram encontrado dados para esse ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Excluindo uma pessoa");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNorFoundException("Sem dados encontrados nesse ID"));
        repository.delete(entity);

    }

}
