package Scripts.CasosPrueba;
import java.awt.Toolkit;

import org.eclipse.hyades.edit.datapool.IDatapoolCell;
import org.eclipse.hyades.edit.datapool.IDatapoolEquivalenceClass;
import org.eclipse.hyades.edit.datapool.IDatapoolRecord;
import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;
import Scripts.Comun.GrabarDatoSalidaUltPasoEjec;
//import Scripts.PasosFunc.fDummy;
import Scripts.CasosPrueba.CasosPrueba;
import resources.Scripts.CasosPrueba.IterarCasoHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.datapool.DatapoolFactory;
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
 * Description   : Script utilizado para iterar un mismo caso con diferentes conjunto de 
 * datos iguales provenientes de un data pool
 * @author SS
 * @since  2016/12/14
 * Script Name   : <b>IterarCaso</b>
 * 0) Usuario (DE entrada - Obligatorio)
 * 1) Clave (De entrada - Obligatorio) 
 * 2) Ambiente (De entrada - Obligatorio - DESA/QA/PREQA) 
 * 3) Nombre del caso (De entrada - Obligatorio - ej: CPO3/CP56)
 * 4) Nombre del Conjunto de Datos (CD)  (De entrada - Obligatorio - CD1 CD2 CD3 etc)
 * 5) Paso desde (De entrada - Obligatorio - Indica el paso, >1 para las reejecuciones x falla)
 * 6) Paso hasta (De entrada - Obligatorio - Indica el paso hasta. FIN para todo). 
 * 7) Usuario alternativo (De entrada - Opcional) 
 * 8) Clave de usuario alternativo (De entrada - Opcional)
 * 9) Modo (si no viene nada es real si se coloca puede ser simulacion)
 * 10) Usuario VEP (No usado aun)
 * 11) Clave VEP (No usado aun)
 * SOIN_SSASTRE Pupina08125 PREQA CPVta CDX A 100 NA NA NA
 * SOIN_JVALERIO jv123456 PREQA CPVta CDX A 100 NA NA NA
 * SOIN_JVALERIO jv123456 PREQA CPCP CDX A 100 NA NA NA
 * SOIN_SSASTRE Pupina08125 PREQA CPCP CDX A 100 NA NA NA
 * soin_ssastre Pupina081213 DESA CPCP CDX A 100 NA NA NA
 */
