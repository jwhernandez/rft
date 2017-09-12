package Scripts.Comun;
import resources.Scripts.Comun.ValidacPostServicioHelper;
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
 * Script Name   : <b>ValidacPostServicio</b>
 * Description   : Valida que Liberar Numero se encuentre habilitado y que 
 * servicio esté inhabilitado
 * @Param 0) NOK / OK
 * @since  2015/12/27
 * @author SS
 */
public class ValidacPostServicio extends ValidacPostServicioHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ProductoObjetivo;
		ProductoObjetivo = new String[4];
		// Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 3)action code 

		argu[0] = "OK";
		switch (argu[1].toString()) {
		case "Prepago":
			ProductoObjetivo[0]=dpString("ServicioPrepago");
			System.out.println("Prepago-Servicio");
			break;
		case "Postpago":
			ProductoObjetivo[0]=dpString("ServicioPostpago");
			System.out.println("Postpago-Servicio");
			break;
		default:  
			System.out.println("Stop");
			break;
		} // end del switch
		
		callScript("Scripts.Comun.BuscarProducto", ProductoObjetivo);

		// Chequear que "Service Id" este inhabilitado
		System.out.println(//"ServiceId=" + (ServiceId().isEnabled()) + 
				" Liberar=" + (LiberarNumero().isEnabled()));
	//	logInfo("ServiceId=" + (ServiceId().isEnabled()));
				//.isEnabled()) + " Liberar=" + (LiberarNumero().isEnabled()));
		if (text_serviceId().isEnabled()) {
			argu[0] = "NOK";
		} else {
			// Chequear que LiberarNumero(). este habilitado
			if (!LiberarNumero().isEnabled()) {
				argu[0] = "NOK";
			}
		}
		System.out.println("ValidacPostServicio=" +argu[0]);
		logInfo("ValidacPostServicio=" +argu[0]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

