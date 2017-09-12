package Scripts.Comun;
import resources.Scripts.Comun.ValidarObservacionHelper;

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
 * Descripción: Valida las observaciones de un pedido
 * Script Name   : <b>fValidarObservacion</b>
 * @author VC
 * Parámetros: Parámetros: 0) OK / NOK (S) 1) Observación a validar
 * @since  2017/03/27
 * Requeire estar en la vista de detalles del pedido
 */
public class ValidarObservacion extends ValidarObservacionHelper
{
	public void testMain(Object[] argu) 
	{		
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		String observacionDP = argu[1].toString();

		IFtVerificationPoint observacionVP = null;
		
		System.out.println("Observación en el DP: " + observacionDP);
		System.out.println("Observación en Siebel: " + Observaciones().getProperty("Text"));
		
		observacionVP = vpManual("Observaciones", argu[1].toString(), Observaciones().getProperty("Text"));
		
		if (observacionVP.performTest()) {
			argu[0] = "OK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

