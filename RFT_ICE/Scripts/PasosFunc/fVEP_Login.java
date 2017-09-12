package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fVEP_LoginHelper;
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
 * Description   : Ejecuta el login de VEP 
 * Script Name   : <b>VEP_Login</b>
 * @since  2016/12/26
 * Parametros: 0) Resultado = OK/NOK 1) usuario 2) clave 3)Ambiente  
 * @author FF
 * ult modif 24 2 2012 se agrega SSO con opcion SSO (el usuario tiene que ser el estandard no el ALT)
 * param 1 NA o SSO
 * CP20_CD1_T1 SSO QA NA NA
 */

public class fVEP_Login extends fVEP_LoginHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Login;
		Login = new String[4];
		// 0) Resultado = OK/NOK 1) usuario 2) clave 3)Ambiente  
		String[] MensError;
		MensError = new String[4];

		if (!args[1].toString().toLowerCase().trim().equals("sso"))
		{
			// Buscar registro en el DP de entrada
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
					dpString("Ambiente").equals(args[2]))) {
				dpNext(); 
			} 		

			Login[1] = dpString("VEP_usr");
			Login[2] = dpString("VEP_pass");
		}
		else
		{
			Login[1] = getUsuario();
			Login[2] = getClave();	
		}
		Login[3] = args[2].toString(); //Ambiente
		callScript("Scripts.Comun.SistemasExternos.VEP_Login", Login);
		

		if  (Login[0].toString().equals("NOK")) {
			//MensError[0] = "Problema al ingresar a BRM";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}
