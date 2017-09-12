package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarBtnAnularREDHelper;
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
 * Script Name   : <b>fValidarBtnAnularRED</b>
 * Description   : Validar que el botón "Anular RED" este habilitado
 * @Param Caso param 1 ambiente true/false nrocaso
 * @Param CP50 true PREQA true 20
 * @since  2016/04/28
 * @author Victor Cordero
 *  * ult modif ss 7/5/2017 agregado de opcion portin
 */
public class fValidarBtnAnularRED extends fValidarBtnAnularREDHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] Validac;
		Validac = new String[3];
		// Parámetro  0)OUT res (OK/NOK) 1) In Resultado esperado (true) Habilitado / (false) Deshabilitado 2) Tramite

		String[] MensError;
		MensError = new String[4];
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		Validac[1]=args[1].toString();
		Validac[2]=dpString("Tramite");
		callScript("Scripts.Comun.ValidarBtnAnularRED",Validac);
		
		System.out.println("Resultado Recibido por fValidarBtnAnularRED: "+ Validac[0] );

		if  (Validac[0].toString().equals("NOK")){
			//MensError[0] = "Validacion de Anular RED fallo";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

