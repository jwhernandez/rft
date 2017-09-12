package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSetEnvioInfoHelper;
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
 * Description   : Administra la casilla de envio de informacion.
 * Si la accion es setear, setea a true o false.
 * Si es consulta, valida que sea true o false
 * @author SS
 * Param 
 * Script Name   : <b>fSetEnvioInfo</b>
 * @since  2017/03/27
 * ej  
 */
public class fSetEnvioInfo extends fSetEnvioInfoHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] EnvioInfo;
		EnvioInfo = new String[3];
		// 0) NOK/OK 1) Setear True o Consultar False o cualquier combinacion 2) Tramite
		
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

		EnvioInfo[1]= args[1].toString();
		EnvioInfo[2]= dpString("Tramite");
		callScript("Scripts.Comun.SetEnvioInfo", EnvioInfo);
		
		if  (EnvioInfo[1].toString().equals("NOK")){
			MensError[0] = "Administrar Envio Info falló";
			// MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

