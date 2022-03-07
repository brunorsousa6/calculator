package br.com.erudio.springbootaws.services;


import br.com.erudio.springbootaws.controller.exception.ResourceNotFoundException;
import br.com.erudio.springbootaws.controller.vo.BookVO;
import br.com.erudio.springbootaws.model.Book;
import br.com.erudio.springbootaws.repository.BookRepository;
import br.com.erudio.springbootaws.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByUsername(username);

        if ( user == null){
            throw new UsernameNotFoundException("username" + username);
        }
        return user;
    }
}
