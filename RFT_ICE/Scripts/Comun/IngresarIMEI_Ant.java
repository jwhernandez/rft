package Scripts.Comun;
import resources.Scripts.Comun.IngresarIMEI_AntHelper;
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
 * Descripci�n: Permite ingresar el imei
 * Par�metros: 0) Postpago / Prepago  / Datos 1) Servicio / "TerminalesPromocionales" o Terminal 
 * 2) IMEI 3) OK/NOK 4)Tramite 5) terminal o NA
 * Ej Datos Terminal 352214043680272 res Venta SAMSUNG TAB 3 LITE 7 NEGRO
 * Prepago TerminalesPromocionales 111 res venta NA
 * SS Nov 2015
 */
public class IngresarIMEI_Ant extends IngresarIMEI_AntHelper
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

		switch (argu[0].toString()+"-" + argu[1].toString()) {
		case "Prepago-Servicio":
			Producto[0]=dpString("ServicioPrepago");
			System.out.println("Prepago-Servicio");
			break;
		case "Postpago-Servicio":
			Producto[0]=dpString("ServicioPostpago");
			System.out.println("Postpago-Servicio");
			break;
		case "Datos-Servicio":
			Producto[0]=dpString("ServicioDatos");
			System.out.println("Datos-Servicio");
			break;
		case "Prepago-TerminalesPromocionales":
			Producto[0]=dpString("Terminales");
			System.out.println("Prepago-TerminalesPromocionales");
			break;
		case "Postpago-TerminalesPromocionales":
			Producto[0]=dpString("Terminales");
			System.out.println("Postpago-TerminalesPromocionales");
			break;
		case "Datos-TerminalesPromocionales":
			Producto[0]=dpString("Terminales");
			System.out.println("Datos-TerminalesPromocionales");
			break;
		case "Prepago-Terminal":
			Producto[0]=dpString("Terminales");
			System.out.println("Prepago-Terminal");
			iAj =1;
			break;
		case "Postpago-Terminal":
			Producto[0]=dpString("Terminales");
			System.out.println("Postpago-Terminal");
			iAj =1;
			break;
		case "Datos-Terminal":
			Producto[0]=dpString("Terminales"); // primero buscar terminales promocionales y lo expande
			System.out.println("Datos-Terminal");
			iAj =1;
			break;
		default:  
			System.out.println("Opcion ingresada invalida");
			break;
		} // end del switch

		Producto[4]="Si"; // se le indica buscar desde el comienzo
		Producto[5]=sTramite;
		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();
		int iPosicion = Integer.parseInt (Producto[2]);

		// Buscar la posici�n del terminal
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
			}  
		} // finaliza tramite com�n

		// Buscar la posici�n del terminal
		if (sTramite.equals("PortIn")) 
		{
			if (iAj==1){ 
				LineasPedidoPI().activateRow(iPosicion);
				System.out.println(!LineasPedidoPI().isRowExpanded(iPosicion));
				if (!LineasPedidoPI().isRowExpanded(iPosicion)) {
					LineasPedidoPI().clickHier(); } // si no est� visible no ejecuta
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
			}
			if (sEncontrado.toLowerCase().equals("encontrado")){
				// Ingresar IMEI
				System.out.println("ICE IMEI Posicion" + iPosicion + "IMEI a ingresar "+ argu[2].toString());

				LineasPedidoPI().activateRow(iPosicion);
				IMEI_PI().setText(argu[2].toString());
				if (LineasPedidoPI().getCellText("ICE IMEI", iPosicion).equals(argu[2].toString().toString()))
					argu[3] = "OK";
				System.out.println("ICE IMEI ingresado" + LineasPedidoPI().getCellText("ICE IMEI", iPosicion));
			}

		}// finaliza tramite port-in

		ImpreResultadoScript(getScriptName( ).toString(), argu[3].toString());
	}
}

