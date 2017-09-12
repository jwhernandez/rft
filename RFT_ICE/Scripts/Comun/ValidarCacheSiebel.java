package Scripts.Comun;
import java.util.Iterator;

import resources.Scripts.Comun.ValidarCacheSiebelHelper;
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
 * Script Name   : <b>ValidarCacheSiebel</b>
 * Generated     : <b>01/12/2016 16:21:59</b>
 * Description   : Cheuquea si existe en memoria mas de una version del cache de siebel 
 * argumentos 0) OK/NOK
 * @since  2016/12/01
 * @author SS
 */
public class ValidarCacheSiebel extends ValidarCacheSiebelHelper
{
	public void testMain(Object[]  argu) throws RationalTestException{
		
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		TestObject[] to;
		TestObject[] toChild;
		GuiTestObject gto; 
		GuiTestObject gtoChild; 
		int caches=0;
		String msj=null;
		try {
			to = Cache().find(atChild(".class", "Html.FRAME"));
			for (int i = 0; i < to.length; i++) {
				gto = 	(GuiTestObject)	to[i];		
				//RegularExpression RegExp = new RegularExpression("*ecommunications_esn*", false);
				toChild = gto.find(atChild(".class", "Html.HtmlDocument")); //

				for (int j = 0; j < toChild.length; j++) {
					gtoChild = 	(GuiTestObject)	toChild[j];	
					if (gtoChild.getProperty("referrer").toString().contains("ecommunications_esn"))
					{
						msj = "Es cache de siebel";
						System.out.println("Propiedades:" + gtoChild.getProperties());
						caches ++;
					} else msj = "NO";
					System.out.println("Elemento nro:" + i + "Con propiedad indice = " + gto.getProperty(".classIndex")+ " " + msj);
				}
			}
			if (caches ==1) argu[0]="OK"; else argu[0]="NOK";
		} catch (Exception e) {
			argu[0]="OK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

