package coral.pagos.com.empleados.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coral.pagos.com.empleados.app.model.SistemaUsuario;
import coral.pagos.com.empleados.app.repository.SistemaUsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private SistemaUsuarioRepository sistemaUsuarioRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SistemaUsuario usuario = this.sistemaUsuarioRepository.findByUsuario(username);
		if( usuario == null) {
			log.error("error, el usuario no existe");
			throw new UsernameNotFoundException("error, el usuario no existe");
		}
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map( role -> new SimpleGrantedAuthority(role.getRol()))
				.peek( authority -> log.info("Role: "+ authority.getAuthority()))
				.collect(Collectors.toList());
		return new User(usuario.getUsuario(), usuario.getPassword(), usuario.getIdActivo()!=2, 
				        usuario.getIdActivo()!=3, usuario.getIdActivo()!=4, usuario.getIdActivo()!=5, 
				        authorities);	
	}
}