package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fATV_LoginHelper;
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
 * Description   : Ejecuta el login de ATV en IE (Idem Siebel)
 * Script Name   : <b>fATV_Login</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * Ej CP76_CD1 NA QA true
 * @since  2016/09/14
 * ul modif ss correccion de clave y usuario de ATV 23-12-2016
 * 20/04/2017 ss se mantiene la opcion de sso si el argumento es SSO
 */
public class fATV_Login extends fATV_LoginHelper
{
	public void testMain(Object[] args) throws RationalTestException 
		{
			ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
			String[] Login;
			Login = new String[4];
			//0) OK/NOK 1) usuario 1) clave 2)Ambiente 
			String[] MensError;
			MensError = new String[4];
	
	
			if (args[1].toString().equals("SSO"))
			{
				Login[1] = getUsuario(); 
				Login[2] = getClave();		
		}
		else
		{
			// Buscar registro en el DP de entrada
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
					dpString("Ambiente").equals(args[2]))) {
				dpNext(); 
			} 		

			Login[1] = dpString("ATV_usr");		//23-12-2016 correccion de clave y usuario de ATV	
			Login[2] = dpString("ATV_pass");
		}

		Login[3] = args[2].toString(); // ambiente
		callScript("Scripts.Comun.SistemasExternos.ATV_Login", Login);

		if  (Login[0].toString().equals("NOK")) {
			MensError[0] = "Problema al ingresar a ATV";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

