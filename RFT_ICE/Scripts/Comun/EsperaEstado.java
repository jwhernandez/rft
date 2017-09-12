package Scripts.Comun;
import resources.Scripts.Comun.EsperaEstadoHelper;
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
 * de estado del pedido
 * Script Name   : <b>EsperaEstado</b>
 * @since  2016/03/03
 * Parámetros: 0)Estado esperado 1)OK / NOK 2)Tramite
 * @author SS Nov 2015
 */
public class EsperaEstado extends EsperaEstadoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		int iReintentos = 1;
		int iEspera = 1;
		int iReintentosMax = 1;
		String sEstado = "No Leido";
		
		String sTramite = "Venta";
		if (argu.length >= 3 ) { 
			sTramite = argu[2].toString(); // tramite
		}
		
		argu[1]="NOK";
		
		switch (argu[0].toString()) {
		case "RED Pagado":
			iReintentosMax = Integer.parseInt (dpString("ReintentosEspera_RedPagado"));
			iEspera = Integer.parseInt (dpString("SleepEspera_RedPagado"));
			break;
		case "Completar":
			iReintentosMax = Integer.parseInt (dpString("ReintentosEspera_PedidoComplete"));
			iEspera = Integer.parseInt (dpString("SleepEspera_PedidoComplete"));
			break;
		default:  
			break;
		}  

		System.out.println("Reintentos Maximos" +iReintentosMax );
		
		do
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

			// Actualizo datos Siebel con el query
			HIQuery().performAction("ExecuteQuery");
			if (!sTramite.equals("PortIn"))
				{sEstado = EstadoPedido().getProperty("ActiveItem").toString();	}
			if (sTramite.equals("PortIn"))
				{sEstado = EstadoPedidoPI().getProperty("ActiveItem").toString();}
			
			System.out.println("Estado " +sEstado );
			iReintentos=iReintentos + 1;
			sleep (iEspera);
			System.out.println("Reintentos Maximos " +iReintentosMax );
			System.out.println("Reintentos " +iReintentos  );
		} while(!(sEstado.equals(argu[0].toString())) && iReintentos<=iReintentosMax);

		if (sEstado.equals(argu[0].toString())) {
			argu[1]="OK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

