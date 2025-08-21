package app.eat.dto;

import app.eat.enumeration.Role;
import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private Role role; // "USER" o "ADMIN"
}