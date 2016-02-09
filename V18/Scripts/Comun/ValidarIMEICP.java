package Scripts.Comun;
import resources.Scripts.Comun.ValidarIMEICPHelper;
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
 * Description   : Functional Test Script
 * Script Name   : <b>ValidarIMEICP</b>
 * @author Sandra
 * @Param 0) IN Terminal Cliente activo true / false 1) IMEI del activo / NA 
 * 2)OK/NOK 3) Postpago / Prepago
 * NOTA: Terminal Cliente activo es el terminal anterior no el nuevo.
 * @since  2016/01/29
 */
public class ValidarIMEICP extends ValidarIMEICPHelper
{
	public void testMain(Object[] argu) 
	{
		argu[2] = "NOK";
		
		String sIMEIlinea = "NA";
		Boolean ServicioIMEIIgual = false;
		Boolean TerminalIMEIIgual = false;

		String[] Producto;
		Producto = new String[4];
		//Busca path producto
		
		switch (argu[3].toString()) {
        case "true":
        	Producto[0]=dpString("ServicioPrepago");
        	System.out.println("Prepago-Servicio");
          	break;
        case "false":
        	Producto[0]=dpString("ServicioPostpago");
        	System.out.println("Postpago-Servicio");
          	break;
        default:  
        break;
	} // end del switch
		
		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();
		int iPosicion = Integer.parseInt (Producto[2]);

		LineasPedido().activateRow(iPosicion);
		System.out.println(!LineasPedido().isRowExpanded(iPosicion));

		System.out.println("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
		logInfo("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
		
		if (sEncontrado == "Encontrado"){
			// Ingresar IMEI
			System.out.println("ICE IMEI Posicion" + iPosicion + "Valor "+ argu[2].toString());
			LineasPedido().activateRow(iPosicion);
			System.out.println("ICE IMEI" + LineasPedido().getCellText("ICE IMEI", iPosicion));
			
			LineasPedido().activateRow(iPosicion);
			sIMEIlinea =IMEI().getProperty("Text").toString();

			if (sIMEIlinea.equals(argu[1].toString())) {
				ServicioIMEIIgual = true;
        	}
			
			switch (argu[0].toString()) { // terminal cliente
	        case "true":
	        	// controlar servicio y terminales promocionales
	        	Producto[0]=dpString("Terminales");
	        	callScript("Scripts.Comun.BuscarProducto",Producto);
	        	sEncontrado = Producto[1].toString();
	        	iPosicion = Integer.parseInt (Producto[2]);

	        	LineasPedido().activateRow(iPosicion);
	        	System.out.println(!LineasPedido().isRowExpanded(iPosicion));

	        	System.out.println("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
	        	logInfo("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);

	        	if (sEncontrado == "Encontrado"){
	        		// Ingresar IMEI
	        		System.out.println("ICE IMEI Posicion" + iPosicion + "Valor "+ argu[2].toString());
	        		LineasPedido().activateRow(iPosicion);
	        		System.out.println("ICE IMEI" + LineasPedido().getCellText("ICE IMEI", iPosicion));

	        		LineasPedido().activateRow(iPosicion);
	        		sIMEIlinea =IMEI().getProperty("Text").toString();
	        		if (sIMEIlinea.equals(argu[1].toString())) {
	        			TerminalIMEIIgual = true;
	        		}
        	}
	        	if (ServicioIMEIIgual && TerminalIMEIIgual) {
		        	argu[2] = "OK";	
	        	}
	        	break;
	        case "false":
	        	// controlar servicio
	        	if (ServicioIMEIIgual) {
		        	argu[2] = "OK";	
	        	}
	          	break;
	        default:  
	        break;
			} // end del switch
		}  
	}
}

