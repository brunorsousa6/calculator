package br.com.erudio.springbootaws.controller.vo.custom;

import br.com.erudio.springbootaws.controller.vo.PersonVOV2;
import br.com.erudio.springbootaws.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PersonConverter {

    public PersonVOV2 convertEntitytoVO(Person person){

        PersonVOV2 vo = PersonVOV2.builder()
                .id(person.getId())
                .address(person.getAddress())
                .birthday(new Date())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .gender(person.getGender()).build();

        return vo;

    }

    public Person VOtoEntity(PersonVOV2 person){

        Person entity = Person.builder()
                .id(person.getId())
                .address(person.getAddress())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .gender(person.getGender()).build();

        return entity;

    }
}
