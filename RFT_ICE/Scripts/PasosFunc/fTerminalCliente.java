package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fTerminalClienteHelper;
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
 * Parámetros: 0)Prepago/Postpago 1) Encontrado / No encontrado
 */
public class fTerminalCliente extends fTerminalClienteHelper
{
	public void testMain(Object[] args) 
	{
		String[] TermCliente;
		TermCliente = new String[2];

		String[] MensError;
		MensError = new String[4];

		TermCliente[0] = "Postpago";
		callScript("Scripts.Comun.TerminalCliente", TermCliente);

		if  (TermCliente[1].toString().equals("No Encontrado")){
			MensError[0] = "Eliminar terminal cliente falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

