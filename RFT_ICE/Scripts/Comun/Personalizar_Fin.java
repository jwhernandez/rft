package Scripts.Comun;
import resources.Scripts.Comun.Personalizar_FinHelper;
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
* Descripción: Sale de la sesión de catálogo luego de presionar valorar
* Script Name   : <b>Personalizar_Fin</b> 
* @since   2017/03/30
* @author SS
* @Param 0) OK / NOK 
* ej:  

*/
public class Personalizar_Fin extends Personalizar_FinHelper
{
	public void testMain(Object[]argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "NOK";
		String[] ValidarMsj;
		ValidarMsj = new String[5];
		// Parámetros: 0) Recibe código de mensaje a validar, 
		// 1) OUT un boolean true / false 2) OUT devuelve mensaje  
		// 3) recibe un tipo de mensaje si no es HTML el mensaje a validar (opcional)
		// HTML o BrowserScript
		// Si el Msj no es el correcto no lo acepta y queda el sistema en la pantalla del Msj para que el paso
		// 4) Ambiente 
				
		
		Terminar().click();
		sleep(5);
		ValidarMsj[0]="DPM0027"; // La configuración que desea realizar está incompleta
		ValidarMsj[3] = "BrowserScript";
		ValidarMsj[4] = "QA";
		callScript("Scripts.Comun.ValidarMensaje",ValidarMsj);

		// Acepta valores como true o No Existe o false 
		// procesa el true
		if  (ValidarMsj[1].toString().equals("true") ){ // dio el mensaje 27
			Finalicelo().click();
			Terminar().click();
		}
		// procesa el false
		if  (ValidarMsj[1].toString().equals("false") ){ // el mensaje no coincide 
			ValidarMsj[0]="DPM0033"; 
			callScript("Scripts.Comun.ValidarMensaje",ValidarMsj);
			if  (ValidarMsj[1].toString().equals("true") ){ // dio el mensaje 33
				Finalicelo().click();
				Terminar().click();
			}
		}
		sleep(5);
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

