package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.PruebaHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author Sandra
 */
public class Prueba extends PruebaHelper
{
	/**
	 * Script Name   : <b>Prueba</b>
	 * Generated     : <b>15/03/2017 18:21:06</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2017/03/15
	 * @author Sandra
	 */
	public void testMain(Object[] args) 
	{
		
		// HTML Browser
		// Document: Instituto Constarricense de Electricidad - REMO: RegularExpression(http://cer\.infocom\.ice/REMOWeb.*)
		text_observacion().click(atPoint(108,9));
		text_observacion().click(atPoint(193,8));
		browser_remoBrowser(document_remo(),DEFAULT_FLAGS).inputChars("Observacion ingresada");
		text_servicioTipoServicioDescr().click(atPoint(125,10));
		
		text_observacion().setProperty(".text", "hola");
		stop();
		//list_clienteTipoCedula().setProperty(".value", "Pasaporte");
		//stop();
		
		// HTML Browser
		// Document: Instituto Constarricense de Electricidad - REMO: RegularExpression(http://cer\.infocom\.ice/REMOWeb.*)
		list_clienteTipoCedula().click();
		browser_remoBrowser(document_remo(),DEFAULT_FLAGS).inputKeys("{ExtPgUp}");
		browser_remoBrowser(document_remo(),DEFAULT_FLAGS).inputKeys("{ExtDown}{ENTER}");
		stop();
		// HTML Browser
		browser_remoBrowser(document_remo(),DEFAULT_FLAGS).inputKeys("{LeftAlt}");
		// Document: Instituto Constarricense de Electricidad - REMO: RegularExpression(http://cer\.infocom\.ice/REMOWeb.*)
		list_clienteTipoCedula().click();
		list_clienteTipoCedula().click();
		browser_remoBrowser(document_remo(),DEFAULT_FLAGS).inputKeys("{ExtPgUp}");
		browser_remoBrowser(document_remo(),DEFAULT_FLAGS).inputKeys("{ExtDown}{ExtDown}");
		
		stop();
		// HTML Browser
		// Document: Instituto Constarricense de Electricidad - REMO: RegularExpression(http://cer\.infocom\.ice/REMOWeb.*)
		list_clienteTipoCedula().click();
		list_clienteTipoCedula().click(atText("Pasaporte"));
		

		// HTML Browser
		// Document: Instituto Constarricense de Electricidad - REMO: RegularExpression(http://cer\.infocom\.ice/REMOWeb.*)
		TipoServicio().hover();
		TipoServicio().click(atText("45 - Celular"));
		TipoServicio().hover();
		TipoServicio().click(atText("2 - Eléctrico"));
		comboListBox_htmlComboLBox().click();
		stop();
		TestObject[] to;
		GuiTestObject gto; 
		to = TablaDeudas().find(atChild(".classIndex", "0", ".text", "22564747"));
		gto = 	(GuiTestObject)	to[0];
		

		//to =	(TestObject[]) TablaDeudas().getSubitem(atCell(atRow(2), atColumn(1)));
		//gto = 	(GuiTestObject)	to[0];
		gto.hover(); 
		sleep(3);
		gto.click(); 
		gto.click(); 
		gto.doubleClick(); 
	}
}

