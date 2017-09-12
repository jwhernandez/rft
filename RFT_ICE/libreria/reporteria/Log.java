package libreria.reporteria;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import libreria.AccionControl;
import libreria.utileria.Tipo;
import libreria.utileria.Utileria;

import java.util.Date;
import java.text.*;
import java.util.Locale;

/**
 * Descripcion	: Entidad que representa al reporte. 
 * Class Name   : <b>Log</b>
 * @author jrosario - Modificado x ssastre para agregar tipo dato, cambiar nombre y ubicacion de archivo, agregar tipo error
 * metodos 	public static void imprimirArchivo() 
 * 			public static void imprimirLog() 
 * 			public static void infoPasosControl(String nombreCaso, Tipo tipo,String info, String valor) 
 *			public static ArrayList<Reporte> getReporte()  
 *			public static void setReporte(ArrayList<Reporte> reporte)  
 * Estos metodos son invocados x accion
 */

public class Log extends AccionControl{

	static ArrayList<Reporte> reporte = new ArrayList<Reporte>();
	public static String nombreCP = "";

	/**
	 * Descripcion :Metodo para imprimir el reporte de la ejecución en Archivo
	 * Recibe un arreglo de tipos (Reporte) y adjunta las lineas por tipog numCaso, String pasoDesde, 
			String Modo, String TipoError, String MensajeError
	 * Imprime en el archivoRute identificada en este metodo
	 * 22/11/16 se agrega formato (string.format) a las lineas, se agrega numero de paso en infopasocotnrol
	 * y se evita imprimir vacios en infopasocontrol
	 * ***/
	 public void imprimirArchivo(String archivoRutaUsuario, String numCaso, String pasoDesde, 
			   String Modo, String TipoError, String MensajeError, String UltPaso, int iniMod, int finMod, Date dateini) {
		FileWriter fichero = null;
		PrintWriter pw = null;

		try {
			fichero = new FileWriter(archivoRutaUsuario, false);
			pw = new PrintWriter(fichero);
			String output=null;
			String specialchar = "'";
			
			System.out.println(" ");
			DateFormat dateFormat =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
			Date dateFinalizar= new Date();
			pw.println();
			pw.append("-----------------------------------------------------------------------------------------");
			pw.println();
			pw.append("Inicio del Informe del Caso Prueba y Conj Datos: "+numCaso + 
					" Desde paso: " + pasoDesde + " Modo:" + Modo);
			pw.println();
			pw.append("Modulo desde Paso : "+iniMod + 
					" Hasta paso: " + finMod );
			pw.println();
			pw.append("Hora de Inicio de la Ejecucion : "+ dateFormat.format(dateini)) ;
			pw.println();
			pw.append("-----------------------------------------------------------------------------------------");
			pw.println();

			pw.println();
			pw.println();
			pw.append("Informe de PASOS Ejecutados");
			pw.println();
			pw.append("-----------------------------------------------------------------------------------------");
			pw.println();
			for (Reporte linea : reporte) {
				if (linea.getTipo() == Tipo.PASO) {
					output = String.format ("%1$-50s %2$-30s %3$-30s"
							, specialchar + linea.getNombre() + specialchar
							, ""
							, specialchar + linea.getValor() + specialchar);
					pw.append(output);
					pw.println();
				}
			}
						
			pw.println();
			pw.println();
			pw.append("Informe de DATOS de salida");
			pw.println();
			pw.append("-----------------------------------------------------------------------------------------");
			pw.println();
			for (Reporte linea : reporte) {
				if (linea.getTipo() == Tipo.DATO) {
					output = String.format ("%1$-50s %2$-30s %3$-30s"
							, specialchar + linea.getNombre() + specialchar
							, specialchar + linea.getInfo()   + specialchar 
							, specialchar + linea.getValor()  + specialchar);
					pw.append(output);
					pw.println();
				}
			}

			pw.println();
			pw.println();
			pw.append("Informe de Bugs");
			pw.println();
			pw.append("-----------------------------------------------------------------------------------------");
			pw.println();
			for (Reporte linea : reporte) {
				if (linea.getTipo() == Tipo.ERROR) {
					output = String.format ("%1$-50s %2$-30s"
							, specialchar +linea.getNombre() + specialchar
							, specialchar +linea.getValor()  + specialchar);
					// specialchar + linea.getInfo()  + specialchar sacado el 28-11-2016
					pw.append(output);
					pw.println();
				}
			}
			
			pw.println();
			pw.append("-----------------------------------------------------------------------------------------");
			pw.println();
			pw.println("Tipo Parada         = " + TipoError );
			pw.println("Mensaje de Parada   = " + MensajeError);
			pw.println("Parada en paso	    = " + UltPaso);
			pw.println();
			pw.println();

			pw.append("-----------------------------------------------------------------------------------------");
			pw.println();
			pw.append("Fin del Informe del Caso Prueba_Conj Datos: "+numCaso);
			pw.println();
			pw.append("-----------------------------------------------------------------------------------------");
			pw.println();

			pw.println();
			pw.println();
			pw.append("Informe de Errores imprevistos de sistema");
			pw.println();
			pw.append("-----------------------------------------------------------------------------------------");
			pw.println();
			for (Reporte linea : reporte) {
				if (linea.getTipo() == Tipo.SYSERR) {
					output = String.format ("%1$-50s %2$-30s %3$-30s"
							, specialchar + linea.getNombre() + specialchar
							, specialchar + linea.getInfo()   + specialchar 
							, specialchar + linea.getValor()  + specialchar);
					pw.append(output);
					//pw.append(linea.getNombre() + ";" + linea.getInfo() + ";"
						//	+ linea.getValor() + ";");
					pw.println();
					pw.println();
				}
			}
			pw.println();
			pw.append("Hora de Finalizacion de la Ejecucion: " + dateFormat.format(dateFinalizar));
			pw.println();
			pw.append("-----------------------------------------------------------------------------------------");
			pw.println();
			pw.close();

		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
					Utileria.abrirArchivo(archivoRutaUsuario);

				} catch (IOException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Descripcion :Metodo para imprimir el reporte de la ejecución en Consola
	 * Recibe un arreglo de tipos (Reporte) y adjunta las lineas por tipo
	 ***/
	public static void imprimirLog() {

		for (Reporte liena : reporte) {
			if (liena.getTipo() == Tipo.PASO) {
				System.out.println((liena.getNombre() + ";" + liena.getInfo()
						+ ";" + liena.getValor() + ";"));

			}
		}
		System.out.println("DATOS");
		for (Reporte liena : reporte) {
			if (liena.getTipo() == Tipo.DATO) {
				System.out.println((liena.getNombre() + ";" + liena.getInfo()
						+ ";" + liena.getValor() + ";"));

			}
		}
	}

	// get y set de arrego Reporte
	public static ArrayList<Reporte> getReporte() {
		return reporte; 	}
	public static void setReporte(ArrayList<Reporte> reporte) {
		Log.reporte = reporte; 	}

	/**
	 * Descripcion : Metodo para acumular informacion para el reporte final.
	 * @param nombre, tipo, info, valor
	 */
	public static void infoPasosControl(String nombreCaso, Tipo tipo,
			String info, String valor) {
		Reporte eReporte = new Reporte(nombreCaso, tipo, info, valor);
		reporte.add(eReporte);
	}
}
