package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscarPedidoVentaPI_AdminHelper;
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
 * Description   : Busca el numero de pedido de un port-in en administracion
 * Script Name   : <b>fBuscarPedidoVentaPI_Admin</b>
 * @author Sandra
 * @since  2016/04/07
 */
public class fBuscarPedidoVentaPI_Admin extends fBuscarPedidoVentaPI_AdminHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
		String[] BuscPedVta;
		BuscPedVta = new String[2];
		//0)OK/NOK 1)NroPedido

		BuscPedVta[1]= getNroPedido();
		System.out.println("NroPedido: " + getNroPedido());
		logInfo("NroPedido: " + getNroPedido() );
		
		callScript("Scripts.Comun.BuscarPedidoVentaPI_Admin", BuscPedVta);
				
		if (BuscPedVta[0].toString().equals("NOK")) 
		{
		//MensError[0] = "Pedido Venta PI Admin no encontrado";
		MensError[0] = "xDefecto";
		MensError[1] = args[3].toString();
		MensError[2] = args[0].toString();
		MensError[3] = getScriptName( );
		callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

