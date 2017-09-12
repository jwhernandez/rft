package Scripts.Comun;
import resources.Scripts.Comun.BuscarPedidoenCtaFacHelper;
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
 * Descripcion   : Buscar que el pedido se encuentre asociado a la cuenta de fact
 * Script Name   : <b>BuscarPedidoenCtaFac</b>
 * @author SS
 * @Param @Param 0)OK / NOK 1) Nro Pedido
 * @since  2016/10/27
 * Ej res 1-1738772752
 */
public class BuscarPedidoenCtaFac extends BuscarPedidoenCtaFacHelper
{
	public void testMain(Object[] argu) throws RationalTestException{
		
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		NewQuery().performAction();
		NroPedido().setText(argu[1].toString());
		ExecuteQuery().performAction();
		
		String RecordCount = Pedidos().getProperty("RowsCount").toString();
		System.out.println(RecordCount);
		
		IFtVerificationPoint BuscaPedCtaFacVP = vpManual("BuscaPedCtaFac", 1, Pedidos().getProperty("RowsCount"));
		if (BuscaPedCtaFacVP.performTest()) argu[0] = "OK"; 	
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}