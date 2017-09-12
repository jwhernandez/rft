package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarMorosidadCMHelper;
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
 * Description   : Presionar el botón de validar Morosidad. Ver que quede inhabilitado.
 * Chequear que la cat crediticia no sea blanco (si se recibe *) o que sea el valor deseado
 * @author SS
 * Script Name   : <b>fValidarMorosidad</b>
 * since  2016/04/11
 */
public class fValidarMorosidadCM extends fValidarMorosidadCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValMorosidad;
		ValMorosidad = new String[2];
		//0)OK / NOK 1) Valor esperado (A, B, *)
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

		ValMorosidad[1]=dpString("CatCrediticia");
		// Solo en QA se trae el valor correcto. En PREQA y DESA el simulador deja un valor cualquiera
		// Se envia * para que simplemente valide que no sea blanco.
		if (!args[2].toString().toLowerCase().equals("qa")) ValMorosidad[1] = "*";
		callScript("Scripts.Comun.ValidarMorosidadCM",ValMorosidad);
		
		if  (ValMorosidad[0].toString().equals("NOK")){
			//MensError[0] = "Validación de morosidad falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}


