package blog.com.Dto;

import blog.com.dominio.Usuario;

public record DadosDetalhamentoUsuario( String nome, String email,  String funcao) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this( usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}
