package Scripts.CasosPrueba;
import libreria.utileria.Tipo;
import resources.Scripts.CasosPrueba.CasosPruebaHelper;
import sun.org.mozilla.javascript.internal.ast.SwitchCase;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
//import com.rational.test.ft.script.DatapoolScriptSupport;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;

import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
/**
 * Descripcion : Script que lanza el framework. 
 * Recibe el caso / modulo a ejecutar y se encarga de dicha ejecución paso a paso.
 * Argumentos: 
 * 0) Usuario (DE entrada - Obligatorio)
 * 1) Clave (De entrada - Obligatorio) 
 * 2) Ambiente (De entrada - Obligatorio - DESA/QA/PREQA) 
 * 3) Nombre del caso (De entrada - Obligatorio - ej: CPO3/CP56)
 * 4) Nombre del Conjunto de Datos (CD)  (De entrada - Obligatorio - CD1 CD2 CD3 etc)
 * 5) Paso desde (De entrada - Obligatorio - Indica el paso, >1 para las reejecuciones x falla). Si se usa la letra A indica
 * reinicio amigable es decir que automaticamente buscara el paso desde
 * 6) Paso hasta (De entrada - Obligatorio - Indica el paso haa
 * Ej JVALERIO1 jvalerio1 QA CP76 CD1 1 13 NA NA simulacion / NA  (ff123456 ff123456)
 * Ej  JVALERIO1 jvalerio1 QA CP14 CD1 1 100 NA NA NA soin_sferx22 ff123456
 * Ej  JVALERIO1 jvalerio1 QA CP09 CD1 1 100 NA NA NA  // soin_sferx22 ff123456
 * Ej  JVALERIO1 jvalerio1 QA GRAL CD2 A 100 NA NA NA  // soin_sferx22 ff123456
 * soin_sferx22 ff123456 QA CP32 CD1 A 99 fragar fgarcia21 NA  
 * JVALERIO1 jvalerio1 QA CP20 CD1 A 99 soin_sferx22 ff123456 NA  
 *  soin_sferx22 ff123456 QA CP26 CD1 A 101 JVALERIO1 jvalerio1 NA  
 *  soin_sferx22 ff123456 QA CP26 CD1 A 999 JVALERIO1 jvalerio1 NA
 *  
 *  ult modif 19/04/2017 se agrega funcionalidad para multimodulo
 *  El parametro paso desde (Param5) permitira opcionalmente agregar el 1er paso del modulo de la siguiente forma A+1erPasoModulo
 *  Última modificación VC 20170517 Se añade funcionabilidad de inicio diferido para ejecuciones salteando pasos
 *  
 *  soin_sferx22 ff123456 QA CP26 CD1 A+157 999 JVALERIO1 jvalerio1 NA
 *  
 *  soin_sferx22 ff123456 QA CPPC CD1 A 999 
 */
