package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fVEP_PagarREDHelper;

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
 * Description   : Pagar un RED por sistema VEP. Recibe el nro de red y el monto para validar en la búsqueda de VEP.
 * @author FF
 * Script Name   :  <b>fVEP_PagarRED</b>
 * @since  2016/12/26
 * ult modif ss 18/4/2017
 * // ss 19 julio 2017 se evita tener que colocar en el dp el monto red usando por defecto no validar
 */
public class fVEP_PagarRED extends fVEP_PagarREDHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] PagoRED;
		PagoRED = new String[5];
		
		//0) OK/NOK 1) Msj Error 2) NroRED 3) Monto  (usar un valor negativo para que el monto no se compare) 4)Resultado
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		PagoRED[2] = getNroRED();	
		System.out.println("NroRED: " + getNroRED() );
		logInfo("NroRED: " + getNroRED() );
		PagoRED[3] = "-1";
		try {
			if ( dpString("MontoRED").equals(null) || dpString("MontoRED").equals("")) 	PagoRED[3] = "-1"; // ss 19 julio 2017 se evita tener que colocar en el dp el monto red usando por defecto no validar
			else PagoRED[3] = dpString("MontoRED");	
		} catch (Exception e) {
			PagoRED[3] = "-1"; // ss 19 julio 2017 se evita tener que colocar en el dp el monto red usando por defecto no validar
		}
		
		System.out.println("Monto RED: " + PagoRED[3] );		
		System.out.println(getManejoError());
		

		callScript("Scripts.Comun.SistemasExternos.VEP_PagarRED", PagoRED);
		
	
		if  (PagoRED[0].toString().equals("NOK")) {
			//MensError[0] = "Problema al ingresar a BRM";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}
