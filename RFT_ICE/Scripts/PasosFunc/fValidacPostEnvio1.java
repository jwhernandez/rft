package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidacPostEnvio1Helper;
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
 * Description   : Llama a la validacion de estado del pedido y bot�n valorar todo
 * Script Name   : <b>fValidacPostEnvio1</b>
 * @Param 0)Nro caso 1) true (para validar el estado) 2) ambiente 3)true / false 4)nropaso
 * @since  2016/01/12
 * @author SS
 */
public class fValidacPostEnvio1 extends fValidacPostEnvio1Helper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validac;
		Validac = new String[2];
		// Par�metros	   : 0)out  OK/NOK si el pedido est� en completar 
		// 1) out OK/NOK si el bot�n est� en inhabilitar

		String[] MensError;
		MensError = new String[4];

		/** 
		 * Validaciones post envio
		 */

		callScript("Scripts.Comun.ValidacPostEnvio1",Validac);
		// valida si corresponde el estado del contrato
		if (args[1].toString().equals("true")) {
			if  (Validac[0].toString().equals("NOK")){
				MensError[0] = "Estado del pedido diferente a completar";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
		}
		if  (Validac[1].toString().equals("NOK")){
			MensError[0] = "Bot�n valorar todo habilitado";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

