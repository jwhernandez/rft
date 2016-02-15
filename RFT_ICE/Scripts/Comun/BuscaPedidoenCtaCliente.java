package Scripts.Comun;
import resources.Scripts.Comun.BuscaPedidoenCtaClienteHelper;
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
 * Description   : Se recibe un pedido y se busca si existe o no existe
 * Script Name   : <b>BuscaPedidoenCtaFact</b>
 * @Param 0) nro pedido 1) Existe / No Existe
 * @author Sandra
 * @since  2016/02/14
 */
public class BuscaPedidoenCtaCliente extends BuscaPedidoenCtaClienteHelper
{
	public void testMain(Object[] argu) 
	{
		argu[1] = "No Existe";
		NewQuery().performAction();
		String sPedido ="'" + argu[0].toString() + "'"; 
		NroPedido().setText(sPedido);
		ExecuteQuery().performAction();
		System.out.println(Pedidos().getProperty("RowsCount"));
		int iRowsCount = Integer.parseInt(Pedidos().getProperty("RowsCount").toString());
		if (iRowsCount >=1) {
			argu[1] = "Existe";	
		}
		System.out.println("Resutado=" + argu[1]);
	}
}

