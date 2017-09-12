package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscarPedidoVentaPIenListaHelper;
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
 * Description   : Busca el numero de pedido de un port-in en la lista de pedidos de port-in.
 * A diferencia de fbuscarPedidoventa que va al detalle del pedido de venta. Aqui solo se posiciona en el registro
 * de la lista de pedidos de port-in
 * Script Name   : <b>fBuscarPedidoVentaPIenLista</b>
 * @author SS
 * @since  2017/03/09
 * @Param 0) Caso 1) NA o frontal 2) Ambiente 3) true / false 4) nro paso
 *  CP46 frontal QA true 20
 */
public class fBuscarPedidoVentaPIenLista extends fBuscarPedidoVentaPIenListaHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
		String[] BuscPedVta;
		BuscPedVta = new String[4];
		//0)OK/NOK 1)NroPedido 2)Ambiente 3) usuario (NA o Frontal)

		BuscPedVta[1]= getNroPedido();
		BuscPedVta[2]= args[2].toString();
		BuscPedVta[3]= args[1].toString();
		System.out.println("NroPedido: " + getNroPedido());
		logInfo("NroPedido: " + getNroPedido() );
		
		callScript("Scripts.Comun.BuscarPedidoVentaPIenLista", BuscPedVta);
				
		if (BuscPedVta[0].toString().equals("NOK")) 
		{
		//MensError[0] = "Pedido Venta PI no encontrado";
		MensError[0] = "xDefecto";
		MensError[1] = args[3].toString();
		MensError[2] = args[0].toString();
		MensError[3] = getScriptName( );
		callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