public class IterarCaso extends IterarCasoHelper
{
	public void testMain(Object[] argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String sDato=null;
		//String sDatoAReemp=null;
		String sIterable=null;
		boolean bEncontrado=false;
		boolean bError=false;
		String sPrueba=null;
		//String output;
		IDatapoolCell cellValue=null;
		// Estos parametros solo se usan con fDummy
		//String[] Parametros;
		//Parametros = new String[5];
		// Parámetros: 0) nro caso CPnn_CDj  1) NA 2) ambiente 3) para ante error true / false 4) Nro paso Pasoi
		//Parametros[0]=argu[3].toString()+"_"+argu[4].toString()+"_T1";
		//Parametros[1]="NA";
		//Parametros[2]=argu[2].toString();
		//Parametros[3]="TRUE";
		//Parametros[4]="NA";
		
		String[] UltPaso;
		UltPaso = new String[5];
		// @Param 0) OK/NOK 1) Nro CP 2) Ambiente 3) Ultimo tramite o NA  4) ultimo paso o NA 

		String[] GrabarDatos;
		GrabarDatos =new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) inicializar o Nombre variable 4)valor variable

		setUltimoTramite("1");
		String sPath_USR = getCurrentProject().getLocation();
		sPath_USR = sPath_USR.replace("RFT_ICE","RFT_ICE_USR");
		sPath_USR = sPath_USR.concat("\\\\Regresion").concat("\\\\").concat(argu[2].toString()).concat("\\\\");
		System.out.println("---------------------------------------------------------");
		System.out.println("---------------------------------------------------------");
		System.out.println("---------------------------------------------------------");
		System.out.println("Se inicia la iteración usando los siguientes data pools:");
		
		// Path del DP del que va a leer
		String sFile_USR_Desde = argu[3].toString().concat("_").concat(argu[4].toString())+"_All" + ".rftdp";
		System.out.println("DP Desde=" + sPath_USR + "-" +sFile_USR_Desde);
		
		// Path del DP en el que va a grabar
		String sFile_USR_Hasta = argu[3].toString().concat("_").concat(argu[4].toString()) + ".rftdp";
		System.out.println("DP Hasta=" + sPath_USR + "-" +sFile_USR_Hasta);
		System.out.println("---------------------------------------------------------");
		System.out.println("---------------------------------------------------------");
		System.out.println("---------------------------------------------------------");
		
		// Leer DP Desde
		java.io.File dpFile_DP_D = new java.io.File(sPath_USR,sFile_USR_Desde);
		DatapoolFactory dpFactory = DatapoolFactory.get(); 
		IDatapool dataPoolD =dpFactory.loadForEdit(dpFile_DP_D, true);
		IDatapoolIterator dataPoolID = dpFactory().open(dataPoolD, null);
		int equivalenceClassIndexD = dataPoolD.getDefaultEquivalenceClassIndex();
		IDatapoolEquivalenceClass equivalenceClassD = (IDatapoolEquivalenceClass)
				dataPoolD.getEquivalenceClass(equivalenceClassIndexD); 
		dataPoolID.dpInitialize(dataPoolD);
		IDatapoolRecord recordD;
		
		// Leer y escribir dp hasta
		java.io.File dpFile_DP_H = new java.io.File(sPath_USR,sFile_USR_Hasta);
		IDatapool dataPoolH = dpFactory.loadForEdit(dpFile_DP_H, true);
		IDatapoolIterator dataPoolIH = dpFactory().open(dataPoolH, null);
		int equivalenceClassIndexH = dataPoolH.getDefaultEquivalenceClassIndex();
		IDatapoolEquivalenceClass equivalenceClassH = (IDatapoolEquivalenceClass)
				dataPoolH.getEquivalenceClass(equivalenceClassIndexH); 
		dataPoolIH.dpInitialize(dataPoolH);
		IDatapoolRecord recordH;
		
		
		// Leer el próximo registro a usar del DP Desde
		dataPoolID.dpReset();
		int nRegD = 0;
		boolean bFin = false;
		while (!dataPoolID.dpDone() && !bFin ) 
		{
			recordD = (IDatapoolRecord) equivalenceClassD.getRecord(nRegD);

			if (dataPoolID.dpString("Procesado").equals("NA") && 
					dataPoolID.dpString("Tipo").toLowerCase().equals("dato") )
			{
				// Si aun no se intentó ejecutar el CD se acutaliza _SeguimientoEjecucion
				System.out.println(nRegD + "-"+dataPoolID.dpString("Resultado"));
				if(dataPoolID.dpString("Resultado").equals("NA") && 
						dataPoolID.dpString("NroPedido").equals("NA"))
				{
					// @Param 0) OK/NOK 1) Nro CP 2) Ambiente 3) Ultimo tramite o NA  4) ultimo paso o NA 
					// res GRAL_CD1 QA 1 45
					UltPaso[1]=argu[3].toString()+"_"+argu[4].toString();
					UltPaso[2]=argu[2].toString();
					UltPaso[3]="1";
					UltPaso[4]="1";
					callScript(new GrabarDatoSalidaUltPasoEjec(), UltPaso);
					
					setNroPedido("NA");
					GrabarDatos[1]=UltPaso[1]; // CPnn_CDi
					GrabarDatos[2]=argu[2].toString(); // nro ambiente
					GrabarDatos[3]="T1"+"_NroPedido"; // Tx_Nombre variable 
					GrabarDatos[4]=getNroPedido();
					callScript("Scripts.Comun.GrabarDatosSalida",GrabarDatos);
					
					setNroServicio("NA");
					GrabarDatos[1]=UltPaso[1]; // CPnn_CDi
					GrabarDatos[2]=argu[2].toString(); // nro ambiente
					GrabarDatos[3]="T1"+"_NroServicio"; // Tx_Nombre variable 
					GrabarDatos[4]=getNroServicio(); 
					callScript("Scripts.Comun.GrabarDatosSalida",GrabarDatos);
					System.out.println("Se inicializan variables");
				}
				// Resetear el data pool hasta
				int nRegH = 0;
				dataPoolIH.dpReset();
				
				boolean bTermino = false;
				for (int i = 1; i <= 99 && !bTermino; i++) 
				{
					sDato = dataPoolID.dpString(String.valueOf(i));
					if (sDato.equals("FIN") ) bTermino = true;
					else 
					{	
						//System.out.println(sDato +"-");
						// Buscar el dato a reemplazar en el data pool hasta
						bEncontrado = false;
						while (!dataPoolIH.dpDone() && !bEncontrado ) 	
						{
							recordH = (IDatapoolRecord) equivalenceClassH.getRecord(nRegH);
							sIterable = recordH.getCell(2).getCellValue().toString();
							//sDatoAReemp = recordH.getCell(5).getCellValue().toString();
							if (!sIterable.toLowerCase().equals("no"))
							{
								//output = String.format ("%1$50s %2$30s %3$30s"
								//		, sIterable , sDatoAReemp , sDato);
								//System.out.println(output);
								bEncontrado = true;
								// se reemplaza el contenido del data pool hasta
								cellValue =  (IDatapoolCell) recordH.getCell(5);
								cellValue.setCellValue(sDato);	
								//String sPrueba = cellValue.getCellValue().toString();
								//System.out.println("Valor Grabado" + sPrueba);
								//save changes in data pool hasta
								//System.out.println("Se graban los cambios");
								dpFactory.save((org.eclipse.hyades.edit.datapool.IDatapool) dataPoolH);
							}
							nRegH++;
							dataPoolIH.dpNext(); 
						}
						if (!bEncontrado)
						{
							System.out.println("El dp no tiene mas datos para ser reemplazaos");
							bError=true;
						}
					}
				}
				if (!bError)
				{
					System.out.println("Se invoca a CasosPrueba");
					//callScript(new fDummy(), Parametros);
					callScript(new CasosPrueba(), argu);
					System.out.println("Se graba el nro de pedido, procesado y resultado");
					// Grabar en el data pool de lectura los resultados
					cellValue =  (IDatapoolCell) recordD.getCell(1); // Resultado
					cellValue.setCellValue(getTipoError().toString());	
					sPrueba = cellValue.getCellValue().toString();
					System.out.println("Valor Grabado en Resultado " + sPrueba);
					
					cellValue =  (IDatapoolCell) recordD.getCell(2); // NroPedido
					sPrueba = cellValue.getCellValue().toString();
					cellValue.setCellValue(getNroPedido().toString());	
					sPrueba = cellValue.getCellValue().toString();
					System.out.println("Valor Grabado en NroPedido " + sPrueba);
					dpFactory.save((org.eclipse.hyades.edit.datapool.IDatapool) dataPoolD);
					if (getTipoError().equals("FIN"))
					{
						cellValue =  (IDatapoolCell) recordD.getCell(0); // Procesado
						sPrueba = cellValue.getCellValue().toString();
						cellValue.setCellValue("OK");	
						sPrueba = cellValue.getCellValue().toString();
						System.out.println("Valor Grabado en Procesar " + sPrueba);
					}
					else 
						{
						System.out.println("Se procede a parar");
						Toolkit.getDefaultToolkit().beep();
						Toolkit.getDefaultToolkit().beep();
						Toolkit.getDefaultToolkit().beep();
						stop();
						}
				}
				else 
				{
					System.out.println("Se saltea el registro porque dio problemas");
				}
			}
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
			System.out.println("Se pasa al próximo registro del pool");
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
			//if (nRegD==1) bFin = true;
			nRegD++;
			dataPoolID.dpNext(); 
		}
		dpFactory.unload(dataPoolH);
		dpFactory.unload(dataPoolD);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

