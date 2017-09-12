// Ult Modif SS 22-11-2016 Reporte 
package libreria;
import java.awt.Rectangle;

import libreria.reporteria.Log;
import libreria.utileria.Tipo;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.ITestObjectMethodState;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.value.managers.ScriptNameValue;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.DomainTestObject;
import com.rational.test.ft.object.interfaces.ITopWindow;	
import com.rational.test.ft.object.interfaces.TestObject;

import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description : Super class for script helper
 * Manejo de variable globales
 * Manejo de script de encabezado y pie de script // Ssastre
 * Manejo de errores
 * Manejo de terminación (OnTerminate) con impresion de reporte
 * Manejo de tabla de terminales
 * @author jrosario // modificaciones y agregados Ssastre
 * @since noviembre 20, 2015
 * Ultima modificacion 23-11-2016 para modificar el log
 *  ss 24-11-2016 para ajuste del log eliminación de carpeta Sistemas Externos
 *  ss feb 2017 agregado de variable global TipoUSR
 *  ss 20 feb 2017 agregado de variables apartado y cod postal
 *  ss 22 2 2017 se agrega variable tramite 	//ss 22022017 se agrega variable con valor por inicio NA. Para CM sera valor CM
 *  ult modif  ss 17 8 2017 se vuelve a nombre flexible el nombre del proyecto con prueba para bat
 */
public abstract class Accion extends RationalTestScript {
	AccionControl accionControl = new AccionControl();
	Log log = new Log();
	public boolean FLAG = true;
	// Definición de variables globales
	static String NroPedido;
	static String NroPedidoAnt;
	static String NroPedidoAntAnt;
	static String NroServicio;
	static String NroCtaFact;
	static String NroRED;
	static String NomListaEspecial;
	static String NomListaEspecial_SMS;
	static String NomAmbiente = "QA"; // Necesita un valor para cuando ejecuta fuera de casosprueba
	static String UsuarioAlt;
	static String ClaveAlt;
	static String Usuario;
	static String Clave;
	static String TipoUSR;
	static String NroPedidoDesc;
	static String IMSIDesc;
	static String IMSIVta;
	static String FechaCM;
	static String NroTramite;
	static String Tramite; 
	static String TaskIdCM;
	static String CodPostal;
	static String Apartado;
	static String NroCaso = "Generico"; // Necvesita un valor para cuando ejecuta fuera de casosprueba
	static String Modo; // Real o Simulación
	static int NroPaso;
	static int NroPasoIniModulo; // ss 19/04/2017 multimodulo
	static int NroPasoFinModulo; // ss 19/04/2017 multimodulo
	static String UltimoPasoOK;
	static String UltimoTramite;
	static String TipoError = "NA";
	static String ResultadoPaso = "NA"; // Ss se usa para logica condicionante
	static String MensajeError = "NA";
	static String ManejoError = "Central";
	static String LoginIf = "NOK";
	static String NroPasoDesde = "1"; // Necesita un valor para cuando ejecuta fuera de casosprueba
	static boolean EjecCond=false;
	static boolean EjecCondResult=true;
	static boolean ConsolaaFile=false;
	static boolean ActDPPasos=false;
	static boolean Re_EjecDesdeIni=false;
	DateFormat dateFormat =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	static Date dateInicio= new Date(); // ss 19/4/2017 se agrega fecha de inicio
	Tipo dato = Tipo.DATO;
	Tipo paso = Tipo.PASO;
	Tipo error = Tipo.ERROR;
	Tipo syserr = Tipo.SYSERR;
	
	public void ponerTexto(String texto) {
		System.out.println(texto);
		}

	// Métodos para colocar el encabezado y pie en cada script
	public void ImpreEncabezadoScript(Object[] Argumentos, String NombreScript) {
		accionControl.ImpreEncabezadoScriptControl(Argumentos, NombreScript);	}
	public void ImpreResultadoScript(String NombreScript, String Resultado) {
		accionControl.ImpreResultadoScriptControl( NombreScript, Resultado);	}
	// se agrega código para que sea uniforme el formato de salida
	public void imprimirArchivo(String archivoRutaUsuario, String numCaso, String pasoDesde,
			String modo, String tipoError, String mensajeError, int iniMod, int finMod, Date dateini) {
		String UltPaso = String.valueOf(getNroPaso()); // 28-11-2016
		log.imprimirArchivo(archivoRutaUsuario, numCaso, pasoDesde, modo, tipoError, mensajeError, UltPaso, iniMod, finMod, dateini);	}
	// 22-11-2016 se agrega código para que sea uniforme el formato de salida
	// 19/4/2017 se agrega inicio y fin de modulo
	
