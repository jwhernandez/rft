package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidacPostServicioHelper;
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
 * Script Name   : <b>fValidacPostServicio</b>
 * Description   : Valida que Liberar Numero se encuentre habilitado y que 
 * servicio esté inhabilitado
 * Parametros 0) IN nombre del caso 1) NA 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * @since  2015/12/27
 * @author SS
 */
public class fValidacPostServicio extends fValidacPostServicioHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validac;
		Validac = new String[2];
		// Parámetros	   : 0)  OK/NOK 1) Prepago/Postpago

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

		Validac[1]=dpString("TipoPerfilCorrecto");

		callScript("Scripts.Comun.ValidacPostServicio",Validac);

		if  (Validac[0].toString().equals("NOK")){
			MensError[0] = "Validaciones post ingreso del servicio fallaron";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

