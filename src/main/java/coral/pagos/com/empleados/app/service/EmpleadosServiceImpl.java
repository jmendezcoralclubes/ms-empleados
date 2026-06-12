package coral.pagos.com.empleados.app.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import coral.pagos.com.empleados.app.dto.ResponseJefeDTO;
import coral.pagos.com.empleados.app.dto.request.entity.EmpleadoRequest;
import coral.pagos.com.empleados.app.dto.response.entity.EmpleadoDTO;
import coral.pagos.com.empleados.app.dto.response.object.EmpleadoResponseRest;
import coral.pagos.com.empleados.app.dto.response.object.JefeResponseRest;
import coral.pagos.com.empleados.app.model.BitacoraRest;
import coral.pagos.com.empleados.app.repository.BitacoraRestRepository;
import coral.pagos.com.empleados.app.repository.EmpleadoCorreoElectronicoRepository;
import coral.pagos.com.empleados.app.repository.EmpleadoRepository;
import coral.pagos.com.empleados.app.sp.SpBuscaEmpleado;
import coral.pagos.com.empleados.app.sp.SpValidarRegistroEmpleado;
import coral.pagos.com.empleados.app.util.Utilidades;

@Service
public class EmpleadosServiceImpl implements EmpleadosService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private EmpleadoCorreoElectronicoRepository empleadoCorreoElectronicoRepository;
	
	@Autowired
	private BitacoraRestRepository bitacoraRestRepository;
	
	@Override
	public ResponseEntity<EmpleadoResponseRest> buscarEmpleado(	EmpleadoRequest empleadoRequest, BindingResult result, final HttpServletRequest request, final MultiValueMap<String, String> headers, String url) throws JsonProcessingException {
		HttpStatus status = HttpStatus.OK;
		EmpleadoResponseRest r = new EmpleadoResponseRest();
		Map<String, String> errores = new HashMap<>();
		EmpleadoDTO c = new EmpleadoDTO();
		
		if (result.hasErrors()) {
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			c.setIsValid(false);
			r.setEmpleadoDTO(c);
			String error = "";
			for (Map.Entry<String, String> entry : errores.entrySet()) {
			    error += entry.getValue()+" ";
			}
			r.setMetadata("Error", "400", error);
			return new ResponseEntity<EmpleadoResponseRest>(r, HttpStatus.BAD_REQUEST);
		}
		
		BitacoraRest bitacoraRest = new BitacoraRest();
		bitacoraRest.setIp(request.getRemoteAddr());
		
		bitacoraRest.setUrl(url);
		String mensaje = "Respuesta exitosa";
		String codigo = "200";
		String response = "Exito";
			
		try {
			String log = registraBitacora(request, headers);
			bitacoraRest.setCabecera(log);
			ObjectMapper objectMapper = new ObjectMapper();
			String requestBody = objectMapper.writeValueAsString(empleadoRequest);
			bitacoraRest.setRequest(requestBody);
			String rfc = empleadoRequest.getRfc().toUpperCase().trim();
			rfc = rfc.replaceAll("[^A-Za-z0-9]", "");
			
			try {
				SpValidarRegistroEmpleado emp = empleadoRepository.spValidarRegistroEmpleado(
						empleadoRequest.getCorreo(), 
						rfc, 
						empleadoRequest.getDesarrollo());
				c.setIsValid(true);
				c.setNumEmpleado(emp.getNumEmpleado());
				c.setNumEmpleadoJefe(emp.getSuperiorId());
				c.setNombreCompleto(emp.getNombreCompleto());
				c.setIdDesarrollo(emp.getLsvIdDesarrollo());
				r.setEmpleadoDTO(c);
			} catch (Exception e) {
				c.setIsValid(false);
				r.setEmpleadoDTO(c);				
				String error = Utilidades.getErrorException(e);
				r.setMetadata("Error", "400", error);
				return new ResponseEntity<EmpleadoResponseRest>(r, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			bitacoraRest.setExcepcion(e.toString());
			mensaje = "Error al consultar al empleado.";
			codigo = "-1";
			response = "Error";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		r.setMetadata(response, codigo, mensaje);
		ObjectMapper respuesta = new ObjectMapper();
		String resp = respuesta.writeValueAsString(r);
		bitacoraRest.setResponse(resp);
		bitacoraRestRepository.save(bitacoraRest);
		return new ResponseEntity<EmpleadoResponseRest>(r, status); // devuelve 200

	}
	
    private String registraBitacora(HttpServletRequest request, MultiValueMap<String, String> headers) {
    	StringBuilder cabeceras = new StringBuilder();
		cabeceras.append(request.getRemoteHost());
		cabeceras.append(" - ");
		cabeceras.append(request.getLocalAddr());
		cabeceras.append(" - ");
		cabeceras.append(request.getRemoteAddr());

        headers.forEach((key, value) -> {
        	cabeceras.append(" - ");
        	cabeceras.append(String.format("Header '%s' = %s", key, value));
        });

        return cabeceras.toString();
	}

	@Override
	public ResponseEntity<JefeResponseRest> buscarJefe(Long numEmpleado, Integer desarrollo,
			HttpServletRequest request, MultiValueMap<String, String> headers, String url) throws JsonProcessingException {
		HttpStatus status = HttpStatus.OK;
		BitacoraRest bitacoraRest = new BitacoraRest();
		JefeResponseRest rest = new JefeResponseRest();
		ResponseJefeDTO dto = new ResponseJefeDTO();
		
		String mensaje = "Respuesta exitosa";
		String codigo = "200";
		String response = "Exito";
		
		try {
		SpBuscaEmpleado emp = empleadoRepository.spBuscaEmpleado(numEmpleado, desarrollo);
		bitacoraRest.setIp(request.getRemoteAddr());
		String log = registraBitacora(request, headers);
		bitacoraRest.setCabecera(log);
		bitacoraRest.setParametros("numeroEmpleado: "+numEmpleado+" desarrollo: "+desarrollo);
		bitacoraRest.setRequest("{}");
		bitacoraRest.setUrl(url);
		dto.setCorreo(emp.getCorreo_Jefe());
		dto.setIdJefeEmpleado(emp.getEpc_Superior_Id());
		
		} catch (Exception e) {
			bitacoraRest.setExcepcion(e.toString());
			mensaje = "Error al consultar al empleado.";
			codigo = "-1";
			response = "Error";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		ObjectMapper respuesta = new ObjectMapper();
		String resp = respuesta.writeValueAsString(dto);
		bitacoraRest.setResponse(resp);
		bitacoraRestRepository.save(bitacoraRest);
		
		rest.setResponseJefeDTO(dto);
		rest.setMetadata(response, codigo, mensaje);
		return new ResponseEntity<JefeResponseRest>(rest, status);
	}
	
}
