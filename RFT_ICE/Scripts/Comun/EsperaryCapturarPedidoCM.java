package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.EsperaryCapturarPedidoCMHelper;
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
 * Description   : Captura el nro de pedido (venta o desconexion) segun lo indicado por argumento.
 * Guarda en variable global nropedido y en dp_datosSalida
 * Script Name   : <b>EsperarYCapturarPedidoCM</b>
 * @Param 0) Validación OK/NOK 1) venta o desconexion  2) nro pedido
 * @author SS
 * @since  2017/02/14x	
 */
public class EsperaryCapturarPedidoCM extends EsperaryCapturarPedidoCMHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="NOK";
		String sScriptName=getScriptName().toString();  
		String sOpcion = argu[1].toString().toLowerCase();
		System.out.println("Opcion "+sOpcion);


		if (LineasdePedidosCMAll().getProperty("RowsCount").toString().equals("2")) {
			for (int i=0;i<=1;i++) {

				LineasdePedidosCMAll().activateRow(i);

				String sTipoTramite=Tipo_TramiteCMAll().getProperty("Text").toString(); 
				String sPedido;

				switch (sTipoTramite) {
				case "Venta":
					if (sOpcion.equals("venta"))
					{
						System.out.println("Datos de la Venta");
						argu[2]=EsperaPedido();	
						if (!argu[2].toString().equals("")) argu[0]="OK";
						System.out.println("Pedido Desconexión "+argu[2].toString());
						infoPaso(sScriptName, Tipo.DATO,"NroPedidoVenta",argu[2].toString());
					}
					break;
				case "Desconexión":
					if (sOpcion.equals("desconexión"))
					{
						System.out.println("Datos de la desconexión");
						argu[2]=EsperaPedido();	
						System.out.println("Pedido Desconexión "+argu[2].toString());
						infoPaso(sScriptName, Tipo.DATO,"NroPedidoDesconexión",argu[2].toString());
						if (!argu[2].toString().equals("")) argu[0]="OK";
					}
					break;
				default:  
					System.out.println("Opción no válida");
					break;
				} // end del switch
			} // end del for
		} else
		{
			System.out.println("El número de pedidos filtrados no es 2");
			argu[0]="NOK";
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}

	private String EsperaPedido() {
		int iReintentos = 0;
		int iEspera = 1;
		int iReintentosMax = 1;

		iReintentosMax = Integer.parseInt (dpString("ReintentosEspera_PedidosCM"));
		iEspera = Integer.parseInt (dpString("SleepEspera_PedidoCM"));

		System.out.println("Reintentos Maximos" +iReintentosMax );
		String sPedido = NroPedidoCM().getProperty("Text").toString(); 
		do
		{ 

			sleep (iEspera);
			iReintentos=iReintentos + 1;
			System.out.println("Reintentos Maximos " +iReintentosMax );
			System.out.println("Reintentos " +iReintentos  );
			sPedido = NroPedidoCM().getProperty("Text").toString(); 
		} while(!(sPedido.equals("")) && iReintentos<=iReintentosMax);

		if (!sPedido.equals(""))  {
			System.out.println("NroPedido= " +sPedido );
		}
		return sPedido;	
	}
}
