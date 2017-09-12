package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fConsultaREDporIDREDATVHelper;

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
 * Script Name   : <b>fConsultaREDporIDREDATV</b>
 * Generated     : <b>ago. 1, 2017 12:37:40 PM</b>
 * Description   : Consulta el número de RED en GITEL por medio del ID de ATV.
 * 
 * @since  2017/08/01
 * @author JH
 */

public class fConsultaREDporIDREDATV extends fConsultaREDporIDREDATVHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] Consultar;
		Consultar = new String[3]; // 0) Resultado = OK/NOK, 1) ID de ATV, 2) numero de pedido.
		
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while(!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		Consultar[2] = getNroPedido();
		callScript("Scripts.Comun.SistemasExternos.ATV_ObtenerIDRED", Consultar);
		
		if(Consultar[0].equals("OK")){
			callScript("Scripts.Comun.SistemasExternos.ConsultaREDporIDREDATV", Consultar);
		}

		if(Consultar[0].toString().equals("NOK")) {
			MensError[0] = "Problema al realizar la consulta en GITEL con el IdRED de ATV";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}else{
			setNroRED(Consultar[2].toString());
		}
	}
}

