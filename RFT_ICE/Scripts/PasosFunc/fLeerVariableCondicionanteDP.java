package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fLeerVariableCondicionanteDPHelper;
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
 * Description   : Lee una variable condicionante del DP de datos
 * @author SS
 * Script Name   : <b>fLeerVariableCondicionanteDP</b>
 * @since  2017/05/17
 * Param 0) CPnn_CD1 1) Variable a leer 2) ambiente 3) Valor
 * ej: CPPC_CD1_T1 Morosidad QA NA NA
 */
public class fLeerVariableCondicionanteDP extends fLeerVariableCondicionanteDPHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Guardar;
		Guardar = new String[5];
		//  0) OK/NOK 1) Variable 2) ambiente 3) CPnn_CD1  4)Valor

		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		Guardar[1] = args[1].toString(); 	// Variable
		Guardar[2] = args[2].toString();	// ambiemte
		int n = args[0].toString().indexOf("_T");
		Guardar[3] = args[0].toString().substring(0,n);	// CPnn_CDx sin el tramite
		System.out.println(Guardar[3]);
		Guardar[4] = dpString("Morosidad1");	// valor
		Guardar[4] = dpString(args[1].toString());	// valor
		callScript("Scripts.Comun.GuardarVariableCondicionante", Guardar);
		
		if (Guardar[0].toString().equals("NOK")) {	
			//MensError[0] = "Problema al guardar variable condicionante leida del DP";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}


