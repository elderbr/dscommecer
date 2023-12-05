package com.devsuperior.dscommece.services;

import com.devsuperior.dscommece.dto.UserDTO;
import com.devsuperior.dscommece.entities.User;
import com.devsuperior.dscommece.repositories.UserRepository;
import com.devsuperior.dscommece.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Page<UserDTO> findAll(Pageable pageable){
        Page<User> pages = repository.findAll(pageable);
        return pages.map(x->new UserDTO(x));
    }

    public UserDTO findById(Long id){
        try {
            User user = repository.findById(id).get();
            return new UserDTO(user);
        }catch (Exception e){
            throw new ResourceNotFoundException("Usuário não existe!!!");
        }
    }

}
