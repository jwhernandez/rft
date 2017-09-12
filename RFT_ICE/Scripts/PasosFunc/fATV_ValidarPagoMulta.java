package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fATV_ValidarPagoMultaHelper;
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
 * Description   : Validar el pago de multas
 * Script Name   : <b>fATV_ValidarPagoMulta</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * Ej CP76_CD1 NA QA true
 * @since  2016/10/27
 * @author SS
 */
public class fATV_ValidarPagoMulta extends fATV_ValidarPagoMultaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidarPago;
		ValidarPago = new String[5];
		//Parametros: 0) Resultado = OK/NOK 1) nro pedido 2)Tipo (VENTA TERMINAL O MULTA CAMBIO DE PLAN
		// 3) Estado multa (ENVIADA A NOMINA) 4) Estado Pago (PAGADO, COMPRADO BRM)
		String[] MensError;
		MensError = new String[4];
		boolean bError = false;

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		int i = Integer.parseInt(args[1].toString());
		String sTipoPedido = dpString("ATV_Tipo Pedido" + i);
		if (sTipoPedido.equals("AnteAnterior")) ValidarPago[1]=getNroPedidoAntAnt();
		else if (sTipoPedido.equals("Anterior")) ValidarPago[1]=getNroPedidoAnt();
		else bError=true;
		ValidarPago[2] = dpString("ATV_Tipo" + i);
		ValidarPago[3] = dpString("ATV_EstadoMulta" + i);
		ValidarPago[4] = dpString("ATV_EstadoPago" + i);	
		
		if (!bError) {
			callScript("Scripts.Comun.SistemasExternos.ATV_ValidarPagoMulta", ValidarPago);
		}

		if  (bError || ValidarPago[0].toString().equals("NOK")) {
			MensError[0] = "Problema al validar pago multa en ATV";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

