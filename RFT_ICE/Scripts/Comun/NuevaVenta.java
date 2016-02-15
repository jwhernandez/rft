package Scripts.Comun;
import resources.Scripts.Comun.NuevaVentaHelper;
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
import com.rational.test.ft.sys.SpyMemory; 
import com.rational.test.ft.sys.SpyMemoryStatistics; 

/**
* Descripción: Generar una nueva venta desde la vista 360
* Parámetros: 0) NroPedido
* SS Nov 2015
*/
public class NuevaVenta extends NuevaVentaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		//Generar un nuevo pedido de venta
  		NuevoPedido().performAction();
  		Pedidos().waitForExistence();
  		sleep(2);
  		Pedidos().drillDownColumn("Order Number", 0);
  		sleep(1);
		Morosidad().performAction();
  		sleep(1);
  		
  		TabsPedido().goTo("Order Entry - Line Items Detail View (Sales)","L3");
  		//Capturar el numero de pedido
  		NroPedido().waitForExistence();
 		System.out.println(NroPedido().getProperty("Text"));
 		String sPedido = NroPedido().getProperty("Text").toString();
		argu[0] = sPedido;
		logInfo("Pedido numero:" + sPedido);
		setNroPedido(sPedido);
		
	}
}

