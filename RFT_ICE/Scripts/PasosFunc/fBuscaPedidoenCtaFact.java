package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBuscaPedidoenCtaFactHelper;
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
 * Description   : Busca un pedido en la cuenta de facturación. Valida contra el parámetro 
 * ingresado en el data pool de pasos
 * Script Name   : <b>BuscaPedidoenCtaFact</b>
 * @Param 0) 0) IN nombre del caso 1) "Existe" o "No Existe" 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * @author Sandra
 * @since  2016/02/14
 */
public class fBuscaPedidoenCtaFact extends fBuscaPedidoenCtaFactHelper
{
	public void testMain(Object[] args) 
	{
		String[] MensError;
		MensError = new String[4];

		String[] BuscaPedidoCtaFact;
		BuscaPedidoCtaFact = new String[2];
		// Parámetros: 0) Nro Pedido 1) Existe / No Existe

		BuscaPedidoCtaFact[0] = getNroPedido();  
		callScript("Scripts.Comun.BuscaPedidoenCtaFact", BuscaPedidoCtaFact);

		if  (!(BuscaPedidoCtaFact[1].toString().equals(args[1].toString()))){
			MensError[0] = "Validación de pedido en cta facturación erronea";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

