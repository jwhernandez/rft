package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fIngresarSIMCMHelper;
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
 * Description   : Permite ingresar un SIM en CM
 * @author SS
 * @since  2016/04/15
 * Script Name   : <b>fIngresarSIMCM</b>
 * @Param 0) numero de caso 1)el nombre del dp SIM Correcta o SIM Incorrecta
 * Ej CP20 "SIM Correcta" PREQA false
 */
public class fIngresarSIMCM extends fIngresarSIMCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] SIM;
		SIM = new String[3];
		// 0) OK/ NOK 1) IN Numero de SIM 2) IN Correcta / Incorrecta
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		SIM[1] = dpString(args[1].toString()); // Sim correcta o SIM incorrecta - Ingresa el nombre del dp
		SIM [2] = "Incorrecta";
		if (args[1].toString().toLowerCase().equals("sim correcta")) 
			SIM [2] = "Correcta";

		callScript("Scripts.Comun.IngresarSIMCM",SIM);
		
		if  ((SIM[0].toString().equals("NOK"))){
			//MensError[0] = "Ingresar SIM falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

