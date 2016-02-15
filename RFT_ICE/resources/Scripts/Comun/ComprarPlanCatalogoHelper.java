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
 * Script Name   : <b>ComprarPlanCatalogo</b><br>
 * Generated     : <b>2016/01/11 08:04:48</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  enero 11, 2016
 * @author Sandra
 */
public abstract class ComprarPlanCatalogoHelper extends libreria.Accion
{
	/**
	 * ListAddToCart: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ListAddToCart
	 */
	protected SiebButtonTestObject Agregar() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("Agregar"));
	}
	/**
	 * ListAddToCart: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ListAddToCart
	 */
	protected SiebButtonTestObject Agregar(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("Agregar"), anchor, flags);
	}
	
	/**
	 * Search: with default state.
	 *		.text : B�squeda
	 * 		.href : javascript:void(0)
	 * 		.id : s_6_1_4_0_si
	 * 		OT : SiebWebnull
	 * 		.title : 
	 * 		.class : Html.A
	 * 		RN : Search
	 * 		.name : 
	 * 		UN : Search
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Buscar() 
	{
		return new GuiTestObject(
                        getMappedTestObject("Buscar"));
	}
	/**
	 * Search: with specific test context and state.
	 *		.text : B�squeda
	 * 		.href : javascript:void(0)
	 * 		.id : s_6_1_4_0_si
	 * 		OT : SiebWebnull
	 * 		.title : 
	 * 		.class : Html.A
	 * 		RN : Search
	 * 		.name : 
	 * 		UN : Search
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Buscar(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("Buscar"), anchor, flags);
	}
	
	/**
	 * Nombre: with default state.
	 *		.id : 
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : Nombre
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject Nombre() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("Nombre"));
	}
	/**
	 * Nombre: with specific test context and state.
	 *		.id : 
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : Nombre
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject Nombre(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("Nombre"), anchor, flags);
	}
	
	/**
	 * SiebScreenViews: with default state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject Pesta�asPedido() 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("Pesta�asPedido"));
	}
	/**
	 * SiebScreenViews: with specific test context and state.
	 *		ClassName : SiebScreenViews
	 * 		.class : SiebScreenViews
	 * 		RepositoryName : SiebScreenViews
	 */
	protected SiebScreenViewsTestObject Pesta�asPedido(TestObject anchor, long flags) 
	{
		return new SiebScreenViewsTestObject(
                        getMappedTestObject("Pesta�asPedido"), anchor, flags);
	}
	
	

	protected ComprarPlanCatalogoHelper()
	{
		setScriptName("Scripts.Comun.ComprarPlanCatalogo");
	}
	
}

