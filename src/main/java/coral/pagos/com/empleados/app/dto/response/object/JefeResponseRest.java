package coral.pagos.com.empleados.app.dto.response.object;

import coral.pagos.com.empleados.app.dto.ResponseCoral;
import coral.pagos.com.empleados.app.dto.ResponseJefeDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class JefeResponseRest extends ResponseCoral{

	private ResponseJefeDTO responseJefeDTO;
	
}
