package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fEnviarPedidoHelper;
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
 * Script Name   : <b>fEnviarPedido</b>
 * Descripcion   : Envia el pedido
 * @author SS
 * @Param @Param 0)IN nombre del caso 1) Estado a esperar 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * CP05 Completar PREQA true
 * @since  2015/12/27
 * Precondiciones Estar en la pantalla del pedido, estado del pedido en pendiente
 */
public class fEnviarPedido extends fEnviarPedidoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] EnviarPedido;
		EnviarPedido = new String[2];
		//  0) Creado / No creado 1) Tramite
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		EnviarPedido[1] = dpString("Tramite"); 	
		callScript("Scripts.Comun.EnviarPedido", EnviarPedido);
		
		if (EnviarPedido[0].toString().equals("No creado")) {	
			//MensError[0] = "Problema al enviar el pedido";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

