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
 * Script Name   : <b>Personalizar_Fin</b><br>
 * Generated     : <b>2017/03/30 13:08:59</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  marzo 30, 2017
 * @author Sandra
 */
public abstract class Personalizar_FinHelper extends libreria.Accion
{
	/**
	 * Finalicelo: with default state.
	 *		.text : Finalícelo
	 * 		.href : RegularExpression(javascript:processInput\("GRPITEM.*"FinishIt","linkMethod"\))
	 * 		.id : RegularExpression(GRPITEM.*LINK)
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Finalicelo() 
	{
		return new GuiTestObject(
                        getMappedTestObject("Finalicelo"));
	}
	/**
	 * Finalicelo: with specific test context and state.
	 *		.text : Finalícelo
	 * 		.href : RegularExpression(javascript:processInput\("GRPITEM.*"FinishIt","linkMethod"\))
	 * 		.id : RegularExpression(GRPITEM.*LINK)
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Finalicelo(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("Finalicelo"), anchor, flags);
	}
	
	/**
	 * Terminado: with default state.
	 *		.text : Terminado
	 * 		.href : RegularExpression(javascript:processInput\("GRPITEM.*"DoneConfig","linkMethod"\) ...
	 * 		.id : RegularExpression(GRPITEM.*LINK)
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Terminar() 
	{
		return new GuiTestObject(
                        getMappedTestObject("Terminar"));
	}
	/**
	 * Terminado: with specific test context and state.
	 *		.text : Terminado
	 * 		.href : RegularExpression(javascript:processInput\("GRPITEM.*"DoneConfig","linkMethod"\) ...
	 * 		.id : RegularExpression(GRPITEM.*LINK)
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Terminar(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("Terminar"), anchor, flags);
	}
	
	

	protected Personalizar_FinHelper()
	{
		setScriptName("Scripts.Comun.Personalizar_Fin");
	}
	
}

