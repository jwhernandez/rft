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
 * Description   : Prueba de uso del metodo find para objetos Siebel
 * Script Name   : <b>Find</b>
 * @since  2016/01/14
 * @author SS
 */
public class Find extends FindHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		// PENDIENTE Insertar código aquí
		System.out.println(siebView_swiSpecialRatingProfi().getChildren()[0]);
		System.out.println(siebView_swiSpecialRatingProfi().getChildren()[0].getProperties());
		ISiebTestObject ISiebObj = (ISiebTestObject)  siebView_swiSpecialRatingProfi().getChildren()[0];
		ISiebObj.submit("Select", "SWI Special Rating Items List Applet");
		System.out.println(siebView_swiSpecialRatingProfi().getChildren()[0].getProperties());
	}
}

