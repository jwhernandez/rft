package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarServicioPedPIenListaHelper;

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
 * Description   : Verifica si el nroservicio esta habilitado o no en la lista de port/in  
 * Precondicion: estar en la lista de pedidos de portin con un solo pedido seleccionado
 * @author SS
 * Script Name   : <b>ValidarServicioPedPIenLista</b>
 * @since  2017/02/03
 * Param 0) OK/NOK  1) estado deseado
 * ej res FALSE
 */
public class ValidarServicioPedPIenLista extends ValidarServicioPedPIenListaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		String sEstado = NroServicioPortabilidad().getProperty("IsEnabled").toString();
		System.out.println( "EstadoPedPI " + sEstado);
		
		boolean sEstadoDeseado=Boolean.valueOf(argu[1].toString().toLowerCase());
		
		IFtVerificationPoint EstadoPedPIVP;
		EstadoPedPIVP = vpManual("EstadoPedPI",sEstadoDeseado, sEstado);
		System.out.println("Estado deseado y en SBL = " + argu[1].toString()+ "-" +sEstado);
		infoPaso(getScriptName().toString(), Tipo.DATO,"EstadoPedPI", sEstado);
		
		if (EstadoPedPIVP.performTest()) argu[0]="OK"; 
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

