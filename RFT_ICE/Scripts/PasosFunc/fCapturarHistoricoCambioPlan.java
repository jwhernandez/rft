package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fCapturarHistoricoCambioPlanHelper;

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
 * Description   : Functional Test Script
 * @author rft
 * /**
	 * Script Name   : <b>fCapturarHistoricoCambioPlan</b>
	 * Generated     : <b>jul. 12, 2017 12:20:31 PM</b>
	 * Description   : Captura el historico de los cambios de planes que se realizaron para un servicio
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * Param 0) numero de caso (CPnn_CDi_Tj) 1) digito iterador 2) ambiente 3) para ante error
	 * @since  2017/07/12
	 * @author VC
	 */

public class fCapturarHistoricoCambioPlan extends fCapturarHistoricoCambioPlanHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validar;
		Validar = new String[5];
		//  0) OK / NOK 1) NroServicio 2) movimiento 3) NroPedido 4) IMEI
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		
		
		int i = Integer.parseInt(args[1].toString());
		Validar[1] = dpString("NroServicio"+i);
		Validar[2] = dpString("Movimiento" +i);
		Validar[3] = getNroPedido();
		Validar[4] = dpString("IMEICP" +i);
		callScript("Scripts.Comun.CapturarHistoricoCambioPlan", Validar);
		

		if  (Validar[0].toString().equals("NOK")) {
			MensError[0] = "Problema al capturar historico de cambio de plan";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

