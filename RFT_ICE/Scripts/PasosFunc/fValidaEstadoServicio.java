package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidaEstadoServicioHelper;
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
 * Descripcion     : Valida el estado del servicio en la vista360 del cliente, asume que se ha buscado
 * previamente el nro de servicio y que hay una sola linea con el nombre del plan
 * @param 0) numero de caso 1)Estado: Activo, Inactivo 2) ambiente 3) true/False 4)Nro paso
 * ej CP46 Activo PREQA true 20
 * Script Name   : <b>fValidaEstadoServicio</b>
 * @since  2016/05/03
 * @author SS
 */
public class fValidaEstadoServicio extends fValidaEstadoServicioHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] EstadoServicio;
		EstadoServicio = new String[2];
		// Parámetros: 0)OK/NOK 1)true (moroso) / false (no moroso)

		EstadoServicio[1] = args[1].toString(); // a proposito no se envia a lowercase

		callScript("Scripts.Comun.ValidaEstadoServicio",EstadoServicio);
		
		if  ((EstadoServicio[0].toString().equals("NOK"))){
			//MensError[0] = "Consulta de Estado del Servicio en la vista 360 falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

