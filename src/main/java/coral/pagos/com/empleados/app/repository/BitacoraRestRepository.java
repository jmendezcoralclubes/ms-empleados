package coral.pagos.com.empleados.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import coral.pagos.com.empleados.app.model.BitacoraRest;

@Repository
public interface BitacoraRestRepository extends CrudRepository<BitacoraRest, Long>{

}
