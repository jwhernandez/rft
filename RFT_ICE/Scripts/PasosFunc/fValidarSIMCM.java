package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarSIMCMHelper;

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
 * Description   : Valida que la SIM se mantenga si se borra al ir hacia adelante y hacia atras
 * @author SS
 * Script Name   : <b>fValidarSIMCM</b>
 * @since  2016/04/15
 *  ult modif ss 10/02/2017 se modifica para que solo valide. El resto de los pasos se hacen separadamente
 * nrocaso (CPx_CDi_Tj) param1 = i 2) Ambiente 
 */
public class fValidarSIMCM extends fValidarSIMCMHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidarSIM;
		ValidarSIM = new String[2];
		// 0)OK/NOK 1) SIM

		String[] MensError;
		MensError = new String[4];
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());

		ValidarSIM[1]=dpString("SIM" +i );
		callScript("Scripts.Comun.ValidarSIMCM",ValidarSIM);

		if  (ValidarSIM[0].toString().equals("NOK")){
			//MensError[0] = "Paso validar que la SIM no se borre yendo atras y adelante falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

