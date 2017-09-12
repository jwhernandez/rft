package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarMorosidadenListaHelper;
import Scripts.Comun.TerminarCasoError;
import Scripts.Comun.ValidarMorosidadenLista;
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
 * Description   : Validar en la vista de pedidos de portabilidad 
 * en el form applet del pedido que el cliente este moroso
 * @author SS
 * Script Name   : <b>fValidarMorosidadenLista</b>
 * @since  2017/03/07
 * param 1: True o false
 * Ej CP26_CD1_T1 TRUE QA NA NA 
 */
public class fValidarMorosidadenLista extends fValidarMorosidadenListaHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] ValidaMorosidadCtePedPI;
		ValidaMorosidadCtePedPI = new String[2];
		//0)OK / NOK 1) EstadoDeseado (true o false)

		ValidaMorosidadCtePedPI[1] = args[1].toString();
		callScript( new ValidarMorosidadenLista(),ValidaMorosidadCtePedPI);

		if (ValidaMorosidadCtePedPI[0].toString().equals("NOK")) {	
			MensError[0] = "Problema en la validación de la morosidad en Ped PortIn";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript( new TerminarCasoError(), MensError);
		} 
	}
}

