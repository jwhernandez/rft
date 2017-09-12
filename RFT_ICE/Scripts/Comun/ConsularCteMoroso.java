package Scripts.Comun;
import java.util.Iterator;

import resources.Scripts.Comun.ConsularCteMorosoHelper;
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
 * Description   : Consultar si el cliente es moroso o no a nivel del pedido o del cliente
 * @author SS
 * Script Name   : <b>ConsularCteMoroso</b>
 * @since  2016/05/02
 * @Param 0)OK/NOK 1)true / false 2)Cliente o Pedido (por defecto toma Pedido)
 * ej res true Cliente
 * Si no se coloca cliente espera estar en la pantalla en la que queda luego de fBuscarPedido.
 * ej . 
 */
public class ConsularCteMoroso extends ConsularCteMorosoHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK"; 	
		
		String sOpcion="pedido";
		if (argu.length >= 3 && !argu[2].equals("NA")) { 
			sOpcion = argu[2].toString().toLowerCase();
		}
		System.out.println("Opcion seleccionada: " + sOpcion);

		Boolean bEstado = null; 
		bEstado = Boolean.parseBoolean(argu[1].toString().toLowerCase());

		IFtVerificationPoint  MorosidadVP;
		switch (sOpcion) {
		case "pedido":
			if (bEstado.equals(true) || bEstado.equals(false)) {
				MorosidadVP = vpManual("MorosidadPedido",bEstado,Morosidad().getProperty("IsChecked"));
				if  (MorosidadVP.performTest()) 	argu[0] = "OK";
			}
			break;
		case "cliente":
			TabsCliente().goTo("Account Customer Profile View","L3");
			sleep(2);
			TabsCliente().goTo("ICE Consulta_Morosidad", "L4");
			sleep(2);
			TabsCliente().goTo("ICE Consulta_Morosidad", "L4");
			sleep(2);

			ConsultarMorosidad().performAction(true);
			sleep (5);

			System.out.println("Monto Deuda: " + MontoDeuda().getProperty("Text"));
			System.out.println("Monto Deuda: " + Identificacion().getProperty("Text"));

			MorosidadVP = vpManual("MorosidadCliente","",MontoDeuda().getProperty("Text"));
			if (bEstado.equals(false)) { // cliente no moroso
				if  (!MorosidadVP.performTest() && 
						MontoDeuda().getProperty("Text").equals("¢,00")) 	argu[0] = "OK";
			} 
			if (bEstado.equals(true)) {

				if  (MorosidadVP.performTest() && 
						!MontoDeuda().getProperty("Text").equals("¢,00")) 	argu[0] = "OK";
			}
		break;
	default:
			System.out.println("Opcion ingresada no valida");
			break;
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

