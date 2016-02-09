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
 * Script Name   : <b>ValidarCamposTerminal</b><br>
 * Generated     : <b>2016/01/21 12:30:28</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  enero 21, 2016
 * @author Sandra
 */
public abstract class ValidarCamposTerminalHelper extends libreria.Accion
{
	/**
	 * Arbol_lista: with default state.
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
	 * Arbol_lista: with specific test context and state.
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
	 * ICEMarca: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Marca
	 */
	protected SiebTextTestObject Marca() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("Marca"));
	}
	/**
	 * ICEMarca: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Marca
	 */
	protected SiebTextTestObject Marca(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("Marca"), anchor, flags);
	}
	
	/**
	 * ICEModelo: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Modelo
	 */
	protected SiebTextTestObject Modelo() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("Modelo"));
	}
	/**
	 * ICEModelo: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Modelo
	 */
	protected SiebTextTestObject Modelo(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("Modelo"), anchor, flags);
	}
	
	/**
	 * ICESerie: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Serie
	 */
	protected SiebTextTestObject Serie() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("Serie"));
	}
	/**
	 * ICESerie: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Serie
	 */
	protected SiebTextTestObject Serie(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("Serie"), anchor, flags);
	}
	
	/**
	 * ICEVersionSoftware: with default state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Version Software
	 */
	protected SiebTextTestObject VersionSoftware() 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("VersionSoftware"));
	}
	/**
	 * ICEVersionSoftware: with specific test context and state.
	 *		ClassName : SiebText
	 * 		.class : SiebText
	 * 		RepositoryName : ICE Version Software
	 */
	protected SiebTextTestObject VersionSoftware(TestObject anchor, long flags) 
	{
		return new SiebTextTestObject(
                        getMappedTestObject("VersionSoftware"), anchor, flags);
	}
	
	

	protected ValidarCamposTerminalHelper()
	{
		setScriptName("Scripts.Comun.ValidarCamposTerminal");
	}
	
}

