package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarLiberarNumeroHelper;
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
 * Description   : Valida contra el estado deseado en el caso de prueba el estado del botón liberarnumero
 * el estado deseado es el que está en el data pool de pasos en la línea de ese paso para ese caso de prueba
 * Script Name   : <b>fValidarLiberarNumero</b>
 * @since  2016/01/21
 * @author Sandra
 */
public class fValidarLiberarNumero extends fValidarLiberarNumeroHelper
{
	public void testMain(Object[] args) 
	{
		String[] Validac;
		Validac = new String[1];
		// Parámetro 0) OUT Habilitado o Deshabilitado

		String[] MensError;
		MensError = new String[4];
		
		System.out.println("Parametros recibidos de Pasos Prueba: "+ args[1] );
		
		callScript("Scripts.Comun.ValidarLiberarNumero",Validac);
		
		System.out.println("Resultado Recibido por fValidarLiberarNumero: "+ Validac[0] );

		if  (!(Validac[0].toString().equals(args[1].toString()))){
			MensError[0] = "Validacion de liberar Numero fallo";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

