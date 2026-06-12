package coral.pagos.com.empleados.app.dto.response.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmpleadoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean isValid;
	
	private Long numEmpleado;
	
	private Long numEmpleadoJefe;
	
	private String nombreCompleto;
	
	private Long idDesarrollo;
	
}
