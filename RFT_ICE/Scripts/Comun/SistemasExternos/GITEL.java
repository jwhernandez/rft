package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.GITELHelper;
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
 * Description  Prueba Gitel
 * @author SS
 * Ej  Usercode: MAFERN password: 123456789
 */
public class GITEL extends GITELHelper
{
	/**
	 * Script Name   : <b>GITEL</b>
	 * Generated     : <b>sept. 28, 2016 12:24:13 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/09/28
	 * @author rft
	 */
	public void testMain(Object[] args) 
	{
		
		// Frame: 192.168.244.5 Web Enabler for ClearPath MCP
		


		_1921682445WebEnablerForClearP().inputKeys("MAFERN{TAB}");
		_1921682445WebEnablerForClearP().inputKeys("	{ENTER}");
		jMenuBar().click(atPath("File"));
		jMenuBar(ANY,MAY_EXIT).click(atPath("File->Exit"));

	}
}

