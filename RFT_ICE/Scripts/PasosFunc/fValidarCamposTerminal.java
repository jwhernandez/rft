package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarCamposTerminalHelper;
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
 * Description   : Valida marca modelo serio y version
 * Parámetros: 0) Nombre del caso 1)digito de conjunto del data pool 2) ambiente 3)Error para ejecucion true o falsa
 * Script Name   : <b>ValidarCamposTerminal</b>
 * @since  2016/01/21
 * @author Sandra
 */
public class fValidarCamposTerminal extends fValidarCamposTerminalHelper
{
	public void testMain(Object[] args) 
	{
		String[] ValCamposTerm;
		ValCamposTerm = new String[3];
		//Parámetros 0)IN producto 1)IN Editable? true o false 2)OUT OK/NOK

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
		/** 
		 * Validaciones post red pendiente de pago
		 */

		int i = Integer.parseInt(args[1].toString());

		ValCamposTerm[0]=dpString("VCT_Prod"+i);
		ValCamposTerm[1]=dpString("VCT_Editable"+i);
		callScript("Scripts.Comun.ValidarCamposTerminal",ValCamposTerm);
		
		if  (ValCamposTerm[2].toString().equals("NOK")){
			MensError[0] = "Valida Campos terminal no coincide";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

