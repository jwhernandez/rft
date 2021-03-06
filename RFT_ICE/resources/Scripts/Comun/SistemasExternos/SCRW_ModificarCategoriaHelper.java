// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.Scripts.Comun.SistemasExternos;
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
 * Script Name   : <b>SCRW_ModificarCategoria</b><br>
 * Generated     : <b>2017/06/20 07:10:44</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  junio 20, 2017
 * @author Sandra
 */
public abstract class SCRW_ModificarCategoriaHelper extends libreria.Accion
{
	/**
	 * Modificarsubmit: with default state.
	 *		.id : 
	 * 		.type : submit
	 * 		.value : Modificar
	 * 		.title : 
	 * 		.class : Html.INPUT.submit
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject btnModificar() 
	{
		return new GuiTestObject(
                        getMappedTestObject("btnModificar"));
	}
	/**
	 * Modificarsubmit: with specific test context and state.
	 *		.id : 
	 * 		.type : submit
	 * 		.value : Modificar
	 * 		.title : 
	 * 		.class : Html.INPUT.submit
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject btnModificar(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("btnModificar"), anchor, flags);
	}
	
	/**
	 * cedula: with default state.
	 *		.id : 
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : cedula
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject cedula() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("cedula"));
	}
	/**
	 * cedula: with specific test context and state.
	 *		.id : 
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : cedula
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject cedula(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("cedula"), anchor, flags);
	}
	
	/**
	 * Consultar: with default state.
	 *		.id : 
	 * 		.type : submit
	 * 		.value : Consultar
	 * 		.title : 
	 * 		.class : Html.INPUT.submit
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject consultaCedula() 
	{
		return new GuiTestObject(
                        getMappedTestObject("consultaCedula"));
	}
	/**
	 * Consultar: with specific test context and state.
	 *		.id : 
	 * 		.type : submit
	 * 		.value : Consultar
	 * 		.title : 
	 * 		.class : Html.INPUT.submit
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject consultaCedula(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("consultaCedula"), anchor, flags);
	}
	
	/**
	 * ConsultarScore: with default state.
	 *		.text : Consultar Score
	 * 		.id : 
	 * 		.href : http://cer.infocom.ice/SCOREWeb/BuscarCliente.do
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject consultarScore() 
	{
		return new GuiTestObject(
                        getMappedTestObject("consultarScore"));
	}
	/**
	 * ConsultarScore: with specific test context and state.
	 *		.text : Consultar Score
	 * 		.id : 
	 * 		.href : http://cer.infocom.ice/SCOREWeb/BuscarCliente.do
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject consultarScore(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("consultarScore"), anchor, flags);
	}
	
	/**
	 * ConsultaScore: with default state.
	 *		.text : Consulta Score
	 * 		.href : http://cer.infocom.ice/SCOREWeb/ClientesCategoriaManual.do#
	 * 		.id : 
	 * 		.title : Consulta Score
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject link_consultaScore() 
	{
		return new GuiTestObject(
                        getMappedTestObject("link_consultaScore"));
	}
	/**
	 * ConsultaScore: with specific test context and state.
	 *		.text : Consulta Score
	 * 		.href : http://cer.infocom.ice/SCOREWeb/ClientesCategoriaManual.do#
	 * 		.id : 
	 * 		.title : Consulta Score
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject link_consultaScore(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("link_consultaScore"), anchor, flags);
	}
	
	/**
	 * categoria: with default state.
	 *		.text : Sin categorķa A B C D E F
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.SELECT
	 * 		.name : categoria
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject listaCategorias() 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("listaCategorias"));
	}
	/**
	 * categoria: with specific test context and state.
	 *		.text : Sin categorķa A B C D E F
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.SELECT
	 * 		.name : categoria
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject listaCategorias(TestObject anchor, long flags) 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("listaCategorias"), anchor, flags);
	}
	
	/**
	 * CategorķaManual: with default state.
	 *		.text : Categorķa Manual
	 * 		.id : 
	 * 		.href : http://cer.infocom.ice/SCOREWeb/ModificarCategoria.do?idProtectoraCliente=110049 ...
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 2
	 */
	protected GuiTestObject modificarCategorķaManual() 
	{
		return new GuiTestObject(
                        getMappedTestObject("modificarCategorķaManual"));
	}
	/**
	 * CategorķaManual: with specific test context and state.
	 *		.text : Categorķa Manual
	 * 		.id : 
	 * 		.href : http://cer.infocom.ice/SCOREWeb/ModificarCategoria.do?idProtectoraCliente=110049 ...
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 2
	 */
	protected GuiTestObject modificarCategorķaManual(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("modificarCategorķaManual"), anchor, flags);
	}
	
	/**
	 * observacion: with default state.
	 *		.id : 
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : observacion
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject observacion() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("observacion"));
	}
	/**
	 * observacion: with specific test context and state.
	 *		.id : 
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : observacion
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject observacion(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("observacion"), anchor, flags);
	}
	
	

	protected SCRW_ModificarCategoriaHelper()
	{
		setScriptName("Scripts.Comun.SistemasExternos.SCRW_ModificarCategoria");
	}
	
}

