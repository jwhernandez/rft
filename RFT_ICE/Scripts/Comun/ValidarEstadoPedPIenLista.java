package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarEstadoPedPIenListaHelper;
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
 * Description   : Verifica el estado sea igual al estado deseado en la lista de port/in haciendo drill down al pedido
 * Precondicion: estar en la lista de pedidos de portin con un solo pedido seleccionado
 * @author SS
 * Script Name   : <b>ValidarEstadoPedPIenLista</b>
 * @since  2017/01/31
 * Param 0) OK/NOK  1) estado deseado
 * ej res Cancelado
 */
public class ValidarEstadoPedPIenLista extends ValidarEstadoPedPIenListaHelper
{

	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		ListaPedidosPortInt().drillDownColumn("Order Number", 0);
		sleep(5);
	
		String sEstado = EstadoPedenCat().getProperty("ActiveItem").toString();
		System.out.println( "EstadoPedPI " + sEstado);
		
		IFtVerificationPoint EstadoPedPIVP;
		EstadoPedPIVP = vpManual("EstadoPedPI",argu[1].toString(), sEstado);
		System.out.println("Estado deseado y en SBL = " + argu[1].toString()+ "-" +sEstado);
		infoPaso(getScriptName().toString(), Tipo.DATO,"EstadoPedPI", sEstado);
		
		if (EstadoPedPIVP.performTest()) argu[0]="OK"; 
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

