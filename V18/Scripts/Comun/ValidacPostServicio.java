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
		argu[0] = "OK";
		// Chequear que "Service Id" este inhabilitado
		System.out.println("ServiceId=" + (ServiceId().isEnabled()) + " Liberar=" + (LiberarNumero().isEnabled()));
		logInfo("ServiceId=" + (ServiceId().isEnabled()) + " Liberar=" + (LiberarNumero().isEnabled()));
		if (ServiceId().isEnabled()) {
			argu[0] = "NOK";
		} else {
			// Chequear que LiberarNumero(). este habilitado
			if (!LiberarNumero().isEnabled()) {
				argu[0] = "NOK";
			}
		}
		System.out.println("ValidacPostServicio=" +argu[0]);
		logInfo("ValidacPostServicio=" +argu[0]);

	}
}