    // Método para buscar en la tabla de terminales un nombre de terminal
	public int[] obtenerFilaColumnaTabla(TestObject tablaRecibida,String textoBuscar) {
		return accionControl.obtenerFilaColumnaTablaControl(tablaRecibida,
				textoBuscar);	}

	// Metodo para buscar valor en tabla - ss 15 03 2017
	public String obtenerValorEnFilaColumnaTabla(TestObject tablaRecibida,
			int fila, int columna) {
		return accionControl.obtenerValorEnFilaColumnaTablaControl(tablaRecibida,
				fila, columna);	}
	
	public void imprimirDataTabla(TestObject table) {
		accionControl.imprimirDataTablaControl(table);	}
	public int[] obtenerFilaColumnaObjCell(TestObject table, String nombreObjeto) {
		return accionControl.obtenerFilaColumnaObjCellControl(table,nombreObjeto);	}
	public boolean scrollDownVentana(String tituloNavegador, int cantidadBajar) {
		return accionControl.scrollDownVentanaControl(tituloNavegador,cantidadBajar);	}
	public void CapturarPantalla(String filename, Rectangle area) {
		 accionControl.CapturarPantallaControl(filename, area) ;	}

	 // Descripcion : Metodo para acumular informacion para el reporte final.
	 //@param nombre, tipo , info, valor
	public static void infoPaso(String nombreCaso, Tipo tipo, String info, String valor) 
		{

		if (!valor.isEmpty()) // Evita imprimir valores vacios 22/11/16
		{
			String nombreScript = nombreCaso; // Debe Recibir el script name 22-11-2016
			nombreCaso="Paso " + String.format ("%1$4s" , String.valueOf(getNroPaso())); // Le agrega el número de paso 22/11/16
			if (tipo != Tipo.PASO) { // cuando no es un paso en nombreCaso viene el nombre del script
				nombreScript = nombreScript.replace("Scripts.", ""); 
				nombreScript = nombreScript.replace("Comun.", "");
				nombreScript = nombreScript.replace("PasosFunc.", "");
				nombreScript= nombreScript.replace("libreria.", "");
				nombreScript= nombreScript.replace("utileria.", "");
				nombreScript= nombreScript.replace("CasosPrueba.", "");
				nombreScript= nombreScript.replace("SistemasExternos.", "");
				nombreCaso=nombreCaso + "  -  " +nombreScript;
			} else 
			{ // Cuando es un paso en info viene el nombre del script 22-11-2016
				info = info.replace("Scripts.", ""); 
				info = info.replace("Comun.", "");
				info = info.replace("PasosFunc.", "");
				info= info.replace("libreria.", "");
				info= info.replace("utileria.", "");
				info= info.replace("CasosPrueba.", "");
				info= info.replace("SistemasExternos.", "");
				nombreCaso=nombreCaso + "  -  " +info;
				info = " " ;
			}
		}
		Log.infoPasosControl(nombreCaso, tipo, info, valor );	
		}

	// Descripcion : imprime informacion correspondiente al reporte en el en la consola.
	public static void imprimirLog() {
		Log.imprimirLog();	
	}

	public static String getNombreCP() {
		return AccionControl.getNombreCPControl();	}
	public static String getNombrePaso() {
		return AccionControl.getNombrePasoControl(); 	}

	// Seteo de variables set y get globales
	// Seteo del set y get de Variable global NroPedido y NroPedidoAnt y NroPedidoAntAnt
	public static void setNroPedido(String nroPedido) {
		NroPedido = nroPedido; 	}
	public static String getNroPedido() {
		return NroPedido;}
	public static void setNroPedidoAnt(String nroPedidoAnt) {
		NroPedidoAnt = nroPedidoAnt; 	}
	public static String getNroPedidoAnt() {
		return NroPedidoAnt;} 	
	public static void setNroPedidoAntAnt(String nroPedidoAntAnt) {
		NroPedidoAntAnt = nroPedidoAntAnt; 	}
	public static String getNroPedidoAntAnt() {
		return NroPedidoAntAnt;} 

