package Scripts.Comun;
import resources.Scripts.Comun.EsperaEventoCMHelper;
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
 * Description   : Espera en el monitor de ordenes que se cumpla el evento pasado como parametro
 * @author SS
 * Script Name   : <b>EsperaEventoCM</b>
 * @Param 0) OK/NOK 1) Evento (TramiteVenta Completo / RED Pagado / )
 * ej res "RED Pagado"
 * ej res "TramiteDesc Completo"
 * ej res "TramiteVenta Completo"
 * ej res "TramiteVenta En Proceso"
 * @since  2016/04/28
 */
public class EsperaEventoCM extends EsperaEventoCMHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		int iReintentos = 0;
		int iEspera = 1;
		int iReintentosMax = 1;
		String sEstado = "No Leido";
		String sTipo = "No Leido";

		iReintentosMax = Integer.parseInt (dpString("ReintentosEspera_PedidosCM"));
		iEspera = Integer.parseInt (dpString("SleepEspera_PedidoCM"));


		argu[0]="NOK";

		System.out.println("Reintentos Maximos" +iReintentosMax );

		int iAccion = argu[1].toString().toLowerCase().indexOf(" ");
		String sAccion = argu[1].toString().toLowerCase().substring(0, iAccion).trim();
		System.out.println("Accion a ejeuctar:*" + sAccion +"*");
		String sEstadoEsperado = argu[1].toString().toLowerCase().substring(iAccion+1).trim();
		System.out.println("Estado a Esperar a ejeuctar:*" + sEstadoEsperado +"*");
		
		switch (sAccion) {
		case "red":
			sTipo = "Venta";
			System.out.println("Opcion Red: " + sTipo + " " +sAccion + " " +sEstadoEsperado );
			break;
		case "tramiteventa":
			sTipo = "Venta";
			System.out.println("Opcion Tramite Vta: " + sTipo + " " +sAccion +" " + sEstadoEsperado );
			break;
		case "tramitedesc":
			sTipo = "Desconexión";
			System.out.println("Opcion Tramite Desc:  " + sTipo +" " + sAccion + " " +sEstadoEsperado );
			break;
		default:  
			System.out.println("No seleccionó una opción valida " + argu[1].toString().toLowerCase() );
			break;
		}  

		// Posicionarse en el registro correcto
		PedidosCM().activateRow(0);
		System.out.println("Tipo tramite Linea 0: " + Tipo_Tramite().getProperty("Text") );
		int iPosicion = 0;

		if (!Tipo_Tramite().getProperty("Text").toString().equals(sTipo)) {
			PedidosCM().activateRow(1);
			iPosicion = 1;
			System.out.println("Tipo tramite Linea 1: " + Tipo_Tramite().getProperty("Text") );}
		
		if (!Tipo_Tramite().getProperty("Text").toString().equals(sTipo)) argu[0]="NOK"; 
		else { 

			do
			{ 
				// Actualizo los datos
				HiQuery().performAction("ExecuteQuery");
				PedidosCM().activateRow(iPosicion);
				// Leo estado deseado

				switch (sAccion) {
				case "red":
					sEstado = EstadoRED().getProperty("Text").toString().toLowerCase();
					System.out.println("Opcion RED: Estado RED " +sEstado );
					break;
				case "tramiteventa":
					sEstado = EstadoTramite().getProperty("Text").toString().toLowerCase();
					System.out.println("Opcion Tramite venta: Estado Tramite " +sEstado );
					break;
				case "tramitedesc":
					sEstado = EstadoTramite().getProperty("Text").toString().toLowerCase();
					System.out.println("Opcion Tramite Desc: Estado Tramite " +sEstado );
					break;
				default:  
					break;
				}  

				sleep (iEspera);
				iReintentos=iReintentos + 1;
				System.out.println("Reintentos Maximos " +iReintentosMax );
				System.out.println("Reintentos " +iReintentos  );
			} while(!(sEstado.equals(sEstadoEsperado)) && iReintentos<=iReintentosMax);

			if (sEstado.equals(sEstadoEsperado)) {
				System.out.println("Estado=Estado esperado " +sEstado );
				argu[0]="OK";
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

