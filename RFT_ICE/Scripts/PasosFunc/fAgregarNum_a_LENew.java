package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fAgregarNum_a_LENewHelper;
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
 * Descripción: Agregar un numero a una lista especial pasada como parametro
 * @param 0) numero de caso 1)un digito indicando el numero de variable en el dp
 * Precondiciones: Estar en la pantalla de lista especial
 * Luego de la ejecución del script si el parametro 2 es volver al pedido volverá al pedido.
 * @author SS 
 * Script Name   : <b>fAgregarNum_a_LE</b>
 * @since 2016/10/20
 */
public class fAgregarNum_a_LENew extends fAgregarNum_a_LENewHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] ListaEsp;
		ListaEsp = new String[7];
		// Parámetros: 0) NOK / OK 1) Nombre lista 2) volver a pedido true false 3) NumTel 
		// 4) Msj a validar o NA 5) Msj real 6) false o true si coincide
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		int i = Integer.parseInt(args[1].toString());
		ListaEsp[1] = getNomListaEspecial(); 
		ListaEsp[2] = dpString("LEVolverPed"+i);
		System.out.println("Numero de Telefono: " +  dpString("LETel"+i));
		ListaEsp[3] = dpString("LETel"+i);
		ListaEsp[4] = dpString("LEMsj"+i);

		callScript("Scripts.Comun.AgregarNum_a_LE", ListaEsp);

		if  ((ListaEsp[0].toString().equals("NOK")) || (ListaEsp[6].toString().equals("false"))){
			MensError[0] = "Lista Especial tuvo error";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		} 
	}
}

