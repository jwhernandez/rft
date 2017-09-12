package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fATV_ValidarPosibMultasHelper;

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
 * Description   : Valida las posibles multas en ATV
 * Script Name   : <b>fATV_ValidarPosibMultas</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * Ej CP76_CD1 NA QA true
 * @since  2016/10/18
 * ult modif ss 24/4/2017 se utiliza el tramite para decidir si tomar el nro de servicio del DP. Si el tramite es PortIn se toma 
 * el nro de servicio del dp
 */
public class fATV_ValidarPosibMultas extends fATV_ValidarPosibMultasHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValMultas;
		ValMultas = new String[11];
		//0) OK/NOK 1) nro pedido 2) upgrade downgrade 3)plan capturado 4) terminal capturado 5) RPT 6) CPT-I 7) CPT-S 8) CPT-h 9)RPB 10)CPB
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
		// obtencion del nro de servicio para portin
		if (dpString("Tramite").equals("PortIn")) setNroServicio(dpString("NumeroServicio"));
	
		String sTipoPedido = dpString("ATV_TipoPedido" + i);
		if (sTipoPedido.equals("Actual")) ValMultas[1]=getNroPedido();
		else if (sTipoPedido.equals("Anterior")) ValMultas[1]=getNroPedidoAnt();
		else bError=true;
		ValMultas[2] = dpString("ATV_TIPO_CP" + i); // upgrade downgrade NA
		if (!bError) {
			callScript("Scripts.Comun.SistemasExternos.ATV_CapturarDatosMultas", ValMultas);
		}

		if  (bError || ValMultas[0].toString().equals("NOK")) {
			MensError[0] = "Problema al capturar datos de multas en ATV";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}else
		{
			callScript("Scripts.Comun.SistemasExternos.ATV_ValidarDatosMultas", ValMultas);	
			if  (bError || ValMultas[0].toString().equals("NOK")) {
				MensError[0] = "Problema al validar multas en ATV";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
		}
	}
}

