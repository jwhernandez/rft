package Scripts.Comun;
import resources.Scripts.Comun.CancelarPedidoHelper;
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
* Descripción: Cancelar el pedido
* Parámetros:  0) OK/NOK  1)Tramite 
* Script Name   : <b>CancelarPedido</b>
* ej res PortIn
* @since  2016/04/08
* @author SS
* ej res postventabutton_bCancelSO().
*/
public class CancelarPedido extends CancelarPedidoHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTramite = "Venta";
		if (argu.length >= 2 ) { 
			sTramite = argu[1].toString(); // tramite
		}

		if (!sTramite.equals("PortIn")){
			System.out.println("Se presionó el botón de cancelar pedido");
			button_bCancelSO().performAction();
			//Cancelar().performAction(); // cambiar por boton de cancelar
			argu[0] = "OK"; // Cambiar a OK cuando se agregue objeto
		}
		if (sTramite.equals("PortIn")){
			System.out.println("Se presionó el botón de cancelar pedido");	
			CancelarPI().performAction();
			argu[0] = "OK";
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString()); 
	}
}

