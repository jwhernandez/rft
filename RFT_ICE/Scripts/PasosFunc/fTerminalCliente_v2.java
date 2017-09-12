package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fTerminalCliente_v2Helper;
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
 * Descripción: Elimina Terminales promocionales y coloca terminal cliente en Y
  * Parámetros: 0) OK / NOK 1) TipoPerfilCorrecto (Postpago o Prepago) 
  * 2) Terminales = true (Tiene) o false (no tiene) 3) Tramite (No se usa)
  * Ültima modificación VC 08/11/2016
 */
public class fTerminalCliente_v2 extends fTerminalCliente_v2Helper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] TermCliente;
		TermCliente = new String[4];
		// 0) OK / NOK 1) TipoPerfilCorrecto (Postpago o Prepago) 
		// 2) Terminales = true (Tiene) o false (no tiene) 3) Tramite 

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

		TermCliente[1] = dpString("TipoPerfilCorrecto");
		TermCliente[2] = dpString("Terminales");
		TermCliente[3] = dpString("Tramite");
		callScript("Scripts.Comun.TerminalCliente", TermCliente);

		if  (TermCliente[0].toString().equals("NOK")){
			MensError[0] = "Eliminar terminal cliente falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

