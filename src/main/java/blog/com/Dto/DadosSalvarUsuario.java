package blog.com.Dto;

import jakarta.validation.constraints.NotNull;

public record DadosSalvarUsuario(@NotNull long id, String senha) {
}
