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
 * Script Name   : <b>ValidarNroOrden</b><br>
 * Generated     : <b>2017/03/23 16:21:53</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  marzo 23, 2017
 * @author Sandra
 */
public abstract class ValidarNroOrdenHelper extends libreria.Accion
{
	/**
	 * OrderNumber: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject NroPedido_PI() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedido_PI"));
	}
	/**
	 * OrderNumber: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject NroPedido_PI(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedido_PI"), anchor, flags);
	}
	
	

	protected ValidarNroOrdenHelper()
	{
		setScriptName("Scripts.Comun.ValidarNroOrden");
	}
	
}

