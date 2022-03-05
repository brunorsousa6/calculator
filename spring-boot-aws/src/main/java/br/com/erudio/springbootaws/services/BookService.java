package br.com.erudio.springbootaws.services;


import br.com.erudio.springbootaws.controller.vo.BookVO;
import br.com.erudio.springbootaws.controller.exception.ResourceNotFoundException;
import br.com.erudio.springbootaws.model.Book;
import br.com.erudio.springbootaws.repository.BookRepository;
import br.com.erudio.springbootaws.controller.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    public BookVO create(BookVO book) {
        var entity = DozerConverter.parseObject(book, Book.class);
        var vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
        return vo;
    }


    public BookVO update(BookVO book) {

        var entity = bookRepository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
        return vo;
    }

    public void delete(Long id) {
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        bookRepository.delete(entity);;

    }

    public BookVO findById(Long id) {
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public List<BookVO> findAll() {
        return DozerConverter.parseListObjects(bookRepository.findAll(), BookVO.class);

    }

}
