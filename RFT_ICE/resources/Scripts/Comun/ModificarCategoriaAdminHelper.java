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
 * Script Name   : <b>ModificarCategoriaAdmin</b><br>
 * Generated     : <b>2016/03/29 13:55:00</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  marzo 29, 2016
 * @author Sandra
 */
public abstract class ModificarCategoriaAdminHelper extends libreria.Accion
{
	/**
	 * CatCredAdmin: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Credit Category
	 */
	protected SiebTextTestObject CatCredAdmin() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("CatCredAdmin"));
	}
	/**
	 * CatCredAdmin: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Credit Category
	 */
	protected SiebTextTestObject CatCredAdmin(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("CatCredAdmin"), anchor, flags);
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
	 * NroPedido: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject NroPedido() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedido"));
	}
	/**
	 * NroPedido: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject NroPedido(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedido"), anchor, flags);
	}
	
	/**
	 * NroPedidoAdmin: with default state.
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
	 * NroPedidoAdmin: with specific test context and state.
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
	 * Pestaņas: with default state.
	 *		ClassName : SiebPageTabs
	 * 		.class : SiebPageTabs
	 * 		RepositoryName : SiebPageTabs
	 */
	protected SiebPageTabsTestObject PestaņasPedido() 
	{
		return new SiebPageTabsTestObject(
                        getMappedTestObject("PestaņasPedido"));
	}
	/**
	 * Pestaņas: with specific test context and state.
	 *		ClassName : SiebPageTabs
	 * 		.class : SiebPageTabs
	 * 		RepositoryName : SiebPageTabs
	 */
	protected SiebPageTabsTestObject PestaņasPedido(TestObject anchor, long flags) 
	{
		return new SiebPageTabsTestObject(
                        getMappedTestObject("PestaņasPedido"), anchor, flags);
	}
	
	/**
	 * TabsPedido: with default state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject TabsPedido() 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("TabsPedido"));
	}
	/**
	 * TabsPedido: with specific test context and state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject TabsPedido(TestObject anchor, long flags) 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("TabsPedido"), anchor, flags);
	}
	
	

	protected ModificarCategoriaAdminHelper()
	{
		setScriptName("Scripts.Comun.ModificarCategoriaAdmin");
	}
	
}

