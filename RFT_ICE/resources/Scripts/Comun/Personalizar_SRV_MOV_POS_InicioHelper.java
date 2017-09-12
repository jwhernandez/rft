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
 * Script Name   : <b>Personalizar_SRV_MOV_POS_Inicio</b><br>
 * Generated     : <b>2017/03/28 18:17:36</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  marzo 28, 2017
 * @author Sandra
 */
public abstract class Personalizar_SRV_MOV_POS_InicioHelper extends libreria.Accion
{
	/**
	 * Advertencia: with default state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 1
	 */
	protected StatelessGuiSubitemTestObject Advertencia() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("Advertencia"));
	}
	/**
	 * Advertencia: with specific test context and state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 1
	 */
	protected StatelessGuiSubitemTestObject Advertencia(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("Advertencia"), anchor, flags);
	}
	
	/**
	 * Personalizar: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : Breconfig
	 */
	protected SiebButtonTestObject BtonPersonalizar() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("BtonPersonalizar"));
	}
	/**
	 * Personalizar: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : Breconfig
	 */
	protected SiebButtonTestObject BtonPersonalizar(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("BtonPersonalizar"), anchor, flags);
	}
	
	/**
	 * PersonalizarPI: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : Breconfig
	 */
	protected SiebButtonTestObject BtonPersonalizarPI() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("BtonPersonalizarPI"));
	}
	/**
	 * PersonalizarPI: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : Breconfig
	 */
	protected SiebButtonTestObject BtonPersonalizarPI(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("BtonPersonalizarPI"), anchor, flags);
	}
	
	/**
	 * ButtonOk: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ButtonOk
	 */
	protected SiebButtonTestObject ButtonOk() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("ButtonOk"));
	}
	/**
	 * ButtonOk: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ButtonOk
	 */
	protected SiebButtonTestObject ButtonOk(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("ButtonOk"), anchor, flags);
	}
	
	

	protected Personalizar_SRV_MOV_POS_InicioHelper()
	{
		setScriptName("Scripts.Comun.Personalizar_SRV_MOV_POS_Inicio");
	}
	
}
