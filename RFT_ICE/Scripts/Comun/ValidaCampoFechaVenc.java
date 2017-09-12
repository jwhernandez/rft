package Scripts.Comun;
import resources.Scripts.Comun.ValidaCampoFechaVencHelper;
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
* Descripción: Validar que el campo fecha de vencimiento se encuentre editable o no editable
* @Param 0) OK/NOK 1) true o false           
* @since  2016/05/04 
* Script Name   : <b>ValidaCampoFechaVenc</b>
* @author SS
* ej: res true
*/
public class ValidaCampoFechaVenc extends ValidaCampoFechaVencHelper
{
public void testMain(Object[] argu) throws RationalTestException
	{
	ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
	argu[0]="NOK";

	Boolean bHabilitado=Boolean.parseBoolean(argu[1].toString().toLowerCase());
	IFtVerificationPoint  FechaFinVP;

	FechaFinVP = vpManual("FechaFinServicioHabilitado",bHabilitado,FechaFinServicio().getProperty("IsEnabled"));
	if (FechaFinVP.performTest()) 	argu[0] = "OK";

	System.out.println("Fecha fin servicio esta editable?: " + FechaFinServicio().getProperty("IsEnabled") +" - y se desea que este:" + bHabilitado);

	ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

