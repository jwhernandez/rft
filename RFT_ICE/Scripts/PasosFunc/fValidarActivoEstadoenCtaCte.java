package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarActivoEstadoenCtaCteHelper;

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
 * Description   : Verificar que el estado sea suspendido en la cuenta cliente.
 * @author ff
 * @since  2016/11/09
 * Script Name   : <b>fValidarActivoEstadoenCtaCte</b>
 */
public class fValidarActivoEstadoenCtaCte extends fValidarActivoEstadoenCtaCteHelper
{
	public void testMain(Object[] args) 
	{
	
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
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
		
		int i = Integer.parseInt(args[1].toString());
		
		String[] MotivoSuspension;
		MotivoSuspension = new String[2];
		MotivoSuspension[1]= dpString("EstadoActivo"+i);; 
	
		callScript("Scripts.Comun.ValidarActivoEstadoenCtaCte",MotivoSuspension);
		
		if  ((MotivoSuspension[0].toString().toLowerCase().equals("NOK"))){
			MensError[0] = "Accion incorrecta";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
}
}