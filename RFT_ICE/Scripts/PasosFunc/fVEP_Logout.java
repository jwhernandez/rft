package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fVEP_LogoutHelper;

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
 * Description   : Ejecuta el logout de VEP en IE (Idem Siebel)
 * Script Name   : <b>fVEP_Logout</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * Ej res 
 * @since  2016/12/26
 * Autor FF
 */
public class fVEP_Logout extends fVEP_LogoutHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Logout;
		Logout = new String[1];
		//0) OK/NOK  
		String[] MensError;
		MensError = new String[4];

		callScript("Scripts.Comun.SistemasExternos.VEP_Logout", Logout);

		if  (Logout[0].toString().equals("NOK")) {
			MensError[0] = "Problema al salir de ATV";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

