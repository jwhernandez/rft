package Scripts.Comun;
import resources.Scripts.Comun.RefrescarBrowserHelper;

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
 * Description   : Presiona la tecla F5 en IE de Siebel
 * @author SS
 * Script Name   : <b>RefrescarBrowser</b>
 * @since  2016/03/22
 * @Param
 */
public class RefrescarBrowser extends RefrescarBrowserHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		IESiebel(Siebel(),DEFAULT_FLAGS).inputKeys("{F5}");
		sleep(10);
	}
}

