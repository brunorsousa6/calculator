package br.com.erudio.springbootaws.controller;


import br.com.erudio.springbootaws.controller.vo.PersonVO;
import br.com.erudio.springbootaws.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {

    @Autowired
    PersonService personService;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<PersonVO>> findAll() {
        List<PersonVO> people = personService.findAll();

        people.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        return ResponseEntity.ok(people);
    }

    @GetMapping(value = "{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<PersonVO> findById(@PathVariable("id") Long id) {
        PersonVO personVO = personService.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(personVO);
    }

    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
        PersonVO personVO = personService.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return new ResponseEntity<>(personVO, HttpStatus.CREATED);
    }


    @PutMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ResponseEntity<PersonVO> update(@RequestBody PersonVO person) {
        PersonVO personVO = personService.update(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return new ResponseEntity<>(personVO, HttpStatus.CREATED);
    }


    @DeleteMapping
    public ResponseEntity<PersonVO> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

