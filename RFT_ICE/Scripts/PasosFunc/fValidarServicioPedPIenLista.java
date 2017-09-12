package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fValidarServicioPedPIenListaHelper;
import Scripts.Comun.TerminarCasoError;
import Scripts.Comun.ValidarServicioPedPIenLista;
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
 * Script Name   : <b>fValidarServicioPedPIenLista</b>
 * Descripcion   : Validación del estado nro servicio de un pedido port-in en la lista
 * @author SS
 * @Param 0)IN nombre del caso 1) EstadoDeseado 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * @since  2017/01/31
 * Ej CP32 FALSE QA true
 * Precondiciones Estar en la lista de pedidos de port-in
 */
public class fValidarServicioPedPIenLista extends fValidarServicioPedPIenListaHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] ValidaNroServPedPI;
		ValidaNroServPedPI = new String[2];
		//0)OK / NOK 1) EstadoDeseado

		ValidaNroServPedPI[1] = args[1].toString();
		callScript( new ValidarServicioPedPIenLista(),ValidaNroServPedPI);

		if (ValidaNroServPedPI[0].toString().equals("NOK")) {	
			MensError[0] = "Problema en la validación del estado del nro servicio en Ped PortIn";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript( new TerminarCasoError(), MensError);
		} 
	}
}

