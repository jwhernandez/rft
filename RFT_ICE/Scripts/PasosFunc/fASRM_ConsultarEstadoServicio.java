package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fASRM_ConsultarEstadoServicioHelper;
import Scripts.Comun.SistemasExternos.ASRM_ConsultarEstadoServicio;
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
 * Description   : Consulta el estado de un servicio en ASRM
 * @author ss
 * Script Name   : <b>fASRM_ConsultarEstadoServicio</b>
 * @since  2017/02/21
 * Param1 i 
 * ej CP20_CD1_T1 1 QA NA NA
 */
public class fASRM_ConsultarEstadoServicio extends fASRM_ConsultarEstadoServicioHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		String[] Consultar;
		Consultar = new String[3];
		//0) Resultado = OK/NOK 1) NroServicio 2) EstadoDeseado   
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 		

		int i = Integer.parseInt(args[1].toString());

		Consultar[1] = dpString("ASRM_NroServ"+i);
		Consultar[2] = dpString("ASRM_EstadoNro"+i);
		callScript( new ASRM_ConsultarEstadoServicio(), Consultar);

		if  (Consultar[0].toString().equals("NOK")) {
			MensError[0] = "Problema al consultar el servicio en ASRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

