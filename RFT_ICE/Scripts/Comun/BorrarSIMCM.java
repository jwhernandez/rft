package Scripts.Comun;
import resources.Scripts.Comun.BorrarSIMCMHelper;

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
 * Description   : Borra la SIM en CM
 * Script Name   : <b>BorrarSIMCM</b>
 * @author ss
 * @since  2017/02/10
 */
public class BorrarSIMCM extends BorrarSIMCMHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		SIM().setText(""); 
		if (SIM().getProperty("Text").toString().equals("")) argu[0] = "OK";
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

