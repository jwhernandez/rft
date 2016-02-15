package Scripts.Comun;
import resources.Scripts.Comun.ValidarMensajeHelper;
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
 * Descripción: Valida Mensaje contra Data Pool
 * Parámetros: 0) IN Código de mensaje a validar, 
 * 1) OUT boolean true / false indicando si coincide o no  2)OUT mensaje  
 * 3) IN tipo de mensaje  a validar HTML, BrowserScript
 * Ejemplo DPM0001 res res HTML
 * Si el Msj no es el correcto no lo acepta y queda el sistema en la pantalla del Msj para que el paso
 * Funcional decida que realizar
 * SS Nov 2015
 */
public class ValidarMensaje extends ValidarMensajeHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{ 
		String sMensaje;
		String sCoincide = "false";
		String sCodigo = "DPM0001";
		
		String[] MensError;
		MensError = new String[2];

		if(argu.length > 0){
			sCodigo = argu[0].toString(); }

		String sTipo = "HTML";
		if(argu.length == 4){
			sTipo = argu[3].toString(); }


		try {

			switch (sTipo) {
			case "HTML":
				System.out.println("Existe Mensaje HTML: " + MensajeJavaError().exists());
				if (MensajeJavaError().exists()) {
					sMensaje = (String) MensajeJavaError().getProperty(".text");
					System.out.println(sMensaje);

					// Paso 16 - Validar que sea el mensaje deseado
					System.out.println("Mensaje del código: *" + dpString(sCodigo)+ "*");
					System.out.println("Mensaje de la pantalla: *" + sMensaje + "*");
					
					if (sMensaje.trim().equals(dpString(sCodigo).trim())) {
						sCoincide = "true";
						System.out.println("Coincide");
						AceptarMensaje().click();
					} 
					else 
					{ 
						System.out.println("No Coincide");
					}
					argu[1] = sCoincide;
					argu[2] = sMensaje;
				} 
				else {
					argu[1] = sCoincide;
					argu[2] = "No hay mensaje";
				}
				break;
			case "BrowserScript":
				System.out.println("Existe Mensaje BrowserScript: " + MensajeBrowserScript().exists());
				if (MensajeBrowserScript().exists()) {
					sMensaje = (String) MensajeBrowserScript().getProperty(".text");
					System.out.println("Mensaje del código: *" + dpString(sCodigo)+ "*");
					System.out.println("Mensaje de la pantalla: *" + sMensaje + "*");

					// Paso 16 - Validar que sea el mensaje deseado
					if (sMensaje.trim().equals(dpString(sCodigo).trim())) {
						sCoincide = "true";
						System.out.println(sCoincide + "Coincide");
						AceptarMensajeBrowser().click();
					} 
					else 
					{ 
						sCoincide = "false";
						System.out.println(sCoincide + "No Coincide");
					}
					argu[1] = sCoincide;
					argu[2] = sMensaje;
				} 
				else {
					argu[1] = sCoincide;
					argu[2] = "No hay mensaje";
				}
				break;
			default:  
				MensError[0] = "Mensaje no correcto";
				MensError[1] = "Si";
				callScript("Scripts.Comun.TerminarCasoError", MensError);
				break;
			} // end del switch


		} catch (ObjectNotFoundException onfe) {
			logInfo("ObjectNotFound Manejada: "+onfe.getMessage());
			System.out.println("ObjectNotFound Manejada: "+onfe.getMessage());

		}catch (PropertyNotFoundException pnfe){
			logInfo("PropertyNotFound Manejado: "+pnfe.getMessage());
			System.out.println("PropertyNotFound Manejado: "+pnfe.getMessage());
		}
	}
}

