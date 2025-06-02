package blog.com.infra;

import blog.com.dominio.Usuario;
import blog.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("usuario nao existe"));
        return  new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), usuario.getAuthorities());
    }
}
