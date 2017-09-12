package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fCambiarUsuarioHelper;
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
 * Description   : Ejecuta un cambio de usuario
 * Script Name   : <b>fCambiarUsuario</b>
 * @author Sandra
 * @Param 0) Caso 1) Principal o Alternativo 2) para frente a error 
 * @since  2016/04/07
 */
public class fCambiarUsuario extends fCambiarUsuarioHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] MensError;
		MensError = new String[4];
		
		String[] CamUsuario;
		CamUsuario = new String[2];
		//0)OK/NOK 1)Usuario 2)Clave
		
		callScript("Scripts.Comun.CambiarUsuario", CamUsuario);
		
		if (CamUsuario[0].toString().equals("NOK")) 
		{
		//MensError[0] = "Captura Pantalla dio error";
		MensError[0] = "xDefecto";
		MensError[1] = args[3].toString();
		MensError[2] = args[0].toString();
		MensError[3] = getScriptName( );
		callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

