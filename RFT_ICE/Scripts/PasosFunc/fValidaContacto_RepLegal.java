package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidaContacto_RepLegalHelper;
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
 * Description   : Valida contacto en caso de cuenta física  y 
 * rep legal en caso de cuenta jurídica
 * Script Name   : <b>fValidaContacto_RepLegal</b>
 * @param 0) numero de caso 1) NA 2) Ambiente 3)Error para ejecución
 * @since  2016/01/26
 * @author Sandra
 */
public class fValidaContacto_RepLegal extends fValidaContacto_RepLegalHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Contacto_Rep;
		Contacto_Rep = new String[4];

		String[] MensError;
		MensError = new String[4];
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		Contacto_Rep[3] = "HTML";
		switch (dpString("TipoCuenta")) {
		case "Física":
			Contacto_Rep[0]= "DPM0024";
			callScript("Scripts.Comun.ValidarMensaje",Contacto_Rep);
			break;
		case "Jurídica":
			Contacto_Rep[0]= "DPM0025";
			callScript("Scripts.Comun.ValidarMensaje",Contacto_Rep);
			break;
		default:  
			Contacto_Rep[1]= "false";
			break;
		}  

		if  (Contacto_Rep[1].toString().equals("false")){
			MensError[0] = "Mensaje diferente al deseado";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);

		}
	}
}


