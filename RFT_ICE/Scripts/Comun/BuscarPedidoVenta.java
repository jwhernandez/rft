package Scripts.Comun;
import resources.Scripts.Comun.BuscarPedidoVentaHelper;
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
* Parámetros:  0)NroPedido 1) OK / NOK
* SS Nov 2015
* Condiciones: Debe estar en Siebel en cualquier pantalla
*/
public class BuscarPedidoVenta extends BuscarPedidoVentaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[1]="NOK";
		Pestañas().gotoScreen("Sales Order Screen");
		sleep(5);
		
		NroPedido().setText(argu[0].toString());

		IraPedido().click();
		sleep(5);
		
		TabsPedido().goTo("Order Entry - All Orders View (Sales)", "L2");
		sleep(5);
		
		ListaPedidos().drillDownColumn("Order Number", 0);
  		sleep(5);
		
		// ir a la pestaña de detalle
		TabsPedido().goTo("Order Entry - Line Items Detail View (Sales)", "L3");
		argu[1]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

