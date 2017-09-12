package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fEvaluarExpresionCondicionanteHelper;
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
 * Script Name   : <b>fEvaluarExpresionCondicionante</b>
 * @since  2017/05/10
 * Param1: Expresion a evaluar
 * ej: "!((CategoriaCrediticia=="A" && Morosidad =="1") || Postpago=="true")" 
 * ej CPX2_CD1 "!((%CategoriaCrediticia%=="A" && %Morosidad% =="1") || %Postpago%=="true")" QA res
 */
public class fEvaluarExpresionCondicionante extends fEvaluarExpresionCondicionanteHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Evaluar;
		Evaluar = new String[5];
		//  0) OK/NOK 1) Expresion a evaluar 2) ambiente 3) CPnn_CD1 4)Resultado
		
//		dpReset();
//		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
//				dpString("Ambiente").equals(args[2]))) {
//			dpNext(); 
//		} 

		Evaluar[1] = args[1].toString(); 	// expresion
		//Evaluar[1] = "!((%CategoriaCrediticia%==\"A\" && %Morosidad% ==\"1\") || %Postpago%==\"true\")";
		Evaluar[2] = args[2].toString();	// ambiemte
		Evaluar[3] = args[0].toString();	// caso
		callScript("Scripts.Comun.EvaluarExpresionCondicionante", Evaluar);
		args[3]=Evaluar[4].toString();
		
		if (Evaluar[0].toString().equals("NOK")) {	
			//MensError[0] = "Problema al evaluar expresión condicionante";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

