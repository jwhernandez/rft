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
 * Script Name   : <b>CambiarUsuario</b><br>
 * Generated     : <b>2016/04/08 09:19:23</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  abril 08, 2016
 * @author Sandra
 */
public abstract class CambiarUsuarioHelper extends libreria.Accion
{
	/**
	 * Archivo: with default state.
	 *		.text : Archivo
	 * 		.class : Html.TextNode
	 * 		.classIndex : 0
	 */
	protected GuiTestObject textNode_archivo() 
	{
		return new GuiTestObject(
                        getMappedTestObject("textNode_archivo"));
	}
	/**
	 * Archivo: with specific test context and state.
	 *		.text : Archivo
	 * 		.class : Html.TextNode
	 * 		.classIndex : 0
	 */
	protected GuiTestObject textNode_archivo(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("textNode_archivo"), anchor, flags);
	}
	
	

	protected CambiarUsuarioHelper()
	{
		setScriptName("Scripts.Comun.CambiarUsuario");
	}
	
}
