package Scripts.Comun;
import resources.Scripts.Comun.IngresarAutenticacionV1Helper;
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
* Descripción: Permite ingresar el tipo de autenticacion
* Pre-Condición: Estar en la pestaña del pedido
* Parámetros:  0) Tipo de autenticacion 1) Encontrado / No Encontrado 2) Canal o NA 3)Tramite
* Ej:  Enrute directo res "Agencia ICE" PortIn
* SS Nov 2015
*/
public class IngresarAutenticacionV1 extends IngresarAutenticacionV1Helper {
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sTipo = argu[0].toString();	
		argu[1] = "No Encontrado";
		
		String sTramite = "Venta";
		if (argu.length >= 4 ) { 
			sTramite = argu[3].toString();
		}

		boolean reintentar = true;

		while (reintentar) {
			try
			{
				BotonConsultaPedido().ensureObjectIsVisible();
				//document_detalles().ensureObjectIsVisible();
				//BotonesPedidoEncabezado().ensureObjectIsVisible();
				reintentar = false;

				// Si canal del DP no es NA ingresa el canal
				if (!(argu[2].toString().equals("NA"))) {
					if (sTramite.equals("Venta")){Canal().setText(argu[2].toString());}
					if (sTramite.equals("PortIn")){CanalPI().select(argu[2].toString());}
				}

				System.out.println("Cantidad de filas del tipo de autenticacion: "+ TipoAutenticacion(). getProperty("Count"));	
				int iTotal = (int) TipoAutenticacion(). getProperty("Count");

				if (iTotal == 0) {
						BotonConsultaPedido().ensureObjectIsVisible();
					//document_detalles().ensureObjectIsVisible();
					//BotonesPedidoEncabezado().ensureObjectIsVisible();
				} 

				// Verifica que el tipo de identificación exista para ese canal y lo ingresa		
				ITestDataList dataList = (ITestDataList)TipoAutenticacion().getTestData("list");
				ITestDataElementList elementList = (ITestDataElementList)dataList.getElements();

				for (int i=0; i<iTotal ;i++) {
					System.out.println("Número de ítem de la lista de autenticación: "+elementList.getElement(i).getElement().toString());
					if (sTipo.equals(elementList.getElement(i).getElement().toString())) { 
						argu[1] = "Encontrado";
						if (sTramite.equals("Venta")){TipoAutenticacion().setText(sTipo);}
						if (sTramite.equals("PortIn")){TipoAutenticacionPI().setText(sTipo);}
						i=iTotal+1;
					}
				}
				System.out.println("Resultado:" + argu[1]);
			} // end del try
			catch (Exception e){
				logInfo("Mensaje de excepción: "+e.getMessage());
				System.out.println("Mensaje de excepción: "+e.getMessage());
				// Se envia un refresco a IE porque a veces la pantalla se bloquea en este paso
				//browser_browserIE(document_siebelCommunications2(),DEFAULT_FLAGS).inputKeys("{F5}");
			}
		} // end del while de reintentar
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}
	

