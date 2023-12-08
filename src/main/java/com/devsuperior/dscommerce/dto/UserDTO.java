package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;

    private List<String> roles = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();
        for(GrantedAuthority authority : entity.getRoles()){
            roles.add(authority.getAuthority());
        }
    }

    public Long getId() {
        return id;
    }

    @NotBlank(message = "O nome não pode ficar em branco!!!")
    public String getName() {
        return name;
    }


    @Email(message = "Digite o email corretamente!!!")
    public String getEmail() {
        return email;
    }

    @NotBlank
    public String getPhone() {
        return phone;
    }

    @NotBlank(message = "Digite a data de nascimento!!!")
    @Past(message = "A data de nascimento deve estar no passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getRoles() {
        return roles;
    }
}
