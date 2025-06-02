package blog.com.Dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroPost(@NotNull String title, @NotNull String conteudo, @NotNull long author_id) {
}
