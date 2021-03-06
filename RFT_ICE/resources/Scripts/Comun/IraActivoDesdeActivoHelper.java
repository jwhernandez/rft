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
 * Script Name   : <b>IraActivoDesdeActivo</b><br>
 * Generated     : <b>2017/06/01 9:52:54 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  junio 01, 2017
 * @author rft
 */
public abstract class IraActivoDesdeActivoHelper extends libreria.Accion
{
	/**
	 * Estado: with default state.
	 *		ClassName : SiebPicklist
	 * 		.class : SiebPicklist
	 * 		RepositoryName : Status
	 */
	protected SiebPicklistTestObject Estado() 
	{
		return new SiebPicklistTestObject(
                        getMappedTestObject("Estado"));
	}
	/**
	 * Estado: with specific test context and state.
	 *		ClassName : SiebPicklist
	 * 		.class : SiebPicklist
	 * 		RepositoryName : Status
	 */
	protected SiebPicklistTestObject Estado(TestObject anchor, long flags) 
	{
		return new SiebPicklistTestObject(
                        getMappedTestObject("Estado"), anchor, flags);
	}
	
	/**
	 * ExecuteQuery: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ExecuteQuery
	 */
	protected SiebButtonTestObject ExecuteQuery() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("ExecuteQuery"));
	}
	/**
	 * ExecuteQuery: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ExecuteQuery
	 */
	protected SiebButtonTestObject ExecuteQuery(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("ExecuteQuery"), anchor, flags);
	}
	
	/**
	 * NroServicio: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Serial Number
	 */
	protected SiebTextTestObject NroServicio() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroServicio"));
	}
	/**
	 * NroServicio: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Serial Number
	 */
	protected SiebTextTestObject NroServicio(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroServicio"), anchor, flags);
	}
	
	/**
	 * Pesta�as: with default state.
	 *		ClassName : SiebPageTabs
	 * 		.class : SiebPageTabs
	 * 		RepositoryName : SiebPageTabs
	 */
	protected SiebPageTabsTestObject Pesta�as() 
	{
		return new SiebPageTabsTestObject(
                        getMappedTestObject("Pesta�as"));
	}
	/**
	 * Pesta�as: with specific test context and state.
	 *		ClassName : SiebPageTabs
	 * 		.class : SiebPageTabs
	 * 		RepositoryName : SiebPageTabs
	 */
	protected SiebPageTabsTestObject Pesta�as(TestObject anchor, long flags) 
	{
		return new SiebPageTabsTestObject(
                        getMappedTestObject("Pesta�as"), anchor, flags);
	}
	
	/**
	 * SiebList: with default state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject SiebList() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("SiebList"));
	}
	/**
	 * SiebList: with specific test context and state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject SiebList(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("SiebList"), anchor, flags);
	}
	
	/**
	 * SiebMenu: with default state.
	 *		ClassName : SiebMenu
	 * 		.class : SiebMenu
	 * 		RepositoryName : SiebMenu
	 */
	protected SiebMenuTestObject SiebMenu() 
	{
		return new SiebMenuTestObject(
                        getMappedTestObject("SiebMenu"));
	}
	/**
	 * SiebMenu: with specific test context and state.
	 *		ClassName : SiebMenu
	 * 		.class : SiebMenu
	 * 		RepositoryName : SiebMenu
	 */
	protected SiebMenuTestObject SiebMenu(TestObject anchor, long flags) 
	{
		return new SiebMenuTestObject(
                        getMappedTestObject("SiebMenu"), anchor, flags);
	}
	
	/**
	 * SiebScreenViews: with default state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject SiebScreenViews() 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("SiebScreenViews"));
	}
	/**
	 * SiebScreenViews: with specific test context and state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject SiebScreenViews(TestObject anchor, long flags) 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("SiebScreenViews"), anchor, flags);
	}
	
	/**
	 * htmlBrowser: with default state.
	 *		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.processName : iexplore.exe
	 */
	protected BrowserTestObject browser_htmlBrowser() 
	{
		return new BrowserTestObject(
                        getMappedTestObject("browser_htmlBrowser"));
	}
	/**
	 * htmlBrowser: with specific test context and state.
	 *		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.processName : iexplore.exe
	 */
	protected BrowserTestObject browser_htmlBrowser(TestObject anchor, long flags) 
	{
		return new BrowserTestObject(
                        getMappedTestObject("browser_htmlBrowser"), anchor, flags);
	}
	
	/**
	 * IBMRationalFunctionalTesterXMLLog: with default state.
	 *		.title : IBM Rational Functional Tester XML log
	 * 		.class : Html.HtmlDocument
	 * 		.url : file://C:\Users\rft\IBM\rationalsdp\workspace\RFT_ICE_logs\Scripts.CasosPrueba.C ...
	 */
	protected DocumentTestObject document_ibmRationalFunctional() 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_ibmRationalFunctional"));
	}
	/**
	 * IBMRationalFunctionalTesterXMLLog: with specific test context and state.
	 *		.title : IBM Rational Functional Tester XML log
	 * 		.class : Html.HtmlDocument
	 * 		.url : file://C:\Users\rft\IBM\rationalsdp\workspace\RFT_ICE_logs\Scripts.CasosPrueba.C ...
	 */
	protected DocumentTestObject document_ibmRationalFunctional(TestObject anchor, long flags) 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_ibmRationalFunctional"), anchor, flags);
	}
	
	/**
	 * TodosLosActivos: with default state.
	 *		.title : Todos los activos
	 * 		.class : Html.HtmlDocument
	 * 		.url : http://crmqa.intranet.ice/ecommunications_esn/start.swe?SWECmd=GetViewLayout&SWE ...
	 */
	protected DocumentTestObject document_todosLosActivos() 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_todosLosActivos"));
	}
	/**
	 * TodosLosActivos: with specific test context and state.
	 *		.title : Todos los activos
	 * 		.class : Html.HtmlDocument
	 * 		.url : http://crmqa.intranet.ice/ecommunications_esn/start.swe?SWECmd=GetViewLayout&SWE ...
	 */
	protected DocumentTestObject document_todosLosActivos(TestObject anchor, long flags) 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_todosLosActivos"), anchor, flags);
	}
	
	/**
	 * img_arrow: with default state.
	 *		.alt : 
	 * 		.id : 
	 * 		.src : http://crmqa.intranet.ice/ecommunications_esn/images/img_arrow.gif
	 * 		.class : Html.IMG
	 * 		.title : 
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject image_img_arrow() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("image_img_arrow"));
	}
	/**
	 * img_arrow: with specific test context and state.
	 *		.alt : 
	 * 		.id : 
	 * 		.src : http://crmqa.intranet.ice/ecommunications_esn/images/img_arrow.gif
	 * 		.class : Html.IMG
	 * 		.title : 
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject image_img_arrow(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("image_img_arrow"), anchor, flags);
	}
	
	/**
	 * HtmlTable_0: with default state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_htmlTable_0() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_htmlTable_0"));
	}
	/**
	 * HtmlTable_0: with specific test context and state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_htmlTable_0(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_htmlTable_0"), anchor, flags);
	}
	
	/**
	 * HtmlTable_0: with default state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_htmlTable_0_2() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_htmlTable_0_2"));
	}
	/**
	 * HtmlTable_0: with specific test context and state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_htmlTable_0_2(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_htmlTable_0_2"), anchor, flags);
	}
	
	

	protected IraActivoDesdeActivoHelper()
	{
		setScriptName("Scripts.Comun.IraActivoDesdeActivo");
	}
	
}

