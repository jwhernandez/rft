package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarDescuentoTerminalHelper;
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
 * Script Name   : <b>fValidarDescuentoTerminal</b>
 * Descripcion   :Valida que esten habilitados o inhabilitados los campos de descuento del terminal
 * precondicion: estar en el producto terminal
 * @Param  0) nombre del caso 1) true (habilitado) / false  2) IN Ambiente 3) IN ErrorStop (Si / No)
 * ej CP46 NA PREQA true 20
 * @since  2016/05/04
 * @author SS
 */
public class fValidarDescuentoTerminal extends fValidarDescuentoTerminalHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidaCamposDesc;
		ValidaCamposDesc = new String[2];
		//0) OK/NOK  1) opcion false (deshabiltado) true (habilitado)   
		
		String[] MensError;
		MensError = new String[4];
		
//		String[] Expandir;
//		Expandir = new String[4];
//		Expandir [0] = args[0].toString();
//		Expandir [1] = "Terminales Promocionales";
//		Expandir [2] = args[2].toString();
//		Expandir [3] = args[3].toString();
//		
//		callScript("Scripts.PasosFunc.fExpandirLinea", Expandir);		
		
		ValidaCamposDesc[1]=args[1].toString().toLowerCase();
		callScript("Scripts.Comun.ValidarDescuentoTerminal", ValidaCamposDesc);

		if  (ValidaCamposDesc[0].toString().equals("NOK")){
			//MensError[0] = "Validar Campos Terminal falló;
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

