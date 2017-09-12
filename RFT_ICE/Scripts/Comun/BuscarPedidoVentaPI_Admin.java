package Scripts.Comun;
import resources.Scripts.Comun.BuscarPedidoVentaPI_AdminHelper;
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
* Descripción: Permite buscar el pedido recibido como parametro e ir a la pantalla de administracion
* Parámetros:  0)OK/NOK 1) NroPedido
* ej 1-1701155949 res
* Script Name   : <b>BuscarPedidoVentaPI_Admin</b>
* SS Nov 2015
* Condiciones: Debe estar en Siebel en cualquier pantalla
*/
public class BuscarPedidoVentaPI_Admin extends BuscarPedidoVentaPI_AdminHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="NOK";
		
		Pestañas().gotoScreen("Port Order Screen");
		sleep(3);
		Pestañas().gotoView("ICE Port Entry - Line Items Detail View Admin");
		sleep(5);
		
		NewQuery().performAction();

		NroPedido_PI_Admin().setText(argu[1].toString());

		ExecuteQuery().performAction();
		sleep(5);

		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

