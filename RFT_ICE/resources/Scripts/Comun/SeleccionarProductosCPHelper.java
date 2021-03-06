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
 * Script Name   : <b>SeleccionarProductosCP</b><br>
 * Generated     : <b>2016/03/30 09:02:05</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  marzo 30, 2016
 * @author Sandra
 */
public abstract class SeleccionarProductosCPHelper extends libreria.Accion
{
	/**
	 * OrderEntryLineItemListAppletSales: with default state.
	 *		RecordCounter : 1 - 10 of 21
	 * 		ClassName : SiebApplet
	 * 		.class : SiebApplet
	 * 		RepositoryName : Order Entry - Line Item List Applet (Sales)
	 */
	protected SiebAppletTestObject AppletLineasPedido() 
	{
		return new SiebAppletTestObject(
                        getMappedTestObject("AppletLineasPedido"));
	}
	/**
	 * OrderEntryLineItemListAppletSales: with specific test context and state.
	 *		RecordCounter : 1 - 10 of 21
	 * 		ClassName : SiebApplet
	 * 		.class : SiebApplet
	 * 		RepositoryName : Order Entry - Line Item List Applet (Sales)
	 */
	protected SiebAppletTestObject AppletLineasPedido(TestObject anchor, long flags) 
	{
		return new SiebAppletTestObject(
                        getMappedTestObject("AppletLineasPedido"), anchor, flags);
	}
	
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
	
	

	protected SeleccionarProductosCPHelper()
	{
		setScriptName("Scripts.Comun.SeleccionarProductosCP");
	}
	
}

