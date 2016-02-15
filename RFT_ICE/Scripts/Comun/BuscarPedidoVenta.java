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
* Parámetros:  0)NroPedido
* SS Nov 2015
* Condiciones: Debe estar en Siebel en cualquier pantalla
*/
public class BuscarPedidoVenta extends BuscarPedidoVentaHelper
{
	public void testMain(Object[] argu) 
	{
		// 	LogoOracle().waitForExistence();
		Pestañas().gotoScreen("Sales Order Screen");
		sleep(5);
		
		NroPedido().setText(argu[0].toString());

		IraPedido().click();

		// Mejorar seleccionando en todas las empresas
		ListaPedidos().waitForExistence();
		sleep(5);
		//
		//  iTotal = (int) ListaPedidos().getProperty("RowsCount");
		// ListaPedidos().getProperty("RowsCount")
		
		sleep(5);
		
		// 
		TabsPedido().goTo("Order Entry - All Orders View (Sales)", 
                    "L2");

	
		sleep(5);
		
		ListaPedidos().waitForExistence();
		
		// preguntar si estoy en el applet? o preguntar si lista pedido tiene roww?
		

		ListaPedidos().drillDownColumn("Order Number", 0);
		
		// ir a la pestaña de detalle
  		sleep(5);
		TabsPedido().goTo("Order Entry - Line Items Detail View (Sales)", 
				"L3");
	}
}

