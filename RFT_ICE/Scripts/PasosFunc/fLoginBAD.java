package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fLoginBADHelper;
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
 * Script Name   : <b>fLogin</b>
 * Description   : Login a Siebel
 * @since  2015/12/13
 * @author Sandra
 * @Param 0) numero de caso 1)digito iterador en el DP 2) ambiente 3) para ante error 4) nro de paso
 * ej: CP76 NA QA true true
 * 31/10/2016 se agrega el close para ALT
 */
public class fLoginBAD extends fLoginBADHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Login;
		Login = new String[5];
		//0)OK/NOK 1) usuario 2) clave 3)Ambiente  4) true / false para cerrar 

		String[] MensError;
		MensError = new String[4];
		
		String[] Close;
		Close = new String[1];
		
		boolean bError=false;
		
		if (args[1].equals("ALT")) {
			Login[1]=getUsuarioAlt();
			Login[2]=getClaveAlt();
			Login[4]="false"; // que indica que no se cierre nada de siebel en el login ni el browser.
		} else {
			Login[1]=getUsuario();
			Login[2]=getClave();
			Login[4]="true"; // que significa que el login cierre siebel y el IE
		}
		Login[3]=args[2].toString(); // ambiente
		
		System.out.println(Login[1]);
		System.out.println(Login[2]);
		
		if ( args[1].equals("ALT")) 
		{
			callScript("Scripts.Comun.LoginIf", Login);

			callScript("Scripts.Comun.CerrarSBL", Close);
			if (Close[0].toString().equals("OK"))
			{

				callScript("Scripts.Comun.Login", Login);
				if (Login[0].toString().equals("NOK")) bError = true;
			} else bError = true;
		}
		else
		{
			if (getNroPasoDesde().equals("1"))
				callScript("Scripts.Comun.Login", Login);
			else
			{ // si el usuario no es ALT y si no es el paso 1 intenta restablecer la sesion sin hacer login
				callScript("Scripts.Comun.LoginIf", Login);
				if  (Login[0].toString().equals("NOK"))  
					callScript("Scripts.Comun.Login", Login);
			}
			if (Login[0].toString().equals("NOK")) bError = true;
		}
			
		if  (bError ) 
		{
			//MensError[0] = "Problema al ingresar a Siebel";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

