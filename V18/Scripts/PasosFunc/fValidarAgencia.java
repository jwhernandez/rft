package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarAgenciaHelper;
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
 * Script Name   : <b>fValidarAgencia</b>
 * Descripcion   : Functional Test Script
 * @since  2015/12/27
 * @author Sandra
 */
public class fValidarAgencia extends fValidarAgenciaHelper
{
	public void testMain(Object[] args) 
	{
		String[] Agencia;
		Agencia = new String[1];
		// Parámetros	   : 0) OK/NOK 

		String[] MensError;
		MensError = new String[4];

		/** 
		 * Ir a validar agencia
		 */

		callScript("Scripts.Comun.ValidarAgencia",Agencia);

		if  (Agencia[0].toString().equals("NOK")){
			MensError[0] = "Agencia no fue completado automaticamente";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

