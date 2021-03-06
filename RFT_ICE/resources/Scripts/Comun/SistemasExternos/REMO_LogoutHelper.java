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
 * Script Name   : <b>REMO_Logout</b><br>
 * Generated     : <b>2017/03/13 12:04:55</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  marzo 13, 2017
 * @author Sandra
 */
public abstract class REMO_LogoutHelper extends libreria.Accion
{
	/**
	 * CambiarSesión: with default state.
	 *		.text : Cambiar Sesión
	 * 		.href : http://cer.infocom.ice/REMOWeb/ibm_security_logout?logoutExitPage=/Salir.do
	 * 		.id : 
	 * 		.title : Cambiar Sesión
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 8
	 */
	protected GuiTestObject CambiarSesión() 
	{
		return new GuiTestObject(
                        getMappedTestObject("CambiarSesión"));
	}
	/**
	 * CambiarSesión: with specific test context and state.
	 *		.text : Cambiar Sesión
	 * 		.href : http://cer.infocom.ice/REMOWeb/ibm_security_logout?logoutExitPage=/Salir.do
	 * 		.id : 
	 * 		.title : Cambiar Sesión
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 8
	 */
	protected GuiTestObject CambiarSesión(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("CambiarSesión"), anchor, flags);
	}
	
	/**
	 * menuDiv: with default state.
	 *		.id : menuDiv
	 * 		.title : 
	 * 		.class : Html.UL
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Menu() 
	{
		return new GuiTestObject(
                        getMappedTestObject("Menu"));
	}
	/**
	 * menuDiv: with specific test context and state.
	 *		.id : menuDiv
	 * 		.title : 
	 * 		.class : Html.UL
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Menu(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("Menu"), anchor, flags);
	}
	
	/**
	 * REMO: with default state.
	 *		.title : Instituto Constarricense de Electricidad - REMO
	 * 		.class : Html.HtmlDocument
	 * 		.url : RegularExpression(http://cer\.infocom\.ice/REMOWeb.*)
	 */
	protected DocumentTestObject REMO() 
	{
		return new DocumentTestObject(
                        getMappedTestObject("REMO"));
	}
	/**
	 * REMO: with specific test context and state.
	 *		.title : Instituto Constarricense de Electricidad - REMO
	 * 		.class : Html.HtmlDocument
	 * 		.url : RegularExpression(http://cer\.infocom\.ice/REMOWeb.*)
	 */
	protected DocumentTestObject REMO(TestObject anchor, long flags) 
	{
		return new DocumentTestObject(
                        getMappedTestObject("REMO"), anchor, flags);
	}
	
	/**
	 * REMOBrowser: with default state.
	 *		.window : 988974
	 * 		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.processName : iexplore.exe
	 */
	protected BrowserTestObject REMOIE() 
	{
		return new BrowserTestObject(
                        getMappedTestObject("REMOIE"));
	}
	/**
	 * REMOBrowser: with specific test context and state.
	 *		.window : 988974
	 * 		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.processName : iexplore.exe
	 */
	protected BrowserTestObject REMOIE(TestObject anchor, long flags) 
	{
		return new BrowserTestObject(
                        getMappedTestObject("REMOIE"), anchor, flags);
	}
	
	/**
	 * Usuario: with default state.
	 *		.id : 
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : j_username
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject Usuario() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("Usuario"));
	}
	/**
	 * Usuario: with specific test context and state.
	 *		.id : 
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : j_username
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject Usuario(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("Usuario"), anchor, flags);
	}
	
	

	protected REMO_LogoutHelper()
	{
		setScriptName("Scripts.Comun.SistemasExternos.REMO_Logout");
	}
	
}

