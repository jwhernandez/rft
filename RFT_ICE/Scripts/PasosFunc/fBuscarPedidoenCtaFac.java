package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscarPedidoenCtaFacHelper;
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
 * Script Name   : <b>fBuscarPedidoenCtaFac</b>
 * @author SS
 * @Param @Param 0)IN nombre del caso 1) NA 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * @since  2016/10/27
 */
public class fBuscarPedidoenCtaFac extends fBuscarPedidoenCtaFacHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] BuscarPedido;
		BuscarPedido = new String[2];

		BuscarPedido[1]=getNroPedido();
		callScript("Scripts.Comun.BuscarPedidoenCtaFac", BuscarPedido);
		
		if (BuscarPedido[0].toString().equals("NOK")) {	
			MensError[0] = "Problema al enviar buscar pedido en cta fac";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

