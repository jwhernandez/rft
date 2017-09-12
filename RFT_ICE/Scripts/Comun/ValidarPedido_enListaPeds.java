package Scripts.Comun;
import resources.Scripts.Comun.ValidarPedido_enListaPedsHelper;

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
 * Script Name   : <b>ValidarPedido_enListaPeds</b>
 * Description   : Valida que un pedido exista en la vista indicada de la lista de pedidos  
 * Parametros: 0) OK / NOK  1) true/false (valida que este o no este en la lista de pedidos) 2) vista a usar 3) usuario 4) nro pedido
 * @since  2017/04/25
 * @author SS
 * ej res false All Frontal 1-1753707433
  */
public class ValidarPedido_enListaPeds extends ValidarPedido_enListaPedsHelper
{
	public void testMain(Object[]  argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		String sVista = argu[2].toString().toLowerCase();
		String sUsuario = argu[3].toString().toLowerCase();
		Boolean bEncontrar = Boolean.parseBoolean(argu[1].toString().toLowerCase());

		Pestañas().gotoScreen("Sales Order Screen");
		
		if (sUsuario.equals("frontal") && sVista.equals("all") )
		{

			// 
			Pestañas().gotoView("Order Entry - My Orders View (Sales)");
			TabsPedido().goTo("Order Entry - All Orders View (Sales)",  "L2");

			System.out.println("Se ingreso en vista All con usuario Frontal");
			sleep(3);
			try {
				TabsPedido().goTo("Order Entry - All Orders View (Sales)",  "L2");}
				catch (Exception e) {
					System.out.println("Mensaje de excepción: "+e.getMessage());
					sleep(1);
					TabsPedido().goTo("Order Entry - All Orders View (Sales)",  "L2");}
					sleep(3);

	 				NewQueryAllOrders().performAction();
					NroPedidoAllOrders().setText(argu[4].toString());
					ExecuteQueryAllOrders().performAction();
 					sleep(5);

					int iCantLineas = Integer.parseInt(ListaPedidos().getProperty("RowsCount").toString());

					if ((iCantLineas ==1 && bEncontrar) || (iCantLineas==0 && !bEncontrar))
						argu[0]="OK";
					else 
						System.out.println("CantLineas erroneas " + iCantLineas);	
				}
			else 
 				System.out.println("Opcion no implementada");		
 
 		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

