package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSeleccionarTerminalHelper;
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
 * Script Name   : <b>fSeleccionarTerminal</b>
 * Description   : Permite seleccionar un terminal desde la lista de terminales en 
 * el configurador de productos
 * Precondicion: Estar en el configurador 
 * @since  2015/12/28
 * @author Sandra
 */
public class fSeleccionarTerminal extends fSeleccionarTerminalHelper
{
	public void testMain(Object[] args) 
	{
		String[] TerminarASelecionar;
		TerminarASelecionar = new String[1];

//		String[] MensError;
//		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		TerminarASelecionar[0] = dpString("NombreTerminal");
		System.out.println("Terminal a agregar: " + TerminarASelecionar[0]);
		logInfo("Terminal a agregar: " + TerminarASelecionar[0]);
		callScript("Scripts.Comun.SeleccionarTerminal", TerminarASelecionar);
		
//		if  (TerminarASelecionar[1].toString().equals("No Encontrado")){
//			MensError[0] = "Seleccionar terminal fall�";
//		//MensError[0] = "xDefecto";
//		MensError[1] = args[3].toString();
//		MensError[2] = args[0].toString();
//		MensError[3] = getScriptName( );
//			callScript("Scripts.Comun.TerminarCasoError", MensError);
//		}
	}
}

