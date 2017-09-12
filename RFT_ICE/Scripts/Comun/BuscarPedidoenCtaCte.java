package Scripts.Comun;
import resources.Scripts.Comun.BuscarPedidoenCtaCteHelper;
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
 * Descripcion   : Buscar que el pedido NO se encuentre asociado a la cuenta cliente
 * Script Name   : <b>BuscarPedidoenCtaCte</b>
 * @author SS
 * @Param @Param 0)OK / NOK  
 * @since  2016/10/27
 * Ej res 11
 */
public class BuscarPedidoenCtaCte extends BuscarPedidoenCtaCteHelper
{
	public void testMain(Object[] argu) throws RationalTestException{
		
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		NewQuery().performAction();
		NroPedido().setText(argu[1].toString());
		ExecuteQuery().performAction();
		String RecordCount = Pedidos().getProperty("RowsCount").toString();
		System.out.println(RecordCount);
		
		IFtVerificationPoint BuscaPedCtaCteVP = vpManual("BuscaPedCtaCte", 0, Pedidos().getProperty("RowsCount"));
		if (BuscaPedCtaCteVP.performTest()) argu[0] = "OK"; 	
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}



