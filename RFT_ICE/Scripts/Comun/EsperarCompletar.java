package Scripts.Comun;
import resources.Scripts.Comun.EsperarCompletarHelper;
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
 * Script Name   : <b>EsperarCompletar</b>
 * Description   : Espera a que el pedido llegue a estado completar durante un cierto tiempo parametrizable
 * @Param 0) IN Cantidad de segundos a esperar 
*  @since  2016/01/12
 * @author Sandra
 */
public class EsperarCompletar extends EsperarCompletarHelper
{
	public void testMain(Object[] args) 
	{
		int total = Integer.parseInt(args[0].toString());
		String sPedido = NroPedido().getProperty("Text").toString();
		for (int i=1; i<=total; i++) {
			System.out.println("Vuelta: " + i + " de " + total);
			// leer estado del pedido
			if (EstadoPedido().getProperty("ActiveItem").toString().equals("Completar")){
				i=total;
			} else 
			{ 
				Consulta().performAction();
				NroPedido().setText(sPedido);
				EjecutarConsulta().performAction();
				sleep(1);
				}
		}
	}
}

