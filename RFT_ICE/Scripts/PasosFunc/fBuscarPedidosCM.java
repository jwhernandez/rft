package Scripts.PasosFunc;
import java.text.SimpleDateFormat;
import java.util.Date;

import libreria.utileria.Tipo;

import resources.Scripts.PasosFunc.fBuscarPedidosCMHelper;
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
 * Description   : Busca el numero de pedido CM
 * Script Name   : <b>fBuscarPedidosCM</b>
 * @author SS
 * @since  2016/04/19
 * Ult modif ss 14022017 se agrega grabado para Multi Tramite de la variable task id
 * 
 * Param 0) Nro caso 1) Prepago/Postpago o Postpago/Prepago 2) ambiente 3) error para o no 
 * 4) nropaso 5) opcional fecha 6) opcional usuario 7)opcional task id
 * los argumentos 5, 6 y y se usan para probar el script en forma independiente
 * CP30 Prepago/Postpago PREQA false 20 04/01/2016 SOIN_KRAMIREZ // metodo varios parametros
 * CP30 Prepago/Postpago PREQA false 20 NA NA  1-RZSETD // metodo task id
 * Ej CP20_CD1_T1 "Prepago a Pospago" QA false NA 
 * Última modificación: VC 20170328 Se agrega try catch para los casos en los que aún no se tiene el número de pedido
 */
public class fBuscarPedidosCM extends fBuscarPedidosCMHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sScriptName=getScriptName().toString(); // 22/11/2016

		// sentencias para probar el script en forma independiente
		if (args.length >= 6 && !args[5].toString().equals("NA")) { 
			setFechaCM(args[5].toString());
			System.out.println("Variable Fecha:   " + getFechaCM());}
		if (args.length >= 7 && !args[6].toString().equals("NA")) { 
			setUsuario(args[6].toString());
			System.out.println("Variable usuario:   " + getUsuario());}
		if (args.length >= 8 && !args[7].toString().equals("NA")) { 
			setTaskIdCM(args[7].toString());
			System.out.println("Variable TaskId:   " + getTaskIdCM());}
		// fin de sentencias para probar el script en forma independiente
		
		String[] MensError;
		MensError = new String[4];
		
		String[] BuscPedCM;
		BuscPedCM = new String[6];
		// 0)NOK OK 1) Nro Servicio 2) tipo operacion 3) fecha 4) usuario 5) task id opcional
		
		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
				
		// Decidir si buscar por task id o por varios parametros
		System.out.println("Valor del task id global:" + getTaskIdCM());
		if (getTaskIdCM()== null) {
			System.out.println("Busqueda por varios parametros por no haber aun task id");

			/**
			 * Itera el data pools de datos del caso para buscar la row correcta
			 */
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
					dpString("Ambiente").equals(args[2]))) {
				dpNext(); 
			} 
			
			BuscPedCM[1]= dpString("NumeroServicio");
			BuscPedCM[2]= args[1].toString(); // Ej. Prepago/Postpago
			BuscPedCM[3]= getFechaCM();
			BuscPedCM[4]= getUsuario();
			BuscPedCM[5]= "NA";

			System.out.println("Valor de fecha global:" + getFechaCM());
			System.out.println("NroServicio: " + BuscPedCM[1]);
		}
		else 
		{
			System.out.println("Busqueda por task id");
			BuscPedCM[1]= "NA";
			BuscPedCM[2]= "NA";
			BuscPedCM[3]= "NA";
			BuscPedCM[4]= "NA";
			BuscPedCM[5]= getTaskIdCM();
		}
		
		callScript("Scripts.Comun.BuscarPedidosCM", BuscPedCM);
		
		// captura el task id en el txt
		infoPaso(sScriptName, Tipo.DATO,"Id Task",getTaskIdCM());

		
		if (BuscPedCM[0].toString().equals("NOK")) {
			//	MensError[0] = "Pedido de CM no encontrado";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		
		int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj  / CPnn_CDi_Tj  ss modif feb 2017 para adaptarlo al nuevo framework mulitramite 
		DatoSalida[1]= args[0].toString().substring(0, sLong-3);
		DatoSalida[2]=args[2].toString(); // nro ambiente
		DatoSalida[3]="T"+ getUltimoTramite()+"_TaskIdCM"; // Nombre variable
		DatoSalida[4]=BuscPedCM[5].toString(); // Valor de la variable task Id

		callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
		
		try{ // VC 20170328 Se agrega try catch para los casos en los que aún no se tiene el número de pedido
			infoPaso(getScriptName().toString(), Tipo.DATO,"NroPedido",getNroPedido());
		}catch (Exception e){
			infoPaso(getScriptName().toString(), Tipo.DATO,"NroPedido","");
		}
		
	}
}

