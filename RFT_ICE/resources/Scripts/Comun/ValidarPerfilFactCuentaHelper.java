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
 * Script Name   : <b>ValidarPerfilFactCuenta</b><br>
 * Generated     : <b>2015/12/13 13:37:19</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  diciembre 13, 2015
 * @author Sandra
 */
public abstract class ValidarPerfilFactCuentaHelper extends libreria.Accion
{
	/**
	 * SiebList: with default state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject PerfilFact() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("PerfilFact"));
	}
	/**
	 * SiebList: with specific test context and state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject PerfilFact(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("PerfilFact"), anchor, flags);
	}
	
	

	protected ValidarPerfilFactCuentaHelper()
	{
		setScriptName("Scripts.Comun.ValidarPerfilFactCuenta");
	}
	
}
