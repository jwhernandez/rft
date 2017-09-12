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
 * Description   : Busca y expande una linea del Pedido
 * El argumento 2 se usa para indicar si hay que busca el producto a expandir
 * "Si" es el defecto a menos que se le pase el argumento 2 como opcional.
 * "Si" indica que hay que buscar el producto, otra alternativa significa que 
 * ya se está en la linea del producto a expandir.
 * @author SS
 * Parámetros: 0) Nombre detable_htmlTable_0().l producto a expandir 1) OK/NOK
 *  2)argumento opcional para indicar desde posicion actual  3) tramite
 * Pre condiciones Estar en las lineas del pedido de venta
 * Script Name   : <b>ExpandirLinea</b>
 * @since  2016/01/19
 * Ej "Servicio de Telefonia Movil" res no
 * ultima modificacion
 * ss 23-3-2017 se agrega tramite para port-in
  */
public class ExpandirLinea extends ExpandirLineaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] BuscProd;
		BuscProd = new String[4];
		
		String sTramite = argu[3].toString(); // ss se agrega tramite
		
		argu[1] = "NOK";
//		LineasPedido().waitForExistence();
//		LineasPedido().ensureObjectIsVisible();
//		sleep(5);
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 
		//3)action code 
		//Queda posicionado en la linea del producto

		// Decidir si buscar producto
		String sBuscaProducto = "Si";
		if (argu.length >= 3 && !argu[2].equals("NA")) { 
			sBuscaProducto = argu[2].toString(); 
		}
		
		int iLinea = 0;
		if (sBuscaProducto.equals("Si")) {
			BuscProd[0] = argu[0].toString(); // No Caller Id
			callScript("Scripts.Comun.BuscarProducto", BuscProd);
			if (BuscProd[1].toString().equals("Encontrado")) {
				iLinea = (Integer) Integer.parseInt(BuscProd[2].toString()) ;
			}
		}else
		{
			System.out.println("Se expande la posicion actual");
			if (!sTramite.equals("PortIn"))
				iLinea = Integer.parseInt(LineasPedido().getProperty("ActiveRow").toString());
			if (sTramite.equals("PortIn"))
				iLinea = Integer.parseInt(LineasPedido_PI().getProperty("ActiveRow").toString());

		}

		if (!sTramite.equals("PortIn"))
		{
			if (!LineasPedido().isRowExpanded(iLinea)) {
				LineasPedido().clickHier(); 
			}	
			if (!LineasPedido().isRowExpanded(iLinea)) {
				LineasPedido().clickHier(); 
			}	
		}
		
		if (sTramite.equals("PortIn"))
		{
			if (!LineasPedido_PI().isRowExpanded(iLinea)) {
				LineasPedido_PI().clickHier(); 
			}	
			if (!LineasPedido_PI().isRowExpanded(iLinea)) {
				LineasPedido_PI().clickHier(); 
			}	
		}
		
		argu[1] = "OK";
		System.out.println("Resultado:" + argu[1]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

