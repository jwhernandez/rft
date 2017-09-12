package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fGuardarVariableCondicionanteHelper;
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
 * Description   : Evalúa la expresión condicionante de un paso
 * @author SS
 * Script Name   : <b>fGuardarVariableCondicionante</b>
 * @since  2017/05/11
 * Param 0) CPnn_CD1 1) Variable a evaluar 2) ambiente 3) Valor
 * ej: " 
 */
public class fGuardarVariableCondicionante extends fGuardarVariableCondicionanteHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Guardar;
		Guardar = new String[5];
		//  0) OK/NOK 1) Variable 2) ambiente 3) CPnn_CD1  4)Valor

		Guardar[1] = args[1].toString(); 	// expresion
		//Evaluar[1] = "!((%CategoriaCrediticia%==\"A\" && %Morosidad% ==\"1\") || %Postpago%==\"true\")";
		Guardar[2] = args[2].toString();	// ambiemte
		Guardar[3] = args[0].toString();	// caso
		Guardar[4] = args[3].toString();	// valor
		callScript("Scripts.Comun.GuardarVariableCondicionante", Guardar);
		
		if (Guardar[0].toString().equals("NOK")) {	
			//MensError[0] = "Problema al guardar variable condicionante";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

