package Scripts.CasosPrueba;
import libreria.utileria.Tipo;
import resources.Scripts.CasosPrueba.CasosPruebaHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
/**
 * Descripcion : Ejecuta en el ambiente indicado desde paso Desde incluido, 
 * Si no se especifica paso desde utiliza los parametros de data pool. 
 * Para los pasos posteriores al paso desde se rige por los parametros del dp
 * Argumentos: 0)Usuario 1)Clave 2) Ambiente DESA/QA/PREQA 
 * 3) Nombre del caso (CPO3,CP56)
 * 4) true se ejecuta el login cualquier otro valor no se ejecuta el login
 * 5) idem logout  
 * 6) Paso desde (opcional) 
 * 7) NroPedido  (opcional)
 * 8) NroServicio (opcional)
 * 9) NroCtaFact (opcional)
 * 10) NomListaEspecial (opcional)
 * ej: vsepulveda sepcrmdes82 DESA CP13 true
 * ej: vsepulveda sepcrmdes82 DESA CP13 false false 4 1-1790719510 10042915
 * SS Nov 2015
 */
public class CasosPrueba extends CasosPruebaHelper
{
	public void testMain(Object[] args) 
	{

		try {
			String[] PasoFuncional;
			PasoFuncional = new String[4];

			String[] InformarPaso;
			InformarPaso = new String[1];

			String sCaso = "CP";
			/** 	
			 * Determina el caso de venta a ejecutar
			 */		
			if (args.length >= 4 ) { 
				sCaso = args[3].toString();}
			/**
			 * Determina si se debe ejecutar el login
			 */
			if ((args.length) >= 5 && (args[4].toString().equals("true"))) { 
				callScript("Scripts.Comun.Login",args);}
		
			/**
			 * Determina si se deben saltear pasos
			 */
			int iPasoDesde = 1;
	
			if ((args.length >= 7) && (Integer.parseInt(args[6].toString()) > 0)) { 
				iPasoDesde =  (Integer.parseInt(args[6].toString())); }

			if (args.length >= 8 && !args[7].toString().equals("NA")) { 
				setNroPedido(args[7].toString());
				infoPaso(sCaso, Tipo.DATO,"NroPedido",getNroPedido());}

			if (args.length >= 9 && !args[8].toString().equals("NA")) { 
				setNroServicio(args[8].toString()); 
				infoPaso(sCaso, Tipo.DATO,"NroServicio",getNroServicio());
				}

			if (args.length >= 10 && !args[9].toString().equals("NA")) { 
				setNroCtaFact(args[9].toString()); 
				infoPaso(sCaso, Tipo.DATO,"NroCtaFact",getNroCtaFact());
				}

			if (args.length >= 11 && !args[10].toString().equals("NA")) { 
				setNomListaEspecial(args[10].toString()); 
				infoPaso(sCaso, Tipo.DATO,"NomListaEspecial",getNomListaEspecial());
				}

			String sCasoPasos = sCaso + "Pasos" ;
			String sCasoParam = sCaso + "Param" ;
			String sCasoEjecuta = sCaso + "Ejecuta" ;
			String sCasoErrorStop = sCaso + "ErrorStop" ;

			/**
			 * Itera el data pools de pasos funcionales para determinar si se ejecuta o no
			 */

			// Mientras aun queden filas por recorrer 

			int currentDatapoolRow = 0; 
			int iNumeroPaso = 1; 

			dpReset();
			
			System. out.println(" ");
			System. out.println("----------------------------------------------------");
			System. out.println("Nombre paso a ejecutar " + dpString(sCasoPasos)); 
			System. out.println("Parametros del paso " + dpString(sCasoParam));
			System. out.println("Indicador de ejecucion el paso " + dpBoolean(sCasoEjecuta));
			System. out.println("----------------------------------------------------");
			
			while (!dpDone() && !(dpString(sCasoPasos).equals("FIN")) ) { 

				// Print the current datapool row number to the playback log 
				logInfo("Starting datapool row " + currentDatapoolRow); 

				System. out.println(" ");
				System. out.println("----------------------------------------------------");
				System. out.println("Nombre paso a ejecutar " + dpString(sCasoPasos)); 
				System. out.println("Parametros del paso " + dpString(sCasoParam));
				System. out.println("Indicador de ejecucion el paso " + dpBoolean(sCasoEjecuta));
				System. out.println("----------------------------------------------------");

				/**
				 * Por cada paso informa estado de ejecución NE/OK
				 */
				if (dpBoolean(sCasoEjecuta)){
					if (iNumeroPaso >= iPasoDesde) {
						System. out.println("El paso se ejecuta ");
						PasoFuncional [0] = sCaso;
						String sCurrentTestCaseStep = Integer.toString(iNumeroPaso);
						InformarPaso [0] = sCurrentTestCaseStep;
						callScript("Scripts.Comun.InfomarPaso", InformarPaso);
						PasoFuncional [1] = dpString(sCasoParam);
						PasoFuncional[2] = args[2].toString();
						PasoFuncional [3] = dpString(sCasoErrorStop);
						logInfo (sCasoPasos + PasoFuncional);
						callScript("Scripts.PasosFunc." + dpString(sCasoPasos),PasoFuncional);
						infoPaso(sCaso, Tipo.PASO,"Paso" + iNumeroPaso, "OK");
					}
					else {
						infoPaso(sCaso, Tipo.PASO,"Paso" + iNumeroPaso, "NE");
					}
				} else {
					infoPaso(sCaso, Tipo.PASO,"Paso" + iNumeroPaso, "NE");}
					iNumeroPaso++;
					currentDatapoolRow++; 
					dpNext(); 
				} // end del while
			/**
			 * Determina si se debe ejecutar el logout
			 */
			if (args.length >= 6 && args[5].toString().equals("true")) { 
				System. out.println("Se llama al script logout ");
				//callScript("Scripts.PasosFunc.Logout", args);
				callScript("Scripts.Comun.Logout",args);

			}
		} catch (RationalTestException e) {
			e.printStackTrace();
		}
	}
}