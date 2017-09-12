package Scripts.Comun;
import resources.Scripts.Comun.NuevoPedidoPortInSimpleHelper;
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
 * Description   : Permite crear un nuevo pedido de port-in. No ingresa el nro de servicio ni nigun dato.
 * Si captura el nro de pedido generado
 * Se asume que el usuario es el frontal para la vista
 * @author SS
 * Script Name   : <b>NuevoPedidoPortInSimple</b>
 * @since  2017/03/07
 *   @Param 0) OK/NOK 1) Nro Pedido
 * 
 */
public class NuevoPedidoPortInSimple extends NuevoPedidoPortInSimpleHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		
		NuevoPedidoPortabilidad().performAction();
		sleep(5);
		
		String sPedido = NroPedidoPortabilidad().getProperty("Text").toString();
		System.out.println(NroPedidoPortabilidad().getProperty("Text"));

		argu[1] = sPedido;
		setNroPedido(sPedido);
		
		argu[0]="OK";
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

