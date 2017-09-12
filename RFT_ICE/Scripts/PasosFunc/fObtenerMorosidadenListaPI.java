package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fObtenerMorosidadenListaPIHelper;
import Scripts.Comun.TerminarCasoError;
import Scripts.Comun.ObtenerMorosidadenListaPI;
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
 * Description   : Obtiene morosidad de un pedido de port-in desde la lista de pedidos
 * de port-in
 * Script Name   : <b>fObtenerMorosidadenListaPI</b>
 * Precondiciones: Estar en la lista de pedidos de PI
 * Parametros 0) OUT OK/NOK 
 * @author SS
 * @since  2017/03/13
 * ej CP26_CD1_T1 NA QA NA NA
 */
public class fObtenerMorosidadenListaPI extends fObtenerMorosidadenListaPIHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] ObtenerMorosidadCtePedPI;
		ObtenerMorosidadCtePedPI = new String[1];
		//0)OK / NOK 

		callScript( new ObtenerMorosidadenListaPI(),ObtenerMorosidadCtePedPI);

		if (ObtenerMorosidadCtePedPI[0].toString().equals("NOK")) {	
			MensError[0] = "Problema en Obtener Morosidad en PI";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript( new TerminarCasoError(), MensError);
		} 
	}
}

