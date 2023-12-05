package com.devsuperior.dscommece.controllers;

import com.devsuperior.dscommece.dto.UserDTO;
import com.devsuperior.dscommece.entities.User;
import com.devsuperior.dscommece.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDTO> findAll(){
        return service.findAll();
    }

}
