package Scripts.Comun;
import resources.Scripts.Comun.ValidaSIMActivoCMHelper;

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
 * Description   : Permite validar un SIM en CM
 * @author VC
 * Script Name   : <b>ValidaSIMActivoCM</b>
 * @since  2017/03/15
 * @Param 0) OK/ NOK 1) IN Numero de SIM 2) IN Correcta / Incorrecta
 */
public class ValidaSIMActivoCM extends ValidaSIMActivoCMHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		String sSIMaValidar = argu[1].toString();
		String sCorrecta = argu[2].toString().toLowerCase();
		System.out.println("SIM en Siebel: " + SIM().getProperty("Text").toString());
		
		// Validar SIM
		if (sCorrecta.equals("correcta")){ //Se desea verificar que ambas SIM sean iguales
			if (sSIMaValidar.equals(SIM().getProperty("Text").toString())) argu[0] = "OK";
			System.out.println("SIM a validar: " + sSIMaValidar);
			System.out.println("El número de SIM es igual al del servicio del cliente");
		} else
		{ //Valida que sean diferentes
			if (!sSIMaValidar.equals(SIM().getProperty("Text").toString())) argu[0] = "OK";
			System.out.println("SIM a validar: " + sSIMaValidar);
			System.out.println("El número de SIM es diferente al del servicio del cliente");
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

