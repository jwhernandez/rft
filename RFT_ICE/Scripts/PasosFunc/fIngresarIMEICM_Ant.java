package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fIngresarIMEICM_AntHelper;
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
 * Description   : Permite ingresar un IMEI en CM
 * @author SS
 * @since  2016/04/15
 * Script Name   : <b>fIngresarIMEICM</b>
 * @Param 0) numero de caso 1)Nombre en el dp de pasos IMEI_Incorrecto o IMEI_Correcto
 * Ej CP20 IMEI_Incorrecto PREQA false
 */
public class fIngresarIMEICM_Ant extends fIngresarIMEICM_AntHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] IMEI;
		IMEI = new String[3];
		// 0) OK/ NOK 1) IN Numero de IMEI 2) IN Correcto / Incorrecto
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		IMEI[1] = dpString(args[1].toString()); // IMEI_Incorrecto o IMEI_Correcto - Ingresa el nombre del dp
		IMEI [2] = "Incorrecto";
		if (args[1].toString().toLowerCase().equals("imei_correcto")) 
			IMEI [2] = "Correcto";

		callScript("Scripts.Comun.IngresarIMEICM",IMEI);
		
		if  ((IMEI[0].toString().equals("NOK"))){
			//MensError[0] = "Ingresar IMEI falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

