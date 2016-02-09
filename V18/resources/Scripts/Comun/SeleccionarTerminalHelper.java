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
 * Script Name   : <b>SeleccionarTerminal</b><br>
 * Generated     : <b>2015/12/28 21:03:17</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  diciembre 28, 2015
 * @author Sandra
 */
public abstract class SeleccionarTerminalHelper extends libreria.Accion
{
	/**
	 * Breconfig: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : Breconfig
	 */
	protected SiebButtonTestObject Personalizar() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("Personalizar"));
	}
	/**
	 * Breconfig: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : Breconfig
	 */
	protected SiebButtonTestObject Personalizar(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("Personalizar"), anchor, flags);
	}
	
	/**
	 * Terminado: with default state.
	 *		.text : Terminado
	 * 		.id : GRPITEM[~^`grpItemId3`^~[LINK
	 * 		.href : javascript:processInput("GRPITEM[~^`grpItemId3", "DoneConfig","linkMethod")
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Terminado() 
	{
		return new GuiTestObject(
                        getMappedTestObject("Terminado"));
	}
	/**
	 * Terminado: with specific test context and state.
	 *		.text : Terminado
	 * 		.id : GRPITEM[~^`grpItemId3`^~[LINK
	 * 		.href : javascript:processInput("GRPITEM[~^`grpItemId3", "DoneConfig","linkMethod")
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Terminado(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("Terminado"), anchor, flags);
	}
	
	/**
	 * _1RZJZBDPORT1DHEG5: with default state.
	 *		.id : RegularExpression(.*PORT.*1-DHEG5)
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject Terminales() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("Terminales"));
	}
	/**
	 * _1RZJZBDPORT1DHEG5: with specific test context and state.
	 *		.id : RegularExpression(.*PORT.*1-DHEG5)
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject Terminales(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("Terminales"), anchor, flags);
	}
	
	/**
	 * GRPITEMGrpItemId13LINK: with default state.
	 *		.text : Terminales Moviles
	 * 		.id : GRPITEM[~^`grpItemId13`^~[LINK
	 * 		.href : javascript:processInput("GRPITEM[~^`grpItemId13", "ChangeCurrentObj","linkMethod ...
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject TerminalesMoviles() 
	{
		return new GuiTestObject(
                        getMappedTestObject("TerminalesMoviles"));
	}
	/**
	 * GRPITEMGrpItemId13LINK: with specific test context and state.
	 *		.text : Terminales Moviles
	 * 		.id : GRPITEM[~^`grpItemId13`^~[LINK
	 * 		.href : javascript:processInput("GRPITEM[~^`grpItemId13", "ChangeCurrentObj","linkMethod ...
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject TerminalesMoviles(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("TerminalesMoviles"), anchor, flags);
	}
	
	

	protected SeleccionarTerminalHelper()
	{
		setScriptName("Scripts.Comun.SeleccionarTerminal");
	}
	
}
