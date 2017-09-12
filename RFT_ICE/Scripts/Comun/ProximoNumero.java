package Scripts.Comun;
import resources.Scripts.Comun.ProximoNumeroHelper;
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
 * Descripción: Presiona el botón de proximo número disponible
 * Parámetros: recibe 0) Prepago o Postpago 1) Devuelve NOK o OK 2)nro pedido 
 * 3)nro servicio deseado O NA (input) 4) output nro de servicio asignado
 * Ej Postpago res 1-1692877975 10202040 res 
 * Precondiciones: estar en la pestaña de pedido, sim asignada
 * SS Nov 2015
 * ult modif ss 12/7/2017  se agrega opcion de hibrido 
 */
public class ProximoNumero extends ProximoNumeroHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Producto;
		Producto = new String[4];
		
		String[] IngresarServicioAdm;
		IngresarServicioAdm = new String[4];

		argu[1] = "OK";
		
		int CantReintentos = 2; 

		// Buscar fila del servicio
		switch (argu[0].toString().toLowerCase()) {
		case "prepago":
			Producto[0]=dpString("ServicioPrepago");
			System.out.println("Prepago-Servicio");
			break;
		case "postpago":
			Producto[0]=dpString("ServicioPostpago");
			System.out.println("Postpago-Servicio");
			break;
		case "datos":
			Producto[0]=dpString("ServicioDatos");
			System.out.println("Datos-Servicio");
			break;
		case "hibrido":
			Producto[0]=dpString("ServicioHibridos");
			System.out.println("Hibrido-Servicio");
			break;
		default:  
			System.out.println("Opción invalida seleccionada");
			argu[1] = "NOK";
			break;
		} // end del switch
		

		System.out.println("Condicion 1 " + (!(argu[3].toString().equals("NA"))));
		
		if (!(argu[3].toString().equals("NA"))) {

			System.out.println("Prox Num Servicio utiliza nro servicio pasado como parametro" );

			IngresarServicioAdm[0] = argu[0].toString(); // prepago o postpago
			IngresarServicioAdm[1] = argu[2].toString(); // nro pedido
			IngresarServicioAdm[2] = argu[3].toString(); // nro a asignar
			System.out.println("Numero de servicio recibe:" + argu[0].toString() + "*" + 
					argu[2].toString()+ "*" + argu[3].toString());

			callScript("Scripts.Comun.IngresarServicioAdmin", IngresarServicioAdm);
			argu[1] = IngresarServicioAdm[3];
		}
		
		if ((argu[3].toString().equals("NA")) || (argu[1].toString() == "OK")) {
			callScript("Scripts.Comun.BuscarProducto",Producto);
			String sEncontrado = Producto[1].toString();
			int iPosicion = Integer.parseInt (Producto[2]);
			logInfo("Servicio Telefonia Movil Prepago" + iPosicion + "flag " + sEncontrado);
			if (sEncontrado == "Encontrado"){
				//Presionar botón de proximo numero disponible
				LineasPedido().activateRow(iPosicion);
				sleep(2);
				for (int j = 0;  j<= CantReintentos; j++) {

					System.out.println("Reintentolll "+ j);	
					System.out.println("Botón próximo número habilitado: "+ PrimerNumero().isEnabled());	
 
					if (PrimerNumero().isEnabled()) {
						logInfo("Botón proximo numero habilitado");
						System.out.println("Botón proximo numero habilitado");	
						PrimerNumero().performAction();
						sleep(10);
						System.out.println("Nro de servicio asignado: "+ LineasPedido().getCellText("Service Id", iPosicion));	
						argu[4] = LineasPedido().getCellText("Service Id", iPosicion);
						j = CantReintentos; 
					} 
					else {
						argu[1] = "NOK";
						logInfo("Botón proximo numero NO habilitado en reintento " + j);
					}
				
				}
			} // si no se encontró la linea del servicio
			else {
				argu[1] = "NOK";
				logInfo("Servicio de telefonia movil no encontrado");
			}
		} else {
			argu[1] = "NOK";
			logInfo("Ingresar Número de Servicio específico no funcionó");
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}