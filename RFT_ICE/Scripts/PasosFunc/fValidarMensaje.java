package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarMensajeHelper;
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
 * Script Name   : <b>fValidarMensaje</b>
 * Description   : Functional Test Script
 * @Param   0) nombre del caso 1) código del msj 2) IN Ambiente 
 * 3) IN ErrorStop (Si / No)
 * @author SS
 * @since  2015/12/27
 * ej CP26_CD1_T1 DPM00014 QA NA NA 
 */
public class fValidarMensaje extends fValidarMensajeHelper
{
	public void testMain(Object[] args)  throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidarMsj;
		ValidarMsj = new String[5];
		//
		// Parámetros: 0) Recibe código de mensaje a validar, 
		// 1) un boolean true / false 2) devuelve mensaje  
		// 3) recibe un tipo de mensaje si no es HTML el mensaje a validar (opcional) HTML, BrowserScript
		// Si el Msj no es el correcto no lo acepta y queda el sistema en la pantalla del Msj para que el paso
		// 4) Ambiente (ya no se usa)
		
		String[] MensError;
		MensError = new String[4];

		/** 
		 * Validar mensaje recibido como parámetro
		 */

		ValidarMsj[0]=args[1].toString();
		ValidarMsj[3] = "HTML";
		ValidarMsj[4] = args[2].toString();
		callScript("Scripts.Comun.ValidarMensaje",ValidarMsj);

		// No acepta valores como Parcialmente o No Existe
		if  (!(ValidarMsj[1].toString().equals("true")|| 
				ValidarMsj[1].toString().equals("Parcialmente"))){
			//MensError[0] = "Mensaje diferente al deseado";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

