package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fATV_ModificarRegRetencionHelper;
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
 * Description   : Ejecuta la ruta registro de retenciones de clientes por ordenes
 * Script Name   : <b>fATV_ModificarRegRetención</b
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * Ej CP76_CD1 NA QA true
 * @since  2016/09/20
 * @author SS
 */
public class fATV_ModificarRegRetencion extends fATV_ModificarRegRetencionHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] RegRetencion;
		RegRetencion = new String[6];
		//Parametros: 0) Resultado = OK/NOK 1) MsjError 2) nro pedido 3)Accion 
		// 4) Motivo 5) Activo
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
		String sTipoTerminal = dpString("ATV_TipoPedido" + i);
		
		if (dpString("ATV_TipoPedido"+ i).equals("Actual")) RegRetencion[2]=getNroPedido();
		else if (dpString("ATV_TipoPedido"+ i).equals("Anterior")) RegRetencion[2]=getNroPedidoAnt();
		else bError=true;
		if (!bError){
			RegRetencion[3] = dpString("ATV_Accion"+ i); // accion
			RegRetencion[4] = dpString ("ATV_Motivo"); // Motivo
			callScript("Scripts.Comun.SistemasExternos.ATV_ModificarRegRetencion", RegRetencion);
		}

		if  (bError || RegRetencion[0].toString().equals("NOK")) {
			MensError[0] = "Problema con el registro de retención";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

