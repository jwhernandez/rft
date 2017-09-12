package Scripts.Comun;
import resources.Scripts.Comun.IngresarIMEIHelper;
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
 * Descripción: Permite ingresar el imei
 * Parámetros: 0) Postpago / Prepago  / Datos 1) Servicio / "TerminalesPromocionales" o Terminal 
 * 2) IMEI 3) OK/NOK 4)Tramite 5) terminal o NA
 * Ej Datos Terminal 352214043680272 res Venta SAMSUNG TAB 3 LITE 7 NEGRO
 * Prepago TerminalesPromocionales 111 res venta NA
 * SS Nov 2015
 * Ultima modificacion 25-11-2016
 * 	ff Se agrega un Ctrl+S luego de ingresar el IMEI para grabarlo y 
 * se agrega un step of usando el campo descripcion del encabezado
 * Ultima modificacion SS 1-2-2017 se agrega decripcion para PI
 * ult modif ss 10-7-2017 se agrega opcion de hibridos
 */
public class IngresarIMEI extends IngresarIMEIHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Producto;
		Producto = new String[6];
		// 0) Nombre del producto 1) Encontrado/No Encontrado 2)posicion 
		// y 3)OUT action code 4) desde la linea o no (Si No) 5)Tramite

		String sTramite = "Venta";
		if (argu.length >= 5 ) { 
			sTramite = argu[4].toString(); // tramite
		}

		argu[3]="NOK";
		int iAj = 0;
		//Busca path producto

		switch (argu[0].toString().toLowerCase()+"-" + argu[1].toString().toLowerCase()) {
		case "prepago-servicio":
			Producto[0]=dpString("ServicioPrepago");
			System.out.println("Prepago-Servicio");
			break;
		case "postpago-servicio":
			Producto[0]=dpString("ServicioPostpago");
			System.out.println("Postpago-Servicio");
			break;
		case "datos-servicio":
			Producto[0]=dpString("ServicioDatos");
			System.out.println("Datos-Servicio");
			break;
		case "hibrido-servicio":
			Producto[0]=dpString("ServicioHibridos");
			System.out.println("Hibrido-Servicio");
			break;
		case "prepago-terminalespromocionales":
			Producto[0]=dpString("Terminales");
			System.out.println("Prepago-TerminalesPromocionales");
			break;
		case "postpago-terminalespromocionales":
			Producto[0]=dpString("Terminales");
			System.out.println("Postpago-TerminalesPromocionales");
			break;
		case "hibrido-terminalespromocionales":
			Producto[0]=dpString("Terminales");
			System.out.println("Hibrido-TerminalesPromocionales");
			break;
		case "datos-terminalespromocionales":
			Producto[0]=dpString("Terminales");
			System.out.println("Datos-TerminalesPromocionales");
			break;
		case "prepago-terminal":
			Producto[0]=dpString("Terminales");
			System.out.println("Prepago-Terminal");
			iAj =1;
			break;
		case "postpago-terminal":
			Producto[0]=dpString("Terminales");
			System.out.println("Postpago-Terminal");
			iAj =1;
			break;
		case "datos-terminal":
			Producto[0]=dpString("Terminales"); // primero buscar terminales promocionales y lo expande
			System.out.println("Datos-Terminal");
			iAj =1;
			break;
		case "hibrido-terminal":
			Producto[0]=dpString("Terminales"); // primero buscar terminales promocionales y lo expande
			System.out.println("Hibridos-Terminal");
			iAj =1;
			break;
		default:  
			System.out.println("Opcion ingresada invalida " +argu[0].toString().toLowerCase()+"-" + argu[1].toString().toLowerCase());
			break;
		} // end del switch

		Producto[4]="Si"; // se le indica buscar desde el comienzo
		Producto[5]=sTramite;
		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();
		int iPosicion = Integer.parseInt (Producto[2]);

		// Buscar la posición del terminal
		if (!sTramite.equals("PortIn")) {
			if ( iAj==1) 
			{
				LineasPedido().activateRow(iPosicion);
				System.out.println(!LineasPedido().isRowExpanded(iPosicion));
				if (!LineasPedido().isRowExpanded(iPosicion)) { // si no se ve el terminal
					LineasPedido().clickHier(); } 
				// llamar a buscar el terminal para que se posicione correctamente en esa linea
				Producto[0]=argu[5].toString(); // primero buscar terminales promocionales y lo expande
				Producto[1]=""; // primero buscar terminales promocionales y lo expande
				Producto[2]="";
				Producto[3]="";
				Producto[4]="Si"; // se le indica buscar desde el comienzo
				Producto[5]=sTramite;	
				callScript("Scripts.Comun.BuscarProducto",Producto);
				sEncontrado = Producto[1].toString();
				iPosicion = Integer.parseInt (Producto[2]);
				// Siebel().processKeyboardAccelerator("Ctrl+S"); // ff 25-11-2016, sacado x ss
			}
			if (sEncontrado.toLowerCase().equals("encontrado")){
				// Ingresar IMEI
				System.out.println("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
				logInfo("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
				System.out.println("ICE IMEI Posicion" + iPosicion + "IMEI a ingresar "+ argu[2].toString());

				LineasPedido().activateRow(iPosicion);
				IMEI().setText(argu[2].toString());
				if (LineasPedido().getCellText("ICE IMEI", iPosicion).equals(argu[2].toString().toString()))
					argu[3] = "OK";
				System.out.println("ICE IMEI ingresado" + LineasPedido().getCellText("ICE IMEI", iPosicion));
				Siebel().processKeyboardAccelerator("Ctrl+S"); // ff 25-11-2016
			}  
		} // finaliza tramite común

		// Buscar la posición del terminal
		if (sTramite.equals("PortIn")) 
		{
			if (iAj==1){ 
				LineasPedidoPI().activateRow(iPosicion);
				System.out.println(!LineasPedidoPI().isRowExpanded(iPosicion));
				if (!LineasPedidoPI().isRowExpanded(iPosicion)) {
					LineasPedidoPI().clickHier(); } // si no está visible no ejecuta
				// llamar a buscar el terminal para que se posicione correctamente en esa linea
				Producto[0]=argu[5].toString(); // primero buscar terminales promocionales y lo expande
				Producto[1]=""; // primero buscar terminales promocionales y lo expande
				Producto[2]="";
				Producto[3]="";
				Producto[4]="Si"; // se le indica buscar desde el comienzo
				Producto[5]=sTramite;	
				callScript("Scripts.Comun.BuscarProducto",Producto);
				sEncontrado = Producto[1].toString();
				iPosicion = Integer.parseInt (Producto[2]);
				System.out.println("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
				logInfo("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
				Siebel().processKeyboardAccelerator("Ctrl+S");
			}
			if (sEncontrado.toLowerCase().equals("encontrado")){
				// Ingresar IMEI
				System.out.println("ICE IMEI Posicion" + iPosicion + "IMEI a ingresar "+ argu[2].toString());

				LineasPedidoPI().activateRow(iPosicion);
				IMEI_PI().setText(argu[2].toString());
				if (LineasPedidoPI().getCellText("ICE IMEI", iPosicion).equals(argu[2].toString().toString()))
					argu[3] = "OK";
				System.out.println("ICE IMEI ingresado" + LineasPedidoPI().getCellText("ICE IMEI", iPosicion));
				// Siebel().processKeyboardAccelerator("Ctrl+S"); // ff 25-11-2016, sacado x ss
			}

		}// finaliza tramite port-in
		
		// Se agrega este paso para que se haga un step of fuera del applet de listas
		//Siebel().processKeyboardAccelerator("Ctrl+S"); // ff 25-11-2016, sacado x ss
		if (!sTramite.equals("PortIn")) Descripcion().setText(" "); // ff 25-11-2016
		if (sTramite.equals("PortIn")) DescripcionPI().setText(" "); // ss 1-2-2017

		ImpreResultadoScript(getScriptName( ).toString(), argu[3].toString());
	}
}