	// Seteo del set y get de Variable global NroServicio
	public static void setNroServicio(String nroServicio) {
		NroServicio = nroServicio; 	}
	public static String getNroServicio() {
		return NroServicio; 	}
	
	// Seteo del set y get de Variable global NroCtaFact
	public static void setNroCtaFact(String nroCtaFact) {
		NroCtaFact = nroCtaFact; 	}
	public static String getNroCtaFact() {
		return NroCtaFact; 	}
	
	// Seteo del set y get de Variable global NroRED
	public static void setNroRED(String nroRED) {
		NroRED = nroRED; 	}
	public static String getNroRED() {
		return NroRED; 	}
	
	// Seteo del set y get de Variable global NomListaEspecial
	public static void setNomListaEspecial(String nomListaEspecial) {
		NomListaEspecial = nomListaEspecial; 	}
	public static String getNomListaEspecial() {
		return NomListaEspecial; 	}
	public static void setNomListaEspecial_SMS(String nomListaEspecial_SMS) {
		NomListaEspecial_SMS = nomListaEspecial_SMS; 	}
	public static String getNomListaEspecial_SMS() {
		return NomListaEspecial_SMS; 	}
	
	// Seteo del set y get de Variable global NomAmbiente
	public static void setNomAmbiente(String nomAmbiente) {
		NomAmbiente = nomAmbiente; 	}
	public static String getNomAmbiente() {
		return NomAmbiente; 	}
	
	// Seteo del set y get de Variable global Usuario alternativo y original
	public static void setUsuarioAlt(String usuarioAlt) {
		UsuarioAlt = usuarioAlt; 	}
	public static String getUsuarioAlt() {
		return UsuarioAlt; 	}
	public static void setClaveAlt(String claveAlt) {
		ClaveAlt = claveAlt; 	}
	public static String getClaveAlt() {
		return ClaveAlt; 	}
	
	// Variable ResultadoCaso
	public static void setResultadoPaso(String resultadoPaso) {
		ResultadoPaso = resultadoPaso; 	}
	public static String getResultadoPaso() {
		return ResultadoPaso; 	}
	
	public static void setUsuario(String usuario) {
	Usuario = usuario; 	}
	public static String getUsuario() {
		return Usuario; 	}
	public static void setClave(String clave) {
	Clave = clave; 	}
	public static String getClave() {
		return Clave; 	}
	
	public static void setTipoUSR(String tipoUSR) {
		TipoUSR = tipoUSR; 	}
	public static String getTipoUSR() {
		return TipoUSR; 	}
	
	// Seteo del set y get de Variables de CM: NroPedidoDesc, IMSIDesc,IMSIVta
	public static void setNroPedidoDesc(String nroPedidoDesc) {
		NroPedidoDesc = nroPedidoDesc; 	}
	public static String getNroPedidoDesc() {
		return NroPedidoDesc; 	}
	public static void setIMSIDesc(String imsiDesc) {
		IMSIDesc = imsiDesc; 	}
	public static String getIMSIDesc() {
		return IMSIDesc; 	}
	public static void setIMSIVta(String imsiVta) {
		IMSIVta = imsiVta; 	}
	public static String getIMSIVta() {
		return IMSIVta; 	}
	
	public static void setNroPasoIniModulo(int nroPasoIniModulo) {
		NroPasoIniModulo = nroPasoIniModulo; 	}
	public static int getNroPasoIniModulo() {
		return NroPasoIniModulo; 	}
	public static void setNroPasoFinModulo(int nroPasoFinModulo) {
		NroPasoFinModulo = nroPasoFinModulo; 	}
	public static int getNroPasoFinModulo() {
		return NroPasoFinModulo; 	}
	
 	// Seteo del set y get de la fecha de CM y task id
	public static void setFechaCM(String fechaCM) {
		FechaCM = fechaCM; 	}
	public static String getFechaCM() {
		return FechaCM; 	}
	public static void setTaskIdCM(String taskIdCM) {
		TaskIdCM = taskIdCM; 	}
	public static String getTaskIdCM() {
		return TaskIdCM; 	}
	
