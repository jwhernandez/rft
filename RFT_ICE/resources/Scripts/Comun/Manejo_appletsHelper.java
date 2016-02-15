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
 * Script Name   : <b>Manejo_applets</b><br>
 * Generated     : <b>2016/01/26 13:59:34</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  enero 26, 2016
 * @author Sandra
 */
public abstract class Manejo_appletsHelper extends libreria.Accion
{
	/**
	 * AccountsScreen: with default state.
	 *		ClassName : SiebScreen
	 * 		.class : SiebScreen
	 * 		RepositoryName : Accounts Screen
	 */
	protected SiebScreenTestObject AccountsScreen() 
	{
		return new SiebScreenTestObject(
                        getMappedTestObject("AccountsScreen"));
	}
	/**
	 * AccountsScreen: with specific test context and state.
	 *		ClassName : SiebScreen
	 * 		.class : SiebScreen
	 * 		RepositoryName : Accounts Screen
	 */
	protected SiebScreenTestObject AccountsScreen(TestObject anchor, long flags) 
	{
		return new SiebScreenTestObject(
                        getMappedTestObject("AccountsScreen"), anchor, flags);
	}
	
	

	protected Manejo_appletsHelper()
	{
		setScriptName("Scripts.Comun.Manejo_applets");
	}
	
}

