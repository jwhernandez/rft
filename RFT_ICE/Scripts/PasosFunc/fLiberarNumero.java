package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fLiberarNumeroHelper;
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
 * Script Name   : <b>fLiberarNumero</b>
 * Description   : Functional Test Script
 * @Param 0) nombre caso 1) NA 2) ambiente
 * @since  2016/01/07
 * @author Sandra
 */
public class fLiberarNumero extends fLiberarNumeroHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] LibNum;
		LibNum = new String[2];
		// Parám: 0) Prepago o Postpago 1) Devuelve NOK o OK 		

		String[] MensError;
		MensError = new String[4];

		System.out.println(args [0] + "*" + args[1]+ "*" ) ;

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		System.out.println("DP Usado: " + dpString("NumeroCaso")+ dpString("Ambiente"));

		LibNum[0] = dpString("TipoPerfilCorrecto"); // Postpago
		callScript("Scripts.Comun.LiberarNumero", LibNum);

		if  ((LibNum[1].toString().equals("NOK"))){
			MensError[0] = "Problema en la liberacion de numero de servicio";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

