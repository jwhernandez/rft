package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAgregarContacto_RepLegalHelper;
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
 * Description   : Agrega contacto en caso de cuenta física 
 * y rep legal en caso de cuenta jurídica
 * Script Name   : <b>fAgregarContacto_RepLegal</b>
 * @param 0) numero de caso 1) NA 2) Ambiente 3)Error para ejecución
 * @since  2016/01/26
 * @author Sandra
 */
public class fAgregarContacto_RepLegal extends fAgregarContacto_RepLegalHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Agregar_Contacto_Rep;
		Agregar_Contacto_Rep = new String[4];
		// 0)Identificación 1)OK/NOK

		String[] MensError;
		MensError = new String[2];
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		Agregar_Contacto_Rep[0] = dpString("Identificacion");
		switch (dpString("TipoCuenta")) {
		case "Física":
			callScript("Scripts.Comun.AgregarContacto",Agregar_Contacto_Rep);
			break;
		case "Jurídica":
			callScript("Scripts.Comun.AgregarRepLegal",Agregar_Contacto_Rep);
			break;
		default:  
			Agregar_Contacto_Rep[1]= "NOK";
			break;
		}  

		if  (Agregar_Contacto_Rep[1].toString().equals("NOK")){
			MensError[0] = "Error al agregar contacto";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

