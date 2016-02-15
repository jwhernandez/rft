package Scripts.Comun;
import resources.Scripts.Comun.Manejo_appletsHelper;
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
 * 	 * Script Name   : <b>Manejo_applets</b>
 * @author Sandra
 * 	 * @since  2016/01/26
 */
public class Manejo_applets extends Manejo_appletsHelper
{

	public void testMain(Object[] args) 
	{
		System.out.println(AccountsScreen().getChildren()[2]);
		System.out.println(AccountsScreen().getChildren()[2].getProperties());
		ISiebTestObject ISiebObj = (ISiebTestObject)  AccountsScreen().getChildren()[2];
		//ISiebObj.submit("Select", "SWI Special Rating Items List Applet");
		System.out.println(AccountsScreen().getChildren()[2].getProperties());
		//System.out.println("***" +AccountsScreen().activeApplet());

	}
}

