package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSeleccionarProxNumHelper;

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
 * Script Name   : <b>fSeleccionarProxNum</b>
 * Description   : Permite seleccionar el boton de proximo numero 
 * @since  201/12/28
 * @author FF
 * Modificacion  01/02/2017 
 */

public class fSeleccionarProxNum extends fSeleccionarProxNumHelper
{

	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] SelInforme;
		SelInforme = new String[2];
		//Parámetros: 0) NOK / OK 1) Opción a imprimir

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		callScript("Scripts.Comun.SeleccionarProxNum", SelInforme);

		if  ((SelInforme[0].toString().equals("NOK"))){
			//MensError[0] = "Asignación de Lista Especial tuvo error";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}
