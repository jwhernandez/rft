package Scripts.Comun;
import resources.Scripts.Comun.TerminalClienteHelper;

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
 * Descripción: Elimina Terminales promocionales y coloca terminal cliente en Y
 * Parámetros: 0) OK / NOK 1) TipoPerfilCorrecto (Postpago o Prepago) 
 * 2) Terminales = true (Tiene) o false (no tiene) 3) Tramite (No se usa) 
 * 4) valida o modifica el check terminal cliente (true, false, validatrue, validafalse)
 * SS Nov 2015
 * Modificaciones: 
 * VC 17/11/2016
 * VC 18/11/2016
 * ss 10/7/2017 se agrega opcion hibridos
 * ss 12/7/2017 se elimina necesidad de enviar correctamente el valor del param 2 indicando si tiene terminal o no
 */
public class TerminalCliente extends TerminalClienteHelper 
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		//Sirve para validar que el check se valide o actuve de forma correcta (18/11/2016)
		boolean bCheck = true;
		
		String[] Producto;
		Producto = new String[4];

		String sTramite = argu[3].toString();
		
		switch (argu[1].toString().toLowerCase()) {
		case "prepago":
			Producto[0]=dpString("ServicioPrepago");
			System.out.println("Prepago-Servicio");
			break;
		case "postpago":
			Producto[0]=dpString("ServicioPostpago");
			System.out.println("Postpago-Servicio");
			break;
		case "hibrido":
			Producto[0]=dpString("ServicioHibridos");
			System.out.println("Postpago-Hibrido");
			break;
		default:  
			System.out.println("Stop");
			break;
		} // end del switch

		// busca el servicio y coloca terminal cliente en Y

		callScript("Scripts.Comun.BuscarProducto",Producto);
		String sResultado = Producto[1].toString();
		if (sResultado == "Encontrado")
		{
			int iPosicion = Integer.parseInt (Producto[2]);
			LineasPedido().activateRow(iPosicion);
			System.out.println("Indica si es Cliente " + EsCliente().getProperty("IsChecked"));
			
			//Si se recibe un NA el script funciona de la forma en la que estaba antes de los cambios (18/11/2016)
			if(argu[4].toString().equals("NA")){
				if (!sTramite.equals("PortIn")) EsCliente().setOn();
				else System.out.println("Tramite no implementado");
			}else{
				//Se agrega la opción de quitar el check si se envía un "false" en argu[4] o ponerlo si se manda un "true" (17/11/2016)
				if(argu[4].toString().toLowerCase().equals("false")){
					try{
						EsCliente().setOff();
						bCheck = true;
					}catch(Exception e){
						bCheck = false;
						System.out.println("No se pudo desmarcar el check de terminal cliente");
					}
					
				} else if(argu[4].toString().toLowerCase().equals("true")){
					try{
						EsCliente().setOn();
						bCheck = true;
					}catch(Exception e){
						bCheck = false;
						System.out.println("No se pudo marcar el check de terminal cliente");
					}
				} else if(argu[4].toString().equals("validatrue") || argu[4].toString().equals("validafalse")){ //se añade la opción de validar el check (18/11/2016)
					boolean bEstadoCheck = Boolean.parseBoolean(EsCliente().getProperty("IsChecked").toString());
					if(bEstadoCheck){
						if(argu[4].toString().equals("validatrue")) bCheck = true;
						else{
							bCheck = false;
							System.out.println("El valor del campo terminal cliente: " + bEstadoCheck + " no coincide con el valor esperado " + argu[4].toString());
						}
					}else{
						if(argu[4].toString().equals("validafalse") && !bEstadoCheck) bCheck = true;
						else{
							bCheck = false;
							System.out.println("El valor del campo terminal cliente: " + bEstadoCheck + " no coincide con el valor esperado " + argu[4].toString());
						}
					}
			
				}
			}

			if (argu[2].toString().toLowerCase().equals("true") && bCheck) //Se añade bCheck para que en caso de que falle con el check de terminal cliente, no elimine el terminal promocional porque el script fallo (18/11/2016)
			{
				// contrae las lineas del pedido pues caso contrario puede no verse terminales promocionales
				// y al no verse no se puede eliminar.

				if (!sTramite.equals("PortIn")) 
				{
					int iTotal1 = (int) LineasPedido().getProperty("RowsCount") ;
					if (LineasPedido().isRowExpanded(iPosicion)) {
						LineasPedido().clickHier(); 
						int iTotal2 = (int) LineasPedido().getProperty("RowsCount") ;
						if (iTotal1 < iTotal2) {
							LineasPedido().clickHier(); 
						}
					} else System.out.println("Tramite no implementado");
				}
			} else 	argu[0] = "OK";

			if (argu[2].toString().toLowerCase().equals("true") && bCheck) //Se añade bCheck para que en caso de que falle con el check de terminal cliente, no elimine el terminal promocional porque el script fallo (18/11/2016)
			{
				Producto[0]=dpString("Terminales");
				System.out.println("Producto a buscar: " + Producto[0] );

				callScript("Scripts.Comun.BuscarProducto",Producto);
				sResultado = Producto[1].toString();
				System.out.println("Resultado de la busqueda: " + Producto[1] + Producto[2]);
				if (sResultado == "Encontrado")
				{
					if (!sTramite.equals("PortIn")) 
					{
						iPosicion = Integer.parseInt (Producto[2]); // estaba con comentario
						LineasPedido().activateRow(iPosicion); // estaba con comentario
						// elimina la linea de terminales promocionales

						Eliminar().performAction();
						Aceptar().click();
						sleep(1); 	
						argu[0] = "OK";
					} else System.out.println("Tramite no implementado");
				} else 	argu[0] = "OK";  // ss 12/07/2017 se agrega para evitar que falle si no se indico correctamente el parametro 2
			}
			
			//si esta variable queda en false, es porque falló la ejecución del script (18/11/2016)
			if(!bCheck){
				argu[0] = "NOK";
			}
			
		}
		if (!sTramite.equals("PortIn")) BtonConsultaPedido().waitForExistence();  
		if (sTramite.equals("PortIn")) BtonConsultaPedidoPI().waitForExistence();  
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

