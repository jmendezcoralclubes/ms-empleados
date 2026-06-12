package coral.pagos.com.empleados.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import coral.pagos.com.empleados.app.dto.request.entity.EmpleadoRequest;
import coral.pagos.com.empleados.app.dto.response.object.EmpleadoResponseRest;
import coral.pagos.com.empleados.app.dto.response.object.JefeResponseRest;
import coral.pagos.com.empleados.app.service.EmpleadosService;

@RestController
@RequestMapping("/apiCoral")
public class EmpleadoController {
	
	@Autowired
	private EmpleadosService empleadosService;
	
	@PostMapping(value = "/empleado")
	public ResponseEntity<EmpleadoResponseRest> buscarEmpleado(@Valid @RequestBody EmpleadoRequest empleadoRequest, BindingResult result,
			HttpServletRequest request, @RequestHeader MultiValueMap<String, String> headers) throws JsonProcessingException {
		return empleadosService.buscarEmpleado(empleadoRequest, result, request, headers, "POST: /apiCoral/empleado");
	}
	
	@GetMapping(value = "/empleado")
	public ResponseEntity<JefeResponseRest> buscarJefe(@RequestParam(name = "empleado") Long numEmpleado, @RequestParam(name = "desarrollo") Integer desarrollo,
			HttpServletRequest request, @RequestHeader MultiValueMap<String, String> headers) throws JsonProcessingException {
		return empleadosService.buscarJefe(numEmpleado, desarrollo, request, headers, "GET: /apiCoral/empleado");
	}

}
