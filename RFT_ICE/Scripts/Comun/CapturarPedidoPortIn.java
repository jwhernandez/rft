package Scripts.Comun;
import resources.Scripts.Comun.CapturarPedidoPortInHelper;
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
 * Description   : Captura el numero de pedido de una venta port-in
 * @author SS
 * Ultima modificacion feb 2017 ss se usa la pantalla estandard y no la de admin para QA pues se asume usuario frontal
 * @Param 0)OK/NOK 1) NroPedido 2) Ambiente
 * Script Name   : <b>CapturarPedidoPortIn</b>
 */
public class CapturarPedidoPortIn extends CapturarPedidoPortInHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		String sAmbiente = "QA";
		if (!argu[2].toString().equals("NA")) sAmbiente = argu[2].toString();
		

 		String sPedido = null;

/* 		if (sAmbiente.equals("QA")) 
 		{
 			sPedido = NroPedidoPortabilidadAdmin().getProperty("Text").toString();
 			System.out.println(NroPedidoPortabilidadAdmin().getProperty("Text"));
 		}
 		else
 		{*/
			sPedido = NroPedidoPortabilidad().getProperty("Text").toString();
 			System.out.println(NroPedidoPortabilidad().getProperty("Text"));
/* 		}	*/	
		argu[1] = sPedido;
		logInfo("Pedido nutext_orderNumber().mero:" + sPedido);
		setNroPedido(sPedido);
		
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString() + "-" + argu[1].toString());
	}
}

