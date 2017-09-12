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
 * Script Name   : <b>SeleccionarPedidoCP_Ant</b><br>
 * Generated     : <b>2016/11/24 10:12:47</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  noviembre 24, 2016
 * @author Sandra
 */
public abstract class SeleccionarPedidoCP_AntHelper extends libreria.Accion
{
	/**
	 * NroPedidoCP: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject NroPedidoCP() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoCP"));
	}
	/**
	 * NroPedidoCP: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : OrderNumber
	 */
	protected SiebTextTestObject NroPedidoCP(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("NroPedidoCP"), anchor, flags);
	}
	
	

	protected SeleccionarPedidoCP_AntHelper()
	{
		setScriptName("Scripts.Comun.SeleccionarPedidoCP_Ant");
	}
	
}
