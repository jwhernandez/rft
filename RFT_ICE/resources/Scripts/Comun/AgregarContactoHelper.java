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
 * Script Name   : <b>AgregarContacto</b><br>
 * Generated     : <b>2016/01/28 12:45:24</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  enero 28, 2016
 * @author Sandra
 */
public abstract class AgregarContactoHelper extends libreria.Accion
{
	/**
	 * ContactLastName: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Contact Last Name
	 */
	protected SiebTextTestObject Contacto() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("Contacto"));
	}
	/**
	 * ContactLastName: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Contact Last Name
	 */
	protected SiebTextTestObject Contacto(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("Contacto"), anchor, flags);
	}
	
	/**
	 * SocialSecurityNumber: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Social Security Number
	 */
	protected SiebTextTestObject Identificacion() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("Identificacion"));
	}
	/**
	 * SocialSecurityNumber: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Social Security Number
	 */
	protected SiebTextTestObject Identificacion(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("Identificacion"), anchor, flags);
	}
	
	/**
	 * NewQuery: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewQuery
	 */
	protected SiebButtonTestObject NewQuery() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("NewQuery"));
	}
	/**
	 * NewQuery: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : NewQuery
	 */
	protected SiebButtonTestObject NewQuery(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("NewQuery"), anchor, flags);
	}
	
	/**
	 * PickRecord: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : PickRecord
	 */
	protected SiebButtonTestObject PickRecord() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("PickRecord"));
	}
	/**
	 * PickRecord: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : PickRecord
	 */
	protected SiebButtonTestObject PickRecord(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("PickRecord"), anchor, flags);
	}
	
	/**
	 * CloseApplet: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : CloseApplet
	 */
	protected SiebButtonTestObject button_closeApplet() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("button_closeApplet"));
	}
	/**
	 * CloseApplet: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : CloseApplet
	 */
	protected SiebButtonTestObject button_closeApplet(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("button_closeApplet"), anchor, flags);
	}
	
	

	protected AgregarContactoHelper()
	{
		setScriptName("Scripts.Comun.AgregarContacto");
	}
	
}
