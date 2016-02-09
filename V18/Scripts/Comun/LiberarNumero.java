package Scripts.Comun;
import resources.Scripts.Comun.LiberarNumeroHelper;
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
 * Script Name   : <b>LiberarNumero</b>
 * Description   : Functional Test Script
 * @Param 0) Prepago / Postpago 1) OK/NOK
 * @since  2016/01/07
 * @author Sandra
 */
public class LiberarNumero extends LiberarNumeroHelper
{
	public void testMain(Object[] argu) 
	{
		String[] Producto;
		Producto = new String[4];
		
		String[] ValidarMsj;
		ValidarMsj = new String[4];
		
		argu[1] = "OK";
		
		// Buscar fila del servicio
		switch (argu[0].toString()) {
		case "Prepago":
			Producto[0]=dpString("ServicioPrepago");
			System.out.println("Prepago-Servicio");
			break;
		case "Postpago":
			Producto[0]=dpString("ServicioPostpago"); // Servicio de Telefonia Movil
			System.out.println("Postpago-Servicio");
			break;
		default:  
			System.out.println("Stop");
			argu[1] = "NOK";
			break;
		} // end del switch
		
	
		if (argu[1].toString() == "OK") {
			callScript("Scripts.Comun.BuscarProducto",Producto);
			String sEncontrado = Producto[1].toString();
			int iPosicion = Integer.parseInt (Producto[2]);
			logInfo("Servicio Telefonia Movil Prepago" + iPosicion + "flag " + sEncontrado);
			if (sEncontrado == "Encontrado"){
				//Presionar botón de proximo numero disponible
				LiberarNum().performAction();
				
				sleep (5); // dar tiempo para que se refresquen los numeros especiales 
				
				ValidarMsj[0]="DPM0020";
				ValidarMsj[3] = "HTML";
				callScript("Scripts.Comun.ValidarMensaje",ValidarMsj);
				System.out.println(ValidarMsj[1]);
				if  (ValidarMsj[1].toString().equals("true")){
					// si el mensaje fue correcto lo liberará el script validar mensaje
					argu[1]= "OK";
				} else { 
					// si el mensaje no fue correcto no lo libero el script validar mensaje
					argu[1]= "NOK";
				}
			}
		}
	}
}

