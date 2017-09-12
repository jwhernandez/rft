package Scripts.Comun;
import resources.Scripts.Comun.AceptarVtnasIEBrowserHelper;
import resources.Scripts.Comun.AceptarVtnasIEBrowserOriHelper;

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
 * Description   : Cierra las ventanas emergentes como Session Warning o Timeout. 
 * Se invoca luego de haber procesado los mensajes de las ventanas de Siebel para ver potenciales bugs.
 * Script Name   : <b>AceptarVtnasIEBrowser</b>
 * @author SS
 * @since  2016/09/22
 * Ej res
 */
public class AceptarVtnasIEBrowser extends AceptarVtnasIEBrowserHelper
{
	public void testMain(Object[] argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		try 
		{
			// Se buscan las instancias abiertas de IE para cerrarlas de a una
			RootTestObject root = RationalTestScript.getRootTestObject();
			Property[] props = new Property[1];
			props[0] = new Property(".class", "Html.HtmlBrowser");

			TestObject[] to = root.find(atChild(props));

			System.out.println("Cantidad de Browsers:" + to.length);

			BrowserTestObject ObjetoaCerrar;

			for (int i = 0; i < to.length; i++)
			{
				// Cerramos todos los objetos en el IE no importa sin son Siebel o no

				ObjetoaCerrar = new BrowserTestObject(to[i]);
				System.out.println("Este objeto será cerrado:" + ObjetoaCerrar);
				//System.out.println(to[i].getProperties());
				ObjetoaCerrar.getProcess().kill();
				// Probar con enter o close
			}
		}
		// A veces al cerrar de a una las ventanas del login aparece una que sugiere cerrar todas
		catch (Exception e) 
		{System.out.println("Mensaje de excepción: "+e.getMessage());}
		finally 
		{unregisterAll();}

		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

