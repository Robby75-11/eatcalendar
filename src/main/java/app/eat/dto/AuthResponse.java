package app.eat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor            // costruttore vuoto
@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
}
