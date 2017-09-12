package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAgregarCamposTerminalICEHelper;
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
 * Description   : Agrega marca modelo serie y version
 * Par�metros: 0) Nombre del caso 1)NA 2) ambiente 3)Error para ejecucion true o false 
 * Script Name   : <b>fAgregarCamposTerminal</b>
 * Ej: CP05 NA PREQA false
 * EJ CP32 NA PREQA false 
 * @since  2017/03/29
 * @author FF
 *  * Ult modif ss ... es el mismo que agregarcamposterminal
 */
public class fAgregarCamposTerminalICE extends fAgregarCamposTerminalICEHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] AgregCamposTerm;
		AgregCamposTerm = new String[6];
		//0)Marca 1) Modelo 2) Serie 3)Version 4) OK/NOK 5)Tramite

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
		AgregCamposTerm[0]=dpString("Serie"); 
		AgregCamposTerm[1]=dpString("Version");
		AgregCamposTerm[3]=dpString("Tramite");
		callScript("Scripts.Comun.AgregaCamposTerminalICE",AgregCamposTerm);
		
		if  (AgregCamposTerm[2].toString().equals("NOK")){
			//MensError[0] = "Agregar Campos terminal no funcion�";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

