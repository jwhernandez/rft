package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarPedidosCMHelper;

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
 * Description   :  Realizar numerosas validaciones y captura y devuelve los campos del monitor de CM. Tiene acciones para realizar validaciones 
 * especificas como ser ValidarRED
 * Script Name   : <b>fValidarPedidosCM</b>
 * @since  2016/04/27
 * @author SS
 * @ Param 0)Nombre del caso 1)Capturar / ValidarRED /ValidarIMSI Todo / ValidarIMSI Desc / ValidarIMSI Vta
 * 2) Ambiente 3) true / false 4) nro paso
 * ej CP20 "ValidarIMSI Todo" PREQA true 10
 * 
 * ult modif ss 22 02 2017 se le agrega opcion por pagar y se graba el nro de red en datos salida
 */
public class fValidarPedidosCM extends fValidarPedidosCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Capturar;
		Capturar = new String[15];
		
		 //@Param 0) Validación OK/NOK 1) out Estado de la red (solo venta  2) out Estado del tramite venta 
		 // 3) estado del tramite desc 4) estado de la tarea venta
		 // 5) Estado de la tarea  desc
		 // 6) Pedido venta  7) Pedido Desconexión 8)Imsi venta  9)out IMSI desconexion
		 // 10) Acciones posibles (ValidarRED, Capturar (lo hace siempre pero si se coloca capturar solo hace eso)
		 // Si se coloca ValidarRED captura y además valida.
		 // 11) nro paso
		 // 12) valor de estado red a validar (Pagado, Pendiente de Pagar, etc)
		 // 13 valor de IMSI vta opc 14) valor de IMSI desc opc

		String[] MensError;
		MensError = new String[4];		

		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		Capturar[11] = args[4].toString();
		switch (args[1].toString().toLowerCase()) {
		case "validartramite": // la accion es validarred el atributo pagado es para comparar
			Capturar[10] = "ValidarTramite";
			Capturar[12] = "NA";
			Capturar[13] = "NA";
			Capturar[14] = "NA";
			break;
		case "validarred por pagar": // la accion es validarred el atributo por pagar es para comparar
			Capturar[10] = "ValidarRED";
			Capturar[12] = "Por pagar";
			Capturar[13] = "NA";
			Capturar[14] = "NA";	
			break;			
		case "validarred pagado": // la accion es validarred el atributo pagado es para comparar
			Capturar[10] = "ValidarRED";
			Capturar[12] = "Pagado";
			Capturar[13] = "NA";
			Capturar[14] = "NA";	
			break;
		case "capturar":
			Capturar[10] = "Capturar";
			Capturar[12] = "NA";
			Capturar[13] = "NA";
			Capturar[14] = "NA";
			break;
		case "validarimsi vta":
			Capturar[10] = "ValidarIMSI Vta";
			Capturar[12] = "NA";
			Capturar[13] = dpString("IMSI");
			Capturar[14] = "NA";
			break;
		case "validarimsi desc":
			Capturar[10] = "ValidarIMSI Desc";
			Capturar[12] = "NA";
			Capturar[13] = "NA";
			Capturar[14] = dpString("IMSI Activo");
			break;
		case "validarimsi todo":
			Capturar[10] = "ValidarIMSI Todo";
			Capturar[12] = "NA";
			Capturar[13] = dpString("IMSI");
			Capturar[14] = dpString("IMSI Activo");
			break;
		default:
			System.out.println("Se pasó como parametro una opción no valida");
			break;
		}// end del switch

		callScript("Scripts.Comun.ValidarPedidosCM",Capturar);

		// guardar variables globales
		setNroPedido(Capturar[6].toString());
		setNroPedidoDesc(Capturar[7].toString());
		setIMSIVta(Capturar[8].toString());
		setIMSIDesc(Capturar[9].toString());

		if (Capturar[0].toString().equals("NOK")) {	
			//MensError[0] = "Problema al capturar datos del monitor de pedidos CM";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		
		if (Capturar[10] == "ValidarRED")
		{
			// grabar el nro de red recibido
			int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj  / CPnn_CDi_Tj  ss modif feb 2017 para adaptarlo al nuevo framework mulitramite 
			DatoSalida[1]= args[0].toString().substring(0, sLong-3);
			DatoSalida[2]=args[2].toString(); // nro ambiente
			DatoSalida[3]="T"+ getUltimoTramite()+"_NroRED"; // Nombre variable
			DatoSalida[4]=getNroRED(); // Valor de la variable
			callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
		}
	}
}

