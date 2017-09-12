package Scripts.Comun;
import resources.Scripts.Comun.RetomarTaskCMHelper;

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
 * Description   : Retomar una task si se salio de esa pantalla
 * @author ss
 * Precondiciones: Solo estar en Siedbel
 * Script Name   : <b>RetomarTaskCM</b>
 * @since  2017/02/21
 * 0) OK/NOK 1) Servicio
 * ej res 89844004
 */
public class RetomarTaskCM extends RetomarTaskCMHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "OK";
		
		if (AceptarMensajeHTMLSiebel().exists())
		{
			AceptarMensajeHTMLSiebel().click();
			sleep(5);
		}
		
		if (!TareaActual().exists())
			{
			argu[0] = "NOK";
			
			Pestañas().gotoScreen("Siebel Universal Inbox Screen");
			sleep(5);

			if (AceptarMensajeHTMLSiebel().exists()) AceptarMensajeHTMLSiebel().click();
			
			NewQuery().performAction();
			sleep(5);

			Contexto().setText(argu[1].toString());

			ExecuteQuery().performAction();
			sleep(1);
			
			ListaTareas().drillDownColumn("Name", 0);
			
			argu[0] = "OK";

			}
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);		

	}
}

