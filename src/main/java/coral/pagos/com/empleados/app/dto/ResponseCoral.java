package coral.pagos.com.empleados.app.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResponseCoral {
	private List<HashMap<String, String>> response = new ArrayList<>();

	public List<HashMap<String, String>> getResponse() {
		return response;
	}

	public void setMetadata(String status, String codigo, String respuesta) {
		HashMap<String, String> mapa = new HashMap<String, String>();
		mapa.put("status", status);
		mapa.put("codigo", codigo);
		mapa.put("respuesta", respuesta);
		response.add(mapa);
	}
}
