package Scripts.Comun;
import resources.Scripts.Comun.PersonalizarPromOpcHelper;
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
 * Description   : Modifica Promociones Opcionales
 * Parámetros:  0) Servicio 1) PlanPaquete500SMS/1000SMS/2000SMS, NoPaquetesSMS o PlanPaquetePromSMS
 * PlanPaquete50Min/100Min/30Min, NoPaquetesMin o PlanPaquetePromMin
 * y NoPaquetes que elimina ambos paquetes
 * 2) Resultado OK/NOK
 * Postpago  NoPaquetes res           
 *  Script Name   : <b>PersonalizarPromOpc</b>
 * @author Sandra
 * @since  2016/03/28
 */
public class PersonalizarPromOpc extends PersonalizarPromOpcHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Producto;
		Producto = new String[4];
		
		argu[2] = "NOK";
		
		switch (argu[0].toString()) {
        case "Prepago":
        	Producto[0]=dpString("ServicioPrepago");
        	System.out.println("Prepago-Servicio");
          	break;
        case "Postpago":
        	Producto[0]=dpString("ServicioPostpago");
        	System.out.println("Postpago-Servicio");
          	break;
           default:  
        	System.out.println("Stop");
        break;
	} // end del switch
		

		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();
		if (sEncontrado =="Encontrado" ){
			int iPosicion = Integer.parseInt (Producto[2]);
			LineasPedido().activateRow(iPosicion);
			sleep(1); 

			Personalizar().performAction();
			PromocionesOpcionales().click();
			PromocionesyDescuentos().click();
			Paquetes().click();
		
			switch (argu[1].toString()) {
			case "NoPaquetes":
				NoPaquetesSMS().click();
				NoPaquetesMin().click();
				break;
			case "NoPaquetesSMS":
				NoPaquetesSMS().click();
				break;
			case "NoPaquetesMin":
				NoPaquetesMin().click();
				break;
			case "PlanPaquete500SMS":
				PlanPaquete500SMS().click();
				break;
			case "PlanPaquete1000SMS":
				PlanPaquete1000SMS().click();
				break;
			case "PlanPaquete2000SMS":
				PlanPaquete2000SMS().click();
				break;
			case "PlanPaquete30Min":
				PlanPaquete30Min().click();
				break;
			case "PlanPaquete50Min":
				PlanPaquete50Min().click();
				break;
			case "PlanPaquete100Min":
				PlanPaquete100Min().click();
				break;
			case "PlanPaquetePromMin":
				PlanPaquetePromMin().click();
				break;
			case "PlanPaquetePromSMS":
				PlanPaquetePromSMS().click();
				break;
			default:  
				argu[2] = "Error";
				break;
			} // end del switch
			Terminado().click();
			argu[2] = "OK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[2].toString());
	}
}

