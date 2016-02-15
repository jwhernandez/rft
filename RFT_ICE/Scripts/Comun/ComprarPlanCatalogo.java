package Scripts.Comun;
import resources.Scripts.Comun.ComprarPlanCatalogoHelper;
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
 * Description   : Compra un plan desde el catalogo
 * Script Name   : <b>ComprarPlanCatalogo</b>
 * @Param 0) IN Producto a comprar 1) OK/NOK
 * @author Sandra
 */
public class ComprarPlanCatalogo extends ComprarPlanCatalogoHelper
{
	public void testMain(Object[] argu) 
	{
		argu[1] = "NOK";
		PestañasPedido().goTo("Sales Order-Search Catalog View", 
                                     "L4");
		Nombre().click();
		String sPlan = "'" + argu[0].toString() + "'";
		//Nombre().setText("'PLAN CONECTADO 4 12 M'");
		Nombre().setText(sPlan);
		
		Buscar().click();
		
		Agregar().performAction();
		argu[1] = "OK";
	}
}

