package Scripts.Comun;
import resources.Scripts.Comun.ExpandirLineaHelper;
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
 * Parámetros: 0) Nombre del producto a expandir 1) OK/NOK
 * Pre condiciones Estar en las lineas del pedido de venta
 * Script Name   : <b>ExpandirLinea</b>
 * @since  2016/01/19
  */
public class ExpandirLinea extends ExpandirLineaHelper
{
	public void testMain(Object[] argu) 
	{
		String[] BuscProd;
		BuscProd = new String[4];
		
		argu[1] = "NOK";
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 
		//3)action code
		//Queda posicionado en la linea del producto

		BuscProd[0] = argu[0].toString(); // No Caller Id
		callScript("Scripts.Comun.BuscarProducto", BuscProd);
		if (BuscProd[1].toString().equals("Encontrado")) {
			int iLinea = (Integer) Integer.parseInt(BuscProd[2].toString()) ;
			
			if (!LineasPedido().isRowExpanded(iLinea)) {
				LineasPedido().clickHier(); 
				argu[1] = "OK";
			}	
		}
		
		System.out.println("Resultado:" + argu[1]);
	}
}

