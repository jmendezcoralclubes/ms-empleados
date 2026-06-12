package coral.pagos.com.empleados.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import coral.pagos.com.empleados.app.model.EmpleadoCorreoElectronico;

public interface EmpleadoCorreoElectronicoRepository extends CrudRepository<EmpleadoCorreoElectronico, Long>{
	
	List<EmpleadoCorreoElectronico> findByIdAndEceEmpLsvIdentificadorDesarrollo(Long id, Integer eceEmpLsvIdentificadorDesarrollo);
	
}
