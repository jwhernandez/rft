package Scripts.Comun;
import resources.Scripts.Comun.FindHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.siebel.ISiebTestObject;


/**
 * Description   : Functional Test Script
 * @author Sandra
 */
public class Find extends FindHelper
{
/**
	 * Script Name   : <b>Find</b>
	 * Generated     : <b>14/01/2016 08:29:15</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/01/14
	 * @author Sandra
	 */
	public void testMain(Object[] args) 
	{
		// PENDIENTE Insertar código aquí
		System.out.println(siebView_swiSpecialRatingProfi().getChildren()[0]);
		System.out.println(siebView_swiSpecialRatingProfi().getChildren()[0].getProperties());
		ISiebTestObject ISiebObj = (ISiebTestObject)  siebView_swiSpecialRatingProfi().getChildren()[0];
		ISiebObj.submit("Select", "SWI Special Rating Items List Applet");
		System.out.println(siebView_swiSpecialRatingProfi().getChildren()[0].getProperties());
	}
}

