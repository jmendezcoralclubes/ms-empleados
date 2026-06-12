package coral.pagos.com.empleados.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import coral.pagos.com.empleados.app.model.Empleado;
import coral.pagos.com.empleados.app.sp.SpBuscaEmpleado;
import coral.pagos.com.empleados.app.sp.SpValidarRegistroEmpleado;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long>{
	
	Optional<Empleado> findByEmpNumeroEmpleadoAndEmpLsvIdentificadorDesarrolloAndEmpRfc( Long numeroEmpleado, Integer desarrollo, String rfc );
	
	Optional<Empleado> findByEmpLsvIdentificadorDesarrolloAndEmpRfc( Integer desarrollo, String rfc );
	
	@Query(value="SELECT * FROM EMPLEADOS WHERE emp_rfc LIKE %:rfc% and EMP_LSV_ESTATUSEMPLEADOS=183", nativeQuery = true)
	public List<Empleado> buscaRFCLike( @Param("rfc") String rfc );
	
	@Query(value="{CALL spBuscaEmpleado(:numEmpleado,:desarrollo)}", nativeQuery = true)
	SpBuscaEmpleado spBuscaEmpleado(@Param("numEmpleado") Long numEmpleado, @Param("desarrollo") Integer desarrollo);
	
	@Query(value = "EXEC dbo.spValidarRegistroEmpleado "
	        + "@param_correo = :param_correo, "
	        + "@param_rfc = :param_rfc, "
	        + "@param_desarrollo = :param_desarrollo",
	        nativeQuery = true)
	SpValidarRegistroEmpleado spValidarRegistroEmpleado(
	        @Param("param_correo") String correo,
	        @Param("param_rfc") String rfc,
	        @Param("param_desarrollo") Integer idDesarrollo
	);
	
	

}
