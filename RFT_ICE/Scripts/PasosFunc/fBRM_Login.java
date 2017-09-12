package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBRM_LoginHelper;
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
 * Description   : Ejecuta el login de BRM
 * Script Name   : <b>fBRM_Login</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)NA 2) ambiente 3) para ante error
 * @since  2016/11/14
 * Autor SS
 * CP20_CD1_T1 NA QA NA NA
  */
public class fBRM_Login extends fBRM_LoginHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Login;
		Login = new String[4];
		// 0) Resultado = OK/NOK 1) usuario 2) clave 3)Ambiente  
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		Login[1] = dpString("BRM_usr");
		Login[2] = dpString("BRM_pass");
		Login[3] = args[2].toString(); // ambiente
		callScript("Scripts.Comun.SistemasExternos.BRM_Login", Login);
		

		if  (Login[0].toString().equals("NOK")) {
			MensError[0] = "Problema al ingresar a BRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

