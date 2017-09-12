package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarDescEnProductoHelper;
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
 * Description   : Valida descuento en producto
 * @author SS
 * Script Name   : <b>fValidarDescEnProducto</b>
 * @since  2016/10/28
 * Parámetros: 0) numero de caso 1)true o false 2) ambiente 
 * 3) para o no para error 4) Nro de paso
 * AE_CD1_T1 1 QA false Paso1
 */
public class fValidarDescEnProducto extends fValidarDescEnProductoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Descuento;
		Descuento = new String[4];
		// Parámetros: 0) OK/NOK  1) true/false 2) valor del descuento (de salida) 3) Tramite 

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		Descuento[1] = args[1].toString();
		Descuento[3] = dpString("Tramite"  );  
		callScript("Scripts.Comun.ValidarDescEnProducto",Descuento);
		
		if  ((Descuento[0].toString().toLowerCase().equals("NOK"))){
			MensError[0] = "Descuento incorrecto";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

