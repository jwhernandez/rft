package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.AAMHelper;
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
 * Description   : Prueba de AAM
 * @author SS
 * ej jvalerio1 jvalerio1
 */
public class AAM extends AAMHelper
{
	/**
	 * Script Name   : <b>AAM</b>
	 * Generated     : <b>sept. 28, 2016 12:20:27 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/09/28
	 * @author rft
	 */
	public void testMain(Object[] args) 
	{
		
		// Frame: Login to Activation Administration
		textfield_11().drag(atPoint(32,13), atPoint(32,12));
		loginToActivationAdministratio().inputKeys("jvalerio1{TAB}");
		loginToActivationAdministratio().inputChars("jvalerio1");
		login().click();
		
		// Frame: Activation Administration Tool
		jMenuBar().click(atPath("Workspace"));
		jMenuBar().click(atPath("Search->Service Request"));
		_htmlExpandQueryRowHtml().click();
		// PENDIENTE Insertar código aquí
	}
}

