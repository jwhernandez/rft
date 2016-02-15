package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fLeerEstadoPedidoHelper;
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
 * Script Name   : <b>fChequearEstadoRED</b>
 * Description   : Chequea el valor del campo estado del RED
 * @since  2015/12/27
 * @author SS
 */
public class fLeerEstadoPedido extends fLeerEstadoPedidoHelper
{
	public void testMain(Object[] args) 
	{
		String[] MensError;
		MensError = new String[4];

		String[] Estado;
		Estado = new String[1];			

		// Paso - Chequear estado
		callScript("Scripts.Comun.LeerEstadoPedido",Estado);
		if (!(Estado[0].equals("Pendiente de pago RED"))) {
			MensError[0] = "Red no pendiente de pago";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);	
		}
	}
}

