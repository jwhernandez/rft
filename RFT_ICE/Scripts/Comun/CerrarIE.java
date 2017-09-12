package Scripts.Comun;
import resources.Scripts.Comun.CerrarIEHelper;
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
 * Description   : Busca y Cierra las ventanas de IE buscando clase Html.HtmlBroser y close.
 * @author Sandra
 *  Script Name   : <b>CerrarIE</b>
 *  @PARAM 0) OK/NOK
 */
public class CerrarIE extends CerrarIEHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		String[] ClasifErr;
		ClasifErr = new String[1]; 
		String[] CierraDialogos;
		CierraDialogos = new String[1]; 
		
		argu[0]="NOK";

		// Se cierra el mensaje de IE de explorador predeterminado
		System.out.println("Mensaje DialogoExplorer existe?:" + DialogoExplorer().exists() );
		try 
		{
			if (DialogoExplorer().exists()){
				System.out.println("Mensaje DialogoExplorer existe");
				sleep (2);
				try
				{DialogoExplorer().close();}
				catch (Exception e) 
				{System.out.println("Mensaje de excepción en IE predeterminado: "+e.getMessage());}
			}

			// Se invoca al script que clasifica potenciales ventanas inesperadas que puedan ser bugs
			// como errores inesperados de SBL, SBL timeout o SBL Session Warning.
			
			sleep(5);
			callScript("Scripts.Comun.AceptarVtnasSBL", ClasifErr);
			
			// Se cierrancualquier otra ventana (Htmldialog) inesperada para luego poder cerrar con close las
			// ventanas de los browsers

			sleep(5);
			callScript("Scripts.Comun.AceptarVtnasIEBrowser", CierraDialogos); 
			
			sleep(3);
			callScript("Scripts.Comun.AceptarVtnasIEBrowser", CierraDialogos); 

			sleep(5);
			
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
					// print test object properties to console sin es que no dio error el objeto
					try
					{
						if (bSinError) {
							System.out.println(to[i].getProperties());
							ObjetoaCerrar.close();
						}
					}
					catch (Exception e) 
					{
						System.out.println("Mensaje de excepción en IEs buscandos con find P2: "+e.getMessage());
						bSinError=false;
					}
				}
				catch (Exception e) 
				{
					System.out.println("Mensaje de excepción en IEs buscandos con find P1: "+e.getMessage());
					bSinError=false;
				}
				// A veces al cerrar de a una las ventanas del login aparece una que sugiere cerrar todas
				if( CerrarIE().exists()) {
					System.out.println("Se cierran todas las ventanas y se sale del loop");	
					i=to.length;
					try 
					{CerrarTodas().click();}
					catch (Exception e) 
					{System.out.println("Mensaje de excepción en IE pop up: "+e.getMessage());}
				}
			}
			sleep(3);
			callScript("Scripts.Comun.AceptarVtnasIEBrowser", CierraDialogos); 

			argu[0]="OK";
		}

		finally 
		{unregisterAll();}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

