package br.com.erudio.springbootaws.services;

import br.com.erudio.springbootaws.controller.vo.PersonVO;
import br.com.erudio.springbootaws.controller.exception.ResourceNotFoundException;
import br.com.erudio.springbootaws.controller.vo.custom.PersonConverter;
import br.com.erudio.springbootaws.model.Person;
import br.com.erudio.springbootaws.repository.PersonRepository;
import br.com.erudio.springbootaws.controller.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonConverter personConverter;


    public PersonVO create(PersonVO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        var entity = personConverter.VOtoEntity(person);
        var vo = personConverter.convertEntitytoVO(personRepository.save(entity));
        return vo;
    }

    public PersonVO update(PersonVO person) {

        var entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);;

    }

    public PersonVO findById(Long id) {
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        return DozerConverter.parseListObjects(personRepository.findAll(), PersonVO.class);

    }


}
