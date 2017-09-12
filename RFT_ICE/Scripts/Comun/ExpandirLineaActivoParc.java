package Scripts.Comun;
import resources.Scripts.Comun.ExpandirLineaActivoParcHelper;

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
 * Description   : Expande una linea del activo con parte del nombre
 * @author VC
 * Parámetros: 0) Nombre del producto a expandir 1) OK/NOK
 *  2)argumento opcional para indicar desde posicion actual  (si indica desde el comienzo)
 * Pre condiciones Estar en las lineas del pedido de venta
 * Script Name   : <b>ExpandirLineaActivo</b>
 * @since  2017/05/03
 * Ej "Servicio de Telefonia Movil" res no
  */
public class ExpandirLineaActivoParc extends ExpandirLineaActivoParcHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] BuscProd;
		BuscProd = new String[4];
		
		argu[1] = "NOK";
		//Parámetros: 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion y 
		//3)action code 
		//Queda posicionado en la linea del producto

		// Decidir si buscar producto
		String sBuscaProducto = "si";
		if (argu.length >= 3 ) { 
			sBuscaProducto = argu[2].toString().toLowerCase(); 
		}
		
		int iLinea = 0;
		if (sBuscaProducto.equals("si")) {
			BuscProd[0] = argu[0].toString(); // No Caller Id
			callScript("Scripts.Comun.BuscarProductoActivoParc", BuscProd);
			if (BuscProd[1].toString().equals("Encontrado")) {
				iLinea = (Integer) Integer.parseInt(BuscProd[2].toString()) ;
			}
		}else
		{
			System.out.println("Se expande desde la posicion actual");
			iLinea = Integer.parseInt(LineasActivoCtaFact().getProperty("ActiveRow").toString());
		}

		if (!LineasActivoCtaFact().isRowExpanded(iLinea)) {
			LineasActivoCtaFact().clickHier(); 
			argu[1] = "OK";
		}	
		System.out.println("Resultado:" + argu[1]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

