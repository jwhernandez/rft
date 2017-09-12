package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarBtonVerMultasHelper;

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
 * Description   : Recibe si debe estar habilitado o no y realiza la validaci�n
 * @author FF
 * Script Name   : <b>fValidarBtonVerMultas</b>
 * @since  2017/04/06
 */
public class fValidarBtonVerMultas extends fValidarBtonVerMultasHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validac;
		Validac = new String[3];
		// Par�metros 0) OK/NOK 1) true /false 
		// True valida habilitado False valida inhabilitado
		// 2) Tramite

		String[] MensError;
		MensError = new String[4];

		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		
		Validac[1]=args[1].toString();
		Validac[2]=dpString ("Tramite");
		callScript("Scripts.Comun.ValidarBtonVerMultas",Validac);

		if  (Validac[0].toString().equals("NOK")){
			//MensError[0] = "Bot�n de Liberar Numero en estado incorrecto";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

