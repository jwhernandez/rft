package Scripts.Comun;
import resources.Scripts.Comun.ValorarTodoCompletoHelper;
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
public class ValorarTodoCompleto extends ValorarTodoCompletoHelper
{
	/**
	 * Script Name   : <b>ValorarTodoCompleto</b>
	 * Generated     : <b>12/01/2016 06:25:56</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/01/12
	 * @author Sandra
	 */
	public void testMain(Object[] args) 
	{
//		boolean exception=false; 
		String sMensaje=null;
		String sCoincide=null;

		for (int intentos=1; intentos <=4 ; intentos++) {
			try
			{
				if (MensajeBrowserScript().exists()) {
					sMensaje = (String) MensajeBrowserScript().getProperty(".text");
					System.out.println("Mensaje del código: *" + dpString(argu[1].toString())+ "*");
					System.out.println("Mensaje de la pantalla: *" + sMensaje + "*");

					// Paso 16 - Validar que sea el mensaje deseado
					if (sMensaje.trim().equals(dpString(argu[1].toString()).trim())) {
						sCoincide = "true";
						System.out.println(sCoincide + "Coincide");
						argu[2] = "OK";
						AceptarMensajeBrowser().click();
					}
					else 
					{ 
						sCoincide = "false";
						System.out.println(sCoincide + "No Coincide");
					}
				} else { argu[2] = "NOK";}
				
				if (intentos ==1) {
					Valorar_Todo().ensureObjectIsVisible();
					if (Valorar_Todo().isEnabled()) {
						Valorar_Todo().performAction();
						argu[0] = "OK";
					} else { 
						argu[0] = "NOK";
						intentos = 3;
					}
				}
			} // end del try
//			catch (ObjectNotFoundException onfe) 
//			{
//				logInfo("ObjectNotFound Manejada: "+onfe.getMessage());
//				System.out.println("ObjectNotFound Manejada: "+onfe.getMessage());}

			//			catch (PropertyNotFoundException pnfe){
			//				logInfo("PropertyNotFound Manejado: "+pnfe.getMessage());
			//				System.out.println("PropertyNotFound Manejado: "+pnfe.getMessage());}

			catch (Exception e){

				logInfo("Mensajeeeeeee: "+e.getMessage());
				System.out.println("Mensajeeeeeee: "+e.getMessage());
			}
		}// end del for
	}
//	public void onObjectNotFound (ITestObjectMethodState  testObjectMethodState) {
//		logInfo ("Estoy dentro");
//		testObjectMethodState.setReturnValue (new Integer(0));
//	}
//	public void onTestObjectMethodException(
//			ITestObjectMethodState testObjectMethodState, TestObject testObject) {
//			logWarning(getScreen().getActiveWindow().getText());
//			logWarning("screenshot", getRootTestObject().getScreenSnapshot());
//			getScreen().inputKeys("{ENTER}");
//			testObjectMethodState.findObjectAgain();
	}
}

