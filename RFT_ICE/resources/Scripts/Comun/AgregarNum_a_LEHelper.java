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
 * Script Name   : <b>AgregarNum_a_LE</b><br>
 * Generated     : <b>2017/06/22 3:35:57 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  junio 22, 2017
 * @author rft
 */
public abstract class AgregarNum_a_LEHelper extends libreria.Accion
{
	/**
	 * BtonNuevo: with default state.
	 *		.text : Nuevo
	 * 		.href : javascript:void(0)
	 * 		.id : s_2_1_8_0_mb
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
	 * 		.href : javascript:void(0)
	 * 		.id : s_2_1_8_0_mb
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
	 * SiebList: with default state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject NumerosLE() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("NumerosLE"));
	}
	/**
	 * SiebList: with specific test context and state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject NumerosLE(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("NumerosLE"), anchor, flags);
	}
	
	/**
	 * NumerosMenu: with default state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject NumerosMenu() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("NumerosMenu"));
	}
	/**
	 * NumerosMenu: with specific test context and state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject NumerosMenu(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("NumerosMenu"), anchor, flags);
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
	 * 3.- SiebelApp -- SS Ult Modif ss 16 Junio 2017                                            : with default state.
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
	 * 3.- SiebelApp -- SS Ult Modif ss 16 Junio 2017                                            : with specific test context and state.
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
	 * SiebelBar: with default state.
	 *		.docclassIndex : 1
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.FRAME
	 * 		.name : _sweviewbar
	 * 		.classIndex : 1
	 */
	protected StatelessGuiSubitemTestObject SiebelBar() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("SiebelBar"));
	}
	/**
	 * SiebelBar: with specific test context and state.
	 *		.docclassIndex : 1
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.FRAME
	 * 		.name : _sweviewbar
	 * 		.classIndex : 1
	 */
	protected StatelessGuiSubitemTestObject SiebelBar(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("SiebelBar"), anchor, flags);
	}
	
	/**
	 * SiebThreadbar: with default state.
	 *		ClassName : SiebThreadbar
	 * 		.class : SiebThreadbar
	 * 		RepositoryName : SiebThreadbar
	 */
	protected SiebThreadbarTestObject Threadbar() 
	{
		return new SiebThreadbarTestObject(
                        getMappedTestObject("Threadbar"));
	}
	/**
	 * SiebThreadbar: with specific test context and state.
	 *		ClassName : SiebThreadbar
	 * 		.class : SiebThreadbar
	 * 		RepositoryName : SiebThreadbar
	 */
	protected SiebThreadbarTestObject Threadbar(TestObject anchor, long flags) 
	{
		return new SiebThreadbarTestObject(
                        getMappedTestObject("Threadbar"), anchor, flags);
	}
	
	/**
	 * Inactive: with default state.
	 *		ClassName : SiebCheckbox
	 * 		.class : SiebCheckbox
	 * 		RepositoryName : Inactive
	 */
	protected SiebCheckboxTestObject checkBox_inactive() 
	{
		return new SiebCheckboxTestObject(
                        getMappedTestObject("checkBox_inactive"));
	}
	/**
	 * Inactive: with specific test context and state.
	 *		ClassName : SiebCheckbox
	 * 		.class : SiebCheckbox
	 * 		RepositoryName : Inactive
	 */
	protected SiebCheckboxTestObject checkBox_inactive(TestObject anchor, long flags) 
	{
		return new SiebCheckboxTestObject(
                        getMappedTestObject("checkBox_inactive"), anchor, flags);
	}
	
	

	protected AgregarNum_a_LEHelper()
	{
		setScriptName("Scripts.Comun.AgregarNum_a_LE");
	}
	
}
