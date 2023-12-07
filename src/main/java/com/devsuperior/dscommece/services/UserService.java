package com.devsuperior.dscommece.services;

import com.devsuperior.dscommece.dto.UserDTO;
import com.devsuperior.dscommece.entities.Role;
import com.devsuperior.dscommece.entities.User;
import com.devsuperior.dscommece.projections.UserDetailsProjection;
import com.devsuperior.dscommece.repositories.UserRepository;
import com.devsuperior.dscommece.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> pages = repository.findAll(pageable);
        return pages.map(x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        try {
            User user = repository.findById(id).get();
            return new UserDTO(user);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Usuário não existe!!!");
        }
    }

    @Transactional
    public UserDTO insert(User user) {
        if (user == null) {
            throw new ResourceNotFoundException("Dados do usuário invalidos!!!");
        }

        // Busca o email para verificar se já existe
        Optional<User> optional = repository.findByEmail(user.getEmail());
        if (optional != null) {
            throw new ResourceNotFoundException("O email já está cadastrado!!!");
        }

        // 11 9 9999-9999
        String phone = user.getPhone().replaceAll("[^0-9]", "");
        if (phone.length() < 11) {
            throw new ResourceNotFoundException("Número do telefone invalido!!!");
        }
        User userNew = repository.findByPhone(phone);
        if(userNew != null){
            throw new ResourceNotFoundException("Número do telefone já está cadastrado!!!");
        }
        user.setPhone(phone);

        // Salvando novo usuário
        userNew = repository.save(user);
        return new UserDTO(userNew);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> projections = repository.searcUserAndRoleByEmail(username);
        if(projections.isEmpty()){
            throw new UsernameNotFoundException("Use not found!");
        }
        User user = new User();
        user.setEmail(username);
        user.setPassword(projections.get(0).getPassword());
        for(UserDetailsProjection projection : projections){
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }

    protected User authenticated(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
            return repository.findByEmail(username).get();
        }catch (Exception e){
            throw new UsernameNotFoundException("Email not found!");
        }
    }

    @Transactional(readOnly = true)
    public UserDTO getMe(){
        User user = authenticated();
        return new UserDTO(user);
    }
}
