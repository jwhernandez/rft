package Scripts.Comun;
import resources.Scripts.Comun.GenerarCtaFactHelper;
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
* Descripción: Genera cta fact
* Parámetros: 0) devuelve el resultado 1) Recibe el tramite 2) recibe el servicio
* SS Nov 2015
* Ult modif ss 10-7-2017 se agrega opcion de buscar en DP de parametria En este caso
* usa variable TipoPerfilCorrecto
*/
public class GenerarCtaFact extends GenerarCtaFactHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String[] Validaciones;
		Validaciones = new String[3];
		
		String[] Producto;
		Producto = new String[6];
		
		String[] Mens;
		Mens = new String[2];

		
		String sTramite = "Venta"; 
		if (argu.length >= 2 ) { 
			sTramite = argu[1].toString(); // tramite
		}
		
		System.out.println(!sTramite.equals("PortIn"));
 
		String sServicio = Producto[0]=argu[2].toString(); // Servicio
		Producto[0]=sServicio; //ss 10-7-2017		
		// ----------------------------------- 
		// se agrega parametria para servicio  //ss 10-7-2017	
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
		Producto[5]=sTramite; // se le envia el tramite
		
		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sEncontrado = Producto[1].toString();
		//int iPosicion = Integer.parseInt (Producto[2]);

		Mens [0] = "ICE SIM Posicion" + Producto[2].toString() + "flag " + sEncontrado; 
		callScript("Scripts.Comun.Informar", Mens);

		if (sEncontrado == "Encontrado"){

			if (sTramite.equals("PortIn")){GenCtaFactPI().performAction();}
			if (!sTramite.equals("PortIn")) {GenCtaFact().performAction();}

			argu[0] = "OK";

			Validaciones[0]="DPM0004"; // este mensaje puede tener la siguiente secuencia una o dos veces (SBL-EXL-00151)
			callScript("Scripts.Comun.ValidarMensaje", Validaciones);

			// Validar que sea el mensaje deseado (que sea true o Parcialmente)
			if (Validaciones[1].toString() == "false" || Validaciones[1].toString() == "No Existe") {
				System.out.println("Terminar Script"+ Validaciones[2].toString());
				argu[0] = "NOK";
			}
		}
		  ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}