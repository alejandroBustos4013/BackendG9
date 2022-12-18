package com.movies.service;

import com.movies.dto.AuthDto;
import com.movies.dto.AuthResponseDto;
import com.movies.entities.User;
import com.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    UserRepository repository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthResponseDto check(AuthDto request) {
        AuthResponseDto response = new AuthResponseDto();
        if (
                request.nombreusuario != null && !request.nombreusuario.equals("") && request.password != null && !request.password.equals("")) {
            Optional<User> user = checkCredential(request.nombreusuario, request.password);

            if (user != null && user.isPresent()) {
                response.id = user.get().getId();
                response.name = user.get().getFull_name();
                response.email = user.get().getEmail();
                response.token = getToken(request.nombreusuario, request.password);
            }
        }

        return response;
    }
    //

    private String getToken(String user, String pass) {
        String tokenString = user + ":" + pass;
        byte[] bytesEncoded = Base64.encodeBase64(tokenString.getBytes());
        return new String(bytesEncoded);
    }

    public Optional<User> checkCredential(String nombreusuario, String password) {

        Optional<User> user = repository.findUserByUsername(nombreusuario);
        if (!matchPass(password, user.get().getPassword())) {
            return null;
        }
        /*if (!password.equals(user.get().getPassword())) {
            return null;
        }*/
        return user;
    }

    public Optional<User> getByCredential(String credential) {
        String pair = new String(Base64.decodeBase64(credential.substring(6)));
        String email = pair.split(":")[0];
        String pass = pair.split(":")[1];

        Optional<User> user = repository.findUserByEmail(email);
        if (!matchPass(pass, user.get().getPassword())) {
            return null;
        }
        return user;
    }


    private Boolean matchPass(String pass, String dbPass) {
        return this.passwordEncoder.matches(pass, dbPass);
    }


}
