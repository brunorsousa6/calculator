package br.com.erudio.springbootaws.v2;

import br.com.erudio.springbootaws.controller.vo.PersonVO;
import br.com.erudio.springbootaws.controller.vo.PersonVOV2;
import br.com.erudio.springbootaws.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonControllerV2 {

    @Autowired
    PersonService personService;


    @PostMapping("/v2")
    public ResponseEntity<PersonVOV2> create(@RequestBody PersonVOV2 person) {
        return new ResponseEntity<>(personService.createV2(person), HttpStatus.CREATED);
    }
}
