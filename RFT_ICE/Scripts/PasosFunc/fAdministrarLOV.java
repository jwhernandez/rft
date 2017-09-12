package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAdministrarLOVHelper;
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
 * Script Name   : <b>fAdministrarLOV</b>
 * Description   : Administra la tabla de LOVs
 * @Param 0) Caso 1) param 2) ambiente 3) true / false para parar frente a errror
 * eJ CP20 1 PREQA true
 * @since  2016/01/15
 * @author Sandra
 * ej: 
 */
public class fAdministrarLOV extends fAdministrarLOVHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] LOV;
		LOV = new String[5];
		/**
		 * @param0) IN Nombre de la LOV 1) IN  Accion Consultar / Setear 
		 * 2) Valor deseado en la LOV o NA 3) OUT OK/NOK 4)LIC
		 */

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		int i = Integer.parseInt(args[1].toString());
		LOV[0] = dpString("LOV"+i); 
		LOV[1] = dpString("LOV_Accion"+i); 
		LOV[2] = dpString("LOV_VAL"+i);
		if (dpString("LOV_LIC1")==null ) 	LOV[4]="NA"; 
		else 					     		LOV[4] = dpString("LOV_LIC"+i); 
		logInfo (getScriptName( ) + LOV[0] + LOV[1] + LOV[2]);
		callScript("Scripts.Comun.AdministrarLOV", LOV);

		if  ((LOV[3].toString().equals("NOK"))){
			MensError[0] = "xDefecto";// "Problemas con administración de la LOV";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

