package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscaPedidoenCtaClienteOLDHelper;
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
 * Description   : Busca un pedido en la cuenta cliente. Valida contra el par�metro 
 * ingresado en el data pool de pasos
 * Script Name   : <b>fBuscaPedidoenCtaCliente</b>
 * @Param 0) 0) IN nombre del caso 1) "Existe" o "No Existe" 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * @author Sandra
 * @since  2016/02/14
 */
public class fBuscaPedidoenCtaClienteOLD extends fBuscaPedidoenCtaClienteOLDHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] BuscaPedidoCtaCte;
		BuscaPedidoCtaCte = new String[2];
		// Par�metros: 0) OK/NOK 1) nroPedido

		BuscaPedidoCtaCte[0] = getNroPedido();  
		callScript("Scripts.Comun.BuscaPedidoenCtaCliente", BuscaPedidoCtaCte);

		if  (!(BuscaPedidoCtaCte[0].toString().equals("NOK"))) {
			MensError[0] = "Validaci�n de pedido en cta facturaci�n erronea";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

