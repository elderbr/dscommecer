package com.devsuperior.dscommece.dto;

import com.devsuperior.dscommece.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;

    public UserDTO() {
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();
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
}
