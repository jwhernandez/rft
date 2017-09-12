package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fComprarPlanCM_AntHelper;
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
 * Description   : Permite seleccionar un plan en CM
 * @author SS
 * @since  2016/04/13
 * Script Name   : <b>fComprarPlanCM</b>
 * @Param 0) nro caso  1) NA para el plan en el DP o LISTAR 2) Ambiente 3) Error para ejecución? 
 * Si recibe en el parametro 1 un * envía LISTAR como plan, caso contrario envía el data del data pool de entrada
 */

public class fComprarPlanCM_Ant extends fComprarPlanCM_AntHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ComprarPlan;
		ComprarPlan = new String[3];
		// Parámetros: 0) OK/NOK 1) Nombre del producto 2) Posicion 
		
		String[] MensError;
		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		System.out.println(dpString("NumeroCaso"));
		System.out.println(dpString("Ambiente"));
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		if (args[1].toString().equals("LISTAR")) ComprarPlan[1]="LISTAR";
		if (args[1].toString().equals("NA")) ComprarPlan[1]=dpString("Plan");

		callScript("Scripts.Comun.ComprarPlanCM", ComprarPlan);

		if (!args[1].toString().equals("*")){
			if  (ComprarPlan[0].toString().equals("No Encontrado")){
				//MensError[0] = "CM no encontró plan destino";
				MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
		}
	}
}

