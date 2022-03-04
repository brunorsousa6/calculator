package br.com.erudio.springbootaws.controller;


import br.com.erudio.springbootaws.controller.vo.PersonVO;
import br.com.erudio.springbootaws.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<PersonVO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @GetMapping("/v1")
    public ResponseEntity<List<PersonVO>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @PostMapping("/v1")
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
        return new ResponseEntity<>(personService.create(person), HttpStatus.CREATED);
    }


    @PutMapping("/v1")
    public ResponseEntity<PersonVO> update(@RequestBody PersonVO person) {

        return new ResponseEntity<>(personService.update(person), HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<PersonVO> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

