package Scripts.Comun;
import resources.Scripts.Comun.ValidaEstadoServicioHelper;
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
 * Description   : Valida el estado del servicio en la vista360 del cliente, asume que se ha buscado
 * previamente el nro de servicio y que hay una sola linea con el nombre del plan
 * @author SS
 * @since  2016/05/03
 * @Param 0)OK /NOK 1) estado deseado del servicio Activo o el que fuera segun figura en el activo
 * Script Name   : <b>ValidaEstadoServicio</b>
 * Ej: res Activo
 */
public class ValidaEstadoServicio extends ValidaEstadoServicioHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		LineasPlanesVista360().activateRow(0);
		String sEstadoServicioDeseado=argu[1].toString(); // No se lleva a lowercase a proposito
		IFtVerificationPoint EstadoServicioVP;
		EstadoServicioVP = vpManual("EstadoServicio", sEstadoServicioDeseado, Estado().getProperty("ActiveItem"));
		if (EstadoServicioVP.performTest()) {
			System.out.println("Estado Servicio Valido: " + Estado().getProperty("ActiveItem"));
			argu[0] = "OK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

