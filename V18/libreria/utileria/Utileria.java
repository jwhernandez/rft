package libreria.utileria;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utileria {

	public static ArrayList<String> textoLog = new ArrayList<>();
	private static String formatoFecha = "";
	private static Date fecha = new Date();
	private static SimpleDateFormat sdf = null;

	public static ArrayList<String> getTextoLog() {
		return textoLog;
	}

	public static void setTextoLog(String textoLog) {
		Utileria.textoLog.add(textoLog);
	}
	
	/**
	 * Método Genera una llave unica con el tiempo en dia, mes, anio, hora, minutos, segundos y si es
	 * PM ó AM. Tomando en cuenta el formato.
	 * 
	 * 
	 * @return String fecha. 
	 * @author Jairis Rosario. Consultor
	 */
	public static String llaveUnica() {
		fecha = new Date();
		formatoFecha = "YYYY-MM-dd hh-mm-ss a";
		sdf = new SimpleDateFormat(formatoFecha);
		return sdf.format(fecha);
	}
	
	/**
	 * Método Encargado de abrir cualquier tipo de archivo.
	 * @params String
	 * @return 
	 */
	public static void abrirArchivo(String nombreDeArchivoRuta) throws IOException {
		try {
			File archivo = new File(nombreDeArchivoRuta);
			Desktop.getDesktop().open(archivo);
		} catch (IllegalArgumentException e1) {
			e1.getStackTrace();
		}catch (Exception e2) {
			e2.getStackTrace();
			e2.printStackTrace();
		}
	}

}
