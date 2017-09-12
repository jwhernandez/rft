package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscarPedidoVentaPIHelper;
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
 * Description   : Busca el numero de pedido de un port-in
 * Script Name   : <b>fBuscarPedidoVentaPI</b>
 * @author SS
 * @Param 0) Caso 1) NA o frontal 2) Ambiente 3) true / false 4) nro paso
 *  CP46 frontal QA true 20
 */
public class fBuscarPedidoVentaPI extends fBuscarPedidoVentaPIHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
		String[] BuscPedVta;
		BuscPedVta = new String[4];
		//0)NroPedido 1)OK/NOK 2)Ambiente 3) usuario (NA o Frontal)

		BuscPedVta[0]= getNroPedido();
		BuscPedVta[2]= args[2].toString();
		BuscPedVta[3]= args[1].toString();
		System.out.println("NroPedido: " + getNroPedido());
		logInfo("NroPedido: " + getNroPedido() );
		
		callScript("Scripts.Comun.BuscarPedidoVentaPI", BuscPedVta);
				
		if (BuscPedVta[1].toString().equals("NOK")) 
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

