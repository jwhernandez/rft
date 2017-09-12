package Scripts.Comun;
import resources.Scripts.Comun.ValidaCampoFechaIniHelper;
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
* Descripción: Chequea que el campo fecha inicio permite o no modificacion
* @Param 0) OK/NOK 1) true o false           
* @since  2016/05/04 
* Script Name   : <b>ValidaCampoFechaIni</b>
* @author SS
* ej: res false 
*/
public class ValidaCampoFechaIni extends ValidaCampoFechaIniHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";

		IFtVerificationPoint  FechaVP;
		Boolean bHabilitado=Boolean.parseBoolean(argu[1].toString().toLowerCase());

		FechaVP = vpManual("FechaIni",bHabilitado,FechaInicioServicio().getProperty("IsEnabled"));
		if (FechaVP.performTest()) 	argu[0] = "OK";

		System.out.println("La Fecha de inicio se encuentra en estado: " + FechaInicioServicio().getProperty("IsEnabled") +"  y se desea estado-" + bHabilitado);

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

