package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarActivoEstadoyMotivoenCtaFactHelper;

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
 * Description   : Valida el estado (Activo o Inactivo) de un nro de servicio
 * Script Name   : <b>fValidarActivoEstadoyMotivoenCtaFact/b>
 * @author FF
 * @since  2016/04/28
 * @Param =
 */

public class fValidarActivoEstadoyMotivoenCtaFact extends fValidarActivoEstadoyMotivoenCtaFactHelper
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
		MotivoSuspension = new String[3];

		MotivoSuspension[1]= dpString("EstadoActivo"+i);; // sin lowercase a proposito
		MotivoSuspension[2]= dpString("MotivoActivo"+i);; // sin lowercase a proposito


		callScript("Scripts.Comun.ValidarActivoEstadoyMotivoenCtaFact",MotivoSuspension);
		
		if  ((MotivoSuspension[0].toString().equals("NOK"))){
			//MensError[0] = "Validacion de motivo falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}