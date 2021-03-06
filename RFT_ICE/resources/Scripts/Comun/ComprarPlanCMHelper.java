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
 * Script Name   : <b>ComprarPlanCM</b><br>
 * Generated     : <b>2016/04/13 22:01:10</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  abril 13, 2016
 * @author Sandra
 */
public abstract class ComprarPlanCMHelper extends libreria.Accion
{
	/**
	 * PlanesCM: with default state.
	 *		ClassName : SiebApplet
	 * 		.class : SiebApplet
	 * 		RepositoryName : ICE Promotion Task List Applet
	 */
	protected SiebAppletTestObject AppletPlanesCM() 
	{
		return new SiebAppletTestObject(
                        getMappedTestObject("AppletPlanesCM"));
	}
	/**
	 * PlanesCM: with specific test context and state.
	 *		ClassName : SiebApplet
	 * 		.class : SiebApplet
	 * 		RepositoryName : ICE Promotion Task List Applet
	 */
	protected SiebAppletTestObject AppletPlanesCM(TestObject anchor, long flags) 
	{
		return new SiebAppletTestObject(
                        getMappedTestObject("AppletPlanesCM"), anchor, flags);
	}
	
	/**
	 * ListaPlanesCM: with default state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject ListaPlanesCM() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("ListaPlanesCM"));
	}
	/**
	 * ListaPlanesCM: with specific test context and state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject ListaPlanesCM(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("ListaPlanesCM"), anchor, flags);
	}
	
	/**
	 * NombrePlanCM: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Name
	 */
	protected SiebTextTestObject NombrePlanCM() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NombrePlanCM"));
	}
	/**
	 * NombrePlanCM: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Name
	 */
	protected SiebTextTestObject NombrePlanCM(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NombrePlanCM"), anchor, flags);
	}
	
	/**
	 * Siguiente: with default state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ButtonNext
	 */
	protected SiebButtonTestObject Siguiente() 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("Siguiente"));
	}
	/**
	 * Siguiente: with specific test context and state.
	 *		ClassName : SiebButton
	 * 		.class : SiebButton
	 * 		RepositoryName : ButtonNext
	 */
	protected SiebButtonTestObject Siguiente(TestObject anchor, long flags) 
	{
		return new SiebButtonTestObject(
                        getMappedTestObject("Siguiente"), anchor, flags);
	}
	
	

	protected ComprarPlanCMHelper()
	{
		setScriptName("Scripts.Comun.ComprarPlanCM");
	}
	
}

