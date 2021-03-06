package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscarProductoParcHelper;
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
 * Description   : Functional Test Script
 * @author rft
 */
public class fBuscarProductoParc extends fBuscarProductoParcHelper
{
	/**
	 * Script Name   : <b>fBuscarProductoParc</b>
	 * Description   : Busca el descuentp indicado en el argumento
	 * @Param 0) Nombre del caso 1) indice que indica el valor a usar en el DP
	 * 2) Ambiente 3) true / false para reportar error 4) nro paso
	 * @since  2017/05/02
	 * @author VC
	 */
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] BuscarDesc;
		BuscarDesc = new String[6];
		//Parámetros: 0) Nombre del descuento 1) Encontrado/No Encontrado 2)posicion y 
		// 3)action code 4) desde la linea o no (Si No) 5)Tramite 
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		int i = Integer.parseInt(args[1].toString());	
		BuscarDesc[0] = dpString("PROD"+i); // No Caller Id
		BuscarDesc[4] = "Si"; // Que busque desde el principio 	
		if (dpString("BuscaDesdeIni"+i)!=null && 
				dpString("BuscaDesdeIni"+i).toLowerCase().equals("no")) 
			BuscarDesc[4] = "No";

		BuscarDesc[5] = dpString("Tramite"); 	
		
		callScript("Scripts.Comun.BuscarProductoParc", BuscarDesc);

		if  ((BuscarDesc[1].toString().equals("No Encontrado"))){
			//MensError[0] = "Descuento no se encontró";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}