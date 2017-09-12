package Scripts.Comun;
import resources.Scripts.Comun.NuevoCambioPlanHelper;
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
 * Descripcion Generar un nuevo cambio de plan desde la vista 360
* Script Name   : <b>CrearPedidoCP</b>
* @since  2016/01/15
* @Param 0) Nombre plan destino 1) Encontrado / No Encontrado 2)Fila en la que fue encontrado 3)nropedido
* Ej: "PLAN CONECTADO 2 24 M 4GLTE" res res
* @author Sandra
* Precondicion estar en la vista 360 en la linea del activo
* Ultima modificacion: 20/3/2017
 */
public class NuevoCambioPlan extends NuevoCambioPlanHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] RecordCount;
		RecordCount = new String[4];
		SiebMenu().select(atPath("CmdMgr1"));

		sleep(3);
		
		argu[1] = "No Encontrado";
		argu[2] = "1";
		
		PlanesDestino().activateRow(0);

		String sPlanDestinoBuscado = argu[0].toString().trim();
		System. out.println("Plan Destino Buscado *" + sPlanDestinoBuscado + "*");
		
		RecordCount[0] = PromotionUpgradePopupApplet().getProperty("RecordCounter").toString();
		System. out.println("Cantidad de planes destino " +PromotionUpgradePopupApplet().getProperty("RecordCounter").toString());
		
		callScript("Scripts.Comun.RecordCount",RecordCount);
		
		int iDesde = Integer.parseInt(RecordCount[1]);
		int iSubtotal = Integer.parseInt(RecordCount[2]);
		int iStart1 = (int) RecordCount[3].toString().indexOf("+");
		int iTotal = 0;
		if (iStart1 >=1) {
			iTotal = 1000; // si encuentra un "+", no importa el total hay mas registros
		} else {
			iTotal = Integer.parseInt(RecordCount[3]);
		}
		
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Inicio de script buscar Buscar Plan Destino (1)" );
		System.out.println("Desde= " + iDesde + " Subtotal= " + iSubtotal + " Total= " + iTotal );
		System.out.println("-------------------------------------------------------------------");
	
		while (iDesde > 1 ) {
			PlanesDestino().firstRowSet();
			RecordCount[0] = PromotionUpgradePopupApplet().getProperty("RecordCounter").toString();
			callScript("Scripts.Comun.RecordCount",RecordCount);

			iDesde = Integer.parseInt(RecordCount[1]);
			iSubtotal = Integer.parseInt(RecordCount[2]);
			iTotal = Integer.parseInt(RecordCount[3]);
			System.out.println("-------------------------------------------------------------------");
			System.out.println("Inicio de script buscar producto (2)" );
			System.out.println("Desde= " + iDesde + "Subtotal= " + iSubtotal + "Total= " + iTotal );
			System.out.println("Subtotal < Total" + iSubtotal + (iSubtotal < iTotal) );
			System.out.println("-------------------------------------------------------------------");
		}

		//Se añade la opción de buscar el plan para solucionar el problema de que no aparezca el cursor 20170320
		PlanABuscar().setText(sPlanDestinoBuscado);
		BuscarPlan().performAction();

		
		//System. out.println(sPlanDestinoBuscado.equals("PLAN CONECTADO 2 24 M 4GLTE")); 
		Boolean Iterar =  true;  
		int iPosicion = 0;
		String sPlanDestino = (String) PlanesDestino().getCellText("Target Promotion Name", 0).toString().trim();
		while (Iterar){
			int	i = 0;
			int irows = (int) PlanesDestino().getProperty("RowsCount") ;
			System. out.println("Planes Destino-Cant: " + PlanesDestino().getProperty("RowsCount")); 
			while (i <= irows - 1 && !(sPlanDestino.equals(sPlanDestinoBuscado))) {
				System. out.println("Plan Destino " + i +" :" + PlanesDestino().getCellText("Target Promotion Name", i)+"*");
				sPlanDestino = (String) PlanesDestino().getCellText("Target Promotion Name", i).toString().trim();  
				System. out.println((sPlanDestino.equals(sPlanDestinoBuscado)));
				PlanesDestino().activateRow(i);
				//Aceptar().performAction();
				i = i + 1;
			} // end del while de 10
			if ((sPlanDestino.equals(sPlanDestinoBuscado))) {
				Iterar = false; 
				iPosicion = i-1;
				argu[1] = "Encontrado";
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Producto encontrado");
				System.out.println("-------------------------------------------------------------------");
		} 
			else {
				if (iSubtotal < iTotal) {
					PlanesDestino().nextRowSet();
					RecordCount[0] = PromotionUpgradePopupApplet().getProperty("RecordCounter").toString();
					System. out.println("Cantidad de planes destino " +PromotionUpgradePopupApplet().getProperty("RecordCounter").toString());
					callScript("Scripts.Comun.RecordCount",RecordCount);
					
					iDesde = Integer.parseInt(RecordCount[1]);
					iSubtotal = Integer.parseInt(RecordCount[2]);
					iStart1 = (int) RecordCount[3].toString().indexOf("+");
					iTotal = 0;
					if (iStart1 >=1) {
						iTotal = 1000; // si encuentra un "+", no importa el total hay mas registros
					} else {
						iTotal = Integer.parseInt(RecordCount[3]);
					}
					
					System.out.println("-------------------------------------------------------------------");
					System.out.println("Siguiente rowset" );
					System.out.println("Desde= " + iDesde + " Subtotal= " + iSubtotal + " Total= " + iTotal );
					System.out.println("-------------------------------------------------------------------");

				} else {
					Iterar = false; 
				}
			}
		} // end del while general
		
		boolean SeleccionoPlan = false; 
		//boolean SeleccionoNroPedido = false; 
		//String sPedido = "1-";
		for (int intentos=1; intentos <=10 ; intentos++) {
			try
			{
				System.out.println("Plan destino = PlanBuscado: " + sPlanDestino.equals(sPlanDestinoBuscado));
				if (sPlanDestino.equals(sPlanDestinoBuscado)) {
					System.out.println("Selecciono plan: " +SeleccionoPlan );
					if (!(SeleccionoPlan)) {
						Aceptar().performAction();
						System.out.println("Selecciono plan sin dar error." );
						SeleccionoPlan = true; 
					}
					// capturar el numero de pedido
//					//System.out.println("Selecciono NroPedido: " +SeleccionoNroPedido );
//					//if (!(SeleccionoNroPedido)){
//						//System.out.println("Entro a Selecciono NroPedido: " );
//						//EncabezadoPedidoHTML().ensureObjectIsVisible();
//						//sPedido = NroPedido().getProperty("Text").toString();
//						//argu[3] = sPedido;
//						//argu[1] = "Encontrado";
//						//System.out.println("Plan destino Seleccionado: "
//							//	+ PlanesDestino().getCellText("Target Promotion Name", iPosicion)
//							//	+ " en posición: " + iPosicion);
//						//argu[2] = Integer.toString(iPosicion);
//						//SeleccionoNroPedido = true; 
//						//System.out.println("Entro a Selecciono NroPedido: "  +SeleccionoNroPedido );
//					//}
				} // por el else devuelve no encontrado
			}// end del try
			catch (Exception e){
				logInfo("Mensaje de AUT: "+e.getMessage());
				System.out.println("Mensaje de AUT: "+e.getMessage());
				System.out.println("Selecciono plan con error." );
				if (e.getMessage().indexOf("AUT")<0) {
					SeleccionoPlan = true; 
					System.out.println("Se coloca SeleccionoPlan en true." );
				}
				sleep(5);
			}
			System.out.println("Selecciono plan : " + (SeleccionoPlan ) );
			if (SeleccionoPlan ) {
				intentos = 10; // fuerzo salida de reintentos
				System.out.println("sale de reintentos " );
			}
		}// end del for
		System.out.println("Resultado: " + argu[1]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString() );
	}
}

