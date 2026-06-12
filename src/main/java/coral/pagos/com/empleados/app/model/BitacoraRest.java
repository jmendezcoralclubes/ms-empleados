package coral.pagos.com.empleados.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "BITACORA_REST")
@Data
@NoArgsConstructor
@ToString
public class BitacoraRest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "IP")
	private String ip;
	
	@Column(name = "REQUEST")
	private String request;
	
	@Column(name = "RESPONSE")
	private String response;
	
	@Column(name = "URL")
	private String url;
	   
	@Column(name = "PARAMETROS")
	private String parametros;
	
	@Column(name = "EXCEPCION")
	private String excepcion;
	
	@Column(name = "CABECERAS")
	private String cabecera;
	
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    
    @PrePersist
    protected void onCreate() {
    	fechaCreacion = new Date();
    }

}
