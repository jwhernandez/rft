package Scripts.Comun;
import resources.Scripts.Comun.BuscarDescuentosHelper;

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
 * Description   : Expande el nodo padre y busca lineas con la palabra descuento (Lineas del pedido)
 * Script Name   : <b>BuscarDescuentos</b>
 * Parametros : 0) Producto padre 1)Encontro / No Encontro
 * Ej: "Servicio de Telefonia Movil" res
 * Precondiciones Estar en las lineas del pedido de venta
 * @author VC
 * @since  2016/12/28
 *  Última modificación VC 20170605 Se agrega el trámite producto de la modificación en el script ExpandirLinea
 * 	Ej	"Servicio Telefonia Movil Prepago" res 
 */
public class BuscarDescuentos extends BuscarDescuentosHelper
{

	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ExpandirLinea;
		ExpandirLinea = new String[4]; // VC 20170605 Se cambia de 3 a 4 parametros para agregar el trámite producto del cambio en expandir linea
		
		String[] BuscDesc;
		BuscDesc = new String[5];
		
		argu[1] = "No Encontro";
		
		ExpandirLinea[0]=argu[0].toString();
		ExpandirLinea[2]="No";
		ExpandirLinea[3]=argu[2].toString(); // VC 20170605 Se agrega el trámite para pasarlo a expandir linea
		callScript("Scripts.Comun.ExpandirLinea",ExpandirLinea);
		
		BuscDesc[0]="descuento";	
		BuscDesc[4]="No";	
		callScript("Scripts.Comun.BuscarProductoParc",BuscDesc);
		argu[1]=BuscDesc[1].toString();
		
		logWarning("This is a warning", getRootTestObject().getScreenSnapshot()); 
		// Ver como asegurarse estar en la pantalla correcta
		logInfo("This is an info message", getRootTestObject().getScreenSnapshot());
		System.out.println("Resultado: "+argu[1]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

