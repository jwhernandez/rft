// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.Scripts.Comun;
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
 * Script Name   : <b>ValidarActivoSIMenCtaFact</b><br>
 * Generated     : <b>2016/11/12 11:35:23</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  noviembre 12, 2016
 * @author Sandra
 */
public abstract class ValidarActivoSIMenCtaFactHelper extends libreria.Accion
{
	/**
	 * IESiebel: with default state.
	 *		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.documentName : RegularExpression(http.*\/ecommunications_esn/star.*)
	 * 		.processName : iexplore.exe
	 */
	protected BrowserTestObject IESiebel() 
	{
		return new BrowserTestObject(
                        getMappedTestObject("IESiebel"));
	}
	/**
	 * IESiebel: with specific test context and state.
	 *		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.documentName : RegularExpression(http.*\/ecommunications_esn/star.*)
	 * 		.processName : iexplore.exe
	 */
	protected BrowserTestObject IESiebel(TestObject anchor, long flags) 
	{
		return new BrowserTestObject(
                        getMappedTestObject("IESiebel"), anchor, flags);
	}
	
	/**
	 * LineasActivoCtaFact: with default state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject LineasActivoCtaFact() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasActivoCtaFact"));
	}
	/**
	 * LineasActivoCtaFact: with specific test context and state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject LineasActivoCtaFact(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasActivoCtaFact"), anchor, flags);
	}
	
	/**
	 * SIM: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE SIM
	 */
	protected SiebTextTestObject SIM() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("SIM"));
	}
	/**
	 * SIM: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE SIM
	 */
	protected SiebTextTestObject SIM(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("SIM"), anchor, flags);
	}
	
	/**
	 * Siebel: with default state.
	 *		.title : Siebel Communications
	 * 		.class : Html.HtmlDocument
	 * 		.url : RegularExpression(http.*\/ecommunications_esn/star.*)
	 */
	protected DocumentTestObject Siebel() 
	{
		return new DocumentTestObject(
                        getMappedTestObject("Siebel"));
	}
	/**
	 * Siebel: with specific test context and state.
	 *		.title : Siebel Communications
	 * 		.class : Html.HtmlDocument
	 * 		.url : RegularExpression(http.*\/ecommunications_esn/star.*)
	 */
	protected DocumentTestObject Siebel(TestObject anchor, long flags) 
	{
		return new DocumentTestObject(
                        getMappedTestObject("Siebel"), anchor, flags);
	}
	
	

	protected ValidarActivoSIMenCtaFactHelper()
	{
		setScriptName("Scripts.Comun.ValidarActivoSIMenCtaFact");
	}
	
}
