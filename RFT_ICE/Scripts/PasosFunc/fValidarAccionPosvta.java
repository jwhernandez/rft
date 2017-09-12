package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarAccionPosvtaHelper;
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
 * Description   : Se valida que en la pantalla 360 que el botón sea consultar y ver multas
 * @author SS
 * @since  2016/10/25
 * Script Name   : <b>fValidarAccionPosvta</b>
 */
public class fValidarAccionPosvta extends fValidarAccionPosvtaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
		{
			ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
			String[] MensError;
			MensError = new String[4];
			
			String[] Valida;
			Valida = new String[1];
			// Parámetros	   : 0) OK/NOK 

			callScript("Scripts.Comun.ValidarAccionPosvta",Valida);

			if  (Valida[0].toString().equals("NOK")){
				MensError[0] = "Valida Accion postventa fallo";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
	}
}

