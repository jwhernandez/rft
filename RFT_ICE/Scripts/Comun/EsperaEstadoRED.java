package Scripts.Comun;
import resources.Scripts.Comun.EsperaEstadoREDHelper;
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
 * Descripción: Permite esperar un tiempo parametrico a que se produzca un cambio 
 * de estado del RED
 * Script Name   : <b>EsperaEstadoRED</b>
 * @since  2016/05/23
 * Parámetros: 0)OK / NOK 1) Estado esperado 2)Tramite 3) NroRED o NA
 * En res "Por Pagar" PortIn res
 * @author SS
 * Ultima modificacion 9/01/2017
 * 				15/03/2017 ss se soluciona bug 190. Valida y devuelve nok pero no reintenta 
 * 				ss 18/4/2017 para solucionar problema PMR 194
 * 				ss 18/4/2017 se modifica para que pueda tomar bien el estado pagado (problema de minusculas)
 * 			ss 21/7/2017 se modifica para agregar estado anulado
 */
public class EsperaEstadoRED extends EsperaEstadoREDHelper
{
	public void testMain(Object[] argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		int iReintentos = 1;
		int iEspera = 1;
		int iReintentosMax = 1;
		String sEstado = "No Leido";
		String sEstadoDeseado = "No Leido";
		String sRED = "No Leido";
		Boolean bErrorOpcion=false;
		Boolean bContinuar = true;
		
		String sTramite = "Venta";
		if (argu.length >= 3 ) { 
			sTramite = argu[2].toString(); // tramite
		}
		
		argu[3]="NA"; // Nro de red a devolver
		argu[0]="NOK"; // Resultado a devolver
		sEstadoDeseado = argu[1].toString().toLowerCase();
		
		switch (sEstadoDeseado) {
		case "por pagar":
		case "pagado":
		case "anulado":
			iReintentosMax = Integer.parseInt (dpString("ReintentosEspera_RedPagado"));
			iEspera = Integer.parseInt (dpString("SleepEspera_RedPagado"));
			break;
		default:  
			bErrorOpcion=true;
			System.out.println("Opcion ingresada no valida" );
			break;
		}  

		BotonConsultaPedido().ensureObjectIsVisible(); // ss 18/4/2017 para solucionar problema PMR 194
		System.out.println("Reintentos Maximos" +iReintentosMax );
		if (!bErrorOpcion) 
		{ // Solo si estado está contemplado como opción válida
			do // mientras la evaluación de que hay que continuar reintentando seguir actualizando
				// la información
			{ 
				// Posicionarse en el panel superior para que la consulta aplique sobre el mismo
				// Se lee y se vuelve a grabar el mismo valor. Se podría reemplazar por un click.

				if (!sTramite.equals("PortIn")){
					String sTipo = TipoAutenticacion().getProperty("ActiveItem").toString();
					TipoAutenticacion().setText(sTipo);
				}
				if (sTramite.equals("PortIn")){
					String sTipo = TipoAutenticacionPI().getProperty("ActiveItem").toString();
					TipoAutenticacionPI().setText(sTipo);
				}

				// Se actualiza la información de nro de red y estado de red al realizar el query
				HIQuery().performAction("ExecuteQuery");
				if (!sTramite.equals("PortIn"))
				{
					sEstado = EstadoRED().getProperty("ActiveItem").toString().toLowerCase().toLowerCase();	// ss 18/4/2017
					sRED = NroRED().getProperty("Text").toString();
				}
				else 
				{
					sEstado = EstadoREDPI().getProperty("ActiveItem").toString().toLowerCase();	
					sRED = NroREDPI().getProperty("Text").toString();
				}

				System.out.println("Estado en SBL " +sEstado );
				System.out.println("RED en SBL " +sRED );

				iReintentos=iReintentos + 1;
				sleep (iEspera);
				System.out.println("Reintentos Maximos " +iReintentosMax );
				System.out.println("Reintentos Realizados" +iReintentos  );
				// -------------------------------------------
				// Evaluar si hay que seguir reintentando o no 
				// -------------------------------------------
				if (iReintentos>iReintentosMax) 
				{
					bContinuar = false;
					System.out.println("Se llegó al numero de reintentos máximo " );
				}
				if (sEstado.equals(sEstadoDeseado) &&  sEstado.equals("por pagar") 
						&& !sRED.equals(""))
				{
					argu[3] = sRED;
					argu[0]="OK";
					bContinuar=false;
					System.out.println("Se llegó a red distinto de blanco y estado por pagar " );
				}
				if (sEstado.equals(sEstadoDeseado) && (sEstado.equals("pagado") || sEstado.equals("anulado"))) // ss 21072017 ss 18/4/2017 se pone en minusc
				{
					argu[3] = sRED;
					argu[0]="OK";
					bContinuar=false;
					System.out.println("Se llegó a estado pagado " );
				}
			}
			while(bContinuar);
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