	// Seteo del set y get de Cod Postal y Apartado
	public static void setApartado(String apartado) {
		Apartado = apartado; 	}
	public static String getApartado() {
		return Apartado; 	}
	public static void setCodPostal(String codPostal) {
		CodPostal = codPostal; 	}
	public static String getCodPostal() {
		return CodPostal; 	}
	
	// Seteo de variables de control del caso
	public static void setNroCaso(String nroCaso) {
		NroCaso = nroCaso; 	}
	public static String getNroCaso() {
		return NroCaso; 	}
	public static void setModo(String modo) {
		Modo = modo; 	}
	public static String getModo() {
		return Modo; 	}
	public static void setNroPaso(int nroPaso) {
		NroPaso = nroPaso; 	}
	public static int getNroPaso() {
		return NroPaso; 	}
	public static void setNroPasoDesde(String nroPasoDesde) {
		NroPasoDesde = nroPasoDesde; 	}
	public static String getNroPasoDesde() {
		return NroPasoDesde; 	}
	public static void setNroTramite(String nroTramite) {
		NroTramite = nroTramite; 	}
	public static String getNroTramite() {
		return NroTramite; 	}
	
	public static void setTramite(String tramite) {
		Tramite = tramite; 	}
	public static String getTramite() {
		return Tramite; 	}
	
	public static void setUltimoPasoOk(String ultimoPasoOK) {
		UltimoPasoOK = ultimoPasoOK; 	}
	public static String getUltimoPasoOk() {
		return UltimoPasoOK; 	}
	public static void setUltimoTramite(String ultimoTramite) {
		UltimoTramite = ultimoTramite; 	}
	public static String getUltimoTramite() {
		return UltimoTramite; 	}
	
	public static void setTipoError(String tipoError) {
		TipoError = tipoError; 	}
	public static String getTipoError() {
		return TipoError; 	}
	public static void setMensajeError(String mensajeError) {
		MensajeError = mensajeError; 	}
	public static String getMensajeError() {
		return MensajeError; 	}	
	public static void setLoginIf(String loginIf) {
		LoginIf = loginIf; 	}
	public static String getLoginIf() {
		return LoginIf; 	}	
	public static void setManejoError(String manejoError) {
		ManejoError = manejoError; 	}
	public static String getManejoError() {
		return ManejoError; 	}	
	public static void setEjecCond(boolean ejecCond) {
		EjecCond = ejecCond; 	}
	public static boolean getEjecCond() {
		return EjecCond; 	}
	public static void setEjecCondResult(boolean ejecCondResult) {
		EjecCondResult = ejecCondResult; 	}
	public static boolean getEjecCondResult() {
		return EjecCondResult; 	}
	public static void setConsolaaFile(boolean consolaaFile) {
		ConsolaaFile = consolaaFile; 	}
	public static boolean getConsolaaFile() {
		return ConsolaaFile; 	}
	public static void setActDPPasos(boolean actDPPasos) {
		ActDPPasos = actDPPasos; 	}
	public static boolean getActDPPasos() {
		return ActDPPasos; 	}
	public static void setRe_EjecDesdeIni(boolean re_EjecDesdeIni) {
		Re_EjecDesdeIni = re_EjecDesdeIni; 	}
	public static boolean getRe_EjecDesdeIni() {
		return Re_EjecDesdeIni; 	}

