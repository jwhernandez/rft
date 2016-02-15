package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidacPostEnvio2Helper;
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
 * Script Name   : <b>fValidacPostEnvio2</b>
 * Description   : Functional Test Script
 * @Param 
 * @since  2015/12/27
 * @author Sandra
 */
public class fValidacPostEnvio2 extends fValidacPostEnvio2Helper
{
	public void testMain(Object[] args) 
	{
		String[] Validac;
		Validac = new String[1];
		// Parámetros	   : 0)  OK/NOK 

		String[] MensError;
		MensError = new String[4];
	
		/** 
		 * Validaciones post envio
		 */

		callScript("Scripts.Comun.ValidacPostEnvio",Validac);

		if  (Validac[0].toString().equals("NOK")){
			MensError[0] = "Validaciones post envio 2 fallaron";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

