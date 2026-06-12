package coral.pagos.com.empleados.app.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseJefeDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idJefeEmpleado;
	
	private String correo;

}