public class CasosPrueba extends CasosPruebaHelper	
{
	public void testMain(Object[] args) throws RationalTestException, FileNotFoundException
	{
		try {
			ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
			
			String[] DPPasos;
			DPPasos = new String[2];
			// Param 0) Resultado  OK / NOK 1) CP (Ej CP14)	
			
			String[] PasoFuncional;
			PasoFuncional = new String[5];
			// param 0)CPnn_CDx param1 QA error_stop nroPaso
			
			Boolean bNoAplica = false; // para identificar en PC que no aplica un paso

			String[] PasoEjecuta;
			PasoEjecuta = new String[9];
			// Param 0) Resultado (true / false) 1) Ambiente 2) paso desde 3) paso a ejecutar 
			// 4)sCasoEjecuta 5)sCasoReEjec 6)sCasoCond 7) sCaso_CD_T // CPxx_CDy_Tj 8) Param1

			String[] InformarPaso;
			InformarPaso = new String[1];
			
			String[] LeerParametria;
			LeerParametria = new String[1];

			String[] LeerDatosEntrada;
			LeerDatosEntrada =new String[4];
			// Datos de entrada 0)OK/NOK 1) Nro CP 2) Ambiente 3)Opción (Dato o Todo) 

			String[] LeerDatosSalida;
			LeerDatosSalida =new String[3];
			// Datos salida     0)OK/NOK 1) Nro CP 2) Ambiente 
			
			String[] LeerDatosSalidaTramite;
			LeerDatosSalidaTramite =new String[4];
			// Datos salida     0)OK/NOK 1) Nro CP 2) Ambiente 3) Tramite
			
			// Reset de variables condicionantes ss 18/5/2017
			String[] ResetCondicionantes;
			ResetCondicionantes =new String[4];
			//     OK/NOK 1) ambiente 2) CPnn_CDy  
			
			// ss logica de condicionantes y condicionados
			String[] EvaluarExpr;
			EvaluarExpr =new String[4];
			// 0) CPnn_CD1 1) Expresion a evaluar 2) ambiente 3) Resultado
			
			String[] GuardarCond;
			GuardarCond =new String[4];
			// 0) CPnn_CD1 1) Variable a evaluar 2) ambiente 3) Valor

			String[] GrabarDatos;
			GrabarDatos =new String[5];
			// 0) OK/NOK 1) Nro CP 2) Ambiente 3) inicializar o Nombre variable 4)valor variable
	
			String[] Consola;
			Consola =new String[3];
			//0) OK/NOK 1) NroCaso 2) QA
			 
			String[] MensError;
			MensError = new String[4];
			
			String sCaso =null;
			String sPasoDesde ;
			int iPasoDesde = 1 ;
			int iPasoHasta ;
			String sCaso_CD;
			String sCaso_CD_T;
			String sNombrePaso = null; // nombre del paso del reporte
			String sTramite = "1";
			setUltimoTramite(sTramite); // Se modifica por Ultimo tramite para unificar NroTramite e UltimoTramite
			setNroServicio("NA");
			setLoginIf("NOK");
			String sScriptName=getScriptName().toString(); // 22/11/2016
			
			System.out.println("Se lee data pool de parametria");
			callScript("Scripts.Comun.LeerDPParametria",LeerParametria);

			setUsuario(args[0].toString()); // Agregado para la busqueda de CM
			setClave(args[1].toString()); // Agregado para el manejo de modulos
			setNomAmbiente(args[2].toString());
			sCaso = args[3].toString(); // CPnn
			sCaso_CD= sCaso + "_" + args[4].toString(); // CPnn_CDi
			setNroCaso(sCaso_CD);// CPnn_CDi
			//setNroMod(iNroMod);
			// Analisis de multi mudulo ss 19/04/2017
			int iModulo = args[5].toString().indexOf("+");
			System.out.println("Ubicacion del +" + iModulo);
		
			int iPrimerPasoModulo = 1;
			setNroPasoIniModulo(iPrimerPasoModulo);
			if (iModulo >=1) 
			{
				// tomar el primer paso modulo
				iPrimerPasoModulo = Integer.valueOf(args[5].toString().substring(iModulo+1));
				setNroPasoIniModulo(iPrimerPasoModulo);
				sPasoDesde=args[5].toString().substring(0, iModulo);
				System.out.println("Paso desde " + sPasoDesde + " Paso ini Mod " +iPrimerPasoModulo);
			} else 
				sPasoDesde = args[5].toString();
			
			if (!sPasoDesde.toLowerCase().equals("a")) iPasoDesde =  (Integer.parseInt(sPasoDesde));
			iPasoHasta = (Integer.parseInt(args[6].toString()));
			setNroPasoFinModulo(iPasoHasta); // ss 18/4/2017 agregado para el manejo de modulos
			setNroPasoDesde(sPasoDesde);
			String sModo = "Real";
			if (args.length >= 10 && args[9].toString().toLowerCase().equals("simulacion")) 
				sModo = "simulacion";
			
			boolean bSimulacion = false;
			if (sModo.equals("simulacion")) bSimulacion = true;
			setModo(sModo);	
			setTipoError("NA"); // Por defecto se coloca el Tipo de Error en NA para que solo un set se realice para el error. 
			
			System.out.println("Modo de Ejecución:" + sModo);

			// Usuario y clave alternativos
			String sUsuarioALT = "NA";
			String sClaveALT = "NA";
			if (args.length >= 8 && !args[7].toString().equals("NA")) { 
				sUsuarioALT = args[7].toString();
				infoPaso(sScriptName, Tipo.DATO,"Usuario Alternativo ",sUsuarioALT);
				System. out.println("Usuario Alternativo "+sUsuarioALT);

				if (args.length >= 9 && !args[8].toString().equals("NA")) { 
					sClaveALT = args[8].toString();
					infoPaso(sScriptName, Tipo.DATO,"Clave Alternativa ",sClaveALT);
					System. out.println("Clave Alternativa "+sClaveALT);
					setUsuarioAlt(sUsuarioALT); 
					setClaveAlt(sClaveALT); }}

			sCaso_CD_T=sCaso_CD + "_T"+getUltimoTramite(); // Se modifica de nrotramite a este valor de variable
			System.out.println("------------------------------------------------------------");
			System.out.println("Inicio de caso de Prueba y Conjunto de Datos:" + getNroCaso());
			System.out.println("------------------------------------------------------------");
			
			
			Consola[1]=sCaso_CD;
			Consola[2]=getNomAmbiente();
			if (getConsolaaFile()) 	callScript("Scripts.Comun.ConsolaaFile", Consola);

			DPPasos[1]=args[3].toString();
			if (getActDPPasos())
				callScript("Scripts.Comun.IngresarDataPoolPasos", DPPasos);
			
			boolean bActualiza = true;
			// ver si es conveniente no actualizar para un segundo modulo

			if (bActualiza && !bSimulacion) { // Ver si actualizar o no los datos de entrada en simulacion 
				System.out.println("----------------------------------");
				System.out.println("Se actualizan los datos de entrada");
				System.out.println("----------------------------------");

				LeerDatosEntrada[1] = sCaso_CD; // CPnn_CDi
				LeerDatosEntrada[2] = args[2].toString();
				LeerDatosEntrada[3] = "Todo";
				callScript("Scripts.Comun.LeerDatosEntrada",LeerDatosEntrada);
				if  (!LeerDatosEntrada[0].toString().equals("OK")){
					MensError[0] = "Actualizar datos entrada falló";
					MensError[1] = args[3].toString();
					MensError[2] = args[0].toString();
					MensError[3] = getScriptName( );
					callScript("Scripts.Comun.TerminarCasoError", MensError);
				} else infoPaso(sScriptName, Tipo.PASO,"LeerDatosEntrada", "OK");
			} else infoPaso(sScriptName, Tipo.PASO,"LeerDatosEntrada", "NE");

			/** Lectura de parametros desde el data pools */
			// Es una re-ejecución
			if (!sPasoDesde.equals("1")) 
			{ // Es A o mayor que 1. 
				System.out.println("----------------------------------");
				System.out.println("Se lee el último trámite y paso");
				System.out.println("----------------------------------");
				LeerDatosSalida[1] = sCaso_CD; // CPnn_CDi
				LeerDatosSalida[2] = args[2].toString();
				callScript("Scripts.Comun.LeerDatoSalidaUltPasoEjec",LeerDatosSalida);
				if  (!LeerDatosSalida[0].toString().equals("OK")){
					MensError[0] = "Leer último trámite falló";
					MensError[1] = args[3].toString();
					MensError[2] = args[0].toString();
					MensError[3] = getScriptName( );
					callScript("Scripts.Comun.TerminarCasoError", MensError);
				} else infoPaso(sScriptName, Tipo.PASO,"LeerDatoSalidaUltPasoEjec", "OK");

				if (sPasoDesde.toLowerCase().equals("a")) 
				{
					iPasoDesde =  (Integer.parseInt(getUltimoPasoOk()));
					iPasoDesde = iPasoDesde + 1;
					sPasoDesde = String.valueOf(iPasoDesde);
					setNroPasoDesde(sPasoDesde);
					if (iPasoDesde < iPrimerPasoModulo) // ss 19/04/2017
					{
						System.out.println("Se reinicia en primer paso del modulo");
						infoPaso("Inicio", Tipo.ERROR,"Se reinicia en primer paso del modulo", " ");
						iPasoDesde=iPrimerPasoModulo;
						sPasoDesde = String.valueOf(iPasoDesde);
						setNroPasoDesde(sPasoDesde);
					}
					System.out.println("Reinicio automatico en paso :" + iPasoDesde);
				}
			}
			
			// Análisis de PasodeInicio mayor a 1 Inicio diferido
			// VC 20170517 Se agrega lógica para que el número de paso inicial se tome del dp de datos entrada, si la variable no existe se inicia según el flujo normal
			String[]pasoInicial;
			pasoInicial =new String[4];
			pasoInicial[1] = sCaso_CD + "_T"+getUltimoTramite(); //sCaso_CD; // CPnn_CDi
			pasoInicial[2] = getNomAmbiente();
			pasoInicial[3] = "NA";
								
			callScript("Scripts.Comun.LeerPasoInicial", pasoInicial);
			
			boolean desdeInicio = false;
			if(!pasoInicial[3].equals("NA") && !sPasoDesde.equals("1")){ // Si es NA o 1 no se tiene que afectar el flujo anterior
				int pasoDP = Integer.parseInt(pasoInicial[3]);
				if(iPasoDesde <= pasoDP){ // Si el paso es menor al deseado significa que hay que saltar pasos y es una ejecución inicial, por tanto cambia el paso desde
					desdeInicio = true;
					iPasoDesde=pasoDP;
					sPasoDesde = String.valueOf(iPasoDesde);
					setNroPasoDesde(sPasoDesde);
				} // Si el paso es mayor significa que es una reejecución, por lo que no se debe cambiar el paso de incio
			}
			
			if (sPasoDesde.equals("1") || desdeInicio) // VC 20170517 Se agrega la validación desdeInicio para inicio diferido
			{ // El paso es 1
				// No es una re-ejecución sino una ejecución que comienza en 1
				//if (!bSimulacion) - Se inicializa igual si es simulacion
				System.out.println("----------------------------------");
				System.out.println("Se inician los datos de salida");
				System.out.println("----------------------------------");
				GrabarDatos[1] = sCaso_CD; // CPnn_CDi
				GrabarDatos[2] = args[2].toString();
				GrabarDatos[3] = "inicializar";
				callScript("Scripts.Comun.GrabarDatosSalida",GrabarDatos);
				if  (!GrabarDatos[0].toString().equals("OK")){
					MensError[0] = "Grabar datos salida falló";
					//MensError[0] = "xDefecto";
					MensError[1] = args[3].toString();
					MensError[2] = args[0].toString();
					MensError[3] = getScriptName( );
					callScript("Scripts.Comun.TerminarCasoError", MensError);
				} else infoPaso(sScriptName, Tipo.PASO,"GrabarDatosSalida", "OK");
				// ss 18-5-2017 reset de datos condicionantes
				System.out.println("----------------------------------------");
				System.out.println("Se inicia el data pool de condicionantes");
				System.out.println("----------------------------------------");
				ResetCondicionantes[1]=sCaso_CD;
				ResetCondicionantes[2]=args[2].toString();
				callScript("Scripts.Comun.ResetVariablesCondicionantes",ResetCondicionantes);
				if  (!ResetCondicionantes[0].toString().equals("OK")){
					MensError[0] = "Iniciar datos condicionantes falló";
					//MensError[0] = "xDefecto";
					MensError[1] = args[3].toString();
					MensError[2] = args[0].toString();
					MensError[3] = getScriptName( );
					callScript("Scripts.Comun.TerminarCasoError", MensError);
				} else infoPaso(sScriptName, Tipo.PASO,"ResetVariablesCondicionantes", "OK");
			}
			else 
			{ // Es  mayor que 1. 
			// Una vez que se sabe el tramite se lee el conjunto de datos de ese trámite
			System.out.println("---------------------------------------");
			System.out.println("Se leen los datos de salida del tramite");
			System.out.println("---------------------------------------");
			
			// Se inicializan los Nros de Pedido
			setNroPedido("NA");
			setNroPedidoAnt("NA");
			setNroPedidoAntAnt("NA");
		
			LeerDatosSalidaTramite[1] = sCaso_CD; // CPnn_CDi
			LeerDatosSalidaTramite[2] = args[2].toString();
			LeerDatosSalidaTramite[3] = "T" + getUltimoTramite();
			sTramite = getUltimoTramite();
			callScript("Scripts.Comun.LeerDatosSalida",LeerDatosSalidaTramite);
			if  (!LeerDatosSalidaTramite[0].toString().equals("OK")){
				MensError[0] = "Leer datos de salida del trámite falló";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			} 
			infoPaso(sScriptName, Tipo.PASO,"LeerDatosSalida", "OK");
			sCaso_CD_T=sCaso_CD + "_T"+getUltimoTramite(); // Modif 4/11/16 (No se actualizaba esta variable antes) // Se modifica de nrotramite a este valor de variable
		} 
			String sCasoPasos = sCaso + "Pasos" ; // CPnnPasos
			String sCasoParam = sCaso + "Param" ;
			String sCasoEjecuta = sCaso + "Ejecuta" ;
			String sCasoErrorStop = sCaso + "ErrorStop" ;
			String sCasoReEjec = sCaso + "ReEjec";
			String sCasoCond = sCaso + "Cond";

			/**
			 * Itera el data pools de pasos funcionales para determinar si se ejecuta o no
			 */

			// Mientras aun queden filas por recorrer 
			int currentDatapoolRow = 0; 
			int iNumeroPaso = 1;
			setNroPaso(iNumeroPaso);
			dpReset();
			while (!dpDone() && !(dpString(sCasoPasos).equals("FIN")) 
					&& !dpString(sCasoEjecuta).toLowerCase().equals("terminar")
					&& iNumeroPaso<=iPasoHasta ) // agregado para manejo del paso hasta
			{ 
				// Print the current datapool row number to the playback log 
				logInfo("Starting datapool row " + currentDatapoolRow); 

				setResultadoPaso("ND");
				/**
				 * Por cada paso informa estado de ejecución NE/OK o NA = 
				 * NE= No ejecutó
				 * NA= No aplica
				 * ND= No Definido
				 * La variable global resultadoPaso puede tener un valor específico que se pueda usar para 
				 * luego comparar en la expresión condicionante contra ese valor
				 */

				// Se analiza si el paso se debe ejecutar en el ambiente
				// solo si no es el modulo actual
				boolean bPasoEjecuta = false;
				String Cond=dpString(sCasoCond); // ss logica de condicionados y condicionantes
				String sCurrentTestCaseStep = Integer.toString(iNumeroPaso); // se adelanta para paso condicional
				if (iPasoDesde >= getNroPasoIniModulo()) // ss 20/4/2017
				{
					PasoEjecuta[1] = args[2].toString(); // ambiente
					PasoEjecuta[2] = Integer.toString(iPasoDesde); // paso desde
					PasoEjecuta[3] = Integer.toString(iNumeroPaso); // paso a ejecutar
					PasoEjecuta[4] = dpString(sCasoEjecuta).toLowerCase();
					PasoEjecuta[5] = dpString(sCasoReEjec).toLowerCase();
					PasoEjecuta[6] = dpString(sCasoCond).toLowerCase(); // Ejec Cond (no se usa aqui)
					PasoEjecuta[7] = sCaso_CD_T;
					PasoEjecuta[8] = dpString(sCasoParam);
					System.out.println("Script a validar:" + dpString(sCasoPasos));
					callScript("Scripts.Comun.ValidaPasoSiEjecuta",PasoEjecuta);
					bPasoEjecuta = Boolean.parseBoolean(PasoEjecuta[0].toString());
					// ------------------------------------------
					// ss se agrega evaluación de condicionantes 
					// ------------------------------------------
					if (bPasoEjecuta && !Cond.equals("NA"))
					{
						if(Cond.indexOf("Condicionado")>=0)
						{
							bNoAplica = false; // para identificar en PC que no aplica un paso
							EvaluarExpr[0] = sCaso_CD ; // CPnn_CDi
							EvaluarExpr[1] = dpString(sCasoCond);
							EvaluarExpr[2] = args[2].toString(); // ambiente
							System.out.println("Expresion a evaluar" + dpString(sCasoCond));
							callScript("Scripts.PasosFunc.fEvaluarExpresionCondicionante",EvaluarExpr);
							bPasoEjecuta = Boolean.parseBoolean(EvaluarExpr[3].toString());
							System.out.println("Expresion a evaluar=" + bPasoEjecuta);
							if (!bPasoEjecuta) bNoAplica=true;
							infoPaso(sScriptName, Tipo.PASO,"fEvaluarExpresionCondicionante", "OK");
							infoPaso(dpString(sCasoPasos), Tipo.DATO,"Expresion" ,String.valueOf(bPasoEjecuta));
						}
					}
				}  

				// sNombrePaso="Paso"+ iNumeroPaso + ":" + dpString(sCasoPasos);
				sNombrePaso=dpString(sCasoPasos);

				if (bPasoEjecuta)
				{
					if (dpString(sCasoCond).equals("Condicionante")) setEjecCond(true);
					// 0) CPnn_CDi_Tj 1) Param 2) AMbiente 3) ErrorStop 4) Paso
					PasoFuncional [0] = sCaso_CD + "_T" + sTramite; // CPnn_CDi_Tj // La variable sTramite tiene valor correcto
					InformarPaso [0] = sCurrentTestCaseStep;
					callScript("Scripts.Comun.InfomarPaso", InformarPaso);
					PasoFuncional [1] = dpString(sCasoParam);
					PasoFuncional[2] = args[2].toString(); // ambiente
					PasoFuncional [3] = dpString(sCasoErrorStop).toLowerCase(); // error para o no ejecucion
					setNroPaso(iNumeroPaso);
					PasoFuncional [4] = "Paso"+Integer.toString(iNumeroPaso ); // Se agrega nro de paso
					switch (dpString(sCasoPasos)) {
					//-------------------------------------------------------------------------
					// Nuevo Tramite
					//-------------------------------------------------------------------------
					case "fNuevoTramite":
						if (iNumeroPaso >= iPasoDesde ) // si es una re-ejecución solo graba si avanzó
						{
							sTramite = dpString(sCasoParam); // Es el nuevo tramite
							setUltimoTramite(sTramite); // Se modifica para unificar NroTramite y UltimoTramite 
							sCaso_CD_T=sCaso_CD + "_T"+getUltimoTramite(); // CPxx_CDn_Ti i=NuevoTramite // // Se modifica de nrotramite a este valor de variable

							GrabarDatos[1]=sCaso_CD; // CPnn_CDi
							GrabarDatos[2]=args[2].toString(); // nro ambiente
							GrabarDatos[3]="UltTramite"; // Nombre variable
							GrabarDatos[4]=sTramite; // Valor de la variable (Se graba solo el digito)
							callScript("Scripts.Comun.GrabarDatosSalida",GrabarDatos);
							
							setUltimoPasoOk(Integer.toString(iNumeroPaso ));
							GrabarDatos[1]=sCaso_CD; // CPnn_CDi
							GrabarDatos[2]=args[2].toString(); // nro ambiente
							GrabarDatos[3]="UltPasoOK"; // Nombre variable
							GrabarDatos[4]=getUltimoPasoOk(); // Valor de la variable (Se graba solo el digito)
							callScript("Scripts.Comun.GrabarDatosSalida",GrabarDatos);

							GrabarDatos[3]=sTramite; // Ultimo Tramite
							GrabarDatos[4]=getUltimoPasoOk(); // Ultimo Paso
							callScript("Scripts.Comun.GrabarDatoSalidaUltPasoEjec",GrabarDatos);

							
							// El nro de pedido anterior y anteanterior y el servicio se deben heredar
							// El nroservicio ya esta seteado, se lo guarda por si hay caidas
							if (!getUltimoTramite().equals("2") && !getNroPedidoAnt().equals("NA")) // del 3 en adelante // // Se modifica de nrotramite a este valor de variable
							{
								setNroPedidoAntAnt(getNroPedidoAnt());
								GrabarDatos[1]=sCaso_CD; // CPnn_CDi
								GrabarDatos[2]=args[2].toString(); // nro ambiente
								GrabarDatos[3]="T"+ getUltimoTramite()+"_NroPedidoAntAnt"; // Tx_Nombre variable  // Se modifica de nrotramite a este valor de variable
								GrabarDatos[4]=getNroPedidoAntAnt(); // Se guarda el pedido del tramite anterior
								callScript("Scripts.Comun.GrabarDatosSalida",GrabarDatos);								
							}
							if (!getNroPedido().equals("NA")) // Se agrego nuevo 28/10/16 el if
							{
								setNroPedidoAnt(getNroPedido());
								GrabarDatos[1]=sCaso_CD; // CPnn_CDi
								GrabarDatos[2]=args[2].toString(); // nro ambiente
								GrabarDatos[3]="T"+ getUltimoTramite()+"_NroPedidoAnt"; // Tx_Nombre variable // Se modifica de nrotramite a este valor de variable 
								GrabarDatos[4]=getNroPedidoAnt(); // Se guarda el pedido del tramite anterior
								callScript("Scripts.Comun.GrabarDatosSalida",GrabarDatos);
								setNroPedido("NA"); // Se agrego nuevo 27/10/16 el set = NA
							}
							if (!getNroServicio().equals("NA")) // Se agrego nuevo 4/11/16 el if
							{
								GrabarDatos[1]=sCaso_CD; // CPnn_CDi
								GrabarDatos[2]=args[2].toString(); // nro ambiente
								GrabarDatos[3]="T"+getUltimoTramite()+"_NroServicio"; // Tx_Nombre variable  // Se modifica de nrotramite a este valor de variable
								GrabarDatos[4]=getNroServicio(); // Se guarda el servicio del tramite anterior
								callScript("Scripts.Comun.GrabarDatosSalida",GrabarDatos);
							}
						}				
						break;
//					case "fLoginIf":
//						// Siempre se ejecuta -- en modo simulacion o no simulacion
//							callScript("Scripts.PasosFunc." + dpString(sCasoPasos),PasoFuncional);
//						break;
					//-------------------------------------------------------------------------
					// Ejecución de los pasos
					//-------------------------------------------------------------------------
					default:
						if (!sModo.equals("simulacion"))
						{
							callScript("Scripts.PasosFunc." + dpString(sCasoPasos),PasoFuncional);
						}
						else 	
						{
							System.out.println("Script Name = " + dpString(sCasoPasos));
							//callScript("Scripts.PasosFunc." + "fSimulacion",PasoFuncional);
						}
						if (iNumeroPaso >= iPasoDesde ) // si es una re-ejecución solo graba si avanzó
						{
							if (!getEjecCond()) // Si es un paso condicionante no se graba
							{
								setUltimoPasoOk(Integer.toString(iNumeroPaso ));
								GrabarDatos[1]=sCaso_CD; // CPnn_CDi
								GrabarDatos[2]=args[2].toString(); // nro ambiente
								GrabarDatos[3]="UltPasoOK"; // Nombre variable
								GrabarDatos[4]=getUltimoPasoOk(); // Valor de la variable
								callScript("Scripts.Comun.GrabarDatosSalida",GrabarDatos);

								GrabarDatos[3]="NA"; // No actualizar
								GrabarDatos[4]=getUltimoPasoOk(); // Ultimo Paso OK
								callScript("Scripts.Comun.GrabarDatoSalidaUltPasoEjec",GrabarDatos);
							}
						}
							break;
					} // End del swich
					infoPaso(sScriptName, Tipo.PASO,sNombrePaso, "OK");
					if (getResultadoPaso().equals("ND")) setResultadoPaso("OK"); // ss se agrega para la logica condicionada
					// si al paso no se le asignó un valor dentro del script del paso o no se le asignó NOK en 
					// terminarCasoError entonces el valor es OK. 
					// if (dpString(sCasoCond).equals("Condicionado")) setEjecCond(false); // Ss se reeemplaza la lógica anterior de paso condicionado por la nueva
				}
				else
				{
					if (iNumeroPaso>= getNroPasoIniModulo()) // ss 19/04/2017 si el paso no corresponde al modulo no se indica este comentaro
						if (bNoAplica) 
						{
							infoPaso(sScriptName, Tipo.PASO,sNombrePaso, "NA");
						setResultadoPaso("NA");
						}
						else 
						{
							infoPaso(sScriptName, Tipo.PASO,sNombrePaso, "NE");
							setResultadoPaso("NE");
						}
				}
				// ------------------------------------------
				// ss se agrega guardado de condicionantes 
				// ------------------------------------------
				if(Cond.indexOf("Condicionante")>=0 && !bNoAplica && bPasoEjecuta) // Solo si el paso aplica y fue ejecutado
				{
					Cond = Cond.replace("Condicionante:", "").trim()	;
					System.out.println("Se graban las variables condicionantes del paso");
					GuardarCond[0]=  sCaso_CD ; // CPnn_CDi
					GuardarCond[1] = Cond;
					GuardarCond[2] = args[2].toString(); // ambiente
					GuardarCond[3] = getResultadoPaso(); // resultado del paso condicionante
					callScript("Scripts.PasosFunc.fGuardarVariableCondicionante",GuardarCond);
					infoPaso(sScriptName, Tipo.PASO,"fGuardarVariableCondicionante", "OK");
					infoPaso(dpString(sCasoPasos), Tipo.DATO,Cond ,getResultadoPaso());
				}
				
				iNumeroPaso++;
				setNroPaso(iNumeroPaso);
				currentDatapoolRow++; 
				dpNext(); 
			} // end del while

			if (dpString(sCasoPasos).equals("FIN")) {
				infoPaso(sScriptName, Tipo.PASO,"Fin del caso", "OK");
				if (getTipoError().equals("NA")) setTipoError("FIN");
				if (getMensajeError().equals("NA")) setMensajeError("FIN del caso en paso " + iNumeroPaso);

				ImpreResultadoScript("Fin del caso", "OK");	} else {
					if (dpString(sCasoEjecuta).toLowerCase().equals("terminar")) {
						if (getTipoError().equals("NA")) setTipoError("Parada Voluntaria");
						if (getMensajeError().equals("NA")) setMensajeError("Parada voluntaria en paso " + iNumeroPaso);

						infoPaso(sScriptName, Tipo.PASO,sScriptName, "Se paro voluntariamente el caso usando el comando Terminar");
						ImpreResultadoScript("Caso parado voluntariamente", "OK");} 
					else 
						if (iNumeroPaso>iPasoHasta) {
							infoPaso(sScriptName, Tipo.PASO,sScriptName, "Parada voluntaria en paso hasta " + iPasoHasta);
							if (getTipoError().equals("NA")) setTipoError("Parada x Paso Hasta");
							if (getMensajeError().equals("NA")) setMensajeError("Parada en paso " + iNumeroPaso);

							ImpreResultadoScript("Caso parado en paso hasta", "OK");
						}
				}	

	} catch (RationalTestException e) {
		e.printStackTrace(); }
}
}

