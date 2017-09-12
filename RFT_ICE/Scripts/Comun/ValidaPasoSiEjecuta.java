package Scripts.Comun;
import resources.Scripts.Comun.ValidaPasoSiEjecutaHelper;

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
 * Description   : Valida que el paso del caso de prueba se deba ejecutar en el 
 * ambiente indicado, para la ejecución normal del paso o la reejeción, para un conjunto de datos, etc.
 * 
 * Script Name   : <b>ValidaPasoSiEjecuta</b>
 * 
 * @Param 0) Resultado (true / false) 1) Ambiente 2) paso-desde 3) paso a ejecutar 
 * 4)sCasoEjecuta 5)sCasoReEjec 6)sCasoCond 7) Caso_CD_T ej CPxx_CDy_Tj 8) Param1
 * 
 * Ej res QA 2 6 true 4-5
 * 
 * Lista de valores del parametro sCasoEjecuta
 * true
 * false
 * soloqa solo preqa solodesa
 * noqa nopreqa nodesa
 * 
 * Lista de valores del parametro sCasoReEjec
 * siempre
 * nunca
 * <nropaso (se re-ejecuta si el paso a ejecutar es menor que ese paso
 * >nropaso (se re-ejecuta si el paso a ejecutar es mayor que ese paso
 * 
 * 
 * @since  2016/03/03 -- modificado 09/Sept/2016 para agregar nuevas opciones 
 * @author SS
 */
public class ValidaPasoSiEjecuta extends ValidaPasoSiEjecutaHelper
{
	public void testMain(Object[] argu) 
	{
		// se comenta para no generar demasiada info en la consola
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString()); 

		int iReEjectDesde = 0 ;
		int iReEjectHasta = 1000;
		//---------------------------------------------------------------
		// Lecutra de prametros
		//---------------------------------------------------------------
		String sAmbiente =  argu[1].toString().toUpperCase();
		int iNroPasoDesde =  (Integer.parseInt(argu[2].toString()));
		int iNroPaso =  (Integer.parseInt(argu[3].toString()));
		String sParam = argu[4].toString(); // sCasoEjecuta
		String sParamReEjec = argu[5].toString(); // sCasoReEjec
		int iGuion = sParamReEjec.indexOf("-"); // Ej 5-18
		if (iGuion != -1) 
		{
			iReEjectHasta =  (Integer.parseInt(sParamReEjec.substring(iGuion+1)));
 			iReEjectDesde = (Integer.parseInt(sParamReEjec.substring(0,iGuion)));

		}
		System.out.println("Desde:" + iReEjectDesde + " Hasta:" + iReEjectHasta);

		String sResultado = "true";
		if (sParam.equals("false")) {
			sResultado = "false";			
		} else
		{
			//----------------------------------------------------------
			// Análsis si el paso ejecuta en un determinado ambiente
			//----------------------------------------------------------

			switch (sAmbiente) {
			case "DESA":
				if (sParam.equals("soloqa")|| sParam.equals("solopreqa") ||
						sParam.equals("nodesa"))
				{
					sResultado = "false";
				}
				break;
			case "PREQA":
				if (sParam.equals("soloqa")|| sParam.equals("solodesa") ||
						sParam.equals("nopreqa"))
				{
					sResultado = "false";
				}
				break;
			case "	QA":
				if (sParam.equals("solopreqa")|| sParam.equals("solodesa") ||
						sParam.equals("noqa"))
				{
					sResultado = "false";
				}

				break;
			default:  
				break;
			}  

			if (sResultado.equals("true")  )
			{	
				//----------------------------------------------------------
				// Análsis si el paso es condicionante o condicionado
				//----------------------------------------------------------
				
//				System.out.println("Se analiza si el paso es condicionante o condicionado");			
//				if (argu[6].toString().equals("Condicionado"))
//				{
//					if (getEjecCond() && getEjecCondResult()) 
//						sResultado="true";
//					else 
//						sResultado="false";
//				}
//				if (argu[6].toString().equals("DP"))
//				{
//					dpReset();
//					while (!dpDone() &&  !(dpString("NumeroCaso").equals(argu[7].toString()) && 
//							dpString("Ambiente").equals(sAmbiente))) 
//					{
//						dpNext(); 
//					} 	
//					int i = Integer.parseInt(argu[8].toString());
//					if (dpString("Ejecutar"+i).equals("true")) sResultado="true";
//					else sResultado = "false";
//				}
			}

		if (sResultado.equals("true")  ) // se elimina && (iNroPasoDesde > 1) porque se deben evitar pasos cuando paso desde = 1 como el buscar pedido
			{
				//----------------------------------------------------------
				// Análsis si el paso ejecuta en una re-ejecución
				//----------------------------------------------------------
				System.out.println("Se analiza si el paso ejecuta en una re-ejecución");
				System.out.println("GetLogin:" + getLoginIf());
				System.out.println("Paso:" + iNroPaso + " Paso desde:" + iNroPasoDesde);
				System.out.println("Desde Inicio?:" + getRe_EjecDesdeIni()); // 2/2/2017 
				
				// Si el parametro del dp es false, evalua si el loginif se ejecuto bien
				// y de asi serlo ejecuta a partir del PasoDesde sin hacer ningun paso de la 
				// re-ejecución.
				// Si el dp es true reejecuta los pasos de la reejecucion incluyendo el login
				// Si el login if dio ok ejecuta los siguientes pasos de la re-ejecucion 
	
				if (!getRe_EjecDesdeIni() && 
						getLoginIf().equals("OK") && (iNroPaso  < iNroPasoDesde ) )
				{	
					System.out.println("GetLogin OK y paso menor que paso Desde");
					sResultado = "false";
				} else // Se re-ejecuta desde el inicio del flujo 
				{
					switch (sParamReEjec) {
					case "siempre":
						System.out.println("Se analiza si el Siempre");	
						sResultado = "true";
						break;
					case "nunca":
						System.out.println("Se analiza si el nunca");		
						if (iNroPaso >= iNroPasoDesde) sResultado = "true";
						else sResultado = "false";
						break;
					default:  
						System.out.println("Se analiza el rango");					
						if (iNroPasoDesde >= iReEjectDesde &&   
						iNroPasoDesde <= iReEjectHasta)
							sResultado = "true";
						else sResultado = "false";
						break;
					} 
				} 				
			} 
		}
		argu[0] = sResultado;

		//Se comenta para no generar demasiada info en la consola
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

