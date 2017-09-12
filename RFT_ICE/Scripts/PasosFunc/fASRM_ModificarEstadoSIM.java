package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fASRM_ModificarEstadoSIMHelper;
import com.rational.test.ft.*;
import Scripts.Comun.SistemasExternos.ASRM_ModificarEstadoSIM;
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
 * Description   : Modifica el estado de una SIM en ASRM
 * @author SS
 * Script Name   : <b>fASRM_ModificarEstadoSIM</b>
 * @since  2017/02/08
 * Param 0) numero de caso (CPnn_CDi_Tj) 1)digito iterador en el DP 2) ambiente 3) para ante error
 * ej CP20_CD1_T1 1 QA res res
 */
public class fASRM_ModificarEstadoSIM extends fASRM_ModificarEstadoSIMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Modif;
		Modif = new String[3];
		//0) OK/NOK 1) nroSIM 2) estadodeseado 
		String[] MensError;
		MensError = new String[4];

		// Buscar registro en el DP de entrada
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		
		String opcion = args[1].toString();
		int i = Integer.parseInt(opcion); // SS Se mueve esta sentencia dentro del if no es NA para que no de error cuando es NA
		Modif[1] = dpString("NroSIM" + i);
		Modif[2] = dpString("ASRM_EstadoSIM" + i);

		callScript( new ASRM_ModificarEstadoSIM(), Modif);


		if  (Modif[0].toString().equals("NOK")) {
			MensError[0] = "Problema al modificar estado de la SIM en ASRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

