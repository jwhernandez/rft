package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.ValidarPedidosCMHelper;
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
 * Description   : Captura y devuelve los campos del monitor de CM. Tiene acciones para realizar validaciones 
 * especificas como ser ValidarRED
 * Script Name   : <b>ValidarPedidosCM</b>
 * @Param 0) Validación OK/NOK 1) out Estado de la red (solo venta  2) out Estado del tramite venta 
 * 3) estado del tramite desc 4) estado de la tarea venta
 * 5) Estado de la tarea  desc
 * 6) Pedido venta  7) Pedido Desconexión 8)Imsi venta  9)out IMSI desconexion
 * 10) Acciones posibles (ValidarRED, Capturar (lo hace siempre pero si se coloca capturar solo hace eso)
 * Si se coloca ValidarRED captura y además valida red
 * si se coloca ValidaIMSI Desc captura y ademas valida IMSI desc
 * OPciones ValidarIMSI Vta, Validar IMSI Desc, Validar IMSI todo, ValidarRED
 * 11) Nro paso
 * 12) valor de estado red a validar (Pagado, Pendiente de Pagar, etc) (parametro opcional)
 * 13 valor de IMSI vta opc 14) valor de IMSI desc opc  
 * @author SS
 * @since  2016/04/26
 * ult modif 22 02 2017 se graba la variable global nro de red
 * Última modificación VC 20170418 Se corrige error de que el script daba OK a pesar de que alguna validación no coincidía 
 */
