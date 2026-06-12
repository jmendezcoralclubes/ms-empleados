package coral.pagos.com.empleados.app.dto.request.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmpleadoRequest {
	
	@NotEmpty(message = "no puede ser vacío.")
	@NotNull(message = "no puede ser nulo.")
	private String correo;
	
	//@JsonProperty("empleado")
	//@NotNull(message = "no puede ser nulo.")
	//private Long numeroEmpleado;
	
	@NotNull(message = "no puede ser nulo.")
	private Integer desarrollo;

	@NotEmpty(message = "no puede ser vacío.")
	@NotNull(message = "no puede ser nulo.")
	private String rfc;
	
}
