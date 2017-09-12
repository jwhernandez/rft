package Scripts.Comun;
import resources.Scripts.Comun.ExpandiryBuscarActivoHelper;
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
 * Description   : Expande el nodo padre y buscar el producto hijo (Lineas del activo)
 * Script Name   : <b>ExpandiryBuscarActivo</b>
 * Parametros : 0) Producto padre 1) Producto Hijo 2)Encontro / No Encontro
 * Ej: "Servicio de Telefonia Movil" "Grupo SVA" res
 * Ej: "Servicio de Telefonia Movil" "Deposito de Garantia" res
 * Precondiciones Estar en las lineas del activo
 * @author SS
 * @since  2016/04/29
 */
public class ExpandiryBuscarActivo extends ExpandiryBuscarActivoHelper
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
		
		MostrarMasMenos().performAction();
		
		ExpandirLinea[0]=argu[0].toString();
		ExpandirLinea[2]="Si"; // que busque desde el comienzo del activo	
		callScript("Scripts.Comun.ExpandirLineaActivo",ExpandirLinea);
		
		BuscProd[0]=argu[1].toString();	
		BuscProd[4]="No";	
		callScript("Scripts.Comun.BuscarProductoActivo",BuscProd);
		argu[2]=BuscProd[1].toString();
		
		ExpandirLinea[0]=argu[0].toString();
		ExpandirLinea[2]="no"; // que solo expanda la ultima linea
		callScript("Scripts.Comun.ExpandirLineaActivo",ExpandirLinea);
		
		logWarning("This is a warning", getRootTestObject().getScreenSnapshot()); 
		// Ver como asegurarse estar en la pantalla correcta
		logInfo("This is an info message", getRootTestObject().getScreenSnapshot());
		System.out.println("Resultado: "+argu[2]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}

