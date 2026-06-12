package coral.pagos.com.empleados.app.dto.response.object;

import coral.pagos.com.empleados.app.dto.ResponseCoral;
import coral.pagos.com.empleados.app.dto.response.entity.EmpleadoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EmpleadoResponseRest extends ResponseCoral{

	private EmpleadoDTO empleadoDTO;
	
}
