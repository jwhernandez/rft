package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.Script1Helper;
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
 * @author rft
 */
public class Script1 extends Script1Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>sept. 28, 2016 11:48:00 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/09/28
	 * @author rft
	 */
	public void testMain(Object[] args) 
	{
		startApp("ATVQA");
		
		// Window: NOTEPAD.EXE: New Text Document - Notepad
		atvgfernandezAsp128text().drag(atPoint(109,7), atPoint(23,9));
		atvgfernandezAsp128text().click(SHIFT_LEFT, atPoint(4,2));
		newTextDocumentNotepadwindow().inputKeys("^c");
		
		// HTML Browser
		// Document: ATV Adm.Term.Vendidas: http://cer.infocom.ice/atv/plantillas/ATV/login.cfm?CFID=8524&CFTOKEN=e89e7b12aa01654a-70C8FBF4-AE2B-45E4-542034B37F905A2E&jsessionid=KsW0DQ5Lj1ragptRYVkqwGW
		text_j_username().click(atPoint(30,15));
		browser_htmlBrowser(document_atvAdmTermVendidas(),DEFAULT_FLAGS).inputKeys("^v");
		
		// Window: NOTEPAD.EXE: New Text Document - Notepad
		atvgfernandezAsp128text().click(atPoint(595,211));
		atvgfernandezAsp128text().click(SHIFT_LEFT, atPoint(112,6));
		newTextDocumentNotepadwindow().inputKeys("^c");
		
		// HTML Browser
		// Document: ATV Adm.Term.Vendidas: http://cer.infocom.ice/atv/plantillas/ATV/login.cfm?CFID=8524&CFTOKEN=e89e7b12aa01654a-70C8FBF4-AE2B-45E4-542034B37F905A2E&jsessionid=KsW0DQ5Lj1ragptRYVkqwGW
		table_htmlTable_0().click(atCell(atRow(atIndex(2)), 
                                   atColumn(atIndex(0))));
		text_j_password().click(atPoint(36,13));
		browser_htmlBrowser(document_atvAdmTermVendidas(),DEFAULT_FLAGS).inputKeys("^v");
		button_conectarsesubmit().click();
		// Document: http://cer.infocom.ice/atv/ATV/index.cfm
		link_posiblesMultasPorCambioDe().click();
		// Document: http://cer.infocom.ice/atv/ATV/consultas/PosibleMultasCP.cfm?_
		link_inicio().click();
		// Document: http://cer.infocom.ice/atv/ATV/index.cfm
		link_históricoDeVentaDeTermina().click();
		// Document: http://cer.infocom.ice/atv/ATV/consultas/terminales.cfm?_
		link_inicio2().click();
		// Document: http://cer.infocom.ice/atv/ATV/index.cfm
		table_htmlTable_0_2().click(atCell(atRow(atIndex(0)), 
                                     atColumn(atIndex(1))));
		browser_htmlBrowser(document_atvAdmTermVendidas(),MAY_EXIT).click(atPoint(1083,12));
		// PENDIENTE Insertar código aquí
	}
}

