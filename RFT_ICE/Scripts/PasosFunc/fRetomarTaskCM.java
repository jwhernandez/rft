package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fRetomarTaskCMHelper;
import Scripts.Comun.RetomarTaskCM;
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
 * Description   :  Retomar una task si se salio de esa pantalla
 * @author ss
 * Script Name   : <b>fRetomarTaskCM</b<b>
 * @since  2017/02/21
 * param1 DP para Data Pool o NA para variable global
 * ej CP20_CD1_T1 DP QA NA NA
 */
public class fRetomarTaskCM extends fRetomarTaskCMHelper
{
	public void testMain(Object[] args)  throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Retomar;
		Retomar = new String[2];
		// 0) Resultado = OK/NOK 1) servicio
		String[] MensError;
		MensError = new String[4];

		if (args[1].toString().equals("DP"))
		{
			// Buscar registro en el DP de entrada
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
					dpString("Ambiente").equals(args[2]))) {
				dpNext(); 
			} 		

			Retomar[1] = dpString("NumeroServicio");
			

		} else Retomar[1] =getNroServicio();
		
		callScript(new  RetomarTaskCM(), Retomar);
		
		if  (Retomar[0].toString().equals("NOK")) {
			MensError[0] = "Problema al retomar task CM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

