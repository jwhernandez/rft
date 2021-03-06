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
 * Script Name   : <b>ValidarBtonLiberarNumero</b><br>
 * Generated     : <b>2016/04/06 16:33:19</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  abril 06, 2016
 * @author Sandra
 */
public abstract class ValidarBtonLiberarNumeroHelper extends libreria.Accion
{
	/**
	 * BtonLiberarNumero: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ICEReleaseNumber
	 */
	protected SiebButtonTestObject BtonLiberarNumero() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("BtonLiberarNumero"));
	}
	/**
	 * BtonLiberarNumero: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ICEReleaseNumber
	 */
	protected SiebButtonTestObject BtonLiberarNumero(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("BtonLiberarNumero"), anchor, flags);
	}
	
	

	protected ValidarBtonLiberarNumeroHelper()
	{
		setScriptName("Scripts.Comun.ValidarBtonLiberarNumero");
	}
	
}

