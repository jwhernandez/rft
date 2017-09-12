package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fLeerNroServicioHelper;
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
 * Description   : Permite leer el nro de servicio de un pedido de venta o cp
 * @author SSASTRE
 * Script Name   : <b>fLeerNroServicio</b>
 * @Param 0)nombre del caso 1)NULL 2) Ambiente 3) true / false para stop x error
 * ej: CP05 NULL DESA false
 * @since  2016/02/08
 */
public class fLeerNroServicio extends fLeerNroServicioHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] NroServicio;
		NroServicio = new String[3];			

		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		System.out.println("DP Usado: " + dpString("NumeroCaso")+ dpString("Ambiente"));
		
		// Paso - Chequear Nro Servicio
		NroServicio[2] = dpString("TipoPerfilCorrecto");

		callScript("Scripts.Comun.LeerNroServicio",NroServicio);
		
		if (args[1].toString().equals("NULL")) {
			if (!(NroServicio[1].equals("NULL"))) {
				MensError[0] = "Campo nro servicio no está limpio.";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);	
			}
		} // fin de cheuqueo de nroservicio limpio
	}
}

