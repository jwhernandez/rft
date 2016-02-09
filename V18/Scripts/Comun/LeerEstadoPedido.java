package Scripts.Comun;
import resources.Scripts.Comun.LeerEstadoPedidoHelper;

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
* Descripción: Leer estado del pedido
* Parámetros:  Devuelve el estado 
* SS Nov 2015
*/
public class LeerEstadoPedido extends LeerEstadoPedidoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{

	    System.out.println(EstadoPedido().getProperty("ActiveItem"));	
	    argu[0] = EstadoPedido().getProperty("ActiveItem");
	}
}

