package blog.com.infra;



import blog.com.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String tokenJWT = recuperaToken(request);
        String path = request.getRequestURI();

        // Liberar arquivos estáticos sem autenticação
        if (path.startsWith("/admin/js/") || path.startsWith("/css/") || path.startsWith("/js/")
                || path.endsWith(".js") || path.endsWith(".css")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (tokenJWT != null) {
            var login = tokenService.validateToken(tokenJWT);
            if (login != null) {
                var usuario = userRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("usuario não existe"));
                // Logando as informações do usuário para depuração
                System.out.println("Usuário autenticado: " + usuario.getEmail());
                System.out.println("Roles: " + usuario.getAuthorities());
                    var authentication = new UsernamePasswordAuthenticationToken(
                            usuario, null, usuario.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
    private String recuperaToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }//null
        return null;
    }
}
