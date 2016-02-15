package Scripts.Comun;
import resources.Scripts.Comun.SeleccionarPedidoCPHelper;
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
 * Description   : Devuelve el nro de pedido
 * Script Name   : <b>SeleccionarPedidoCP</b>
 * Param 0) OUT nro pedido 1) OUT OK/ NOK
 * @since  2016/01/27
 * @author Sandra
 */
public class SeleccionarPedidoCP extends SeleccionarPedidoCPHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		argu[1]="NOK";
		EncabezadoPedidoHTML().ensureObjectIsVisible();
		System.out.println(NroPedido().getProperty("Text"));
		
		argu[0] = NroPedido().getProperty("Text").toString();
		argu[1]="OK";
		
		System.out.println("Resultado:" + argu[0] +"-" +  argu[1]);
	}
}

