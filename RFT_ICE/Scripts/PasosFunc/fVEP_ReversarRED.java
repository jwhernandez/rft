package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fVEP_ReversarREDHelper;

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
 * Description   : Reversar un RED por sistema VEP. Recibe el nro de red.
 * @author FF
 * Script Name   :  <b>fVEP_ReversarRED</b>
 * @since  2016/12/26
 */
public class fVEP_ReversarRED extends fVEP_ReversarREDHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ReversarRED;
		ReversarRED = new String[4];
		// 0) Resultado = OK/NOK 1) usuario 2) clave 3)Ambiente  
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		ReversarRED[2] = getNroRED();	
		System.out.println("NroRED: " + getNroRED() );
		logInfo("NroRED: " + getNroRED() );
		callScript("Scripts.Comun.SistemasExternos.VEP_ReversarRED", ReversarRED);
		

		if  (ReversarRED[0].toString().equals("NOK")) {
			//MensError[0] = "Problema al ingresar a BRM";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}
