package br.com.erudio.springbootaws.security;

import lombok.Data;

import java.io.Serializable;


@Data
public class AccountCredentialsVO implements Serializable {

    private String username;
    private String password;
}
