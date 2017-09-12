package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fValidarEstadoPedPIenListaHelper;
import com.rational.test.ft.*;
import Scripts.Comun.ValidarEstadoPedPIenLista;
import Scripts.Comun.TerminarCasoError;
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
 * Script Name   : <b>fValidarEstadoPedPortIn</b>
 * Descripcion   : Validación del estado de un pedido port-in en la lista
 * @author SS
 * @Param 0)IN nombre del caso 1) EstadoDeseado 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * @since  2017/01/31
 * Ej CP32 Cancelado QA true
 * Precondiciones Estar en la lista de pedidos de port-in
 */
public class fValidarEstadoPedPIenLista extends fValidarEstadoPedPIenListaHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] ValidaEstadoPedPI;
		ValidaEstadoPedPI = new String[2];
		//0)OK / NOK 1) EstadoDeseado

		ValidaEstadoPedPI[1] = args[1].toString();
		callScript( new ValidarEstadoPedPIenLista(),ValidaEstadoPedPI);

		if (ValidaEstadoPedPI[0].toString().equals("NOK")) {	
			MensError[0] = "Problema en la validación del estado del Ped PortIn";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript( new TerminarCasoError(), MensError);
		} 
	}
}

