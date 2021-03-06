package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fATV_ValidarEstadoTerminal_PlanHelper;
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
 * Description   : Ejecuta el login de ATV en IE (Idem Siebel)
 * Script Name   : <b>fATV_ValidarEstadoTerminal_Plan</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * Ej CP76_CD1 NA QA true
 * @since  2016/09/16
 */
public class fATV_ValidarEstadoTerminal_Plan extends fATV_ValidarEstadoTerminal_PlanHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValEstado;
		ValEstado = new String[4];
		//0) OK/NOK 1) nro pedido 2)estado deseado
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
		String sTipoPedido = dpString("ATV_TipoPedido" + i);
		if (sTipoPedido.equals("Actual")) ValEstado[1]=getNroPedido();
		else if (sTipoPedido.equals("Anterior")) ValEstado[1]=getNroPedidoAnt();
		else bError=true;

		ValEstado[2] = dpString("ATV_Estado" + i);
		if (!bError) {
			callScript("Scripts.Comun.SistemasExternos.ATV_ValidarEstadoTerminal_Plan", ValEstado);
		}

		if  (bError || ValEstado[0].toString().equals("NOK")) {
			MensError[0] = "Problema al chequear estado enATV";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

