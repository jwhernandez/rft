package Scripts.Comun.SistemasExternos;
import java.awt.Rectangle;
import java.io.FileWriter;
import java.io.PrintWriter;

import resources.Scripts.Comun.SistemasExternos.getClippedScreenRectangleHelper;
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
public class getClippedScreenRectangle extends getClippedScreenRectangleHelper
{
	/**
	 * Script Name   : <b>Prueba</b>
	 * Generated     : <b>25/08/2016 15:46:37</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/08/25
	 * @author Sandra
	 */
	public void testMain(Object[] args) 
	{
		Rectangle area = new Rectangle();
		area = document_ventanillaElectrónica().getClippedScreenRectangle();
		
		String filename = "C:\\pipi7.jpg";
		document_ventanillaElectrónica().ensureObjectIsVisible();
		CapturarPantalla(filename, area);
		
		area =table_totalColonesComisiónCont().getClippedScreenRectangle();
		table_totalColonesComisiónCont().ensureObjectIsVisible();
		filename = "C:\\pipi8.jpg";
		CapturarPantalla(filename, area);
		
	}
}

