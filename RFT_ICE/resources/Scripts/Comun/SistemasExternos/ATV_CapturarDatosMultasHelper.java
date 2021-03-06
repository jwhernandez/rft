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
 * Script Name   : <b>ATV_CapturarDatosMultas</b><br>
 * Generated     : <b>2016/10/20 11:08:47</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  octubre 20, 2016
 * @author Sandra
 */
public abstract class ATV_CapturarDatosMultasHelper extends libreria.Accion
{
	/**
	 * BtonConsultar: with default state.
	 *		.id : 
	 * 		.type : submit
	 * 		.value : Consultar
	 * 		.title : 
	 * 		.class : Html.INPUT.submit
	 * 		.name : btnConsultar
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Consutar() 
	{
		return new GuiTestObject(
                        getMappedTestObject("Consutar"));
	}
	/**
	 * BtonConsultar: with specific test context and state.
	 *		.id : 
	 * 		.type : submit
	 * 		.value : Consultar
	 * 		.title : 
	 * 		.class : Html.INPUT.submit
	 * 		.name : btnConsultar
	 * 		.classIndex : 0
	 */
	protected GuiTestObject Consutar(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("Consutar"), anchor, flags);
	}
	
	/**
	 * HistóricoDeVentaDeTerminalesYPlanes: with default state.
	 *		.text : Histórico de Venta de Terminales y Planes
	 * 		.id : 
	 * 		.href : http://cer.infocom.ice/atv/ATV/consultas/terminales.cfm?_
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 11
	 */
	protected GuiTestObject HistoricoVentaTerminal_Plan() 
	{
		return new GuiTestObject(
                        getMappedTestObject("HistoricoVentaTerminal_Plan"));
	}
	/**
	 * HistóricoDeVentaDeTerminalesYPlanes: with specific test context and state.
	 *		.text : Histórico de Venta de Terminales y Planes
	 * 		.id : 
	 * 		.href : http://cer.infocom.ice/atv/ATV/consultas/terminales.cfm?_
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 11
	 */
	protected GuiTestObject HistoricoVentaTerminal_Plan(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("HistoricoVentaTerminal_Plan"), anchor, flags);
	}
	
	/**
	 * Inicio: with default state.
	 *		.text : Inicio
	 * 		.id : 
	 * 		.href : http://cer.infocom.ice/atv/ATV/index.cfm
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 3
	 */
	protected GuiTestObject Inicio() 
	{
		return new GuiTestObject(
                        getMappedTestObject("Inicio"));
	}
	/**
	 * Inicio: with specific test context and state.
	 *		.text : Inicio
	 * 		.id : 
	 * 		.href : http://cer.infocom.ice/atv/ATV/index.cfm
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 3
	 */
	protected GuiTestObject Inicio(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("Inicio"), anchor, flags);
	}
	
	/**
	 * list: with default state.
	 *		.id : list
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 1
	 */
	protected StatelessGuiSubitemTestObject ListaTerminalesPlanes() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("ListaTerminalesPlanes"));
	}
	/**
	 * list: with specific test context and state.
	 *		.id : list
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 1
	 */
	protected StatelessGuiSubitemTestObject ListaTerminalesPlanes(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("ListaTerminalesPlanes"), anchor, flags);
	}
	
	/**
	 * Tipo: with default state.
	 *		.text : SERVICIO CUENTA ROWID CTA NUM.ORDEN CEDULA
	 * 		.id : tipo
	 * 		.title : 
	 * 		.class : Html.SELECT
	 * 		.name : tipo
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject Tipo() 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("Tipo"));
	}
	/**
	 * Tipo: with specific test context and state.
	 *		.text : SERVICIO CUENTA ROWID CTA NUM.ORDEN CEDULA
	 * 		.id : tipo
	 * 		.title : 
	 * 		.class : Html.SELECT
	 * 		.name : tipo
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject Tipo(TestObject anchor, long flags) 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("Tipo"), anchor, flags);
	}
	
	/**
	 * ValorTipo: with default state.
	 *		.id : vtipo
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : vtipo
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject ValorTipo() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("ValorTipo"));
	}
	/**
	 * ValorTipo: with specific test context and state.
	 *		.id : vtipo
	 * 		.type : text
	 * 		.title : 
	 * 		.class : Html.INPUT.text
	 * 		.name : vtipo
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject ValorTipo(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("ValorTipo"), anchor, flags);
	}
	
	

	protected ATV_CapturarDatosMultasHelper()
	{
		setScriptName("Scripts.Comun.SistemasExternos.ATV_CapturarDatosMultas");
	}
	
}

