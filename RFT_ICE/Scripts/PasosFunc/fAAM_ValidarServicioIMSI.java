package Scripts.PasosFunc;
import java.util.Iterator;

import resources.Scripts.PasosFunc.fAAM_ValidarServicioIMSIHelper;
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
 * Description   : Valida servicio IMSI 
 * Script Name   : <b>fAAM_ValidarServicioIMSI</b>
 * @author SS
 * @since  2016/11/28
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)i del dp 2) ambiente 3) para ante error
 * 
 */
public class fAAM_ValidarServicioIMSI extends fAAM_ValidarServicioIMSIHelper
{
	public void testMain(Object[] args) 
	{
		String[] Consultar;
		Consultar = new String[15];
		//0)OK/NOK 1)usuario 2) Clave 3)código 4) cant param 5)param1 6) param2 7) param3 8)param4 9)Param5 
		// 10)param6 11) param7 12) param8 13)param9 14)Param10 
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		
		int i = Integer.valueOf(args[1].toString());
		Consultar[1] = dpString("AAM_usr");
		Consultar[2] = dpString("AAM_pass");
		Consultar[3] = dpString("AAM_SQL"+i);
		Consultar[4] = dpString("AAM_CantParam"+i);
		int iCantParam = Integer.valueOf(dpString("AAM_CantParam"+i));
		for (int j = 0; j < iCantParam; j++) {
			Consultar[j+5] = dpString("AAM_Param"+(j+1)+"_"+i); // [5] = dp (AAM_Param1_1 [6]=dp(AAM_Param2_1
		}
		callScript("Scripts.Comun.SistemasExternos.AAM_ValidarServicioIMSI", Consultar);

		if  (Consultar[0].toString().equals("NOK")) {
			MensError[0] = "Problema al validar en AAM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

