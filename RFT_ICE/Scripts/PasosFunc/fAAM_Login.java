package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAAM_LoginHelper;
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
 * Description   : Login AAM
 * Script Name   : <b>fAAM_Login</b>
 * @author SS
 * @since  2016/11/28
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)NA 2) ambiente 3) para ante error
 * 
 */
public class fAAM_Login extends fAAM_LoginHelper
{
	public void testMain(Object[] args) 
	{
		String[] Login;
		Login = new String[3];
		//0) res 1)usuario 2) Clave
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		
		Login[1] = dpString("AAM_usr");
		Login[2] = dpString("AAM_pass");
		callScript("Scripts.Comun.SistemasExternos.AAM_Login", Login);

		if  (Login[0].toString().equals("NOK")) {
			MensError[0] = "Problema al hacer login en AAM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

