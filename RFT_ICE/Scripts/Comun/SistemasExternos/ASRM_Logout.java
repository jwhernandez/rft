package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ASRM_LogoutHelper;

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
 * Description   : Ejecuta el login de ASRM 
 * Script Name   : <b>ASRM_Logout</b>
 * @since  2016/10/14
 * Parametros: 0) Resultado = OK/NOK 
 * ej  res  
 */
public class ASRM_Logout extends ASRM_LogoutHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		if (Cerrarbutton().exists()){
			System.out.println("Saliendo del sistema ASRM:");
			Cerrarbutton().click();
			argu[0] = "OK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

