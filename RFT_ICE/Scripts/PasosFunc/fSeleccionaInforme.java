package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSeleccionaInformeHelper;
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
 * Description   : Selecciona la opción de informe a imprimir
 * Script Name   : <b>fSeleccionaInforme</b>
 * @Param
 * @author Sandra
 * @since  2016/01/29
 */
public class fSeleccionaInforme extends fSeleccionaInformeHelper
{

	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] SelInforme;
		SelInforme = new String[2];
		//Parámetros: 0) NOK / OK 1) Opción a imprimir

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		SelInforme[1] = dpString("Informe"); 
		callScript("Scripts.Comun.SeleccionaInforme", SelInforme);

		if  ((SelInforme[0].toString().equals("NOK"))){
			//MensError[0] = "Asignación de Lista Especial tuvo error";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

