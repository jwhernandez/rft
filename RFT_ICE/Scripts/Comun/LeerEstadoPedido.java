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
* Parámetros:  0) Devuelve el estado 1)Tramite 
* Script Name   : <b>LeeEstadoPedido</b>
* ej res PortIn
* SS Nov 2015
*/
public class LeerEstadoPedido extends LeerEstadoPedidoHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = "Venta";
		if (argu.length >= 2 ) { 
			sTramite = argu[1].toString(); // tramite
		}

		if (!sTramite.equals("PortIn")){
			System.out.println(EstadoPedido().getProperty("ActiveItem"));	
			argu[0] = EstadoPedido().getProperty("ActiveItem");
		}
		if (sTramite.equals("PortIn")){
			System.out.println(EstadoPedidoPI().getProperty("ActiveItem"));	
			argu[0] = EstadoPedidoPI().getProperty("ActiveItem");
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString()); 
	}
}

