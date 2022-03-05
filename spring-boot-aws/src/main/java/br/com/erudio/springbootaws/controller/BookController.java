package br.com.erudio.springbootaws.controller;


import br.com.erudio.springbootaws.controller.vo.BookVO;
import br.com.erudio.springbootaws.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/book/v1")
public class BookController {

    @Autowired
    BookService bookService;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<List<BookVO>> findAll() {
        List<BookVO> people = bookService.findAll();

        people.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));

        return ResponseEntity.ok(people);
    }

    @GetMapping(value = "{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<BookVO> findById(@PathVariable("id") Long id) {
        BookVO bookVO = bookService.findById(id);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(bookVO);
    }

    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ResponseEntity<BookVO> create(@RequestBody BookVO book) {
        BookVO bookVO = bookService.create(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return new ResponseEntity<>(bookVO, HttpStatus.CREATED);
    }


    @PutMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ResponseEntity<BookVO> update(@RequestBody BookVO book) {
        BookVO bookVO = bookService.update(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return new ResponseEntity<>(bookVO, HttpStatus.CREATED);
    }


    @DeleteMapping
    public ResponseEntity<BookVO> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

