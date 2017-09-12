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
 * @author SS
 * Ult Modif SS 24/11/2016 ajuste objecto usado para PMR 4793
 * Ult Modif SS 20/12/2016 agregado de wait for existence para QA
 */
public class SeleccionarPedidoCP extends SeleccionarPedidoCPHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[1]="NOK";
		EncabezadoPedidoHTML().waitForExistence();
		EncabezadoPedidoHTML().ensureObjectIsVisible();
		
		System.out.println(NroPedidoCP().getProperty("Text"));
		
		argu[0] = NroPedidoCP().getProperty("Text").toString();
		
		argu[1]="OK";
		
		System.out.println("Resultado:" + argu[0] +"-" +  argu[1]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString() +"-" +  argu[1]);
	}
}

