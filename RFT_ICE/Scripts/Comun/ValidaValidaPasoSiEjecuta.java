package Scripts.Comun;
import resources.Scripts.Comun.ValidaValidaPasoSiEjecutaHelper;
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
 * Description   : Script que sirve para validar la correcta ejecución del script validasiejecuta
 * Script Name   : <b>ValidaValidaPasoSiEjecuta</b>
 * @since  2016/09/09
 * @author Sandra
 */
public class ValidaValidaPasoSiEjecuta extends ValidaValidaPasoSiEjecutaHelper
{
	public void testMain(Object[] args) 
	{
		
		String[] PasoEjecuta;
		PasoEjecuta = new String[6];
		// @Param 0) Resultado (true / false) 1) Ambiente 2) paso desde 3) paso a ejecutar 4)sCasoEjecuta 5)sCasoReEjec

		int dpRow = 0; 
		dpReset();
		while (!dpDone()) 
		{ 
			PasoEjecuta[1] = dpString("Ambiente"); // ambiente
			PasoEjecuta[2] = dpString("PasoDesde"); // paso desde
			PasoEjecuta[3] = dpString("Paso"); // paso a ejecutar
			PasoEjecuta[4] = dpString("Ejecuta");
			PasoEjecuta[5] = dpString("ReEject");
			
			callScript("Scripts.Comun.ValidaPasoSiEjecuta",PasoEjecuta);

			System.out.println(dpRow +": Caso de uso=" + dpString("Caso de uso") + " " +
					"Resultado esperado="+ dpString("resultado") + "=" + 
					"Resultado obtenido=" + PasoEjecuta[0] + "-" +
					PasoEjecuta[1] +"-" + PasoEjecuta[2] + "-" +PasoEjecuta[3]
							+"-" +PasoEjecuta[4] +"-" +PasoEjecuta[5]);

			dpNext(); 
			dpRow = dpRow + 1; 
		} // end del while

	}
}