	// Manejo de fin de ejecución
	@Override
	public void onTerminate() {
		boolean mainscript = getTopScript().isMainScript();
		if (mainscript) {
			// Se genera el reporte TXT
//			String[] Logout;
//			Logout = new String[1];
//			callScript("Scripts.Comun.Logout", Logout);
			imprimirLog();
			String archivo = getCurrentProject().getLocation();
			String Name =  getCurrentProject().getName();
			archivo = archivo.replace(Name,Name+"_USR"); 
			archivo = archivo + "//Resultados//" + 
			getNomAmbiente()+ "//" + getNroCaso() +"_" +getNroPasoDesde() + ".txt";
			imprimirArchivo(archivo, getNroCaso(),getNroPasoDesde(), getModo(), getTipoError(), getMensajeError() 
					, getNroPasoIniModulo(), getNroPasoFinModulo(), dateInicio);
			}
		super.onTerminate(); 	}

	
	// Excepción no esperada
	@Override
	public boolean onUnhandledException(Throwable e) {
		if (!getManejoError().equals("Controlado")) // Solo se procesan las excepciones no esperadas 
		{
			String sMensajeError;
			String sScriptName;
			sScriptName = getScriptName().replace("Scripts.", "").replace("PasosFunc.","").replace("Comun.","").replace("SistemasExternos.","").replace("CasosPrueba.","");
			sMensajeError = "Script=" + sScriptName;
			sMensajeError = sMensajeError + " Línea=" + Integer.toString(getLineNumber());
			sMensajeError = sMensajeError + " Error=" + e.getMessage();

			infoPaso("Accion", Tipo.SYSERR,"Excepción no esperada", sMensajeError);
			System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			System.out.println("Excepción no esperada=" + sMensajeError);
			e.printStackTrace();
			System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

			if (getTipoError().equals("NA")) setTipoError("Excepción no esperada");
			if (getMensajeError().equals("NA")) setMensajeError(sMensajeError);
		}
		return super.onUnhandledException(e);	}

	// Excepción en Método
	@Override
	public void onTestObjectMethodException
	(ITestObjectMethodState testObjectMethodState, TestObject foundObject) {
		if (!getManejoError().equals("Controlado")) // Solo se procesan las excepciones no esperadas 
		{
			String sMensajeError;
			String sScriptName;
			sScriptName = getScriptName().replace("Scripts.", "").replace("PasosFunc.","").replace("Comun.","").replace("SistemasExternos.","").replace("CasosPrueba.","");
			sMensajeError = "Script=" + sScriptName;
			sMensajeError = sMensajeError + " Línea=" + Integer.toString(getLineNumber());
			sMensajeError = sMensajeError + " Método=" + testObjectMethodState.getMethod();
			sMensajeError = sMensajeError + " Error=" + testObjectMethodState.getThrowableMessage();
			sMensajeError = sMensajeError + " Clase=" + testObjectMethodState.getThrowableClassName();
			sMensajeError = sMensajeError + " Objeto=" + testObjectMethodState.getTestObject().toString() ;
			infoPaso("Accion", Tipo.SYSERR,"Excepción en Método", sMensajeError);

			System.out.println("##################################################################");
			System.out.println("getThrowableclassname " + sMensajeError);
			System.out.println("##################################################################");

			if (getTipoError().equals("NA")) setTipoError("Excepción en Método");
			if ( getMensajeError().equals("NA")) setMensajeError(sMensajeError);
		}
    }

	// Objeto no encontrado
	@Override
	public void onObjectNotFound(ITestObjectMethodState testObjectMethodState) {
		System.out.println("[onObjectNotFound]" + testObjectMethodState.toString());	
		infoPaso("Accion", Tipo.SYSERR,"Objeto no encontrado", testObjectMethodState.toString()  );
		if (getTipoError().equals("NA")) setTipoError("Objeto no encontrado");
		if ( getMensajeError().equals("NA")) setMensajeError(testObjectMethodState.toString()  );
		
		
		super.onObjectNotFound(testObjectMethodState);	}

	// Reconocimiento ambiguo 
	@Override
	public void onAmbiguousRecognition(ITestObjectMethodState testObjectMethodState, 
			TestObject[] choices,int[] scores) 
	{
		TestObject primerObjeto = null;
		for (int i = 0; i < choices.length; i++) {
			primerObjeto = choices[i];
			System.out.println("[[Reconocimiento Ambiguo]]"+i + primerObjeto.getProperties());	
			infoPaso("Accion", Tipo.SYSERR,"Reconocimiento Ambiguo", testObjectMethodState.toString()  );
			infoPaso("Accion", Tipo.SYSERR,"Reconocimiento Ambiguo", primerObjeto.getProperty(".text").toString()  );
			if  (getTipoError().equals("NA")) setTipoError("Reconocimiento Ambiguo");
			if (getMensajeError().equals("NA")) setMensajeError(primerObjeto.getProperty(".text").toString() );

		}

		// La siguiente instrucción (de Jairis) no debe funcionar porque no resuelve el problema
		testObjectMethodState.setFoundTestObject(choices[0]);
	}
	
}
