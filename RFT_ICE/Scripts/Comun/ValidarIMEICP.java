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
 * @Param 0) IN Terminal Cliente activo true / false (NO SE USA) 1) IMEI del activo / NA 
 * 2)OK/NOK 3) Postpago / Prepago
 * Terminal cliente activo es true si el activo es con terminal ice. Caso contrario es false.
 * NOTA: Terminal Cliente activo es el terminal anterior no el nuevo. 
 * Indica si el activo tiene terminal ice (true) o terminal cliente (false) 
 * @since  2016/05/29
 * 10/06/2016 se sacan dos validaciones
 * true 358685052334014 res Postpago
 */
public class ValidarIMEICP extends ValidarIMEICPHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[2] = "NOK";
		
		String sIMEIlinea = "NA";
    	//Boolean ServicioIMEIIgual = false;
    	
		String[] Producto;
		Producto = new String[4];
		//Busca path producto
		
		switch (argu[3].toString()) {
        case "Prepago":
        	Producto[0]=dpString("ServicioPrepago");
        	System.out.println("Prepago-Servicio");
          	break;
        case "Postpago":
        	Producto[0]=dpString("ServicioPostpago");
        	System.out.println("Postpago-Servicio");
          	break;
        case "Datos":
        	Producto[0]=dpString("ServicioDatos");
        	System.out.println("Datos-Servicio");
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
			
			IFtVerificationPoint IMEIServicioVP = vpManual("IMEIServicio", argu[1].toString(), IMEI().getProperty("Text"));
			IMEIServicioVP.performTest(); 
			
			if (sIMEIlinea.equals(argu[1].toString())) {
				//ServicioIMEIIgual = true;
        		System.out.println("El numero de IMEI en Servicio esta OK");
        	}
        	argu[2] = "OK";	

        	/**
			Boolean TerminalIMEIIgual = false;
			Boolean IMEICheck=null;
        	 
        	IMEICheck =Boolean.parseBoolean(CheckTerminalCliente().getProperty("IsChecked").toString());
			IFtVerificationPoint TerminalClienteCheckVP;
			
			switch (argu[0].toString().toLowerCase()) { 
	        case "true":  // terminal cliente

	        	TerminalClienteCheckVP= vpManual("TerminalClienteCheck", false, CheckTerminalCliente().getProperty("IsChecked"));
				TerminalClienteCheckVP.performTest(); 
				
				if(!IMEICheck)   System.out.println("El check de terminal cliente en Servicio esta OK");
	        

	        	// Controlar IMEI en servicio  
	        	Producto[0]=dpString("Terminales");
	        	callScript("Scripts.Comun.BuscarProducto",Producto);
	        	sEncontrado = Producto[1].toString();
	        	iPosicion = Integer.parseInt (Producto[2]);

	        	LineasPedido().activateRow(iPosicion);
	        	System.out.println(!LineasPedido().isRowExpanded(iPosicion));

	        	System.out.println("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
	        	logInfo("ICE IMEI Posicion" + iPosicion + "flag " + sEncontrado);
	        	

	        	if (sEncontrado == "Encontrado"){
	        		// Controlar IMEI en terminales
	        		System.out.println("ICE IMEI Posicion" + iPosicion + "Valor "+ argu[2].toString());
	        		LineasPedido().activateRow(iPosicion);
	        		System.out.println("ICE IMEI" + LineasPedido().getCellText("ICE IMEI", iPosicion));

	        		LineasPedido().activateRow(iPosicion);
	        		sIMEIlinea =IMEI().getProperty("Text").toString();
	        		IFtVerificationPoint IMEITerminalesVP = vpManual("IMEITerminales", argu[1].toString(), IMEI().getProperty("Text"));
	        		IMEITerminalesVP.performTest(); 

	        		if (sIMEIlinea.equals(argu[1].toString())) {
	        			TerminalIMEIIgual = true;
	        			System.out.println("El numero de IMEI en Terminales Promocionales esta OK");
	        		}
	        	}

	        	if (ServicioIMEIIgual && TerminalIMEIIgual && !IMEICheck) {
		        	argu[2] = "OK";	
	        	}
	        	break;
	        case "false": // terminal ice
	        	// controlar servicio
	        	if (ServicioIMEIIgual) {
		        	argu[2] = "OK";	
	        	}
	          	break;
	        default:  
	        break;
			} // end del switch
        	 */
		} // fin del if por encontrado

		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}

