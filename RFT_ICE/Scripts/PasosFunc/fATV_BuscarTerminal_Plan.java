package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fATV_BuscarTerminal_PlanHelper;
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
 * Description   : Ejecuta la ruta Historico terminales y planes
 * Busca el pedido y compara plan y articulo con lo pasado en el dp de datos
 * Si el articulo es un terminal busca que haya un registro en el applet de detalles
 * de pago del terminal
 * Script Name   : <b>fATV_BuscarTerminal_Plan</b
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * Ej CP76_CD1 NA QA true
 * @since  2016/09/14
 */
public class fATV_BuscarTerminal_Plan extends fATV_BuscarTerminal_PlanHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] BuscarTerminal;
		BuscarTerminal = new String[4];
		//Parametros: 0) Resultado = OK/NOK 1) nro pedido 2)Nombre plan 
		// 3) Nombre terminal o PLAN SIN TERMINAL
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
		
		if (sTipoTerminal.equals("Actual")) BuscarTerminal[1]=getNroPedido();
		else if (sTipoTerminal.equals("Anterior")) BuscarTerminal[1]=getNroPedidoAnt();
		else bError=true;
		if (!bError){
			BuscarTerminal[2] = dpString("ATV_Plan"+ i); // plan
			BuscarTerminal[3] = dpString ("ATV_Terminal"+i); // terminal
			callScript("Scripts.Comun.SistemasExternos.ATV_BuscarTerminal_Plan", BuscarTerminal);
		}

		if  (bError || BuscarTerminal[0].toString().equals("NOK")) {
			MensError[0] = "Problema al buscar terminal / plan en ATV";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

