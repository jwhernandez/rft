package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarCamposTerminalCM_AntHelper;
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
 * Description   : Valida que marca modelo serie y version sean o no editables
 * Parámetros: 0) Nombre del caso 1)NA 2) ambiente 3)Error para ejecucion true o false 
 * Script Name   : <b>fValidarCamposTerminalCM</b>
 * Ej: CP05 NA PREQA false
 * EJ CP32 NA PREQA false 
 * @since  2017/03/15
 * @author VC
 */
public class fValidarCamposTerminalCM_Ant extends fValidarCamposTerminalCM_AntHelper
{

	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] AgregCamposTerm;
		AgregCamposTerm = new String[2];
		//0) OK/NOK  

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
		
		AgregCamposTerm[1]=args[1].toString();

		callScript("Scripts.Comun.ValidarCamposTerminalCM",AgregCamposTerm);
		
		if  (AgregCamposTerm[0].toString().equals("NOK")){
			//MensError[0] = "Validar Campos terminal no funcionó";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

