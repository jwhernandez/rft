package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fASRM_ConsultaEstadoSIMHelper;

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
 * Última modificación VC 08/11/2016 01/12/2016
 */
public class fASRM_ConsultaEstadoSIM extends fASRM_ConsultaEstadoSIMHelper
{
	public void testMain(Object[] args) throws RationalTestException
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

		//Se agrega está linea para poder ejecutar el Script con diferentes conjuntos de datos (08/11/2016)
		int i = Integer.parseInt(args[1].toString());

		//Se cambia el nombre de la variable en el datapool para poder validar varios números de SIM sin alterar la variable SIM Correcta que es utilizada por varios scripts (08/11/2016)
		//Consultar[1] = dpString("SIM Correcta");

		Consultar[1] = dpString("SIM_ASRM"+i);
		Consultar[2] = dpString("ASRM_EstadoSIM"+i);
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

