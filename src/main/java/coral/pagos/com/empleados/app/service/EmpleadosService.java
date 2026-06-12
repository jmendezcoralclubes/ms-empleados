package coral.pagos.com.empleados.app.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;

import coral.pagos.com.empleados.app.dto.request.entity.EmpleadoRequest;
import coral.pagos.com.empleados.app.dto.response.object.EmpleadoResponseRest;
import coral.pagos.com.empleados.app.dto.response.object.JefeResponseRest;

public interface EmpleadosService {

	public ResponseEntity<EmpleadoResponseRest> buscarEmpleado(EmpleadoRequest empleadoRequest, BindingResult result, 
			HttpServletRequest request, MultiValueMap<String, String> headers, String url) throws JsonProcessingException;

	public ResponseEntity<JefeResponseRest> buscarJefe(Long numEmpleado, Integer desarrollo,
			HttpServletRequest request, MultiValueMap<String, String> headers, String string) throws JsonProcessingException;

}
