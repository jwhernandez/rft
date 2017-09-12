package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.CapturarPedidoCM_AntHelper;
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
 * Script Name   : <b>CapturarPedidoCM</b>
 * @Param 0) Validación OK/NOK 1) venta o desconexion  2) nro pedido
 * @author SS
 * @since  2017/02/14
 */
public class CapturarPedidoCM_Ant extends CapturarPedidoCM_AntHelper
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
						sPedido = NroPedidoCM().getProperty("Text").toString(); 
						argu[2]=sPedido;	
						System.out.println("Pedido Desconexión "+sPedido);
						infoPaso(sScriptName, Tipo.DATO,"NroPedidoVenta",sPedido);
						argu[0]="OK";
					}
					break;
				case "Desconexión":
					if (sOpcion.equals("desconexión"))
					{
						System.out.println("Datos de la desconexión");
						sPedido = NroPedidoCM().getProperty("Text").toString(); 
						argu[2]=sPedido;	
						System.out.println("Pedido Desconexión "+sPedido);
						infoPaso(sScriptName, Tipo.DATO,"NroPedidoDesconexión",sPedido);
						argu[0]="OK";
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
}
