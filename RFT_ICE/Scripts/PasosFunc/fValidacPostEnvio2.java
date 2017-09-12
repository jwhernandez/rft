package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidacPostEnvio2Helper;
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
 * Script Name   : <b>fValidacPostEnvio2</b>
 * Description   : Functional Test Script
 * @Param 
 * @since  2015/12/27
 * @author Sandra
 */
public class fValidacPostEnvio2 extends fValidacPostEnvio2Helper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validac;
		Validac = new String[2];
		// Parámetros	   : 0)  OK/NOK  1) Prepago o Postpago

		String[] MensError;
		MensError = new String[4];
	
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		//  Validaciones post envio
	
		Validac[1]=dpString("TipoPerfilCorrecto");
		callScript("Scripts.Comun.ValidacPostEnvio2",Validac);

		if  (Validac[0].toString().equals("NOK")){
			MensError[0] = "Validaciones post envio 2 fallaron";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

