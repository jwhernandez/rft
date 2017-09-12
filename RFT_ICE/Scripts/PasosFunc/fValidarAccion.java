package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fValidarAccionHelper;
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
 * Description   : Valida la accion de las lineas del pedido para el producto en el cual esta posicionado el cursor
 * Script Name   :  <b>fValidarAccion</b>
 * @since  2016/11/02
 * Parámetros	   : 0) nro caso (CP05)  1) Argumento (indice de variables, etc. 
 * 2) ambiente 3) para ante error true / false 4) Nro paso Pasoi
 * ej CP76_CD_T1 NA QA true Paso4
 * @author SS
 * res 
 */
public class fValidarAccion extends fValidarAccionHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Accion;
		Accion = new String[3];
		// Parámetros: 0) OK/NOK  1) Accion 2) Tramite 

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		Accion[1] = args[1].toString();
		Accion[2] = dpString("Tramite"  );  
		callScript("Scripts.Comun.ValidarNuevaAccion",Accion);
		
		if  ((Accion[0].toString().toLowerCase().equals("NOK"))){
			MensError[0] = "Accion incorrecta";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

