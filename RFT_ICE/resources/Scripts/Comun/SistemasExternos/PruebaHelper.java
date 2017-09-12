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
 * Script Name   : <b>Prueba</b><br>
 * Generated     : <b>2017/03/15 19:25:02</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows 7 x86 6.1 <br>
 * 
 * @since  marzo 15, 2017
 * @author Sandra
 */
public abstract class PruebaHelper extends libreria.Accion
{
	/**
	 * ec_table: with default state.
	 *		.id : ec_table
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 1
	 */
	protected StatelessGuiSubitemTestObject TablaDeudas() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("TablaDeudas"));
	}
	/**
	 * ec_table: with specific test context and state.
	 *		.id : ec_table
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 1
	 */
	protected StatelessGuiSubitemTestObject TablaDeudas(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("TablaDeudas"), anchor, flags);
	}
	
	/**
	 * tipoServicio: with default state.
	 *		.text : 1 - Telefonico 2 - El�ctrico 3 - Conexi�n Internet Inal�mbrica 4 - Television po ...
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.SELECT
	 * 		.name : tipoServicio
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject TipoServicio() 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("TipoServicio"));
	}
	/**
	 * tipoServicio: with specific test context and state.
	 *		.text : 1 - Telefonico 2 - El�ctrico 3 - Conexi�n Internet Inal�mbrica 4 - Television po ...
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.SELECT
	 * 		.name : tipoServicio
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject TipoServicio(TestObject anchor, long flags) 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("TipoServicio"), anchor, flags);
	}
	
	/**
	 * REMOBrowser: with default state.
	 *		.window : 988974
	 * 		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.processName : iexplore.exe
	 */
	protected BrowserTestObject browser_remoBrowser() 
	{
		return new BrowserTestObject(
                        getMappedTestObject("browser_remoBrowser"));
	}
	/**
	 * REMOBrowser: with specific test context and state.
	 *		.window : 988974
	 * 		.class : Html.HtmlBrowser
	 * 		.browserName : MS Internet Explorer
	 * 		.processName : iexplore.exe
	 */
	protected BrowserTestObject browser_remoBrowser(TestObject anchor, long flags) 
	{
		return new BrowserTestObject(
                        getMappedTestObject("browser_remoBrowser"), anchor, flags);
	}
	
	/**
	 * HtmlComboLBox: with default state.
	 *		.class : Html.ComboLBox
	 */
	protected GuiTestObject comboListBox_htmlComboLBox() 
	{
		return new GuiTestObject(
                        getMappedTestObject("comboListBox_htmlComboLBox"));
	}
	/**
	 * HtmlComboLBox: with specific test context and state.
	 *		.class : Html.ComboLBox
	 */
	protected GuiTestObject comboListBox_htmlComboLBox(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("comboListBox_htmlComboLBox"), anchor, flags);
	}
	
	/**
	 * REMO: with default state.
	 *		.title : Instituto Constarricense de Electricidad - REMO
	 * 		.class : Html.HtmlDocument
	 * 		.url : RegularExpression(http://cer\.infocom\.ice/REMOWeb.*)
	 */
	protected DocumentTestObject document_remo() 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_remo"));
	}
	/**
	 * REMO: with specific test context and state.
	 *		.title : Instituto Constarricense de Electricidad - REMO
	 * 		.class : Html.HtmlDocument
	 * 		.url : RegularExpression(http://cer\.infocom\.ice/REMOWeb.*)
	 */
	protected DocumentTestObject document_remo(TestObject anchor, long flags) 
	{
		return new DocumentTestObject(
                        getMappedTestObject("document_remo"), anchor, flags);
	}
	
	/**
	 * _22564747: with default state.
	 *		.text : 22564747
	 * 		.href : http://cer.infocom.ice/REMOWeb/ConsultaServicioReasignarForm.do?idServicio=44202 ...
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject link__22564747() 
	{
		return new GuiTestObject(
                        getMappedTestObject("link__22564747"));
	}
	/**
	 * _22564747: with specific test context and state.
	 *		.text : 22564747
	 * 		.href : http://cer.infocom.ice/REMOWeb/ConsultaServicioReasignarForm.do?idServicio=44202 ...
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 0
	 */
	protected GuiTestObject link__22564747(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("link__22564747"), anchor, flags);
	}
	
	/**
	 * _22564747: with default state.
	 *		.text : 22564747
	 * 		.href : http://cer.infocom.ice/REMOWeb/ConsultaServicioReasignarForm.do?idServicio=32015 ...
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 1
	 */
	protected GuiTestObject link__22564747_2() 
	{
		return new GuiTestObject(
                        getMappedTestObject("link__22564747_2"));
	}
	/**
	 * _22564747: with specific test context and state.
	 *		.text : 22564747
	 * 		.href : http://cer.infocom.ice/REMOWeb/ConsultaServicioReasignarForm.do?idServicio=32015 ...
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.A
	 * 		.name : 
	 * 		.classIndex : 1
	 */
	protected GuiTestObject link__22564747_2(TestObject anchor, long flags) 
	{
		return new GuiTestObject(
                        getMappedTestObject("link__22564747_2"), anchor, flags);
	}
	
	/**
	 * clienteTipoCedula: with default state.
	 *		.text : Fisica Juridica Pasaporte
	 * 		.id : 
	 * 		.value : Pasaporte
	 * 		.title : 
	 * 		.class : Html.SELECT
	 * 		.selectedIndex : 1
	 * 		.name : cliente.tipoCedula
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject list_clienteTipoCedula() 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("list_clienteTipoCedula"));
	}
	/**
	 * clienteTipoCedula: with specific test context and state.
	 *		.text : Fisica Juridica Pasaporte
	 * 		.id : 
	 * 		.value : Pasaporte
	 * 		.title : 
	 * 		.class : Html.SELECT
	 * 		.selectedIndex : 1
	 * 		.name : cliente.tipoCedula
	 * 		.classIndex : 0
	 */
	protected SelectGuiSubitemTestObject list_clienteTipoCedula(TestObject anchor, long flags) 
	{
		return new SelectGuiSubitemTestObject(
                        getMappedTestObject("list_clienteTipoCedula"), anchor, flags);
	}
	
	/**
	 * HtmlTable_0: with default state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_htmlTable_0() 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_htmlTable_0"));
	}
	/**
	 * HtmlTable_0: with specific test context and state.
	 *		.id : 
	 * 		.title : 
	 * 		.class : Html.TABLE
	 * 		.caption : 
	 * 		.classIndex : 0
	 */
	protected StatelessGuiSubitemTestObject table_htmlTable_0(TestObject anchor, long flags) 
	{
		return new StatelessGuiSubitemTestObject(
                        getMappedTestObject("table_htmlTable_0"), anchor, flags);
	}
	
	/**
	 * observacion: with default state.
	 *		.text : 
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.TEXTAREA
	 * 		.name : observacion
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject text_observacion() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_observacion"));
	}
	/**
	 * observacion: with specific test context and state.
	 *		.text : 
	 * 		.id : 
	 * 		.title : 
	 * 		.class : Html.TEXTAREA
	 * 		.name : observacion
	 * 		.classIndex : 0
	 */
	protected TextGuiTestObject text_observacion(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_observacion"), anchor, flags);
	}
	
	/**
	 * servicioTipoServicioDescripcion: with default state.
	 *		.id : 
	 * 		.type : text
	 * 		.class : Html.INPUT.text
	 * 		.title : 
	 * 		.name : servicio.tipoServicio.descripcion
	 * 		.classIndex : 5
	 */
	protected TextGuiTestObject text_servicioTipoServicioDescr() 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_servicioTipoServicioDescr"));
	}
	/**
	 * servicioTipoServicioDescripcion: with specific test context and state.
	 *		.id : 
	 * 		.type : text
	 * 		.class : Html.INPUT.text
	 * 		.title : 
	 * 		.name : servicio.tipoServicio.descripcion
	 * 		.classIndex : 5
	 */
	protected TextGuiTestObject text_servicioTipoServicioDescr(TestObject anchor, long flags) 
	{
		return new TextGuiTestObject(
                        getMappedTestObject("text_servicioTipoServicioDescr"), anchor, flags);
	}
	
	

	protected PruebaHelper()
	{
		setScriptName("Scripts.Comun.SistemasExternos.Prueba");
	}
	
}
