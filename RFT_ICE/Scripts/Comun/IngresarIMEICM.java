package Scripts.Comun;
import resources.Scripts.Comun.IngresarIMEICMHelper;
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
 * Description   : Permite ingresar un IMEI en CM
 * @author SS
 * Script Name   : <b>IngresarIMEICM</b>
 * @since  2016/04/15
 * @Param 0) OK/ NOK 1) IN Numero de IMEI 2) IN Correcto / Incorrecto
 */
public class IngresarIMEICM extends IngresarIMEICMHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		String sIMEI = argu[1].toString();
		String sCorrecta = argu[2].toString().toLowerCase();
		String sIMEIAnterior = IMEI().getProperty("Text").toString();
		System.out.println("IMEIAnterior: " + sIMEIAnterior);
		
		// ingresar IMEI
		IMEI().setText(sIMEI);
		String sIMEINuevo = IMEI().getProperty("Text").toString();
		// para pararse en otro campo y que de la pontecial validacion
		SIM().getProperty("Text");
		if (sCorrecta.equals("correcto")){
			if (sIMEINuevo.equals(IMEI().getProperty("Text").toString())) {
				argu[0] = "OK";
				System.out.println("IMEI Nuevo: " + sIMEINuevo);
				System.out.println("Se realizó el cambio de IMEI correctamente");
			}
		} else // IMEI Incorrecto
		{
			if (sIMEIAnterior.equals(IMEI().getProperty("Text").toString())) {
				argu[0] = "OK";
				System.out.println("No se realizó cambio de IMEI porque el IMEI no es correcto");
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

