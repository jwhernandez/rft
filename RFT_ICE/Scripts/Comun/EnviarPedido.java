package Scripts.Comun;
import resources.Scripts.Comun.EnviarPedidoHelper;
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
 * Descripción: Permite Seleccionar enviar pedido
 * Parámetros: 1 || 0) Creado / No creado
 * SS Nov 2015
 */
public class EnviarPedido extends EnviarPedidoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		argu[0] = "No creado";
		if (enviarPedido().isEnabled()) {
			enviarPedido().performAction();
			argu[0] = "Creado";
		}
	}
}

