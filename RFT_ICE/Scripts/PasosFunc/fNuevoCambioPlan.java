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
 * Parámetros	   : 0) nro caso (CP05) 1) Argumento (indice de variables, etc. 
 * Pre-condiciones : Estar en la vista 360 de la cuenta
 * SS Nov 2015
 */
public class fNuevoCambioPlan extends fNuevoCambioPlanHelper
{
	public void testMain(Object[] args) 
	{
		String[] NuevoPedidoCP;
		NuevoPedidoCP = new String[4];
		// Parámetros: 0) Plan Destino 1) Encontrado / No Encontrado 2) Posicion 3) Nro Pedido
		
		String[] NroPedidoCP;
		NroPedidoCP = new String[2];
		// Parámetros: 0) nro pedido 1) OK /NOK
		
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
		System.out.println(dpString("Plan"));
		NuevoPedidoCP[0]=dpString("Plan");

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
		setNroPedido(NroPedidoCP[0].toString());
		infoPaso(args[0].toString(), Tipo.DATO,"NroPedido",getNroPedido());
		
		if  (NroPedidoCP[1].toString().equals("No Encontrado")){
			MensError[0] = "CP no encontro nro pedido";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

