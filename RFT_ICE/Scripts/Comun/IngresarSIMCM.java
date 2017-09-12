package Scripts.Comun;
import resources.Scripts.Comun.IngresarSIMCMHelper;
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
 * Description   : Permite ingresar un SIM en CM
 * @author SS
 * Script Name   : <b>IngresarSIMCM</b>
 * @since  2016/04/15
 * @Param 0) OK/ NOK 1) IN Numero de SIM 2) IN Correcta / Incorrecta
 */
public class IngresarSIMCM extends IngresarSIMCMHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		String sSIM = argu[1].toString();
		String sCorrecta = argu[2].toString().toLowerCase();
		String sSIMAnterior = SIM().getProperty("Text").toString();
		System.out.println("SIM Anterior: " + sSIMAnterior);
		
		// ingresar SIM
		SIM().setText(sSIM);
		String sSIMNueva = SIM().getProperty("Text").toString();
		//IMEI().getProperty("Text");
		if (sCorrecta.equals("correcta")){
			if (sSIMNueva.equals(SIM().getProperty("Text").toString())) argu[0] = "OK";
			System.out.println("SIM Nueva: " + sSIMNueva);
			System.out.println("Se realizó el cambio de la SIM correctamente");
		} else
		{
			if (sSIMAnterior.equals(SIM().getProperty("Text").toString())) argu[0] = "OK";
			System.out.println("No se realizó cambio de SIM porque la SIM no es correcta");
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

