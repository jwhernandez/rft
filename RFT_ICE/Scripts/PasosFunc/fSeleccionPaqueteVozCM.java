package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSeleccionPaqueteVozCMHelper;
import Scripts.Comun.SeleccionPaqueteVozCM;
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
 * Description   : Selección de paquete de Voz en CM
 * @author SS
 * Script Name   : <b>SeleccionPaqueteVozCM</b>
 * @since  2017/02/08
 * Param 1 = digito
 */
public class fSeleccionPaqueteVozCM extends fSeleccionPaqueteVozCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Seleccionar;
		Seleccionar = new String[3];
		//0) OK/NOK  1) NombrePaquete 2) Encender / Apagar

		String[] MensError;
		MensError = new String[4];
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());

		Seleccionar[1]=dpString("Paquete"+i);
		Seleccionar[2]=dpString("PaqAccion"+i);
				
		callScript(new SeleccionPaqueteVozCM(),Seleccionar);

		if  (Seleccionar[0].toString().equals("NOK")){
			//MensError[0] = "Paso validar que la SIM no se borre yendo atras y adelante falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

