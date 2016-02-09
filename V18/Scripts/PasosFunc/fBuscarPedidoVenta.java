package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fBuscarPedidoVentaHelper;
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
 * Script Name   : <b>BuscarPedidoVenta</b>
 * @author Sandra
 * @since  2016/01/19
 */
public class fBuscarPedidoVenta extends fBuscarPedidoVentaHelper
{
	public void testMain(Object[] args) 
	{
//		String[] MensError;
//		MensError = new String[4];
		
		String[] BuscPedVta;
		BuscPedVta = new String[1];

		BuscPedVta[0]= getNroPedido();
		System.out.println("NroPedido: " + getNroPedido());
		logInfo("NroPedido: " + getNroPedido() );
		
		callScript("Scripts.Comun.BuscarPedidoVenta", BuscPedVta);

//		if (BuscPedVta[1].toString().equals("NOK")) {
//			MensError[0] = "Pedido Venta no encontrado";
//						MensError[0] = "xDefecto";
//		MensError[1] = args[3].toString();
//		MensError[2] = args[0].toString();
//		MensError[3] = getScriptName( );
//			callScript("Scripts.Comun.TerminarCasoError", MensError);
//		}
	}
}

