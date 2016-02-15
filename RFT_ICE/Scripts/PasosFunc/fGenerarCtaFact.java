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
 */
public class fGenerarCtaFact extends fGenerarCtaFactHelper
{
	public void testMain(Object[] args) 
	{
		String[] ResultadoGen;
		ResultadoGen = new String[1];
		
		String[] MensError;
		MensError = new String[4];

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

