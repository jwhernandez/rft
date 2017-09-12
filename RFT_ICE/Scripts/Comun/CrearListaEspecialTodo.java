package Scripts.Comun;
import resources.Scripts.Comun.CrearListaEspecialTodoHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
import com.rational.test.ft.sys.SpyMemoryStatistics;
import com.rational.test.ft.sys.SpyMemory;
/**
 * Descripción: Crea una lista especial con el nombre pasado como parametro
 * @param Parámetros: 0) NOK / OK 1) Tipo de lista Número de teléfono / Número de SMS
 * 2)Desde pedido true/false 3) volver a pedido true false 4) NumTel o NA 5) Nombre lista 
 * 6) Msj a validar o NA 7) Msj real 8) false o true si coincide o no
 * Precondiciones: Estar en el pedido
 * Ejemplo: res "Número de SMS" true false 24314578 res NA res res 
 * @author SS 
 * @since Nov 2015
 */
public class CrearListaEspecialTodo extends CrearListaEspecialTodoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
//        SpyMemoryStatistics stats =  SpyMemory.getStats();                 
//        System.out.println("Number for Active heaps "+ stats.numberOfActiveHeaps );  
//        
//		String[] Validaciones;
//		Validaciones = new String[4];
//		/**
//		 * Invoca a Validaciones con los siguientes parámetros:
//		 * Parámetros: 0) Recibe código de mensaje a validar, 
//		 * 1) devuelve mensaje y 2) un boolean true / false 
//		 * 3) recibe un tipo de mensaje si no es HTML el mensaje a validar (opcional) HTML, BrowserScript
//		 */
//		
//		argu[0] = "NOK";
//		argu[8] = "true";
//		
////		// Si esta en el pedido va a la pantalla de lista especial
////		if (argu[2].toString().equals("true")) {
////			LineasPedido().activateRow(0);
////			LineasPedido().drillDownColumn("Billing Account", 1);
////			sleep(4);	
////			Pestañas().goTo("Account Customer Profile View", "L3");
////			sleep(3);
////			Pestañas().goTo("SWI Special Rating Profile View", "L4");
//		}
//
//
//		// No se usa esta parte del código 
////		String sCta = text_accountNumber().getProperty("Text").toString(); 
////		button_newQuery().performAction();
////		text_accountNumber().setText(sCta);
////		button_executeQuery().performAction();
//		
//		System.out.println("***" +VistaListaEspecial().activeApplet());
//		// En la primera fila genera una nueva lista especial y se la hace lista x defecto}		
//		// Crear la lista especial
//		NuevaLista().performAction();
//		ListaEspecial().activateRow(0);
//		ListaEsp().select(argu[1].toString());
//		Primaria().setOn();
//
//		// Capturar y retornar el nombre de la lista especial
//		System.out.println(NombreLE().getProperty("Text"));
//		argu[5] = NombreLE().getProperty("Text");
//
//		// Si se le paso un num de tel se lo ingresa en la lista
//		System.out.println("Numero de Telefono: " + argu[4].toString());
//		for (int intentos=1; intentos <=6 ; intentos++) {
//			try
//			{	
//				if (!(argu[4].toString().equals("NA"))) { 
//					
//					System.out.println("***" +VistaListaEspecial().activeApplet());
//					AppletSuperior().ensureObjectIsVisible();
//					if (AppletSuperior().getProperty(".disable").toString().equals("false")) {
//						AppletSuperior().click(atCell(atRow(atIndex(0)), atColumn(atIndex(0))));
//					} else 
//						
//					
//					Siebel().processKeyboardAccelerator("Ctrl+S"); // es necesario para que luego funcione el desplazamiento
//					//AppletSuperior().click(atCell(atRow(atIndex(0)), atColumn(atIndex(0))));
//					BrowserIE(SiebelDoc(),DEFAULT_FLAGS).inputKeys("^{ExtPgDn}");
//					Siebel().processKeyboardAccelerator("Ctrl+Down");
//
//					System.out.println(VistaListaEspecial().getChildren()[0]);
//					System.out.println(VistaListaEspecial().getChildren()[0].getProperties());
//					ISiebTestObject ISiebObj = (ISiebTestObject)  VistaListaEspecial().getChildren()[0];
//					ISiebObj.submit("Select", "SWI Special Rating Items List Applet");
//					System.out.println(VistaListaEspecial().getChildren()[0].getProperties());
//					System.out.println("***" +VistaListaEspecial().activeApplet());
//					if (VistaListaEspecial().activeApplet().toString().equals("SWI Special Rating Items List Applet") ) {
//						ListaEspecial().ensureObjectIsVisible();
//						ListaEspecial().activateRow(0);	
//						NuevoNumero().performAction();
//						NumTel().ensureObjectIsVisible();
//						NumTel().setText(argu[4].toString());
//
//						//Si se incuyó Msja se llama a script de validar Msj
//						System.out.println("Mensaje a validar: " + argu[6].toString());
//						if (!(argu[6].toString().equals("NA"))) { 
//							Validaciones[0]=argu[6].toString();  
//							Validaciones[3]="HTML";  
//							callScript("Scripts.Comun.ValidarMensaje", Validaciones);
//							argu[7] = Validaciones[1];
//							argu[8] = Validaciones[2];
//							Siebel().processKeyboardAccelerator("Ctrl+U");
//							if (Validaciones[2].toString().equals("true")) {
//								argu[0] = "OK"; 
//								intentos = 6;
//							} 
//							if ((!(argu[6].toString().equals("NA")) && Validaciones[2].toString().equals("false")) 
//									|| (argu[6].toString().equals("NA"))) { 
//
//								NumTel().getProperty("Text");
//								if (!(NumTel().getProperty("Text").toString().equals(argu[4].toString()))) {
//									Siebel().processKeyboardAccelerator("Ctrl+U");
//								} else { 
//									argu[0] = "OK";
//									intentos = 6;
//								}
//							} else {
//								argu[0] = "OK"; 
//								Siebel().processKeyboardAccelerator("Ctrl+S");
//								intentos = 6;
//								}
//						}
//					}
//				}
//
//				// Retorna a pedido si el argumento lo indica y si no dio problemas validar el mensaje
//				if ((argu[3].toString().equals("true")) 
//						&& ((argu[6].toString().equals("NA"))||(Validaciones[2].toString().equals("true")))) {
//					Threadbar().goTo("Order Entry - Line Items Detail View (Sales)0");
//					sleep(1);
//				} else {
//					ListaEspecial().activateRow(0);	
//				}
//			}
//			catch (Exception e){
//				logInfo("Mensaje de excepción: "+e.getMessage());
//				System.out.println("Mensaje de excepción: "+e.getMessage());
//			}
//		}// end del for
//		stats = SpyMemory.getStats(); 
//        System.out.println("After script "+ stats.numberOfActiveHeaps); 
//        com.rational.test.ft.script.RationalTestScript.unregisterAll();
	}
}


