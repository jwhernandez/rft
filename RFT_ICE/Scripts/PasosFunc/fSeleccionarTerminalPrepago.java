package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSeleccionarTerminalPrepagoHelper;

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
 * Script Name   : <b>fSeleccionarTerminalPrepago</b>
 * Description   : Permite seleccionar un terminal desde la lista de terminales en 
 * el configurador de productos para un servicio prepago
 * Precondicion: Estar en el configurador 
 * @since  2016/12/28
 * @author FF
 *  Ej. CP03 NA PREQA true  
 */
public class fSeleccionarTerminalPrepago extends fSeleccionarTerminalPrepagoHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] TerminarASelecionar;
		TerminarASelecionar = new String[3];

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
		
		TerminarASelecionar[2]= dpString("Tramite");
		TerminarASelecionar[0] = dpString("NombreTerminal");
		System.out.println("Terminal a agregar: " + TerminarASelecionar[0]);
		logInfo("Terminal a agregar: " + TerminarASelecionar[0]);
		callScript("Scripts.Comun.SeleccionarTerminalPrepago", TerminarASelecionar);
		
		if  (TerminarASelecionar[1].toString().equals("No Encontrado")){
			//MensError[0] = "Seleccionar terminal falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}
