package coral.pagos.com.empleados.app.model;

import java.util.Date;

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
@Table(name = "EMPLEADOS")
@Data
@NoArgsConstructor
@ToString
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_NUMERO_EMPLEADO")
	private long empNumeroEmpleado;

	@Column(name = "EMP_LSV_IDENTIFICADORDESARROLLO")
	private int empLsvIdentificadorDesarrollo;
	
	@Column(name = "EMP_APATERNO")
	private String empAPaterno;
	
	@Column(name = "EMP_AMATERNO")
	private String empAMaterno;
	
	@Column(name = "EMP_NOMBRE")
	private String empNombre;
	
	@Column(name = "EMP_SEGNOMBRE")
	private String empSegNombre;
	
	@Column(name = "EMP_FECNACIMIENTO")
	private Date empFecNacimiento;
	
	@Column(name = "EMP_RFC")
	private String empRfc;
	
	@Column(name = "EMP_CURP")
	private String empCurp;
	
	@Column(name = "EMP_LSV_ESTADOCIVIL")
	private int empLsvEstadoCivil;
	
	@Column(name = "EMP_LSV_GENERO")
	private int empLsvGenero;
	
	@Column(name = "EMP_FECHA_INGRESO")
	private Date empFechaIngreso;
	
	@Column(name = "EMP_LSV_ESTATUSEMPLEADOS")
	private int empLsvEstatusEmpleados;
	
	@Column(name = "EMP_USR_USUARIO_CAPTURA")
	private String empUsrUusuarioCaptura;
	
	@Column(name = "EMP_FECHA_REGISTRO")
	private Date empFechaRegistro;

}
