package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fValidaRedReversadoHelper;

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
 * Script Name   : <b>fValidaRedReversado</b>
 * Descripcion   : Validación post reversar un RED 
 * @author SS
 * @Param 0)IN nombre del caso 1) NA 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * @since  2016/05/25
 * Ej CP03 NA QA true
 * Precondiciones Estar en la pantalla del pedido 
 * Ult modif ss 18/4/2017 se modifica la lectura para framework multitramite
 * se agrega opcion de portin
 */
public class fValidaRedReversado extends fValidaRedReversadoHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String[] MensError;
		MensError = new String[4];

		String[] ValidaRED;
		ValidaRED = new String[3];
		//0)OK / NOK 1) NroRED 2) Tramite

/*		String[] LeerDatoSalida;
		LeerDatoSalida = new String[4];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Tramite = Tj  (Ejemplo T1)
		// Chequear porque no debería hacer falta leer datos salida. Ya lo tiene
		// que haber hecho casos prueba esta lectura.

		LeerDatoSalida[1] = args[0].toString(); // Nro Caso
		LeerDatoSalida[2] = args[2].toString(); // Ambiente
		callScript("Scripts.Comun.LeerDatosSalida",LeerDatoSalida);*/ //ss 18/4/2017 se modifica la lectura para framework multitramite

		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
 
		ValidaRED[1] = getNroRED();
		ValidaRED[2] = dpString("Tramite"); // ss 18/4/2017
		callScript("Scripts.Comun.ValidaRedReversado",ValidaRED);

		if (ValidaRED[0].toString().equals("NOK")) {	
			//MensError[0] = "Problema en la validación del RED reversado";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		} 
		infoPaso(sScriptName, Tipo.DATO,"NroRED",getNroRED());

	}
}

