package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fREMO_LogoutHelper;
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
 * Description   : Ejecuta el logout de REMO en IE (Idem Siebel)
 * Script Name   : <b>fREMO_Logout</b>
 * @since  2017/03/13
 * Parametros: 0) OK/NOK  
 * @author SS
 * Param 1 NA
 * ej 

 */
public class fREMO_Logout extends fREMO_LogoutHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Logout;
		Logout = new String[1];
		// 0) Resultado = OK/NOK 1
		String[] MensError;
		MensError = new String[4];

		callScript("Scripts.Comun.SistemasExternos.REMO_Logout", Logout);
		
		if  (Logout[0].toString().equals("NOK")) {
			MensError[0] = "Problema al hacer logout de REMO";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

