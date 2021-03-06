package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ASRM_LoginHelper;

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
 * Script Name   : <b>ASRM_Login</b>
 * @since  2016/10/14
 * Parametros: 0) Resultado = OK/NOK 1) usuario 2) clave 3)Ambiente  
 * ej  res jvalerio1 jvalerio1 QA
 */
public class ASRM_Login extends ASRM_LoginHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		String sApp_Ambiente = "ASRMQA"; 
		if (argu.length >= 4 ) {
			sApp_Ambiente = "ASRM" + argu[3].toString();
		}
		startApp(sApp_Ambiente);
		sleep(5);
		Tabs().waitForExistence();
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

