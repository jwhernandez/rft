package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.VEP_CerrarIEHelper;
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
 * Description   : Busca y Cierra las ventanas de IE 64 bits que tengan VEP
 * @author SS
 * Script Name   : <b>VEP_CerrarIE</b>
 * @since  2016/08/24
 * @PARAM 0) OK/NOK
 * eJ res 
 */
public class VEP_CerrarIE extends VEP_CerrarIEHelper
{
	public void testMain(Object[] argu) throws RationalTestException
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

			System.out.println("Cantidad de objetos a cerrar:" + to.length);

			BrowserTestObject ObjetoaCerrar;
			boolean bSinError=true; 
			for (int i = 0; i < to.length; i++)
			{
				// Cerramos todos los objetos en el IE no importa sin son Siebel o no
				try
				{
					System.out.println("Este objeto será cerrado");
					ObjetoaCerrar = new BrowserTestObject(to[i]);
					bSinError=true;
					// print test object properties to console. Si es que no dio error el objeto, cerrar.
					try
					{
						if (bSinError) {
							System.out.println(to[i].getProperties());
							// ObjetoaCerrar.close();
						}
					}
					catch (Exception e) 
					{
						System.out.println("Mensaje de excepción 1 en -- IEs buscandos con find -- "+e.getMessage());
						bSinError=false;
					}
				}
				catch (Exception e) 
				{
					System.out.println("Mensaje de excepción 2 -- IEs buscandos con find -- "+e.getMessage());
					bSinError=false;
				}
			}
			argu[0]="OK";
			ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
		}
		finally 
		{unregisterAll();}
	}
}

