package Scripts.Comun;
import resources.Scripts.Comun.BuscarPedidoVentaPIHelper;
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
* Descripción: Permite buscar el pedido recibido como parametro e ir al detalle
* Parámetros:  0)NroPedido 1) OK/NOK 2)Ambiente 3) usuario (NA o Frontal)
* ej 1-1750546749 res QA Frontal
* Script Name   : <b>BuscarPedidoVentaPI</b>
* SS Nov 2015
* Condiciones: Debe estar en Siebel en cualquier pantalla
*/
public class BuscarPedidoVentaPI extends BuscarPedidoVentaPIHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[1]="NOK";
		String sAmbiente ="QA";
		if (!argu[2].toString().equals("NA")) sAmbiente = argu[2].toString();

		String sUsuario = argu[3].toString().toLowerCase();
		
		Pestañas().gotoScreen("Port Order Screen");
		
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

			NroPedidoAllOrdersAdmin().setText(argu[0].toString());
			ExecuteQueryAllOrdersAdmin().performAction();
			sleep(5);

			ListaPedidosPortInAllOrdersAdmin().drillDownColumn("Order Number", 0);
			sleep(5);		
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
				NroPedidoAllOrders().setText(argu[0].toString());
				ExecuteQueryAllOrders().performAction();
				sleep(5);

				ListaPedidosAllOrders().drillDownColumn("Order Number", 0);
				sleep(5);
			}
			else 
			{
				NewQueryMyOrders().performAction();

				NroPedido().setText(argu[0].toString());
				ExecuteQueryMyOrders().performAction();
				sleep(5);

				ListaPedidosPortIntMyOrders().drillDownColumn("Order Number", 0);
				sleep(5);
			}

		TabsPedidoPortabilidad().goTo("ICE Port Entry - Line Items Detail View (Sales)", "L3");
		sleep(5);

		argu[1]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

