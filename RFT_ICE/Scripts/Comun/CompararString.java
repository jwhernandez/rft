package Scripts.Comun;
import resources.Scripts.Comun.CompararStringHelper;
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
public class CompararString extends CompararStringHelper
{
	/**
	 * Script Name   : <b>CompararString</b>
	 * Generated     : <b>17/05/2016 05:49:32</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/05/17
	 * @author Sandra
	 */
	public void testMain(Object[] args) 
	{
		int iCount = 0;
		char sCharDP;
		char sChar;
		sCharDP=sMensajeDP.charAt(0);
		sChar=sMensaje.charAt(0);
		
		System.out.println(sCharDP);
		System.out.println( sChar);
		System.out.println(sMensajeDP.length());
		System.out.println(sMensaje.length());

		
//		for (int i = 0; i < sMensaje.length(); i++) {
//			char sCharDP=sMensajeDP.charAt(i);
//			char sChar=sMensaje.charAt(i);
//			if (sChar!=sCharDP)
//					iCount = iCount + 1;
//		}
	}
}

