package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fASRM_ModificarEstadoServicio_viejoHelper;
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
 * Description   : Modifica el estado de un servicio en ASRM
 * Script Name   : <b>fASRM_ModificarEstadoServicio</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * Ej res  
 * @since  2016/10/14
 * Autor SS
  */
public class fASRM_ModificarEstadoServicio_viejo extends fASRM_ModificarEstadoServicio_viejoHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Modif;
		Modif = new String[3];
		//0) OK/NOK 1) nroservicio 2) estadodeseado 
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		Modif[1] = dpString("NumeroServicio");
		Modif[2] = dpString("ASRM_EstadoServicio");

		callScript("Scripts.Comun.SistemasExternos.ASRM_ModificarEstadoServicio", Modif);


		if  (Modif[0].toString().equals("NOK")) {
			MensError[0] = "Problema al modificar estado de ASRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

