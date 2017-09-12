package Scripts.Comun;
import java.awt.Toolkit;

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
 * Ultima Modif 
 * ss 25-11-2016 se ajusta el tipo de parada (setTipoError, setMensajeError)
 * ss 12-2016 se agrega el beep antes de terminar
 * ss 16-4-2017 se agrega valor de NOK a la variable global ResultadoPaso para pasos condicionados
 */
public class TerminarCasoError extends TerminarCasoErrorHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sMensaje ="No hay Mensaje";
		String sDebeParar = "No";
		String sScriptName=argu[3].toString(); // 28/11/2016
		if (getResultadoPaso().equals("ND")) setResultadoPaso("NOK"); // ss para los pasos condicionantes se establece si el script falló.
		// Se graba el valor NOK solo si el valor es ND dando la posibilidad al paso en sí de determinar que resultado
		// quiere devolver para condicionar futuros pasos.

		if (argu[0].toString().equals("xDefecto")) {
			dpReset();
			while (!dpDone() &&  !(dpString("NombreScript").equals(argu[3].toString()))) {
				//System.out.println("Script =" + dpString("NombreScript"));
				//System.out.println("Mensaje =" + dpString("Mensaje"));
				dpNext(); 
			} 
			if (!dpDone() && dpString("NombreScript").equals(argu[3].toString())){
				sMensaje = dpString("Mensaje");
				System.out.println("Mensaje parametrico =" + sMensaje);
			} 
		}	else {
			sMensaje = argu[0].toString();
		}

		if (argu[1].toString().toLowerCase().equals("false")) { sDebeParar = "No";} 
		else {sDebeParar = "Si";}

		logInfo("Mensaje de Error: " + sMensaje + " Debe parar la ejecución del CP: " + sDebeParar);

		System.out.println("----------------------------------------------------------");
		System.out.println("Mensaje de Error: " + sMensaje);
		System.out.println("Debe parar la ejecución del CP: " + sDebeParar);
		System.out.println("----------------------------------------------------------");

		//if (getTipoError().equals("Bug") && sDebeParar == "Si") setMensajeError(sMensaje); ss 25-11-2016 El parar lo determina
		// el argumento 1.
		
		// if (!getTipoError().equals("NA")) se saco el if el 28-11-2015
		setTipoError("Bug");  // SS Se agregó el 25-11-2016 
		// if (!getMensajeError().equals("NA")) se saco el if el 28-11-2015
		setMensajeError(sMensaje);
		
		infoPaso(sScriptName, Tipo.ERROR, sScriptName, sMensaje);
		if (sDebeParar == "Si") 
		{
			setManejoError("Controlado");
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			stop();  // SS Se modificó el 25-11-2016
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}

