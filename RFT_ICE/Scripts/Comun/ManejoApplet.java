package Scripts.Comun;
import resources.Scripts.Comun.ManejoAppletHelper;
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
public class ManejoApplet extends ManejoAppletHelper
{
	/**
	 * Script Name   : <b>ManejoApplet</b>
	 * Generated     : <b>26/01/2016 13:53:01</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/01/26
	 * @author Sandra
	 */
	public void testMain(Object[] args) 
	{
		System.out.println(VistaListaEspecial().getChildren()[0]);
		System.out.println(VistaListaEspecial().getChildren()[0].getProperties());
		ISiebTestObject ISiebObj = (ISiebTestObject)  VistaListaEspecial().getChildren()[0];
		ISiebObj.submit("Select", "SWI Special Rating Items List Applet");
		System.out.println(VistaListaEspecial().getChildren()[0].getProperties());
		System.out.println("***" +VistaListaEspecial().activeApplet());

	}
}

