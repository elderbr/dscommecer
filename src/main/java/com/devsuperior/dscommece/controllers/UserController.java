package com.devsuperior.dscommece.controllers;

import com.devsuperior.dscommece.dto.UserDTO;
import com.devsuperior.dscommece.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

}
