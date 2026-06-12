package coral.pagos.com.empleados.app.util;

import java.sql.SQLException;

import org.hibernate.exception.SQLGrammarException;

public class Utilidades {

	public static String getErrorException(Throwable e) {
	    try {
	        if (e == null) {
	            return "Excepción desconocida (objeto null)";
	        }

	        // Recorre toda la cadena de causas buscando una SQLGrammarException
	        Throwable current = e;
	        while (current != null) {
	            if (current instanceof SQLGrammarException) {
	                SQLException sqlEx = ((SQLGrammarException) current).getSQLException();
	                if (sqlEx != null && sqlEx.getMessage() != null) {
	                    return sqlEx.getMessage();
	                }
	            }
	            current = current.getCause();
	        }

	        // Si no es SQLGrammarException, devuelve el mensaje general
	        return e.getMessage() != null ? e.getMessage() : e.toString();

	    } catch (Exception ex) {
	        return "Error al obtener el mensaje de la excepción: " + ex.toString();
	    }
	}
}
