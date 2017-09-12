package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fValidarActivoEstadoenActivoHelper;
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
 * Descripcion     : Selecciona la pestaña de activos, busca un activo con el estado deseado. 
 * Script Name   : <b>fValidarActivoEstadoenActivo</b>
 * @param 0) numero de caso 1) Estado (Inactivo, Activo, Suspensión) 
 * 2) ambiente 3) para ante error true / false 4) Nro paso Pasoi
 * ej CP76_CD_T1 Suspensión QA true Paso4
 * @since 2016/11/04
 * @author SS
 */
public class fValidarActivoEstadoenActivo extends fValidarActivoEstadoenActivoHelper
{
	public void testMain(Object[] args)  throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidarActivo;
		ValidarActivo = new String[3];
		// 0) Resultado = OK/NOK 1) activo 2) estado deseado (Activo, Inactivo, Suspensión)

		String[] MensError;
		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		
		ValidarActivo[1] = dpString("NumeroServicio");
		ValidarActivo[2]=args[1].toString();
		callScript("Scripts.Comun.ValidarActivoEstadoenActivo", ValidarActivo);
		
		if  ((ValidarActivo[0].toString().equals("NOK"))){
			MensError[0] = "Ir a activo falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

