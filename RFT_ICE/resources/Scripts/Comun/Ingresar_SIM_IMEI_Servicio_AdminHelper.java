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
 * Script Name   : <b>Ingresar_SIM_IMEI_Servicio_Admin</b><br>
 * Generated     : <b>2016/12/14 12:04:35</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  diciembre 14, 2016
 * @author Sandra
 */
public abstract class Ingresar_SIM_IMEI_Servicio_AdminHelper extends libreria.Accion
{
	/**
	 * EstadoPedidoAdmin: with default state.
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
	 * EstadoPedidoAdmin: with specific test context and state.
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
	protected SiebButtonTestObject ExecuteQuery_Ant() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("ExecuteQuery_Ant"));
	}
	/**
	 * ExecuteQuery: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ExecuteQuery
	 */
	protected SiebButtonTestObject ExecuteQuery_Ant(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("ExecuteQuery_Ant"), anchor, flags);
	}
	
	/**
	 * ICEIMEI: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE IMEI
	 */
	protected SiebTextTestObject IMEI() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("IMEI"));
	}
	/**
	 * ICEIMEI: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE IMEI
	 */
	protected SiebTextTestObject IMEI(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("IMEI"), anchor, flags);
	}
	
	/**
	 * ICEIMSI: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE IMSI
	 */
	protected SiebTextTestObject IMSI() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("IMSI"));
	}
	/**
	 * ICEIMSI: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE IMSI
	 */
	protected SiebTextTestObject IMSI(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("IMSI"), anchor, flags);
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
	 * SiebMenu: with default state.
	 *		ClassName : SiebMenu
	 * 		.class : SiebMenu
	 * 		RepositoryName : SiebMenu
	 */
	protected SiebMenuTestObject Menu() 
	{
		return new SiebMenuTestObject(
                        getMappedTestObject("Menu"));
	}
	/**
	 * SiebMenu: with specific test context and state.
	 *		ClassName : SiebMenu
	 * 		.class : SiebMenu
	 * 		RepositoryName : SiebMenu
	 */
	protected SiebMenuTestObject Menu(TestObject anchor, long flags) 
	{
		return new SiebMenuTestObject(
                        getMappedTestObject("Menu"), anchor, flags);
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
	 * NewQuery: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewQuery
	 */
	protected SiebButtonTestObject NewQueryAnt() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("NewQueryAnt"));
	}
	/**
	 * NewQuery: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewQuery
	 */
	protected SiebButtonTestObject NewQueryAnt(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("NewQueryAnt"), anchor, flags);
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
	 * OrderNumber: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject NroPedidoAdmin_Ant() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoAdmin_Ant"));
	}
	/**
	 * OrderNumber: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject NroPedidoAdmin_Ant(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoAdmin_Ant"), anchor, flags);
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
	 * ICESIM: with default state.
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
	 * ICESIM: with specific test context and state.
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
	
	/**
	 * ExecuteQuery: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ExecuteQuery
	 */
	protected SiebButtonTestObject button_executeQuery() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("button_executeQuery"));
	}
	/**
	 * ExecuteQuery: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ExecuteQuery
	 */
	protected SiebButtonTestObject button_executeQuery(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("button_executeQuery"), anchor, flags);
	}
	
	

	protected Ingresar_SIM_IMEI_Servicio_AdminHelper()
	{
		setScriptName("Scripts.Comun.Ingresar_SIM_IMEI_Servicio_Admin");
	}
	
}
