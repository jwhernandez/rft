package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPagoREDOnlyCMHelper;

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
 * Valida que el check de enviar un cm a red se encuentre habilitado-deshabilitado y marcado-desmarcado
 * Script Name   : <b>fPagoREDOnlyCM</b>
 * @Param 0) Nombre del caso 1) indice que indica el valor a usar en el DP
 * 2) Ambiente 3) Si / No para reportar error
 * @since  2017/03/16
 * ej CP20_CD1_T1 1 QA res res 
 * ej CP20_CD1_T1 2 QA res res 
 * @author VC
 */
public class fPagoREDOnlyCM extends fPagoREDOnlyCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidaRED;
		ValidaRED = new String[4];
		// 0) OK/NOK 1) Producto (PRODi) 2) marcado (true) o desmarcado (false) (PROD_Marcadoi) 3) habilitado (true) o deshabilitado (false) (PROD_Habilitado)

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
		
		int i = Integer.parseInt(args[1].toString());
		ValidaRED[1]=dpString("PROD"+i); 
		ValidaRED[2]= dpString("PROD_Marcado"+i);
		ValidaRED[3]= dpString("PROD_Habilitado"+i);

		callScript("Scripts.Comun.PagoREDOnlyCM", ValidaRED);

		if  (ValidaRED[0].toString().equals("NOK")) {
			MensError[0] = "Paso validar solo red dio error";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

