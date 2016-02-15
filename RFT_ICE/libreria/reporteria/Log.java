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

public class Log extends AccionControl {

	static ArrayList<Reporte> reporte = new ArrayList<Reporte>();
	private static Utileria utilieria = new Utileria();
	public static String nombreCP = "";

	/**
	 * Descripcion :Metodo para imprimir el reporte de la ejeccion
	 * correspondiente en un archivo .txt
	 * 
	 * ***/
	public static void imprimirArchivo() {
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		String archivoRuta = getCurrentProject().getLocation()+"/Resultados//Ejecucion "
				+ utilieria.llaveUnica() + ".txt";

		try {
			fichero = new FileWriter(archivoRuta, false);
			pw = new PrintWriter(fichero);

			pw.append("DATOS;");
			pw.println();
			for (Reporte linea : reporte) {
				if (linea.getTipo() == Tipo.DATO) {
					pw.append(linea.getNombre() + ";" + linea.getInfo() + ";"
							+ linea.getValor() + ";");
					pw.println();
				}
			}

			for (Reporte linea : reporte) {
				if (linea.getTipo() == Tipo.PASO) {
					pw.append(linea.getNombre() + ";" + linea.getInfo() + ";"
							+ linea.getValor() + ";");
					pw.println();
				}
			}

			pw.append("Errores;");
			pw.println();
			for (Reporte linea : reporte) {
				if (linea.getTipo() == Tipo.ERROR) {
					pw.append(linea.getNombre() + ";" + linea.getInfo() + ";"
							+ linea.getValor() + ";");
					pw.println();
				}
			}

			pw.close();
			

		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
					Utileria.abrirArchivo(archivoRuta);

				} catch (IOException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}

			}
		}

	}

	/**
	 * Descripcion :Metodo para imprimir el reporte de la ejeccion
	 * correspondiente en consola
	 * 
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

	/**
	 * @return the reporte
	 */
	public static ArrayList<Reporte> getReporte() {
		return reporte;
	}

	/**
	 * @param reporte
	 *            the reporte to set
	 */
	public static void setReporte(ArrayList<Reporte> reporte) {
		Log.reporte = reporte;
	}

	/**
	 * Descripcion : Metodo para acumular informacion para el reporte final.
	 * 
	 * @param nombre
	 * @param tipo
	 * @param info
	 * @param valor
	 */
	public static void infoPasosControl(String nombreCaso, Tipo tipo,
			String info, String valor) {
		Reporte eReporte = new Reporte(nombreCaso, tipo, info, valor);
		reporte.add(eReporte);
	}

}
