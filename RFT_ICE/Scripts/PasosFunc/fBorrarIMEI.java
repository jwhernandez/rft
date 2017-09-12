package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBorrarIMEIHelper;

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
 * Descripcion     : Borrar un IMEI
 * @param 0) numero de caso 1)digito iterador en el DP 2) ambiente 
 * 3) para o no para error 4) Nro de paso
 * 
 * Pre-condiciones : Estar en la vista del pedido. No hace falta estar en el producto.
 * Toma del DP el servicio a buscar de parametria
 * ej CP46 4 PREQA true
 * VC 15/12/2016
 * FF 29/12/2016 Se instancia el datapool de datos de entrada, con su correspondiente mapa de objetos de Siebel
 */
public class fBorrarIMEI extends fBorrarIMEIHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] argsIMEI;
		argsIMEI = new String[5];
		// Parámetros: 0) Postpago / Prepago 1) Servicio / "TerminalesPromocionales" 
		// o Terminal  2) IMEI 3) No Encontrado o Encontrado 4)Tramite
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());
		argsIMEI[0] = dpString("TipoPerfilCorrecto");
		argsIMEI[1] = dpString("IMEI_Lugar" +i ); // "Servicio"
		argsIMEI[3] = dpString("Tramite"); 

		callScript("Scripts.Comun.BorrarIMEI",argsIMEI);
		
		if  ((argsIMEI[2].toString().toLowerCase().equals("no Encontrado"))){
			MensError[0] = "Error al borrar IMEI";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}
