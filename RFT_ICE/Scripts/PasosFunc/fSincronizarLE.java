package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fSincronizarLEHelper;

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
 * Script Name   : <b>fSincronizarLE</b>
 * Generated     : <b>jun. 23, 2017 3:39:09 PM</b>
 * Description   : Presiona el botón sincronizar para actualizar el número en una LE
 * Original Host : WinNT Version 6.1  Build 7601 (S)
 * 
 * @since  2017/06/23
 * @author VC
 */
public class fSincronizarLE extends fSincronizarLEHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		// Parámetros: 0) NOK / OK 
		
		String[] ListaEsp;

		ListaEsp = new String[3];
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		int i = Integer.parseInt(args[1].toString());
		
		ListaEsp[1] = dpString("SincLEVolverPed"+i);
		ListaEsp[2] = dpString("Tramite");
		
		callScript("Scripts.Comun.SincronizarLE", ListaEsp);

		if  ((ListaEsp[0].toString().equals("NOK"))){
			MensError[0] = "Sincronizar Lista Especial tuvo error";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		} 
	}
}

