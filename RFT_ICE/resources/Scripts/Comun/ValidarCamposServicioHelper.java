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
 * Script Name   : <b>ValidarCamposServicio</b><br>
 * Generated     : <b>2017/04/25 12:00:20 PM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  abril 25, 2017
 * @author rft
 */
public abstract class ValidarCamposServicioHelper extends libreria.Accion
{
	/**
	 * IMSI: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE IMSI
	 */
	protected SiebTextTestObject IMSI() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("IMSI"));
	}
	/**
	 * IMSI: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE IMSI
	 */
	protected SiebTextTestObject IMSI(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("IMSI"), anchor, flags);
	}
	
	/**
	 * ICEIMSI: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE IMSI
	 */
	protected SiebTextTestObject IMSI_PI() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("IMSI_PI"));
	}
	/**
	 * ICEIMSI: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE IMSI
	 */
	protected SiebTextTestObject IMSI_PI(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("IMSI_PI"), anchor, flags);
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
	
	/**
	 * LineasPedidoPI: with default state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject LineasPedido_PI() 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasPedido_PI"));
	}
	/**
	 * LineasPedidoPI: with specific test context and state.
	 *		ClassName : SiebList
	 * 		.class : SiebList
	 * 		RepositoryName : SiebList
	 */
	protected SiebListTestObject LineasPedido_PI(TestObject anchor, long flags) 
	{
		return new SiebListTestObject(
                        getMappedTestObject("LineasPedido_PI"), anchor, flags);
	}
	
	/**
	 * Tecnologia: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE SIM Tech
	 */
	protected SiebTextTestObject SIMTech() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("SIMTech"));
	}
	/**
	 * Tecnologia: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE SIM Tech
	 */
	protected SiebTextTestObject SIMTech(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("SIMTech"), anchor, flags);
	}
	
	/**
	 * ICESIMTech: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE SIM Tech
	 */
	protected SiebTextTestObject SIMTech_PI() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("SIMTech_PI"));
	}
	/**
	 * ICESIMTech: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE SIM Tech
	 */
	protected SiebTextTestObject SIMTech_PI(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("SIMTech_PI"), anchor, flags);
	}
	
	

	protected ValidarCamposServicioHelper()
	{
		setScriptName("Scripts.Comun.ValidarCamposServicio");
	}
	
}

