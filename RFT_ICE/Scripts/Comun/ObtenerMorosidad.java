package Scripts.Comun;
import resources.Scripts.Comun.ObtenerMorosidadHelper;
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
 * Description   : Obtiene morosidad de un pedido de CP o de Venta
 * Script Name   : <b>ObtenerMorosidad</b>
 * Precondiciones: Estar en el pedido en cualquier pestaña
 * Parametros 0) OUT OK/NOK
 * ej res
 * @author Sandra
 * @since  2016/01/19
 */
public class ObtenerMorosidad extends ObtenerMorosidadHelper
{
	public void testMain(Object[] argu) 
	{
		argu[0]="NOK";
		PestañasPedido().goTo("Sales Order-Browse Catalog View","L3");
		ConsultaMorosidad().performAction();
		PestañasPedido().goTo("Order Entry - Line Items Detail View (Sales)","L3");
		argu[0]="OK";
	}
}

