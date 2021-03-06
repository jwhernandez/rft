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
 * Script Name   : <b>ValidarNroServicio_SIM_IMEI</b><br>
 * Generated     : <b>2016/11/25 10:36:41</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  noviembre 25, 2016
 * @author Sandra
 */
public abstract class ValidarNroServicio_SIM_IMEIHelper extends libreria.Accion
{
	/**
	 * LineasPedido: with default state.
	 *		ActiveRow : 9
	 * 		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 * 		SelectedRows : 9
	 */
	protected SiebListTestObject LineasPedido() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasPedido"));
	}
	/**
	 * LineasPedido: with specific test context and state.
	 *		ActiveRow : 9
	 * 		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 * 		SelectedRows : 9
	 */
	protected SiebListTestObject LineasPedido(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasPedido"), anchor, flags);
	}
	
	/**
	 * LineasPedidoPI: with default state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject LineasPedidoPI() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasPedidoPI"));
	}
	/**
	 * LineasPedidoPI: with specific test context and state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject LineasPedidoPI(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasPedidoPI"), anchor, flags);
	}
	
	/**
	 * SIM: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE SIM
	 */
	protected SiebTextTestObject SIM() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("SIM"));
	}
	/**
	 * SIM: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE SIM
	 */
	protected SiebTextTestObject SIM(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("SIM"), anchor, flags);
	}
	
	

	protected ValidarNroServicio_SIM_IMEIHelper()
	{
		setScriptName("Scripts.Comun.ValidarNroServicio_SIM_IMEI");
	}
	
}

