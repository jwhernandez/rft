package Scripts.Comun;
import resources.Scripts.Comun.ValidaCampoEstadoHelper;
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
* Descripción: Chequea que el campo estado permite o no modificacion
* @Param 0) OK/NOK 1) true o false           
* @since  2016/05/04 
* Script Name   : <b>ValidaCampoEstado</b>
* @author SS
* ej: res true
*/
public class ValidaCampoEstado extends ValidaCampoEstadoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";

		IFtVerificationPoint  EstadoVP;
		Boolean bHabilitado=Boolean.parseBoolean(argu[1].toString().toLowerCase());

		EstadoVP = vpManual("EstadoLineaHabilitado",bHabilitado,EstadoLinea().getProperty("IsEnabled"));
		if (EstadoVP.performTest()) 	argu[0] = "OK";

		System.out.println("El estado de la linea esta editable?: " + EstadoLinea().getProperty("IsEnabled") +" - y se desea que este:" + bHabilitado);

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

