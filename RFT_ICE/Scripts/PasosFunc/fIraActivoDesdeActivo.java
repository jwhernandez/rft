package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fIraActivoDesdeActivoHelper;
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
 * Descripcion     :  Selecciona la pestaña de activos, busca un activo y hace drill down en la cta de facturacion
 * Script Name   : <b>fIraActivoDesdeActivo</b>
 * @param 0) numero de caso 1) NA para que lo tome de la variable global o (DP) para que lo tome del DP 
 * seguido de esto debe ir la expresión "<>Activo" "=Inactivo"
 * ej de param 1 NA+<>Activo // DP+=Inactivo
 * 2) ambiente 3) para ante error true / false 4) Nro paso Pasoi
 * ej CP76_CD_T1 NA QA true Paso4
 * @since  2016/11/02
 * @author SS
 * ej CP09_CD_T1 DP+=Inactivo QA NA NA 
 *  * ult modif ss 30/05/2017 se agrega timing entre pestañas / se agrega parametro para 
 * fltrar por estado (parametro 2)
 */
public class fIraActivoDesdeActivo extends fIraActivoDesdeActivoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String[] irActivo;
		irActivo = new String[3];
		// Parámetros: 0) OK/NOK 1) nroServicio 2) 2) Expresion de estado ejemplos =Activo o <>Inactivo

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
		
		int posicion = args[1].toString().indexOf("+");
		String opcion = args[1].toString().substring(0, posicion);
		String filtro = args[1].toString().substring(posicion+1);
		System.out.println(opcion +"-" + filtro);
		if (opcion.trim().toLowerCase().equals("na")) {
			irActivo[1]= getNroServicio();	
		} else {
			irActivo[1]= dpString("NumeroServicio");
			setNroServicio(irActivo[1].toString());
			infoPaso(sScriptName, Tipo.DATO,"NroServicio",getNroServicio());		
		}
		irActivo[2]=filtro.trim();
	
		callScript("Scripts.Comun.IraActivoDesdeActivo", irActivo);
		
		if  ((irActivo[0].toString().equals("NOK"))){
			MensError[0] = "Ir a activo falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

