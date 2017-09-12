package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarMotivoPedidoHelper;

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
 * Description   : Validar motivo del pedido 
 * @author ff
 * Script Name   : <b>fValidarMotivoPedido</b>
 *  @since  2016/11/08
 */
public class fValidarMotivoPedido extends fValidarMotivoPedidoHelper
{
	public void testMain(Object[] args)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] MotivoPedido;
		MotivoPedido = new String[3];
		// Parámetros: 0) OK/NOK  1) Accion 2) Tramite 

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());
		MotivoPedido[1] = dpString("Motivo" + i);
		MotivoPedido[2] = dpString("Tramite"  );  
		callScript("Scripts.Comun.ValidarNuevaAccion",MotivoPedido);
		
		if  ((MotivoPedido[0].toString().toLowerCase().equals("NOK"))){
			MensError[0] = "Accion incorrecta";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
	}
	}}
