package coral.pagos.com.empleados.app.sp;

import java.time.LocalDate;

public interface SpValidarRegistroEmpleado {
	Long getNumEmpleado();
	Long getLsvIdDesarrollo();
	String getLsvDesarrollo();
	String getNombreCompleto();
	LocalDate getFechaNacimiento();
	String getRfc();
	String getCurp();
	LocalDate getFechaIngreso();
	Long getIdDesarrolloJefe();
	Long getSuperiorId();
	String getCorreoJefe();
	String getEmail();
}
