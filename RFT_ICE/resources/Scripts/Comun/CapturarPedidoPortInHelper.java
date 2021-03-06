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
 * Script Name   : <b>CapturarPedidoPortIn</b><br>
 * Generated     : <b>2016/06/23 11:07:02</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  junio 23, 2016
 * @author Sandra
 */
public abstract class CapturarPedidoPortInHelper extends libreria.Accion
{
	/**
	 * NroPedidoPortabilidad: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Order Number
	 */
	protected SiebTextTestObject NroPedidoPortabilidad() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoPortabilidad"));
	}
	/**
	 * NroPedidoPortabilidad: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Order Number
	 */
	protected SiebTextTestObject NroPedidoPortabilidad(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoPortabilidad"), anchor, flags);
	}
	
	/**
	 * NroPedidoPortabilidadAdmin: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Order Number
	 */
	protected SiebTextTestObject NroPedidoPortabilidadAdmin() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoPortabilidadAdmin"));
	}
	/**
	 * NroPedidoPortabilidadAdmin: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : Order Number
	 */
	protected SiebTextTestObject NroPedidoPortabilidadAdmin(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoPortabilidadAdmin"), anchor, flags);
	}
	
	

	protected CapturarPedidoPortInHelper()
	{
		setScriptName("Scripts.Comun.CapturarPedidoPortIn");
	}
	
}

