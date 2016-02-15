package Scripts.Comun;
import resources.Scripts.Comun.IngresarSIMHelper;
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
import com.rational.test.ft.sys.SpyMemory; 
import com.rational.test.ft.sys.SpyMemoryStatistics; 

/**
 * Descripción: Permite ingresar el SIM
 * Parámetros: Total=3 || 0) IN Numero de SIM 1) IN Validar / No Validar 2) OUT Correcto/Incorrecto 
 * 3)IN Servicio
 * Validar chequear que haya quedado en la linea de servicio el sim pasado como parametro
 * SS Nov 2015
 */
public class IngresarSIM extends IngresarSIMHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		SpyMemoryStatistics stats =  SpyMemory.getStats();                 
		System.out.println("Number for Active heaps "+ stats.numberOfActiveHeaps );
		System.out.println("Number of RegisteredObjects "+ getRegisteredTestObjects() );

		String[] Mens;
		Mens = new String[2];

		String[] Producto;
		Producto = new String[4];

		argu[2]="Incorrecto";
		// Buscar fila del servicio
		if (argu.length == 4) 
			//Producto[0]="Servicio Telefonia Movil Prepago";
			Producto[0]=argu[3].toString();

		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();
		int iPosicion = Integer.parseInt (Producto[2]);

		Mens [0] = "ICE SIM Posicion" + Producto[2].toString() + "flag " + sEncontrado; 
		callScript("Scripts.Comun.Informar", Mens);

		LineasPedido().ensureObjectIsVisible();

		if (sEncontrado == "Encontrado"){

			// Ingresar SIM
			LineasPedido().activateRow(iPosicion);
			String sSIM = argu[0].toString();

			Mens [0] = "ICE SIM Posicion" + Producto[2].toString()  + "Valor " + "*" + sSIM +"*"; 
			callScript("Scripts.Comun.Informar", Mens);


			LineasPedido().activateRow(iPosicion);
			sleep(2);
			SIM().setText(sSIM);  	

			Mens [0] = "ICE SIM" +  LineasPedido().getCellText("ICE SIM", iPosicion);
			callScript("Scripts.Comun.Informar", Mens);

			if (argu[1].toString().equals("Validar")) {
				if (LineasPedido().getCellText("ICE SIM", iPosicion).toString().equals(sSIM)) {
					argu[2]="Correcto";
					callScript("Scripts.Comun.RefrescarGuardarPedido"); // se agrega pues queda a veces bloqueado el campo sim
				} 
			}
		}
		
		stats = SpyMemory.getStats(); 
		System.out.println("After script "+ stats.numberOfActiveHeaps); 
		com.rational.test.ft.script.RationalTestScript.unregisterAll(); 
	}
}


