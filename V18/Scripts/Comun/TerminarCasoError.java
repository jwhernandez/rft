package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.TerminarCasoErrorHelper;
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
 * Descripción: Se llama a este script para informar errores, se recibe un mensaje y si se desea parar el 
 * script debido al error.
 * Parametros: 0)IN Mensaje a Transmitir de error o xDefecto para que use el de defecto el dp  1) IN false 
 * no hace stop cc si 2) Caso en el que da el error 3) script en el que da error  
 */
public class TerminarCasoError extends TerminarCasoErrorHelper
{
	public void testMain(Object[] argu) 
	{
		String sMensaje ="No hay Mensaje";
		String sDebeParar = "No";
		if (argu[0].toString().equals("xDefecto")) {
			dpReset();
			while (!dpDone() &&  !(dpString("NombreScript").equals(argu[3].toString()))) {
				dpNext(); 
			} 
			if (dpString("NombreScript").equals(argu[0].toString())){
				sMensaje = dpString("Mensaje");
			} 
		}	else {
				sMensaje = argu[0].toString();
			}

		if (argu[1].toString().equals("false")) { sDebeParar = "No";} else {sDebeParar = "Si";}
		
		logInfo("Mensaje de Error: " + sMensaje + " Debe parar la ejecución del CP: " + sDebeParar);
				
		System.out.println("----------------------------------------------------------");
		System.out.println("Mensaje de Error: " + sMensaje);
		System.out.println("Debe parar la ejecución del CP: " + sDebeParar);
		System.out.println("----------------------------------------------------------");
		
		infoPaso(argu[2].toString(), Tipo.ERROR,"Error en script: " + argu[3].toString(), sMensaje);

		if (sDebeParar == "Si") {
			stop();
		}
	}
}

