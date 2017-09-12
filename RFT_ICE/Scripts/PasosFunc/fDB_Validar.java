package Scripts.PasosFunc;
import Scripts.Comun.SistemasExternos.DB_Validar;
import Scripts.Comun.TerminarCasoError;
import java.util.Iterator;

import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fDB_ValidarHelper;
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
 * Description   : Validar usando una consulta a una DB Oracle
 * Script Name   : <b>fDB_Validar</b>
 * @author SS
 * @since  2016/11/28
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)i del dp 2) ambiente 3) para ante error
 * 
 * CP20_CD1_T1 3 QA NA NA
 */
public class fDB_Validar extends fDB_ValidarHelper
{
	public void testMain(Object[] args) 
	{
		String[] Consultar;
		Consultar = new String[17];
		//Param 0)OK/NOK 1)usuario 2) Clave 3) Codigo connectString 4)códigoSQL 5) Validar (Si / No) 6) cant param 
		// 7)param1 8) param2 9) param3 10)param4 11)Param5 
		// 12)param6 13) param7 14) param8 15)param9 16)Param10 
		String[] MensError;
		MensError = new String[4];
		String sScriptName = getScriptName();
		String sMensaje = null;
		boolean bError = false;

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		int i = Integer.valueOf(args[1].toString());
		String sAplic = dpString("DB_Aplic"+i);
		switch (sAplic) {
		case "AAM":
			Consultar[1] = dpString("AAM_usr");
			Consultar[2] = dpString("AAM_pass");
			Consultar[3] = "ConectAAM";
			break;
		case "SBL":
			Consultar[1] = dpString("SBL_usr");
			Consultar[2] = dpString("SBL_pass");
			Consultar[3] = "ConectSBL";
			break;
		case "USM":
			Consultar[1] = dpString("USM_usr");
			Consultar[2] = dpString("USM_pass");
			Consultar[3] = "ConectUSM";
			break;
		default:
			System.out.println("Aplicación no parametrizada");
			sMensaje = "Aplicación no parametrizada";
			infoPaso(sScriptName, Tipo.ERROR, sScriptName, sMensaje);
			bError = true;
			break;
		}
		if (!bError)
		{
			// Lectura de consulta y parametros
			Consultar[4] = dpString("DB_SQL"+i);
			Consultar[5] = dpString("DB_Validar"+i);
			Consultar[6] = dpString("DB_CantParam"+i);
			int iCantParam = Integer.valueOf(dpString("DB_CantParam"+i));
			for (int j = 0; j < iCantParam; j++) {
				Consultar[j+7] = dpString("DB_Param"+(j+1)+"_"+i); // [7] = dp (AAM_Param1_1 [8]=dp(AAM_Param2_1
			}
			callScript(new DB_Validar(), Consultar);
			//callScript("Scripts.Comun.SistemasExternos.DB_Validar", Consultar);
		}
		if  (bError || Consultar[0].toString().equals("NOK")) {
			MensError[0] = "Problema al validar en DB";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript(new TerminarCasoError(), MensError);
			//callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

