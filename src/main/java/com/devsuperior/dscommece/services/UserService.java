package com.devsuperior.dscommece.services;

import com.devsuperior.dscommece.dto.UserDTO;
import com.devsuperior.dscommece.entities.User;
import com.devsuperior.dscommece.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> findAll(){
        List<User> users = repository.findAll();
        return users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    public UserDTO findById(Long id){
        User user = repository.findById(id).get();
        return new UserDTO(user);
    }

}
