package libreria;

import libreria.reporteria.Log;
import libreria.utileria.Tipo;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.ITestObjectMethodState;
import com.rational.test.ft.script.RationalTestScript;

/**
 * Description : Super class for script helper
 * 
 * @author jrosario
 * @since noviembre 20, 2015
 */
public abstract class Accion extends RationalTestScript {
	AccionControl accionControl = new AccionControl();
	Log log = new Log();
	public boolean FLAG = true;
	static String NroPedido;
	static String NroServicio;
	static String NroCtaFact;
	static String NomListaEspecial;
	Tipo dato = Tipo.DATO;
	Tipo paso = Tipo.PASO;
	Tipo error = Tipo.ERROR;

	public void ponerTexto(String texto) {
		System.out.println(texto);
	}

	public int[] obtenerFilaColumnaTabla(TestObject tablaRecibida,
			String textoBuscar) {
		return accionControl.obtenerFilaColumnaTablaControl(tablaRecibida,
				textoBuscar);

	}

	public void imprimirDataTabla(TestObject table) {
		accionControl.imprimirDataTablaControl(table);

	}

	public int[] obtenerFilaColumnaObjCell(TestObject table, String nombreObjeto) {
		return accionControl.obtenerFilaColumnaObjCellControl(table,
				nombreObjeto);
	}

	public boolean scrollDownVentana(String tituloNavegador, int cantidadBajar) {

		return accionControl.scrollDownVentanaControl(tituloNavegador,
				cantidadBajar);
	}

	/**
	 * Descripcion : Metodo para acumular informacion para el reporte final.
	 * @param nombre
	 * @param tipo
	 * @param info
	 * @param valor
	 */
	public static void infoPaso(String nombreCaso, Tipo tipo, String info, String valor) {
		Log.infoPasosControl(nombreCaso, tipo, info, valor);
	}

	/**
	 * Descripcion : imprime informacion correspondiente al reporte en el en la
	 * consola.
	 */

	public static void imprimirLog() {
		Log.imprimirLog();
	}

	/**
	 * @return el nombre del caso de prueba.
	 */

	public static String getNombreCP() {
		return AccionControl.getNombreCPControl();
	}

	/**
	 * @return el nombrePaso
	 */
	public static String getNombrePaso() {
		return AccionControl.getNombrePasoControl();
	}

	/**
	 * @return el nroPedido
	 */
	public static String getNroPedido() {
		return NroPedido;
	}

	/**
	 * @param nroPedido
	 *            el nroPedido a establecer
	 */
	public static void setNroPedido(String nroPedido) {
		NroPedido = nroPedido;
	}

	/**
	 * @return el nroServicio
	 */
	public static String getNroServicio() {
		return NroServicio;
	}

	/**
	 * @param nroServicio
	 *            el nroServicio a establecer
	 */
	public static void setNroServicio(String nroServicio) {
		NroServicio = nroServicio;
	}
	
	/**
	 * @return el nroPedido
	 */
	public static String getNroCtaFact() {
		return NroCtaFact;
	}

	/**
	 * @param nroCtaFac
	 *            el nroCtaFact a establecer
	 */
	public static void setNroCtaFact(String nroCtaFact) {
		NroCtaFact = nroCtaFact;
	}
	/**
	 * @return el nomListaEspecial
	 */
	public static String getNomListaEspecial() {
		return NomListaEspecial;
	}

	/**
	 * @param nomListaEspecial
	 *            el nomListaEspecial a establecer
	 */
	public static void setNomListaEspecial(String nomListaEspecial) {
		NomListaEspecial = nomListaEspecial;
	}
	
	@Override
	public boolean onUnhandledException(Throwable e) {
		// TODO Apéndice de método generado automáticament
		// infoPaso(getNombrePaso(), "FALLIDO");
		return super.onUnhandledException(e);
	}

	@Override
	public void onObjectNotFound(ITestObjectMethodState testObjectMethodState) {
		// TODO Apéndice de método generado automáticamente
		//infoPaso(getNombrePaso(), "FALLIDO");
		super.onObjectNotFound(testObjectMethodState);
	}

	@Override
	public void onTerminate() {
		// TODO Apéndice de método generado automáticamente
		boolean mainscript = getTopScript().isMainScript();
		if (mainscript) {
			imprimirLog();
			Log.imprimirArchivo();

		}
		super.onTerminate();
	}

	@Override
	public void onAmbiguousRecognition(
			ITestObjectMethodState testObjectMethodState, TestObject[] choices,
			int[] scores) {

		TestObject primerObjeto = null;
		for (int i = 0; i < choices.length; i++) {
			primerObjeto = choices[i];
			System.out.println("[[Reconocimiento Ambiguo]]"
					+ primerObjeto.getProperty(".text"));
			// comparar texto con el datapool de sandra.

		}
		testObjectMethodState.setFoundTestObject(choices[0]);
	}
}
