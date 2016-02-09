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
 * Script Name   : <b>PagarRED</b><br>
 * Generated     : <b>2015/12/16 11:30:27</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  diciembre 16, 2015
 * @author Sandra
 */
public abstract class PagarREDHelper extends RationalTestScript
{
	/**
	 * ICEEstadoRED: with default state.
	 *		ClassName : SiebPicklist
	 * 		.class : SiebPicklist
	 * 		RepositoryName : ICEEstadoRED
	 */
	protected SiebPicklistTestObject EstadoPedidoAdmin() 
	{
		return new SiebPicklistTestObject(
                        getMappedTestObject("EstadoPedidoAdmin"));
	}
	/**
	 * ICEEstadoRED: with specific test context and state.
	 *		ClassName : SiebPicklist
	 * 		.class : SiebPicklist
	 * 		RepositoryName : ICEEstadoRED
	 */
	protected SiebPicklistTestObject EstadoPedidoAdmin(TestObject anchor, long flags) 
	{
		return new SiebPicklistTestObject(
                        getMappedTestObject("EstadoPedidoAdmin"), anchor, flags);
	}
	
	/**
	 * Status: with default state.
	 *		ClassName : SiebPicklist
	 * 		.class : SiebPicklist
	 * 		RepositoryName : Status
	 */
	protected SiebPicklistTestObject EstadoREDAdmin() 
	{
		return new SiebPicklistTestObject(
                        getMappedTestObject("EstadoREDAdmin"));
	}
	/**
	 * Status: with specific test context and state.
	 *		ClassName : SiebPicklist
	 * 		.class : SiebPicklist
	 * 		RepositoryName : Status
	 */
	protected SiebPicklistTestObject EstadoREDAdmin(TestObject anchor, long flags) 
	{
		return new SiebPicklistTestObject(
                        getMappedTestObject("EstadoREDAdmin"), anchor, flags);
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
	 * ICEMontoRED: with default state.
	 *		ClassName : SiebCalculator
	 * 		.class : SiebCalculator
	 * 		RepositoryName : ICEMontoRED
	 */
	protected SiebCalculatorTestObject MontoREDAdmin() 
	{
		return new SiebCalculatorTestObject(
                        getMappedTestObject("MontoREDAdmin"));
	}
	/**
	 * ICEMontoRED: with specific test context and state.
	 *		ClassName : SiebCalculator
	 * 		.class : SiebCalculator
	 * 		RepositoryName : ICEMontoRED
	 */
	protected SiebCalculatorTestObject MontoREDAdmin(TestObject anchor, long flags) 
	{
		return new SiebCalculatorTestObject(
                        getMappedTestObject("MontoREDAdmin"), anchor, flags);
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
	 * ICERecipientNumber: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Recipient Number
	 */
	protected SiebTextTestObject NroREDAdmin() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroREDAdmin"));
	}
	/**
	 * ICERecipientNumber: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Recipient Number
	 */
	protected SiebTextTestObject NroREDAdmin(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroREDAdmin"), anchor, flags);
	}
	
	/**
	 * SiebPageTabs: with default state.
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
	 * SiebPageTabs: with specific test context and state.
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
	 * OrderNumber: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject text_orderNumber() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("text_orderNumber"));
	}
	/**
	 * OrderNumber: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject text_orderNumber(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("text_orderNumber"), anchor, flags);
	}
	
	

	protected PagarREDHelper()
	{
		setScriptName("Scripts.Comun.PagarRED");
	}
	
}
