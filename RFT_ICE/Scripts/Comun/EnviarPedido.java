package Scripts.Comun;
import resources.Scripts.Comun.EnviarPedidoHelper;
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
 * Descripción: Permite Seleccionar enviar pedido
 * Parámetros: 1 || 0) Creado / No creado 1) Tramite
 * SS Nov 2015
 * ult modif 23 - 3 - 2017 se agrega opcion de portin
 * junio 2017 ss se ajusta el acople del objeto para que sea mas fuerte y se resuelva el ambiguos definition
 */
public class EnviarPedido extends EnviarPedidoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "No creado";

		int iReintentos = 1;
		int iEspera = 1;
		int iReintentosMax = 1;
		boolean hayMensaje=false;
		String sTramite = argu[1].toString();
		boolean Habilitado = false;

		iReintentosMax = Integer.parseInt (dpString("ReintentosEsperaAUT_EnviarPedido"));
		iEspera = Integer.parseInt (dpString("SleepEsperaAUT_EnviarPedido"));
		System.out.println("Cantidad de intentos maximo"+ iReintentosMax);
		System.out.println("Tiempo de espera entre reintentos"+ iEspera);

		for (iReintentos=1; iReintentos <=iReintentosMax  ; iReintentos++) {
			try
			{
				System.out.println("cantidad de intentos"+ iReintentos);
				if (iReintentos ==1) {
					BotonConsultaPedidoAjustado().ensureObjectIsVisible(); // se modifica el padre para que dependa del objeto 1AF.hHIqoPDc282:18KtoR:Q8m7bmu:8WU
					// con la propiedad text igual Detalles 1-.*Pedido de ventas.* ss junio 2017
					//BotonConsultaPedido().ensureObjectIsVisible(); // Se agrega por problema del PMR julio 2016 - se modifica a 100 la propiedad .id 
					
					if (!sTramite.equals("PortIn"))
						Habilitado = enviarPedido().isEnabled();
					if (sTramite.equals("PortIn"))
						Habilitado = enviarPedido_PI().isEnabled();
						
					if (Habilitado) {
						if (!sTramite.equals("PortIn"))
							enviarPedido().performAction(true);
						if (sTramite.equals("PortIn"))
							enviarPedido_PI().performAction(true);
						argu[0] = "Creado";
					} else { 
						argu[0] = "No creado";
						iReintentos = iReintentosMax;
					}
					sleep (20); 
				}
				if (!hayMensaje) { iReintentos = iReintentosMax;}
			} // end del try
			catch (Exception e){
				hayMensaje = true;
				logInfo("Mensaje de excepción: "+e.getMessage());
				System.out.println("Mensaje de excepción: "+e.getMessage());
				sleep(iEspera);
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

