package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fExpandirActivoHelper;
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
 *  Script Name   : <b>fExpandirActivo</b>
 * Description   : Functional Test Script
 * @since  2015/12/28
 * @author Sandra
 */
public class fExpandirActivo extends fExpandirActivoHelper
{
	public void testMain(Object[] args) 
	{
		String[] Expandir;
		Expandir = new String[1];

		//		String[] MensError;
		//		MensError = new String[4];			

		callScript("Scripts.Comun.ExpandirActivo",Expandir);

		//			if (EnviarPedido[0].toString().equals("No creado")) {	
		//				/S/MensError[0] = "Problema al enviar el pedido";
		//		MensError[0] = "xDefecto";
		//		MensError[1] = args[3].toString();
		//		MensError[2] = args[0].toString();
		//		MensError[3] = getScriptName( );
		//				callScript("Scripts.Comun.TerminarCasoError", MensError);
		//			}
	}
}

