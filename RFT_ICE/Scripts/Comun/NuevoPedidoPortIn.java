package Scripts.Comun;
import resources.Scripts.Comun.NuevoPedidoPortInHelper;
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
 * Description   : Functional Test Script
 * @author Sandra
 * @since  2016/03/21
 * Junio SS modifica para incluir los objetos de QA que son una vista diferente
 * ss feb / 2017 los objetos se unifican y se modifica para unificar
 * @Param 0)numero ASRM 1)OK/NOK 2) Ambiente
 * Por defecto el ambiente es QA.
 * Script Name   : <b>NuevoPedidoPortIn</b>
 * ej; ASRM invalido  - Valido = 89782666
 */
public class NuevoPedidoPortIn extends NuevoPedidoPortInHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[1]="NOK";
		String sAmbiente = "QA";
		if (!argu[2].toString().equals("NA")) sAmbiente = argu[2].toString();
		
		NuevoPedidoPortabilidad().performAction();
		if (sAmbiente.equals("QA")) 
		{
			NroServicioPortabilidad().setText(argu[0].toString()); // Se unifica y se usa el mismo objeto en qa a partir feb/2017 
			Portar().performAction(); // Se unifica y se usa el mismo objeto en qa a partir feb/2017 
			// a partir de esta acción el pedido puede cancelarse si el numero es invalido
		}
		else 
			{
			NroServicioPortabilidad().setText(argu[0].toString());
			Portar().performAction();
			// a partir de esta acción el pedido puede cancelarse si el numero es invalido
			}

		
		argu[1]="OK";
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

