package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarBtonValorarTodoHelper;
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
 * Script Name   : <b>fValidarBtonValorarTodo</b>
 * Description   : Valida que botón ValorarTodo esté habilitado o deshabilitado
 * @since  2016/02/15
 * @PARAM 0) nro caso 1) true / false 2) ambiente 3) true/false 4)nro paso
 * @author SSASTRE
 */
public class fValidarBtonValorarTodo extends fValidarBtonValorarTodoHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validac;
		Validac = new String[3];
		// Parámetros 0) OK/NOK 1) true /false 2) Tramite
		// True valida habilitado False valida inhabilitado

		String[] MensError;
		MensError = new String[4];

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		Validac[1]=args[1].toString();
		Validac[2] = dpString("Tramite"); 			
		callScript("Scripts.Comun.ValidarBtonValorarTodo",Validac);

		if  (Validac[0].toString().equals("NOK")){
			//MensError[0] = "Botón de valorar Todo en estado incorrecto";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

