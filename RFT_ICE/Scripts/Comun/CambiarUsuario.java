package Scripts.Comun;
import resources.Scripts.Comun.CambiarUsuarioHelper;
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
 * Description   :Ejecuta un cambio de usuario
 * Script Name   : <b>CambiarUsuario</b>
 * @author Sandra
 * @since  2016/04/07
 */
public class CambiarUsuario extends CambiarUsuarioHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		Menu().select(atPath("File->Logout"));
		
		// HTML Browser
		// Document: Siebel Communications: RegularExpression(http://.*ecommunications_esn/start.*)
		// Document: Siebel Communications: RegularExpression(http://.*/ecommunications_esn/start\.swe\?SWECmd=GetCachedFrame&SWEACn=.*&SWEFrame=top.*sweclient.*)
		// Document: Siebel Communications: RegularExpression(http://.*/ecommunications_esn/start\.swe\?SWE.*)
		textNode_archivo().click(atPoint(10,6));
		
		
	}
}

