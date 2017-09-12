package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarBtonCancelarPedidoHelper;
import Scripts.Comun.ValidarBtonCancelarPedido;
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
 * Script Name   : <b>fValidarBtonCancelarPedido</b>
 * Description   : Validar que el botón "cancelar pedido" este habilitado / deshabilitado
 * @Param Caso (true false) ambiente true/false nrocaso
 * @Param CP32_CD1_T1 true QA true 20
 * @since  2017/02/02
 * @author SS
 */
public class fValidarBtonCancelarPedido extends fValidarBtonCancelarPedidoHelper
{
	public void testMain(Object[] args)  throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] Validac;
		Validac = new String[3];
		// Parámetro  0)OUT res (OK/NOK) 1) In Resultado esperado (true) Habilitado / (false) Deshabilitado 2) tramite

		String[] MensError;
		MensError = new String[4];
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		Validac[1]=args[1].toString();
		Validac[2]=dpString("Tramite");
		callScript(new ValidarBtonCancelarPedido(),Validac);
		
		if  (Validac[0].toString().equals("NOK")){
			MensError[0] = "Validacion de Cancelar Pedido fallo";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

