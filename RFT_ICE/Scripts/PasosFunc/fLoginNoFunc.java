package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fLoginNoFuncHelper;
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
 * @author SS
 * @Param 0) numero de caso 1)digito iterador en el DP 2) ambiente 3) para ante error 4) nro de paso
 * ej: CP76 NA QA true true
 */
public class fLoginNoFunc extends fLoginNoFuncHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Login;
		Login = new String[5];
		//0)OK/NOK 1) usuario 2) clave 3)Ambiente  4) true / false para cerrar 
		
		String[] Close;
		Close = new String[1];

		String[] MensError;
		MensError = new String[4];
		
		boolean bError=false;
		
		if (args[1].equals("ALT")) {
			Login[1]=getUsuarioAlt();
			Login[2]=getClaveAlt();
		} else {
			Login[1]=getUsuario();
			Login[2]=getClave();
		}
		System.out.println(Login[1]);
		System.out.println(Login[2]);
		
		Login[3]=args[2].toString(); // ambiente
		Login[4]="cerrar";
		
		if (getNroPasoDesde().equals("1") ) 
		{
			callScript("Scripts.Comun.Login", Login);
			if (Login[0].toString().equals("NOK")) bError = true;
		}
		else
		{
			callScript("Scripts.Comun.LoginIf", Login);
			if  (Login[0].toString().equals("NOK"))  
			{
				callScript("Scripts.Comun.Login", Login);
				if (Login[0].toString().equals("NOK")) bError = true;
			}
			else // Si el usuario es ALT se debe cambiar de usuario, no alcanza con el refresh de loginIf
				if (args[1].equals("ALT"))
				{					
					// intenta un close y un login sin cerrar IE
					callScript("Scripts.Comun.CerrarSBL", Close);
					if (Close[0].toString().equals("OK"))
					{
						Login[4]="ALT"; // para que no cierre IE
						callScript("Scripts.Comun.Login", Login);
						if (Login[0].toString().equals("NOK")) bError = true;
					} else bError = true;
				}
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

