package coral.pagos.com.empleados.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import coral.pagos.com.empleados.app.model.SistemaUsuario;

@Repository
public interface SistemaUsuarioRepository extends CrudRepository<SistemaUsuario, Integer>{
	public SistemaUsuario findByUsuarioAndIdActivo(String usuario, Integer activo);
	public SistemaUsuario findByUsuario(String usuario);
}