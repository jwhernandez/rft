package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fASRM_ConsultaEstadoSIM_viejoHelper;
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
 * Description   : Ejecuta el login de ASRM
 * Script Name   : <b>fASRM_ConsultaEstadoSIM</b>
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * Ej 
 * @since  2016/10/14
 * @author ss
 */
public class fASRM_ConsultaEstadoSIM_viejo extends fASRM_ConsultaEstadoSIM_viejoHelper
{
	public void testMain(Object[] args) 
	{
		String[] Consultar;
		Consultar = new String[3];
		//0) Resultado = OK/NOK 1) SIM 2) EstadoDeseado   
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		Consultar[1] = dpString("SIM Correcta");
		Consultar[2] = dpString("ASRM_EstadoSIM");
		callScript("Scripts.Comun.SistemasExternos.ASRM_ConsultaEstadoSIM", Consultar);

		if  (Consultar[0].toString().equals("NOK")) {
			MensError[0] = "Problema al consultar SIM en ASRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

