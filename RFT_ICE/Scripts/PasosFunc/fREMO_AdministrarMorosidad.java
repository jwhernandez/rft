package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fREMO_AdministrarMorosidadHelper;
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
 * Description   : Permite cambiar la morosidad a false o consultar la morosidad. 
 * La morosidad a true no se ha implementado aun
 * @since  2017/03/10
 * @author SS
 * Script Name   : <b>fREMO_AdministrarMorosidad</b>
 * param1 = Consultar TRUE o Consultar FALSE o Setear FALSE
 * No se implementa Setear True (aun no se necesita)
 * CP26_CD1_T1 "Consultar TRUE" QA NA NA
 * CP26_CD1_T1 "Setear FALSE" QA NA NA
 */
public class fREMO_AdministrarMorosidad extends fREMO_AdministrarMorosidadHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] AdministrarDeuda;
		AdministrarDeuda = new String[6];
		//0) OK/NOK 1) Accion Consultar / Setear 2) Estado (True / False )
		// 3) Nro identificacion 4) Id cliente al cual reasignar la deuda 5) Tipo cliente
		// al cual reasignar la deuda  
		// Opciones: Consultar TRUE o Consultar FALSE o Setear FALSE
		// No se implementa Setear True (aun no se necesita)
		// res consultar true 121660600
		// res setear false 121660600 603310575 Fisica

		int iAccion = args[1].toString().toLowerCase().indexOf(" ");
		String sAccion = args[1].toString().toLowerCase().substring(0, iAccion).trim();
		System.out.println("Accion a ejecutar:*" + sAccion +"*");
		String sMoroso = args[1].toString().toLowerCase().substring(iAccion+1).trim();
		System.out.println("Morosidad Esperada:*" + sMoroso +"*");
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		AdministrarDeuda[1] = sAccion;
		AdministrarDeuda[2] = sMoroso;
		AdministrarDeuda[3] = dpString("Identificacion");
		AdministrarDeuda[4] = "NA";
		AdministrarDeuda[5] = "NA";
		if (sAccion.equals("setear"))
		{
			AdministrarDeuda[4] = dpString("CtaDestinoId");
			AdministrarDeuda[5] = dpString("CtaDestinoTipo");
		}
		
		callScript("Scripts.Comun.SistemasExternos.REMO_AdministrarMorosidad", AdministrarDeuda);
		
		if (AdministrarDeuda[0].toString().equals("NOK")) {	
			MensError[0] = "Administrar REMO falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

