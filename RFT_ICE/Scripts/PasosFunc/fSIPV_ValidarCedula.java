package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSIPV_ValidarCedulaHelper;

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
 * @author rft
 * �ltima modificaci�n: VC 20170405 anterior 20170331
 */
public class fSIPV_ValidarCedula extends fSIPV_ValidarCedulaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());	
		String[] ValidaCedula;
		ValidaCedula = new String[4];
		// 0) OK/NO 1) tramite

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

		ValidaCedula[2]=dpString("IMEI_SIPV");
		ValidaCedula[3]=dpString("Identificacion"); // VC 20170331 se agrega la identificaci�n de siebel para poder validarla con la de SIPV
		callScript("Scripts.Comun.SistemasExternos.SIPV_ValidarCedula", ValidaCedula);

		if  (ValidaCedula[0].toString().equals("NOK")){
			MensError[0] = "Fall� al validar la c�dula en SIPV"; // VC 20170405 Se corrige el mensaje de error
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}