public class ValidarPedidosCM extends ValidarPedidosCMHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="NOK";
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String sEstadoRedDeseado=null;
		if (argu.length >= 13 && !argu[12].toString().equals("NA"))  
			sEstadoRedDeseado=argu[12].toString();
		String sIMSIVtaDeseado=null;
		if (argu.length >= 14 && !argu[13].toString().equals("NA"))  
			sIMSIVtaDeseado=argu[13].toString();
		String sIMSIDescDeseado=null;
		if (argu.length >= 15 && !argu[14].toString().equals("NA"))  
			sIMSIDescDeseado=argu[14].toString();

		
		String sEstadoRED=null ;
		String sMontoRED=null;
		String sNroRED=null;
		String IMSIVta=null;
		String IMSIDesc=null;
		Boolean bVenta=null;
		Boolean bDesc=null;

		if (LineasdePedidosCMAll().getProperty("RowsCount").toString().equals("2")) {
			for (int i=0;i<=1;i++) {

				LineasdePedidosCMAll().activateRow(i);

				String sTipoTramite=Tipo_TramiteCMAll().getProperty("Text").toString(); 
				String sPedido;

				System.out.println("Id Task"+Id_Task_CMAll().getProperty("Text"));
				infoPaso(sScriptName, Tipo.DATO,"Id Task",Id_Task_CMAll().getProperty("Text").toString());

				switch (sTipoTramite) {
				case "Venta":
					System.out.println("Datos de la Venta");
					bVenta = true;

					sEstadoRED = EstadoREDCMAll().getProperty("Text").toString();
					argu[1]=sEstadoRED;
					argu[2]=Estado_TramiteAll().getProperty("Text").toString();
					argu[4]=EstadoTareaCMAll().getProperty("Text");
					argu[6]=NroPedidoCM().getProperty("Text");
					IMSIVta = IMSIAll().getProperty("Text").toString();
					argu[8]=IMSIVta;

					if (!sEstadoRED.equals("")) {
						System.out.println("EstadoRED"+EstadoREDCMAll().getProperty("Text"));
						infoPaso(sScriptName, Tipo.DATO,"EstadoRED",EstadoREDCMAll().getProperty("Text").toString());
						sNroRED = NroREDCM().getProperty("Text").toString();
						System.out.println("NroRED"+sNroRED);
						infoPaso(sScriptName, Tipo.DATO,"NroPedidoRED",sNroRED);
						sMontoRED = MontoREDCM().getProperty("Text").toString();
						System.out.println("MontoRED"+sMontoRED);
						infoPaso(sScriptName, Tipo.DATO,"MontoRED",sMontoRED);
					}

					System.out.println("IMEIVta"+IMEIAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"IMEIVta",IMEIAll().getProperty("Text").toString());
					System.out.println("SIMVta"+SIMAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"SIMVta",SIMAll().getProperty("Text").toString());
					System.out.println("IMSIVta"+IMSIAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"IMSIVta",IMSIAll().getProperty("Text").toString());
					System.out.println("EstadoTramiteVta"+Estado_TramiteAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"EstadoTramiteVta",Estado_TramiteAll().getProperty("Text").toString());
					System.out.println("EstadoTareaVta"+EstadoTareaCMAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"EstadoTareaVta",EstadoTareaCMAll().getProperty("Text").toString());
					System.out.println("IMSIVenta"+IMSIAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"IMSIVta",IMSIAll().getProperty("Text").toString());

					//Termino con pedido para que la pantalla quede bien para la captura
					sPedido = NroPedidoCM().getProperty("Text").toString(); 
					System.out.println("Pedido Venta "+NroPedidoCM().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"NroPedidoVenta",sPedido);
					break;
				case "Desconexión":
					System.out.println("Datos de la desconexión");
					bDesc=true;

					argu[3]=Estado_TramiteAll().getProperty("Text").toString();
					argu[5]=EstadoTareaCMAll().getProperty("Text");
					argu[7]=NroPedidoCM().getProperty("Text");	
					IMSIDesc = IMSIAll().getProperty("Text").toString();
					argu[9]=IMSIDesc;


					System.out.println("IMEIDesc"+IMEIAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"IMEIDesc",IMEIAll().getProperty("Text").toString());
					System.out.println("SIMDesc"+SIMAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"SIMDesc",SIMAll().getProperty("Text").toString());
					System.out.println("EstadoTramiteDesc"+Estado_TramiteAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"EstadoTramiteDesc",Estado_TramiteAll().getProperty("Text").toString());

					System.out.println("EstadoTareaDesc"+EstadoTareaCMAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"EstadoTareaDesc",EstadoTareaCMAll().getProperty("Text").toString());
					System.out.println("IMSIDesc"+IMSIAll().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"IMSIDesc",IMSIAll().getProperty("Text").toString());

					//Termino con pedido para que la pantalla quede bien para la captura
					sPedido = NroPedidoCM().getProperty("Text").toString(); 
					System.out.println("Pedido Desconexión "+NroPedidoCM().getProperty("Text"));
					infoPaso(sScriptName, Tipo.DATO,"NroPedidoDesconexión",sPedido);

					break;
				default:  
					System.out.println("Opción no válida");
					break;
				} // end del switch
			} // end del for
			argu[0]="OK"; /******************************************XXXXXXXXXXXXXXXXXXXX****************************************************/

			System.out.println("argumento 1 - Estado Red:" +argu[1]);
			System.out.println("argumento 2 - Estado Tramite Venta:" +argu[2]);		
			System.out.println("argumento 3 - Estado Tramite Desc:" +argu[3]);		
			System.out.println("argumento 4 - Estado Tarea Venta:" +argu[4]);		
			System.out.println("argumento 5 - Estado Tarea Desc:" +argu[5]);		
			System.out.println("argumento 6 - Pedido Venta:" +argu[6]);		
			System.out.println("argumento 7 - Pedido Desc:" +argu[7]);		
			System.out.println("argumento 8 - IMSI Venta:" +argu[8]);		
			System.out.println("argumento 9 - IMSI Desc:" +argu[9]);		

			IFtVerificationPoint IMSIVtaVP;
			IFtVerificationPoint IMSIDescVP;
			String sAccion = argu[10].toString().toLowerCase();
			switch (sAccion) {
			case "validartramite":
				if (bVenta && bDesc) 		argu[0]="OK";
				else argu[0]="NOK"; //VC 20170418 Si la validación fallaba no se tomaba como error ya que en la linea 49 argu[0] se puso en OK
				System.out.println("Validacion de Estado tramite Venta:" + bVenta);
				System.out.println("Validacion de Estado tramite Desconexion:" + bDesc);
				break;
			case "validarred":
				Boolean bEstado = false;
				Boolean bMonto= false;
				Boolean bNro= false;
				IFtVerificationPoint EstadoVP = vpManual("EstadoRED",sEstadoRedDeseado,sEstadoRED);
				if  (EstadoVP.performTest()) 	bEstado=true;
				IFtVerificationPoint MontoVP = vpManual("MontoRED","",sMontoRED);
				if  (!MontoVP.performTest()) 	bMonto=true;
				IFtVerificationPoint NroVP = vpManual("NroRED","",sNroRED);
				if  (!NroVP.performTest()) 	bNro=true;
				if (bEstado && bMonto && bNro) 		argu[0]="OK";
				else argu[0]="NOK"; //VC 20170418 Si la validación fallaba no se tomaba como error ya que en la linea 49 argu[0] se puso en OK

				System.out.println("Validacion Estado:" +sEstadoRedDeseado + "-" + sEstadoRED + "-" + bEstado);
				System.out.println("Validacion Monto dif blanco:" + sMontoRED + "-" + bMonto);
				System.out.println("Validacion Nro dif blanco:" + sNroRED + "-" + bNro);
				
				setNroRED(sNroRED);
			
				break;
			case "capturar":
				argu[0]="OK";
				break;
			case "validarimsi desc":
				IMSIDescVP = vpManual("IMSIDesc",sIMSIDescDeseado,IMSIDesc);
				IMSIDescVP.performTest() ;
				if (IMSIDesc.equals(sIMSIDescDeseado)) argu[0]="OK";
				else argu[0]="NOK"; //VC 20170418 Si la validación fallaba no se tomaba como error ya que en la linea 49 argu[0] se puso en OK
				break;
			case "validarimsi vta":
				IMSIVtaVP = vpManual("IMSIVta",sIMSIVtaDeseado,IMSIVta);
				IMSIVtaVP.performTest() ;
				if (IMSIVta.equals(sIMSIVtaDeseado)) argu[0]="OK";
				else argu[0]="NOK"; //VC 20170418 Si la validación fallaba no se tomaba como error ya que en la linea 49 argu[0] se puso en OK
				break;
			case "validarimsi todo":
				IMSIDescVP = vpManual("IMSIDesc",sIMSIDescDeseado,IMSIDesc);
				IMSIDescVP.performTest(); 
				IMSIVtaVP = vpManual("IMSIVta",sIMSIVtaDeseado,IMSIVta);
				IMSIVtaVP.performTest() ;
				System.out.println("Coinciden los IMSIs de Venta: " + IMSIVta + "-"+ sIMSIVtaDeseado + "-" +(IMSIVta.equals(sIMSIVtaDeseado)) );
				System.out.println("Coinciden los IMSIs de Desc: " + IMSIDesc+ "-"+ sIMSIDescDeseado + "-"+ (IMSIDesc.equals(sIMSIDescDeseado)) );

				if ((IMSIVta.equals(sIMSIVtaDeseado)) && (IMSIDesc.equals(sIMSIDescDeseado))) argu[0]="OK"; 
				else argu[0]="NOK"; 
				break;
			default:
				System.out.println("Se pasó como parametro una opción no valida");
				break;
			}// end del switch
		} else
		{
			System.out.println("El número de pedidos filtrados no es 2");
			argu[0]="NOK";
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

