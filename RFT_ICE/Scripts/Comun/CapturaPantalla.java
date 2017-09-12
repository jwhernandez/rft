package Scripts.Comun;
import resources.Scripts.Comun.CapturaPantallaHelper;
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
 * Descripción: Captura la pantalla
 * Script Name   : <b>CapturaPantalla</b>
 * argunmento Parámetros: 0) OK/NOK 1)opciones a futuro para mas paginas 
 * SS 2016/04/07
 * ej res res
 */
public class CapturaPantalla extends CapturaPantallaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		LogoICE().ensureObjectIsVisible();
		logWarning("This is a warning", getRootTestObject().getScreenSnapshot());
		logInfo("This is an info message", getRootTestObject().getScreenSnapshot());
		ImpreResultadoScript(getScriptName( ).toString(), "Sin parametro de resultado");
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

