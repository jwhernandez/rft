package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fNuevoCambioModalidadHelper;
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
 * Script Name   : <b>fNuevoCambioModalidad</b>
 * Description   : Functional Test Script
 * @Param
 * @author Sandra
 * @since  2016/01/19
 */
public class fNuevoCambioModalidad extends fNuevoCambioModalidadHelper
{
	public void testMain(Object[] args) 
	{
		String[] PedidoCM;
		PedidoCM = new String[2];
		
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

		PedidoCM [0] = dpString("NumeroServicio");
		callScript("Scripts.Comun.NuevoCambioModalidad", PedidoCM);
		
		if  (!(PedidoCM[1].toString().equals("NOK"))){
			MensError[0] = "CM Paso Cuenta no funcionó";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

