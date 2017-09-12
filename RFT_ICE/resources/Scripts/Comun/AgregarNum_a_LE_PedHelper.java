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
 * Script Name   : <b>AgregarNum_a_LE_Ped</b><br>
 * Generated     : <b>2016/12/21 11:52:14</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  diciembre 21, 2016
 * @author Sandra
 */
public abstract class AgregarNum_a_LE_PedHelper extends libreria.Accion
{
	/**
	 * BtonConsultaPedido: with default state.
	 *		.text : Consulta
	 * 		.href : javascript:void(0)
	 * 		.id : s_1_1_151_0_mb
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject BtonConsultaPedido() 
	{
		return new GuiTestObject(
                        getMappedTestObject("BtonConsultaPedido"));
	}
	/**
	 * BtonConsultaPedido: with specific test context and state.
	 *		.text : Consulta
	 * 		.href : javascript:void(0)
	 * 		.id : s_1_1_151_0_mb
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject BtonConsultaPedido(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("BtonConsultaPedido"), anchor, flags);
	}
	
	/**
	 * BtonConsultaPedidoPI: with default state.
	 *		.text : Consulta
	 * 		.href : javascript:void(0)
	 * 		.id : s_4_1_152_0_mb
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject BtonConsultaPedidoPI() 
	{
		return new GuiTestObject(
                        getMappedTestObject("BtonConsultaPedidoPI"));
	}
	/**
	 * BtonConsultaPedidoPI: with specific test context and state.
	 *		.text : Consulta
	 * 		.href : javascript:void(0)
	 * 		.id : s_4_1_152_0_mb
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject BtonConsultaPedidoPI(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("BtonConsultaPedidoPI"), anchor, flags);
	}
	
	/**
	 * BtonNuevo: with default state.
	 *		.text : Nuevo
	 * 		.id : s_2_1_8_0_mb
	 * 		.href : javascript:void(0)
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject BtonNuevo() 
	{
		return new GuiTestObject(
                        getMappedTestObject("BtonNuevo"));
	}
	/**
	 * BtonNuevo: with specific test context and state.
	 *		.text : Nuevo
	 * 		.id : s_2_1_8_0_mb
	 * 		.href : javascript:void(0)
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject BtonNuevo(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("BtonNuevo"), anchor, flags);
	}
	
	/**
	 * NewQuery: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewQuery
	 */
	protected SiebButtonTestObject Buscar() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("Buscar"));
	}
	/**
	 * NewQuery: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewQuery
	 */
	protected SiebButtonTestObject Buscar(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("Buscar"), anchor, flags);
	}
	
	/**
	 * ExecuteQuery: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ExecuteQuery
	 */
	protected SiebButtonTestObject EjecutarBuscar() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("EjecutarBuscar"));
	}
	/**
	 * ExecuteQuery: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ExecuteQuery
	 */
	protected SiebButtonTestObject EjecutarBuscar(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("EjecutarBuscar"), anchor, flags);
	}
	
	/**
	 * LineasPedido: with default state.
	 *		ActiveRow : 9
	 * 		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 * 		SelectedRows : 9
	 */
	protected SiebListTestObject LineasPedido() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasPedido"));
	}
	/**
	 * LineasPedido: with specific test context and state.
	 *		ActiveRow : 9
	 * 		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 * 		SelectedRows : 9
	 */
	protected SiebListTestObject LineasPedido(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasPedido"), anchor, flags);
	}
	
	/**
	 * LE: with default state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject Listas() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("Listas"));
	}
	/**
	 * LE: with specific test context and state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject Listas(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("Listas"), anchor, flags);
	}
	
	/**
	 * LogoICE: with default state.
	 *		.alt : Con tecnolog�a Siebel
	 * 		.id : 
	 * 		.src : RegularExpression(http://.*\/ecommunications_esn/images/ebus\.gif)
	 * 		.title : 
	 * 		.class : Html.IMG
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject LogoICE() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("LogoICE"));
	}
	/**
	 * LogoICE: with specific test context and state.
	 *		.alt : Con tecnolog�a Siebel
	 * 		.id : 
	 * 		.src : RegularExpression(http://.*\/ecommunications_esn/images/ebus\.gif)
	 * 		.title : 
	 * 		.class : Html.IMG
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject LogoICE(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("LogoICE"), anchor, flags);
	}
	
	/**
	 * SpecialRatingListName: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Special Rating List Name
	 */
	protected SiebTextTestObject NombreLE() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NombreLE"));
	}
	/**
	 * SpecialRatingListName: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Special Rating List Name
	 */
	protected SiebTextTestObject NombreLE(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NombreLE"), anchor, flags);
	}
	
	/**
	 * NewRecord: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewRecord
	 */
	protected SiebButtonTestObject NuevoNumero() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("NuevoNumero"));
	}
	/**
	 * NewRecord: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewRecord
	 */
	protected SiebButtonTestObject NuevoNumero(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("NuevoNumero"), anchor, flags);
	}
	
	/**
	 * PhoneNumber: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Phone Number
	 */
	protected SiebTextTestObject NumTel() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NumTel"));
	}
	/**
	 * PhoneNumber: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Phone Number
	 */
	protected SiebTextTestObject NumTel(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NumTel"), anchor, flags);
	}
	
	/**
	 * NumerosLE: with default state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.FORM
	 * 		.name : SWEForm3_0
	 * 		.classIndex : 0
	 */
	protected GuiTestObject NumerosLE() 
	{
		return new GuiTestObject(
                        getMappedTestObject("NumerosLE"));
	}
	/**
	 * NumerosLE: with specific test context and state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.FORM
	 * 		.name : SWEForm3_0
	 * 		.classIndex : 0
	 */
	protected GuiTestObject NumerosLE(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("NumerosLE"), anchor, flags);
	}
	
	/**
	 * TabsCliente: with default state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject Pesta�as() 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("Pesta�as"));
	}
	/**
	 * TabsCliente: with specific test context and state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject Pesta�as(TestObject anchor, long flags) 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("Pesta�as"), anchor, flags);
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
	 * TabsPedidoVta: with default state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject SubPesta�as() 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("SubPesta�as"));
	}
	/**
	 * TabsPedidoVta: with specific test context and state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject SubPesta�as(TestObject anchor, long flags) 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("SubPesta�as"), anchor, flags);
	}
	
	

	protected AgregarNum_a_LE_PedHelper()
	{
		setScriptName("Scripts.Comun.AgregarNum_a_LE_Ped");
	}
	
}
