package Scripts.Comun;
import resources.Scripts.Comun.ValidarBtonVerMultasHelper;

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
 * Script Name   : <b>ValidarBtonVerMultas</b>
 * Descripcion   : Recibe si debe estar habilitado o no y realiza la validación
 * @Param 0) OK/NOK 1) true /false para saber que se debe validar
 * Ej: res true  
 * @since  2017/04/06
 * @author FF
 */

public class ValidarBtonVerMultas extends ValidarBtonVerMultasHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "OK";
		String sTramite = argu[2].toString();
		boolean Habilitado = Boolean.parseBoolean(argu[1].toString().toLowerCase());
		IFtVerificationPoint VerMultasVP = null;
		
		if (!sTramite.equals("PortIn"))
		{
			VerMultasVP = vpManual("VerMultas", Habilitado, BtonVerMulta().isEnabled());
			if (!VerMultasVP.performTest()) argu[0] = "NOK"; 
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}