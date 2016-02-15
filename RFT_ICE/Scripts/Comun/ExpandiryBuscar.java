package Scripts.Comun;
import resources.Scripts.Comun.ExpandiryBuscarHelper;
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
 * Description   : Expande el nodo padre y buscar el producto hijo
 * Script Name   : <b>ExpandiryBuscar</b>
 * Parametros : 0) Producto padre 1) Producto Hijo 2)Encontro / No Encontro
 * Ej: "Servicio de Telefonia Movil" "Grupo SVA" res
 * Ej: "Servicio de Telefonia Movil" "Deposito de Garantia" res
 * Precondiciones Estar en las lineas del pedido de venta
 * @author Sandra
 * @since  2016/01/19
 */
public class ExpandiryBuscar extends ExpandiryBuscarHelper
{
	public void testMain(Object[] argu) 
	{
		String[] ExpandirLinea;
		ExpandirLinea = new String[2];
		
		String[] BuscProd;
		BuscProd = new String[4];
		
		argu[2] = "No Encontro";
		
		ExpandirLinea[0]=argu[0].toString();
		callScript("Scripts.Comun.ExpandirLinea",ExpandirLinea);
		
		BuscProd[0]=argu[1].toString();	
		callScript("Scripts.Comun.BuscarProducto",BuscProd);
		argu[2]=BuscProd[1].toString();
		
		System.out.println("Resultado: "+argu[2]);
	}
}

