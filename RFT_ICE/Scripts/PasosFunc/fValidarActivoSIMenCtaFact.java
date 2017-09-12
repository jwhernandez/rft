package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarActivoSIMenCtaFactHelper;
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
 * Descripcion     : Selecciona la pestaña de activos, busca un activo con el estado deseado. 
 * Script Name   : <b>fValidarActivoSIMenCtaFact</b>
 * @param 0) Resultado = OK/NOK 1) SIM Deseada 
 * @since 2016/11/07
 * @author VC
 */
public class fValidarActivoSIMenCtaFact extends fValidarActivoSIMenCtaFactHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidarActivoSIM;
		ValidarActivoSIM = new String[2];
		// 0) Resultado = OK/NOK 1) SIM Deseada

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
		
		int i = Integer.parseInt(args[1].toString());
		ValidarActivoSIM[1] = dpString("SIMActivo" + i);

		callScript("Scripts.Comun.ValidarActivoSIMenCtaFact", ValidarActivoSIM);
		
		if  ((ValidarActivoSIM[0].toString().equals("NOK"))){
			MensError[0] = "Validar activo SIM falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

