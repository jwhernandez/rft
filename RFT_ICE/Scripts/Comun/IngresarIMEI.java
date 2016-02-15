package Scripts.Comun;
import javax.swing.JOptionPane;

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
import com.rational.test.ft.script.ss.SleepAction;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
* Descripción: Permite ingresar el imei
* Parámetros: 0) Postpago / Prepago 1) Servicio / "TerminalesPromocionales" o Terminal 2) IMEI
* 3) No Encontrado o Encontrado
* SS Nov 2015
*/
public class IngresarIMEI extends IngresarIMEIHelper
{
	public void testMain(Object[] argu)
	{
		String[] Producto;
		Producto = new String[4];
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
        case "Prepago-TerminalesPromocionales":
        	Producto[0]=dpString("Terminales");
        	System.out.println("Prepago-TerminalesPromocionales");
            break;
        case "Prepago-Terminal":
        	Producto[0]=dpString("Terminales");
        	System.out.println("Prepago-Terminal");
      		iAj =1;
            break;
        default:  
        	System.out.println("Stop");
        break;
	} // end del switch
		
		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();
		int iPosicion = Integer.parseInt (Producto[2]);
		LineasPedido().activateRow(0);
		sleep(1); 

		LineasPedido().activateRow(iPosicion);
		System.out.println(!LineasPedido().isRowExpanded(iPosicion));

		if (!LineasPedido().isRowExpanded(iPosicion)) {
			LineasPedido().clickHier(); } // si no está visible no ejecuta
		iPosicion =iPosicion + iAj;
		
		System.out.println("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
		logInfo("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
		
		if (sEncontrado == "Encontrado"){
			// Ingresar IMEI
			System.out.println("ICE IMEI Posicion" + iPosicion + "Valor "+ argu[2].toString());
			LineasPedido().activateRow(iPosicion);
			System.out.println("ICE IMEI" + LineasPedido().getCellText("ICE IMEI", iPosicion));
			
			LineasPedido().activateRow(iPosicion);
			IMEI().setText(argu[2].toString());
			//callScript("Scripts.Comun.RefrescarGuardarPedido");
			}  
		argu[3] = sEncontrado;
		
		// 

  
	}
}

