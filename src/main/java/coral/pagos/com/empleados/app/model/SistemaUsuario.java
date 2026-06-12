package coral.pagos.com.empleados.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "SISTEMA_USUARIO")
@Data
@NoArgsConstructor
@ToString
public class SistemaUsuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "USUARIO")
	private String usuario;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "CORREO")
	private String correo;
	
	@Column(name = "CELULAR")
	private String celular;
	
	@JsonIgnore
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "PASSWORD_TEMPORAL")
	private String passwordTemporal;
	
	@Column(name = "INTENTOS")
	private Integer intentos;
	
	@JsonIgnore
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@JsonIgnore
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;
	
	@Column(name = "ID_ACTIVO")
	private Integer idActivo;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "SISTEMA_USUARIO_ROL",
	joinColumns = @JoinColumn(name = "SISTEMA_USUARIO_ID"),
	inverseJoinColumns = @JoinColumn(name = "SISTEMA_ROL_ID"),
	uniqueConstraints = {@UniqueConstraint(columnNames =  {"SISTEMA_USUARIO_ID", "SISTEMA_ROL_ID"})})
	private List<SistemaRol> roles;
	
}
