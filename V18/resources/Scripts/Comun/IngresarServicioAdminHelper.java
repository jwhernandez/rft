// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.Scripts.Comun;

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
 * Script Name   : <b>IngresarServicioAdmin</b><br>
 * Generated     : <b>2015/12/23 21:48:25</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  diciembre 23, 2015
 * @author Sandra
 */
public abstract class IngresarServicioAdminHelper extends RationalTestScript
{
	/**
	 * AceptarMensajeHTMLSiebel: with default state.
	 *		.text : RegularExpression((Aceptar|OK))
	 * 		.class : Html.DialogButton
	 * 		.disabled : false
	 * 		.classIndex : 0
	 */
	protected GuiTestObject AceptarMensaje() 
	{
		return new GuiTestObject(
                        getMappedTestObject("AceptarMensaje"));
	}
	/**
	 * AceptarMensajeHTMLSiebel: with specific test context and state.
	 *		.text : RegularExpression((Aceptar|OK))
	 * 		.class : Html.DialogButton
	 * 		.disabled : false
	 * 		.classIndex : 0
	 */
	protected GuiTestObject AceptarMensaje(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("AceptarMensaje"), anchor, flags);
	}
	
	/**
	 * Status: with default state.
	 *		ClassName : SiebPicklist
	 * 		.class : SiebPicklist
	 * 		RepositoryName : Status
	 */
	protected SiebPicklistTestObject EstadoPedidoAdmin() 
	{
		return new SiebPicklistTestObject(
                        getMappedTestObject("EstadoPedidoAdmin"));
	}
	/**
	 * Status: with specific test context and state.
	 *		ClassName : SiebPicklist
	 * 		.class : SiebPicklist
	 * 		RepositoryName : Status
	 */
	protected SiebPicklistTestObject EstadoPedidoAdmin(TestObject anchor, long flags) 
	{
		return new SiebPicklistTestObject(
                        getMappedTestObject("EstadoPedidoAdmin"), anchor, flags);
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
	 * ICEReleaseNumber: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ICEReleaseNumber
	 */
	protected SiebButtonTestObject LiberarNumero() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("LiberarNumero"));
	}
	/**
	 * ICEReleaseNumber: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ICEReleaseNumber
	 */
	protected SiebButtonTestObject LiberarNumero(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("LiberarNumero"), anchor, flags);
	}
	
	/**
	 * LineasPedidoAdmin: with default state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject LineasPedidoAdmin() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasPedidoAdmin"));
	}
	/**
	 * LineasPedidoAdmin: with specific test context and state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject LineasPedidoAdmin(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasPedidoAdmin"), anchor, flags);
	}
	
	/**
	 * NewQuery: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewQuery
	 */
	protected SiebButtonTestObject NewQuery() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("NewQuery"));
	}
	/**
	 * NewQuery: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewQuery
	 */
	protected SiebButtonTestObject NewQuery(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("NewQuery"), anchor, flags);
	}
	
	/**
	 * OrderNumber: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject NroPedidoAdmin() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoAdmin"));
	}
	/**
	 * OrderNumber: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject NroPedidoAdmin(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoAdmin"), anchor, flags);
	}
	
	/**
	 * ServiceId: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Service Id
	 */
	protected SiebTextTestObject NroServicioAdmin() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroServicioAdmin"));
	}
	/**
	 * ServiceId: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Service Id
	 */
	protected SiebTextTestObject NroServicioAdmin(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroServicioAdmin"), anchor, flags);
	}
	
	/**
	 * Pesta�as: with default state.
	 *		ClassName : SiebPageTabs
	 * 		.class : SiebPageTabs
	 * 		RepositoryName : SiebPageTabs
	 */
	protected SiebPageTabsTestObject PestanasPedido() 
	{
		return new SiebPageTabsTestObject(
                        getMappedTestObject("PestanasPedido"));
	}
	/**
	 * Pesta�as: with specific test context and state.
	 *		ClassName : SiebPageTabs
	 * 		.class : SiebPageTabs
	 * 		RepositoryName : SiebPageTabs
	 */
	protected SiebPageTabsTestObject PestanasPedido(TestObject anchor, long flags) 
	{
		return new SiebPageTabsTestObject(
                        getMappedTestObject("PestanasPedido"), anchor, flags);
	}
	
	/**
	 * SiebelPowerCommunications: with default state.
	 *		ClassName : SiebApplication
	 * 		.class : SiebApplication
	 * 		RepositoryName : Siebel Power Communications
	 */
	protected SiebApplicationTestObject Siebel() 
	{
		return new SiebApplicationTestObject(
                        getMappedTestObject("Siebel"));
	}
	/**
	 * SiebelPowerCommunications: with specific test context and state.
	 *		ClassName : SiebApplication
	 * 		.class : SiebApplication
	 * 		RepositoryName : Siebel Power Communications
	 */
	protected SiebApplicationTestObject Siebel(TestObject anchor, long flags) 
	{
		return new SiebApplicationTestObject(
                        getMappedTestObject("Siebel"), anchor, flags);
	}
	
	

	protected IngresarServicioAdminHelper()
	{
		setScriptName("Scripts.Comun.IngresarServicioAdmin");
	}
	
}

