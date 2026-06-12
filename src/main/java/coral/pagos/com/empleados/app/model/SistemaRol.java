package coral.pagos.com.empleados.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "SISTEMA_ROL")
@Data
@NoArgsConstructor
@ToString
public class SistemaRol implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	private String rol;
	
	@JsonIgnore
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@JsonIgnore
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;
	
	@Column(name = "ID_ACTIVO")
	private Integer idActivo;
	
}
