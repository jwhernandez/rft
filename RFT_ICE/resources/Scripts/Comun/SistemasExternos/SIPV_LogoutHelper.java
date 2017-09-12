// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.Scripts.Comun.SistemasExternos;
import libreria.Accion;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.vp.IFtVerificationPoint;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Script Name   : <b>SIPV_Logout</b><br>
 * Generated     : <b>2016/08/29 16:25:39</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  agosto 29, 2016
 * @author Sandra
 */
public abstract class SIPV_LogoutHelper extends libreria.Accion
{
	/**
	 * IEBrowserSIPV: with default state.
	 *		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.processName : iexplore.exe
	 * 		.documentName : RegularExpression(http://.*Sistema_Inventarios/App_Forms.*)
	 */
	protected BrowserTestObject IEBrowserSIPV() 
	{
		return new BrowserTestObject(
                        getMappedTestObject("IEBrowserSIPV"));
	}
	/**
	 * IEBrowserSIPV: with specific test context and state.
	 *		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.processName : iexplore.exe
	 * 		.documentName : RegularExpression(http://.*Sistema_Inventarios/App_Forms.*)
	 */
	protected BrowserTestObject IEBrowserSIPV(TestObject anchor, long flags) 
	{
		return new BrowserTestObject(
                        getMappedTestObject("IEBrowserSIPV"), anchor, flags);
	}
	
	/**
	 * Salir: with default state.
	 *		.text : Salir
	 * 		.id : 
	 * 		.href : RegularExpression(http://.*Sistema_Inventarios/App_Form.*)
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Menu_Salir() 
	{
		return new GuiTestObject(
                        getMappedTestObject("Menu_Salir"));
	}
	/**
	 * Salir: with specific test context and state.
	 *		.text : Salir
	 * 		.id : 
	 * 		.href : RegularExpression(http://.*Sistema_Inventarios/App_Form.*)
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Menu_Salir(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("Menu_Salir"), anchor, flags);
	}
	
	

	protected SIPV_LogoutHelper()
	{
		setScriptName("Scripts.Comun.SistemasExternos.SIPV_Logout");
	}
	
}
