package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarCamposRepLegalHelper;
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
 * Script Name   : <b>fValidarCamposRepLegal</b>
 * Description   : Valida que los campos de rep legal esten habilitados o deshabilitados
 * @since  2016/02/15
 * @author SSASTRE
 */
public class fValidarCamposRepLegal extends fValidarCamposRepLegalHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validac;
		Validac = new String[2];
		// Parámetros 0) OK/NOK 1) true /false 
		// True valida habilitado False valida inhabilitado

		String[] MensError;
		MensError = new String[4];

		Validac[1]=args[1].toString();
		callScript("Scripts.Comun.ValidarCamposRepLegal",Validac);

		if  (Validac[0].toString().equals("NOK")){
			//MensError[0] = "Campos de rep legal estado incorrecto";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

