package Scripts.Comun;
import resources.Scripts.Comun.BuscarPedidoVentaPIenListaHelper;
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
* Descripción: Permite buscar el pedido recibido como parametro  en la lista de pedidos de portin
* No va al detalle del pedido
* Parámetros:  0)NroPedido 1) OK/NOK 2)Ambiente 3) usuario (NA o Frontal)
* ej res 1-1701155949  QA Frontal
* Script Name   : <b>BuscarPedidoVentaPIenLista</b>
* @since  2017/03/09
* @author SS
* Condiciones: Debe estar en Siebel en cualquier pantalla
*/
public class BuscarPedidoVentaPIenLista extends BuscarPedidoVentaPIenListaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="NOK";
		String sAmbiente ="QA";
		if (!argu[2].toString().equals("NA")) sAmbiente = argu[2].toString();

		String sUsuario = argu[3].toString().toLowerCase();
		
		Pestañas().gotoScreen("Port Order Screen");
		
		if (sAmbiente.equals("QA") && !sUsuario.equals("frontal"))
		{
			sleep(3);
			try {
				TabsPedidoPortabilidad().goTo("ICE Order Entry - All Orders View (Port)","L2");}
			catch (Exception e) {
				logInfo("Mensaje de excepción: "+e.getMessage());
				System.out.println("Mensaje de excepción: "+e.getMessage());
				sleep(1);
				TabsPedidoPortabilidad().goTo("ICE Order Entry - All Orders View (Port)","L2");}
			sleep(3);
			NewQueryAllOrdersAdmin().performAction();

			NroPedidoAllOrdersAdmin().setText(argu[1].toString());
			ExecuteQueryAllOrdersAdmin().performAction();
			sleep(5);
			argu[0]="OK";
		}
		else
			if (sAmbiente.equals("QA") && sUsuario.equals("frontal"))
			{
				System.out.println("Se ingreso a QA Frontal");
				sleep(3);
				try {
				TabsPedidoPortabilidad().goTo("ICE Order Entry - All Orders View (Port)","L2");}
				catch (Exception e) {
					logInfo("Mensaje de excepción: "+e.getMessage());
					System.out.println("Mensaje de excepción: "+e.getMessage());
					sleep(1);
					TabsPedidoPortabilidad().goTo("ICE Order Entry - All Orders View (Port)","L2");}
				sleep(3);
				NewQueryAllOrders().performAction();
				NroPedidoAllOrders().setText(argu[1].toString());
				ExecuteQueryAllOrders().performAction();
				sleep(5);
				argu[0]="OK";
			} else argu[0]="NOK";
 
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

