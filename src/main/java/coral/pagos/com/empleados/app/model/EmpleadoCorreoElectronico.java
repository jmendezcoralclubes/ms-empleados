package coral.pagos.com.empleados.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "EMPLEADOS_CORREOS_ELECTRONICOS")
@Data
@NoArgsConstructor
@ToString
public class EmpleadoCorreoElectronico implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ECE_EMP_NUMERO_EMPLEADO")
	private Long id;
	
	@Column(name = "ECE_EMP_LSV_IDENTIFICADORDESARROLLO")
	private Integer eceEmpLsvIdentificadorDesarrollo;
	
	@Column(name = "ECE_EMAIL")
	private String eceEmail;
	
	@Column(name = "ECE_EMAIL_CONFIRMACION")
	private String eceEmailConfirmacion;

}