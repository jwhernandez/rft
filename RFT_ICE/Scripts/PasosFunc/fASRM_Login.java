package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fASRM_LoginHelper;

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
 * Description   : Ejecuta el login de ASRM
 * Script Name   : <b>fSRM_Login</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * @since  2016/10/14
 * Autor SS
 * CP20_CD1_T1 NA QA NA NA
  */
public class fASRM_Login extends fASRM_LoginHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Login;
		Login = new String[4];
		//0) OK/NOK 1) usuario 1) clave 2)Ambiente 
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		Login[1] = dpString("ASRM_usr");
		Login[2] = dpString("ASRM_pass");
		Login[3] = args[2].toString(); // ambiente
		callScript("Scripts.Comun.SistemasExternos.ASRM_Login", Login);
		

		if  (Login[0].toString().equals("NOK")) {
			MensError[0] = "Problema al ingresar a ASRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

