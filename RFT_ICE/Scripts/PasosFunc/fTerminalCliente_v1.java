package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fTerminalCliente_v1Helper;
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
public class fTerminalCliente_v1 extends fTerminalCliente_v1Helper
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

		TermCliente[1] = "TipoPerfilCorrecto";
		TermCliente[2] = "Terminales";
		TermCliente[3] = "Tramite";
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

