// Ultima Modificacion Reporte 22-11-2016
package libreria;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.Enumeration;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataTable;
import com.rational.test.ft.vp.ITestDataText;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Rectangle;

public class AccionControl extends RationalTestScript {

	private ITestDataTable tabla = null;
	private IWindow[] ventana = null;

	/**Método Imprime encabezado de un script técnico.
	 */
	public void ImpreEncabezadoScriptControl(Object[] Argumentos, String NombreScript) {
		System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-" );
		System.out.println("Inicio de script:" + NombreScript);
		System.out.println("Cantidad de argumentos:" + Argumentos.length);
		for (int i=0; i< Argumentos.length; i++) {
			System.out.println("Argumento " + i + ":=" + Argumentos[i]);	
		}
		System.out.println("-----------------------------------------------------------" );
	}
	
	/**Método Imprime resultado de un script técnico.
	 */
	public void ImpreResultadoScriptControl(String NombreScript, String Resultado) {
		System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-" );
		System.out.println("Fin de script:" + NombreScript);
		System.out.println("Resultado:" + Resultado);
		System.out.println("-----------------------------------------------------------" );
	}
	
	/**Método retorno el nombre del caso de prueba en ejecucion.
     * @params 
     * @return nombreCP
     */
	public static String getNombreCPControl() {
		// RationalTestScript.getTopScriptName();
		String nombre = getTopScript().getScriptCaller().getScriptName();
		if (!nombre.contains(".")) {
			return nombre;
		} else {
			String[] nombreCompleto = nombre.split("\\.");
			nombre = nombreCompleto[nombreCompleto.length-1];
			return null;
		}
	}
	/**Método retorno el nombre del paso correspondiente al caso de prueba de prueba en ejecucion.
     * @params 
     * @return nombreCP
     */
	public static String getNombrePasoControl() {
		// RationalTestScript.getTopScriptName();
		String nombre = getTopScriptName();
		if (!nombre.contains(".")) {
			return nombre;
		} else {
			String[] nombreCompleto = nombre.split("\\.");
			nombre = nombreCompleto[nombreCompleto.length-1];
			return nombre;
		}
	}

	/**Método para obtener la fila y la columna a la que pertenece el texto buscado. 
     * @params tablaRecibida, textoBuscar
     * @return int[] {fila, columna}
     */
	public int[] obtenerFilaColumnaTablaControl(TestObject tablaRecibida,
			String textoBuscar) {
		String texto = "";
		tabla = (ITestDataTable) tablaRecibida.getTestData("contents");
		for (int fila =1; fila < tabla.getRowCount(); fila++) {
			for (int columna = 1; columna < tabla.getColumnCount(); columna++) {
				if (tabla.getCell(fila, columna) != null) {
					texto = tabla.getCell(fila, columna).toString();
					if (texto.equalsIgnoreCase((textoBuscar))) {
						return new int[] { fila, columna };
					}
				}
			}
		}
		return null;
	}
	
	/**Método para obtener el valor en la fila y la columna recibida. 
	 * @params tablaRecibida, fila, columna
	 * @return string valor de la celda
	 * ss 15 03 2017
	 */
	public String obtenerValorEnFilaColumnaTablaControl(TestObject tablaRecibida,
			int fila, int columna) {
		String texto = "";
		tabla = (ITestDataTable) tablaRecibida.getTestData("contents");
		texto = tabla.getCell(fila, columna).toString();
		return texto;
	}
	
	/**Método para obtener la fila y la columna a la que pertenece el texto buscado. 
     * @params tablaRecibida, textoBuscar, 
     * @return int[] {fila, columna,indice}  
     */
	public int[] obtenerFilaColumnaObjCellControl(TestObject table, String nombreObjeto) {
		Enumeration<String> testDataTypes = table.getTestDataTypes().keys();

		while (testDataTypes.hasMoreElements()) {

			String testDataType = testDataTypes.nextElement();

			ITestData iData = table.getTestData(testDataType);

			System.out.println("Objeto buscado " + nombreObjeto);

			if (iData instanceof ITestDataTable) {
				ITestDataTable iDataTable = (ITestDataTable) table.getTestData(testDataType);

				int rows = iDataTable.getRowCount();

				for (int row = 0; row <= rows; row++) {
					for (int col = 0; col < 2; col++) {
						String cell ="";
						cell = iDataTable.getCell(row, col).toString();
						System.out.println(iDataTable.getPropertyKeys()[4]+iDataTable.getPropertyKeys()[5]+iDataTable.getPropertyKeys()[6]);
						System.out.println("Objeto leido " + cell);
						
						if (!cell.isEmpty()) {
							if (cell.equals((nombreObjeto))) {
								System.out.println("Objeto Encontrado");
								int index = (int) iDataTable.getRowIndex(atRow(row));
								int[] filaColumnaIndice = { row, col,index};
								return filaColumnaIndice;
							}
						}
					}
				}
			} else if (iData instanceof ITestDataText) {
				return null;
				// System.out.println(text + "Me imprimo\n\n");
			}
		}
		return null;
	}
	
	/**Método para obtener todo el contenido de una tabla, ya esté visible o no.  
     * @params table
     * 
     */

	public void imprimirDataTablaControl(TestObject table) {
		Enumeration<String> testDataTypes = table.getTestDataTypes().keys();

		while (testDataTypes.hasMoreElements()) {
			String testDataType = testDataTypes.nextElement();
			// System.out.println(testDataType);
			ITestData iData = table.getTestData(testDataType);

			if (iData instanceof ITestDataTable) {
				ITestDataTable iDataTable = (ITestDataTable) table
						.getTestData(testDataType);

				int rows = iDataTable.getRowCount();
				int cols = iDataTable.getColumnCount();

				// for (int col = 0; col < cols; col++) {
				// System.out.println(iDataTable.getColumnHeader(col));
				// System.out.println("\t\t");
				// }
				for (int row = 0; row < rows; row++) {
					for (int col = 0; col < cols; col++) {

						System.out.println(iDataTable.getCell(row, col));

					}
					System.out.println("\n");

				}
				System.out.println("\n");

			} else if (iData instanceof ITestDataText) {
				ITestDataText iText = (ITestDataText) iData;
				String text = iText.getText();
				System.out.println(text + "\n\n");
			}
		}

	}
	/**
	 * Método Bajar el Scrll de una ventana.
	 * 
	 * @params String
	 * @return boolean
	 */
	public boolean scrollDownVentanaControl(String tituloNavegador, int cantidadBajar) {
		ventana = RationalTestScript.getTopWindows();
		try {
			if (ventana != null) {
				for(IWindow unaVentana : ventana){
					System.out.println(unaVentana.getText());
					if(unaVentana.getText().contains(tituloNavegador)){
						//unaVentana.activate();
						for (int i = 0; i <= cantidadBajar; i++) {
							unaVentana.inputKeys("{ExtDown}");
						}
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
			e.printStackTrace();
			System.out.println("No bajo el scroll de la ventana con el titulo: "+tituloNavegador);
		}
		return false;
	}

	/**
	 * Método Capturar una pantalla
	 * @params String
	 * @return boolean
	 */
	public void CapturarPantallaControl(String filename, Rectangle area) {
		ventana = RationalTestScript.getTopWindows();
		try {
			BufferedImage capture = null;
			Robot robot = new Robot();
			capture = robot.createScreenCapture(area);
			FileOutputStream out = new FileOutputStream(filename);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(capture);
			out.flush();
			out.close();
		}
		catch (Exception e) {
			System.out.println("Exepcion ");
		}
	}
}
