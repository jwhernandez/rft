package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fCapturarApartadoyCodPostalHelper;
import Scripts.Comun.CapturarApartadoyCodPostal;

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
 * Description   : Captura el cod postal y apartado de la cuenta de facturacion
 * @author ss
 * Script Name   : <b>fCapturarApartadoyCodPostal</b>
 * @since  2017/02/20
 * param 1 NA
 * Ej CP20_CD1_T1 DP QA res res 
 */
public class fCapturarApartadoyCodPostal extends fCapturarApartadoyCodPostalHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Capturar;
		Capturar = new String[2];
		// 0) Resultado = OK/NOK 1) cta fact
		String[] MensError;
		MensError = new String[4];


		if (args[1].toString().equals("DP"))
		{
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
					dpString("Ambiente").equals(args[2]))) {
				dpNext(); 
			} 		
			Capturar[1] = dpString("NroCuentaFac");
		} 
		

		callScript(new CapturarApartadoyCodPostal(), Capturar);
		

		if  (Capturar[0].toString().equals("NOK")) {
			MensError[0] = "Problema al capturar Apartado y Cod Postal en SBL";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

