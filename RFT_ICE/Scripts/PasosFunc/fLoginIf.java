package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fLoginIfHelper;

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
 * Description   : Hace login solo si hubo timeout y se fue el browser de IE sino solo hace refresh del browser
 * @author SS
 * Script Name   : <b>fLoginIf</b>
 * 2016/09/20
 */
public class fLoginIf extends fLoginIfHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Loginif;
		Loginif = new String[5];
		//0) OK/NOK 1) usuario 2) clave 3)Ambiente 4) true / false para bajar siebel FMW

		String[] Login;
		Login = new String[5];
		//0) usuario 1) clave 2)Ambiente 3) OK/NOK 4) true / false para bajar siebel FMW

		String[] MensError;
		MensError = new String[4];

		if (args[1].equals("ALT")) {
			Loginif[1]=getUsuarioAlt();
			Loginif[2]=getClaveAlt();
		} else {
			Loginif[1]=getUsuario();
			Loginif[2]=getClave();
		}
		System.out.println(Loginif[1]);
		System.out.println(Loginif[2]);

		Loginif[3]=args[2].toString(); // ambiente
		Loginif[4]="false";
		callScript("Scripts.Comun.LoginIf", Loginif);

		if  (Loginif[0].toString().equals("NOK")) {
			Login[0]=Loginif[1].toString(); //usuario
			Login[1]=Loginif[2].toString(); //clave
			Login[2]=Loginif[3].toString(); //ambiente
			Login[4]=Loginif[4].toString(); //true / false para reiniciar ambiente
			callScript("Scripts.Comun.Login", Login);
			if  (Login[3].toString().equals("NOK")) {
				MensError[0] = "Problema al hacer login a Siebel";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}  
		}		
	}
}

