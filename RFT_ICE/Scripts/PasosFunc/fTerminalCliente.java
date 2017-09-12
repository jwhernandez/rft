package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fTerminalClienteHelper;

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
 * Descripción: Elimina Terminales promocionales y coloca terminal cliente en Y
 * Parámetros: 
 * Ültima modificación VC 17/11/2016 v2
 */
public class fTerminalCliente extends fTerminalClienteHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] TermCliente;
		
		//se agrega un parametro mas para que se pueda marcar o desmarcar el check de terminal cliente, solo va a funcionar cuando se envía un # en DP de pasos (17/11/2016)
		TermCliente = new String[5]; //antes: TermCliente = new String[4];
		
		// 0) OK / NOK 1) TipoPerfilCorrecto (Postpago o Prepago) 
		// 2) Terminales = true (Tiene) o false, si se envia un # en el DP de pasos la variable es Terminales(i) (no tiene) 3) Tramite 
		// 4) TerminalCteCheck = si, no o NA marca o desmarca el check de terminal cliente.

		String[] MensError;
		MensError = new String[4];
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		TermCliente[1] = dpString("TipoPerfilCorrecto");
		
		//Se agrega este if para darle la opcion de probar con diferentes conjuntos de datos sin afectar los casos configurados anteriormente (17/11/2016)
		String opcion = args[1].toString();
		if(opcion.equals("NA") || opcion.equals("DP")){
			TermCliente[2] = dpString("Terminales");
			TermCliente[4] = "NA";
		}else{
			int i = Integer.parseInt(opcion);
			TermCliente[2] = dpString("Terminales" + i); // true si tiene false sino tiene
			TermCliente[4] = dpString("TerminalCteCheck" + i);
		}
		
		TermCliente[3] = dpString("Tramite");
		callScript("Scripts.Comun.TerminalCliente", TermCliente);

		if  (TermCliente[0].toString().equals("NOK")){
			MensError[0] = "Terminal cliente falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

