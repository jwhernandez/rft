package Scripts.Comun;
import resources.Scripts.Comun.AceptarVtnasDomainsHelper;
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
 * Script Name   : <b>AceptarVtnasDomains</b>
 * @since  2016/09/27
 * @author SS
 */
public class AceptarVtnasDomains extends AceptarVtnasDomainsHelper
{
	public void testMain(Object[] argu) 
	// Creo que intentaba dar enter en ventanas que no aceptaban el enter
	// Es para usar en onobject not found por ejemplo
	// Para ser usada ahi tiene que ser una clase java.
	{
 		boolean dismissedAWindow = false;
		DomainTestObject domains[] = getDomains();
		System.out.println("Cantidad de dominios encontrados:" + domains.length);
		for (int i = 0; i < domains.length; ++i)
		{
			System.out.println("Nombre del dominio:" + 	domains[i].getName() );
			if (domains[i].getName().equals("Html"))
			{
				// HTML domain is found.
				TestObject[] topObjects = domains[i].getTopObjects();
				if (topObjects != null)
				{
					System.out.println("Cantidad de objetos top en ese dominio:" + 	topObjects.length );
					try
					{
						for (int j = 0; j < topObjects.length; ++j)
						{
							System.out.println("La clase del topobject es:"+topObjects[j].getProperty(".class"));
							if (topObjects[j].getProperty(".class").equals("Html.Dialog"))
							{
								// A top-level HtmlDialog is found.
								System.out.println("El mensaje de la ventana inesperada es:" + topObjects[j].getProperty(".text"));
								System.out.println("La clase de la ventana inesperada es:" + topObjects[j].getProperty(".class"));

								try
								{
									dismissedAWindow = true;
									System.out.println("Se le daria enter:");

									//( (ITopWindow) topObjects[j]).inputKeys("{enter}");
								}
								catch(RuntimeException e) {}
							}
						}
					}
					finally
					{
						//unregister all references to top objects
						unregister(topObjects);
					}
				}

			}
		}
		if (!dismissedAWindow) 
				System.out.println("HtmlScript.onObjectNotFound; no Html Dialog to dismiss");
//		else
//		{
//
//			//  try again
//			testObjectMethodState.findObjectAgain();
//		}
	}
}

