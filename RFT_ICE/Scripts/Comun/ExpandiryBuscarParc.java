package Scripts.Comun;
import resources.Scripts.Comun.ExpandiryBuscarParcHelper;

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
 * Description   : Expande el nodo padre y buscar parte del nombre del producto hijo (Lineas del pedido)
 * Script Name   : <b>ExpandiryBuscar</b>
 * Parametros : 0) Producto padre 1) Producto Hijo 2)Encontro / No Encontro
 * Ej: "Servicio de Telefonia Movil" "Grupo SVA" res
 * Ej: "Servicio de Telefonia Movil" "Deposito de Garantia" res
 * Precondiciones Estar en las lineas del pedido de venta
 * @author Sandra
 * @since  2017/05/02
 * 	Ej	"Servicio de Telefonia Movil" "No identificador de llamada" res 
 */
public class ExpandiryBuscarParc extends ExpandiryBuscarParcHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ExpandirLinea;
		ExpandirLinea = new String[3];
		//0) Nombre del producto a expandir 1) OK/NOK 2)argumento opcional para indicar desde posicion actual 
		
		String[] BuscProd;
		BuscProd = new String[5];
		
		argu[2] = "No Encontro";
		
		ExpandirLinea[0]=argu[0].toString();
		ExpandirLinea[2]="No";	
		callScript("Scripts.Comun.ExpandirLineaParc",ExpandirLinea);
		
		BuscProd[0]=argu[1].toString();	
		BuscProd[4]="No";	
		callScript("Scripts.Comun.BuscarProductoParc",BuscProd);
		argu[2]=BuscProd[1].toString();
		
		logWarning("This is a warning", getRootTestObject().getScreenSnapshot()); 
		// Ver como asegurarse estar en la pantalla correcta
		logInfo("This is an info message", getRootTestObject().getScreenSnapshot());
		System.out.println("Resultado: "+argu[2]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}

