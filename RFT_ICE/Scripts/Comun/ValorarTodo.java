package Scripts.Comun;
import resources.Scripts.Comun.ValorarTodoHelper;
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
 * Descripción: Presiona el botón valorar todos
 * Parametros: 0)OK / NOK si pudo presionar boton 
 * si coincide o no
 * Pre-Condición: Se debe estar en el pedido 
 */
public class ValorarTodo extends ValorarTodoHelper
{
	public void testMain(Object[] argu) 
	{
		argu[0] = "NOK";
//		for (int intentos=1; intentos <=6 ; intentos++) {
//			try
//			{
//				System.out.println("cantidad de intentos"+ intentos);
//				if (MensajeBrowserScript().exists()) {
//					String sMensaje = (String) MensajeBrowserScript().getProperty(".text");
//					System.out.println("Mensaje de la pantalla: *" + sMensaje + "*");
//					System.out.println("Resultado " + argu[0]);
//					argu[0] = "OK";
//				}
//				if (intentos ==1) {
					Valorar_Todo().ensureObjectIsVisible();
					if (Valorar_Todo().isEnabled()) {
						setInvocationTimeout(100);
						Valorar_Todo().performAction();
						argu[0] = "OK";
					} else { 
						argu[0] = "NOK";
//						intentos = 6;
					}
//				}
//			} // end del try
//			catch (Exception e){
//
//				logInfo("Mensaje de excepción: "+e.getMessage());
//				System.out.println("Mensaje de excepción: "+e.getMessage());
//			}
//		}// end del for
	}
}

