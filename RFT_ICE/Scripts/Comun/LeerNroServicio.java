package Scripts.Comun;
import resources.Scripts.Comun.LeerNroServicioHelper;
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
 * Description   : Permite leer el nro de servicio de un pedido de venta o cp
 * Devuelve NULL si el campo está vacio.
 * @author SSASTRE
 * Script Name   : <b>LeerNroServicio</b>
 * @param 0) OK/NOK 1) NULL o el valor del nro de servicio 2) Prepago / Postpago
 * ej: res res Postpago
 * @since  2016/02/08
 */
public class LeerNroServicio extends LeerNroServicioHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Producto;
		Producto = new String[4];
		
		String[] MensError;
		MensError = new String[4];
		
		argu[0]="NOK";
		
		switch (argu[2].toString()) {
        case "Prepago":
        	Producto[0]=dpString("ServicioPrepago");
        	System.out.println("Tipo:" + Producto[0]);
          	break;
        case "Postpago":
        	Producto[0]=dpString("ServicioPostpago");
        	System.out.println("Tipo:" + Producto[0]);
          	break;
        default:  
			MensError[0] = "Parámetro no esperado";
			//MensError[0] = "xDefecto";
			MensError[1] = "true";
			MensError[2] = "No aplica";
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);	
        break;
	} // end del switch
		
		callScript("Scripts.Comun.BuscarProducto",Producto);
		int iPosicion = Integer.parseInt (Producto[2]);
		LineasPedido().activateRow(iPosicion);
		sleep(1); 

		argu[1]=NroServicio().getProperty("Text");
		System.out.println(argu[1].toString());
		System.out.println(argu[1].toString().equals(""));
		if (argu[1].toString().equals("")) {
			argu[1]="NULL";
		}
		argu[0]="OK";
		System.out.println("Resultado =" + argu[0] + " Valor" + argu[1]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString() + " Valor" + argu[1].toString());
	}
}

