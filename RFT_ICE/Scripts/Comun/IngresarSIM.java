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
 * 3)IN Servicio 4) Tramite
 * Validar chequear que haya quedado en la linea de servicio el sim pasado como parametro
 * SS Nov 2015
 * Ult modif ss 10-7-2017 se agrega opcion de buscar en DP de parametria En este caso
 * usa variable TipoPerfilCorrecto
 * ej:  89506010511110639592 Validar res "Servicio Telefonia Movil Prepago" PortIn
 */

public class IngresarSIM extends IngresarSIMHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Mens;
		Mens = new String[2];

		String[] Producto;
		Producto = new String[6];
		
		String[] Refrescar;
		Refrescar = new String[2];

		
		String sTramite = "Venta";
		if (argu.length >= 5 ) { 
			sTramite = argu[4].toString(); // tramite
		}

		argu[2]="Incorrecto";
		// Buscar fila del LineasPedidoPI().SIM_PI().servicio
		
		//Producto[0]="Servicio Telefonia Movil Prepago";
		String sServicio = Producto[0]=argu[3].toString(); // Servicio
		Producto[0]=sServicio; // 10-7-2017		
		
		// ----------------------------------- 
		// se agrega parametria para servicio
		// ----------------------------------- 

		if (sServicio.contains("DP:"))
		{
			String sOpcion = sServicio.replace("DP:","");
			switch (sOpcion.toLowerCase()) {
			case "prepago":
				Producto[0]=dpString("ServicioPrepago");
				System.out.println("Opcion Prepago");
				break;
			case "postpago":
				Producto[0]=dpString("ServicioPostpago");
				System.out.println("Opcion Postpago");
				break;
			case "datos":
				Producto[0]=dpString("ServicioDatos");
				System.out.println("Opcion Datos");
				break;
			case "hibrido":
				Producto[0]=dpString("ServicioHibridos");
				System.out.println("Opcion Hibrido");
				break;
			default:  
				System.out.println("Opcion ingresada invalida");
				break;
			} // end del switch
		}
		// ----------------FIN------------------- 

		Producto[4]="Si"; // se le indica buscar desde el comienzo
		Producto[5]=argu[4].toString(); // se le envia el tramite
		
		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();
		int iPosicion = Integer.parseInt (Producto[2]);

		Mens [0] = "ICE SIM Posicion" + Producto[2].toString() + "flag " + sEncontrado; 
		callScript("Scripts.Comun.Informar", Mens);

		if (sEncontrado == "Encontrado"){

			// Ingresar SIM
			if (!sTramite.equals("PortIn")){ LineasPedido().activateRow(iPosicion);}
			if (sTramite.equals("PortIn")){ LineasPedidoPI().activateRow(iPosicion);}

			String sSIM = argu[0].toString();

			Mens [0] = "ICE SIM Posicion" + Producto[2].toString()  + "Valor " + "*" + sSIM +"*"; 
			callScript("Scripts.Comun.Informar", Mens);

			String sSIMIngresada="No Ingresada";
			if (!sTramite.equals("PortIn")){LineasPedido().activateRow(iPosicion);
				sleep(2);
				SIM().setText(sSIM); 
				sSIMIngresada = LineasPedido().getCellText("ICE SIM", iPosicion);}	
			if (sTramite.equals("PortIn")){ LineasPedidoPI().activateRow(iPosicion);
				sleep(2);
				SIM_PI().setText(sSIM); 
				sSIMIngresada = LineasPedidoPI().getCellText("ICE SIM", iPosicion);}	

			Mens[0] = "ICE SIM" +  sSIMIngresada;
			callScript("Scripts.Comun.Informar", Mens);

			if (argu[1].toString().equals("Validar")) {
				if (sSIMIngresada.equals(sSIM)) {
					argu[2]="Correcto";
					Refrescar[1] = argu[4].toString();
					callScript("Scripts.Comun.RefrescarGuardarPedido", Refrescar); // se agrega pues queda a veces bloqueado el campo sim
				} 
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}
