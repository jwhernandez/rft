package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fGenerarCtaFactHelper;
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
 *  Script Name   : <b>fGenerarCtaFact</b>
 * Description   : Seleccionar Generar Cta de Facturacion
 * @Param 0) Nombre del caso 
 * @since  2015/12/27
 * @author SS
 * Ult modif ss 10-7-2017 se agrega opcion de buscar en DP de parametria En este caso
 * usa variable TipoPerfilCorrecto
 */
public class fGenerarCtaFact extends fGenerarCtaFactHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ResultadoGen;
		ResultadoGen = new String[3];
		// 0) devuelve el resultado 1) Recibe el tramite 2) recibe el servicio
		
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

		ResultadoGen[1] = dpString("Tramite");
		if (dpString("Servicio").equals("DP")) // ss 10-7-2017
		{
			ResultadoGen[2]  = "DP:" + dpString("TipoPerfilCorrecto");
		}
		else ResultadoGen[2] = dpString("Servicio");
		callScript("Scripts.Comun.GenerarCtaFact", ResultadoGen);	   
		if (ResultadoGen[0].toString() == "NOK") {
			//MensError[0] = "Cuenta de facturación no generada";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}

	}
}

