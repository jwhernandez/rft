package Scripts.PasosFunc;
import Scripts.Comun.Ingresar_SIM_IMEI_Servicio_Admin;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fIngresar_SIM_IMEI_Servicio_AdminHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Descripcion     :Ingresar datos via Admin
 * @since  2016/09/14
 * Script Name   : <b>fIngresar_SIM_IMEI_Servicio_Admin</b>
 * Parametros 0) CPnn_CDi_Tj 1) NA 2) Ambiente 3) ErrorStop 4) Paso
 * Ej: CP76_CD1_T1 NA QA true 11
 * @author Sandra
 */
public class fIngresar_SIM_IMEI_Servicio_Admin extends fIngresar_SIM_IMEI_Servicio_AdminHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String[] DatosAdmin;
		DatosAdmin = new String[9];
		//Parámetros: 0) Devuelve OK o NOK 1) servicio (Prepago / Postpago) 2) nroPedido  3)Recibe tipo terminal
		// (Cliente / ICE) 4) servicio 5) imei 6) IMSI 7) SIM 8)tramite 
		
		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable

		String[] MensError;
		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		int i = Integer.parseInt(args[1].toString());
		DatosAdmin[1] = dpString("TipoPerfilCorrecto"); // Postpago - Prepago
		DatosAdmin[2] = getNroPedido();
		if (dpString("TerminalCteCheck"+i).equals("true"))
			DatosAdmin[3] = "Cliente"; // true indica terminal cliente
		else DatosAdmin[3] = "ICE";
		DatosAdmin[4] = dpString("NumeroServicio");
		DatosAdmin[5] = dpString("IMEI"+i); // IMEI
		DatosAdmin[6] = dpString("IMSI"+i); // IMSI	
		DatosAdmin[7] = dpString("SIM Correcta"); // SIM
		DatosAdmin[8] = dpString("Tramite");

		callScript(new Ingresar_SIM_IMEI_Servicio_Admin(), DatosAdmin);

		if  ((DatosAdmin[0].toString().equals("NOK"))){
			MensError[0] = "Ingresar Datos vía Admin falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		} else
		{
			setNroServicio(DatosAdmin[4].toString());
			
			int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj
			DatoSalida[1]= args[0].toString().substring(0, sLong-3);
			DatoSalida[2]=args[2].toString(); // nro ambiente
			DatoSalida[3]="T"+ getUltimoTramite()+"_NroServicio"; // Nombre variable // Se modifica de nrotramite a este valor de variable
			DatoSalida[4]=DatosAdmin[4].toString(); // Valor de la variable
			callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);

			infoPaso(sScriptName, Tipo.DATO,"NroServicio",getNroServicio());
		}
	}
}

