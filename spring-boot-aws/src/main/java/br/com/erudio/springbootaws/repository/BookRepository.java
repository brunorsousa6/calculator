package br.com.erudio.springbootaws.repository;

import br.com.erudio.springbootaws.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
