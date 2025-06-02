package blog.com.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(@NotNull String nome, @Email String email, @NotNull String senha) {
}
