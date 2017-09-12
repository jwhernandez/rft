package Scripts.Comun;
import resources.Scripts.Comun.ModificarCategoriaAdminHelper;
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
 * Descripción: Permite modificar la categoria vía Administración
 * Parámetros: 0) Categoria a ingresar 1) OK / NOK 
 * Ej: A res
 * Precondición: Estar en la pestaña del pedido
 * @since  2016/02/15
 * @author SSASTRE
 */
public class ModificarCategoriaAdmin extends ModificarCategoriaAdminHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Pedido;
		Pedido = new String[2];

		argu[1]="NOK";
		
		// Se asegura la vista dado que luego de un CP no es esta la que queda
		TabsPedido().goTo("Order Entry - Line Items Detail View (Sales)", "L3");
		
		String sPedido = NroPedido().getProperty("Text").toString();
		
		PestañasPedido().gotoView("ICE Order Entry - Line Items Detail View Admin");
		sleep(3);
		
		NewQuery().performAction();
		NroPedidoAdmin(ANY,DISABLED).setText(sPedido);
		ExecuteQuery().performAction();
		sleep(2);
		
		CatCredAdmin().setText(argu[0].toString());
		
		Pedido[0] = sPedido;
		callScript("Scripts.Comun.BuscarPedidoVenta", Pedido);
		
		argu[1]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString() );
	}
}

