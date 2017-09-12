package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarActivoHelper;
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
 * Description   : Valida el estado (Activo o Inactivo) de un nro de servicio
 * Script Name   : <b>fValidarActivo</b>
 * @author SS
 * @since  2016/04/28
 * @Param 0)caso 1) Desconexión Inactivo o Venta Activo 2)ambiente 3) true false 4)nro paso opc
 * eje CP20_CD1_T1 "Desconexión Inactivo" QA true 20
 */
public class fValidarActivo extends fValidarActivoHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Valida;
		Valida = new String[5];
		//0)OK/NOK 1)Servicio 2)Estado 3)nro paso para capturar otros datos 4)tipo (Venta / Desconexión)
		
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
		
	
		if (args[1].toString().toLowerCase().equals("desconexión inactivo")){
			Valida[2]= "Inactivo";
			Valida[4]= "Desconexión";
		}
		if (args[1].toString().toLowerCase().equals("venta activo")){
			Valida[2]= "Activo";
			Valida[4]= "Venta";
		}

		
		Valida[1]=dpString("NumeroServicio");
		Valida[3]= args[4].toString();
	
		callScript("Scripts.Comun.ValidarActivo",Valida);


		if  ((Valida[0].toString().equals("NOK")))
		{
			//MensError[0] = "Paso valida activo dio error";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

