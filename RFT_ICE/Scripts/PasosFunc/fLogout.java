package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fLogoutHelper;
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
 * Script Name   : <b>fLogout</b>
 * Description   : Realiza el logout de Siebel
 * @since  2016/04/08
 * @author SS
 * ult modif ss 1 03 2017 se agrega opcion de solo cerrar SBL
 * C20_CD1_T1 SBL QA NA NA 
 */
public class fLogout extends fLogoutHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Logout;
		Logout = new String[2];

		String[] MensError;
		MensError = new String[4];
		
		if (args[1].toString().toLowerCase().equals("cambiousuario")) Logout[1]="cambiousuario"; else Logout[1]="NA";
		
		callScript("Scripts.Comun.Logout", Logout);

		if  (Logout[0].toString().equals("NOK")) {
			//MensError[0] = "Problema al salir de Siebel";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

