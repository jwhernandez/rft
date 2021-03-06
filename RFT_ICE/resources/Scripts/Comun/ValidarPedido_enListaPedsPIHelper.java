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
 * Script Name   : <b>ValidarPedido_enListaPedsPI</b><br>
 * Generated     : <b>2017/04/25 1:38:46 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  abril 25, 2017
 * @author rft
 */
public abstract class ValidarPedido_enListaPedsPIHelper extends libreria.Accion
{
	/**
	 * ExecuteQueryAllOrders: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ExecuteQuery
	 */
	protected SiebButtonTestObject ExecuteQueryAllOrders() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("ExecuteQueryAllOrders"));
	}
	/**
	 * ExecuteQueryAllOrders: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ExecuteQuery
	 */
	protected SiebButtonTestObject ExecuteQueryAllOrders(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("ExecuteQueryAllOrders"), anchor, flags);
	}
	
	/**
	 * SiebList: with default state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject ListaPedidosPI() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("ListaPedidosPI"));
	}
	/**
	 * SiebList: with specific test context and state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject ListaPedidosPI(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("ListaPedidosPI"), anchor, flags);
	}
	
	/**
	 * NewQueryAllOrders: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewQuery
	 */
	protected SiebButtonTestObject NewQueryAllOrders() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("NewQueryAllOrders"));
	}
	/**
	 * NewQueryAllOrders: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewQuery
	 */
	protected SiebButtonTestObject NewQueryAllOrders(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("NewQueryAllOrders"), anchor, flags);
	}
	
	/**
	 * NroPedidoAllOrders: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Order Number
	 */
	protected SiebTextTestObject NroPedidoAllOrders() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoAllOrders"));
	}
	/**
	 * NroPedidoAllOrders: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Order Number
	 */
	protected SiebTextTestObject NroPedidoAllOrders(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoAllOrders"), anchor, flags);
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
	 * TabsPedidoPortabilidad: with default state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject TabsPedidoPortabilidad() 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("TabsPedidoPortabilidad"));
	}
	/**
	 * TabsPedidoPortabilidad: with specific test context and state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject TabsPedidoPortabilidad(TestObject anchor, long flags) 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("TabsPedidoPortabilidad"), anchor, flags);
	}
	
	

	protected ValidarPedido_enListaPedsPIHelper()
	{
		setScriptName("Scripts.Comun.ValidarPedido_enListaPedsPI");
	}
	
}

