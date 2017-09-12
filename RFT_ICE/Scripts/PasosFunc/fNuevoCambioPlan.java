package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fNuevoCambioPlanHelper;

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
 * Descripcion     : Crear un pedido de CP desde la cuenta cliente
 * Script Name   : <b>fNuevoCambioPlan</b>
 * @param 0) numero de caso 1)digito iterador en el DP
 * Parámetros	   : 0) nro caso (CP05) 1) NA, i o Validacion-i 2) Ambiente 3) true/False 4) Nro paso
 * Pre-condiciones : Estar en la vista 360 de la cuenta
 * SS Nov 2015
 * VC 20170710 Se añade la opción de enviar un número por parámetro para realizar n cambios de plan en un escenario
 * VC 20170711 Se añade opción de ejecutar script sin capturar el número de pedido usando "Validacion+i" como parámetro
 */
public class fNuevoCambioPlan extends fNuevoCambioPlanHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String[] NuevoPedidoCP;
		NuevoPedidoCP = new String[4];
		// Parámetros: 0) Plan Destino 1) Encontrado / No Encontrado 2) Posicion 3) Nro Pedido
		
		String[] NroPedidoCP;
		NroPedidoCP = new String[2];
		// Parámetros: 0) nro pedido 1) OK /NOK

		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
		String[] MensError;
		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		System.out.println(dpString("NumeroCaso"));
		System.out.println(dpString("Ambiente"));
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		/** 
		 * Generar Venta
		 */
		// VC 20170710 Se añade la opción de enviar un número por parámetro para realizar n cambios de plan en un escenario
		String sValidacion = "No"; // VC 20170711 Se usa para saber si el script se va a usar en una validación
		if(args[1].toString().equals("NA")){
			System.out.println(dpString("Plan"));
			NuevoPedidoCP[0]=dpString("Plan");
		} else {
			String indice = "0";
			if(args[1].toString().indexOf("Validacion") > -1){
				String aux[] = args[1].toString().split("-");
				indice = aux[1]; // Toma el indice de la variable
				sValidacion = aux[0]; //Coloca la palabra "Validacion" en la variable
				
			}else{
				indice = args[1].toString();
			}
			int i = Integer.parseInt(indice);
			System.out.println(dpString("Plan"+i));
			NuevoPedidoCP[0]=dpString("Plan"+i);
		}

		callScript("Scripts.Comun.NuevoCambioPlan", NuevoPedidoCP);

		if  (NuevoPedidoCP[1].toString().equals("No Encontrado")){
			MensError[0] = "CP no encontro plan destino";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		
		callScript("Scripts.Comun.SeleccionarPedidoCP", NroPedidoCP);
		
		if(!sValidacion.equals("Validacion")){ // VC 20170711 Si se está ejecutando el script como una validacion, no se debe actualizar el # de pedido
			setNroPedido(NroPedidoCP[0].toString());
			infoPaso(sScriptName, Tipo.DATO,"NroPedido",getNroPedido());
		}else {
			NroPedidoCP[0] = getNroPedido(); // VC 20170711 Se mete el pedido correcto, el que contiene no se debe de almacenar ya que es una validacion
		}
		
		if  (NroPedidoCP[1].toString().equals("No Encontrado")){
			MensError[0] = "CP no encontro nro pedido";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		/**  Guardar el nro de pedido en variable global, dp y texto salida */

		setNroPedido(NroPedidoCP[0].toString());
		
		int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj
		DatoSalida[1]= args[0].toString().substring(0, sLong-3);
		DatoSalida[2]=args[2].toString(); // nro ambiente
		DatoSalida[3]="T"+ getUltimoTramite()+"_NroPedido"; // Tx_Nombre variable // Se modifica de nrotramite a este valor de variable
		DatoSalida[4]=NroPedidoCP[0].toString(); // Valor de la variable

		callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
		
		if  ((DatoSalida[0].toString().equals("NOK"))){
			//MensError[0] = "Nueva venta falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

