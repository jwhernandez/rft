package Scripts.Comun;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.hyades.edit.datapool.IDatapoolCell;
import org.eclipse.hyades.edit.datapool.IDatapoolRecord;
import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.Scripts.Comun.GeneradorCPsETEHelper;

import com.rational.test.ft.*;
import com.rational.test.ft.datapool.DatapoolFactory;
import com.rational.test.ft.datapool.impl.DatapoolEquivalenceClass;
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
 * Script Name   : <b>GeneradorCPsETE</b>
 * Generated     : <b>ago. 3, 2017 3:11:03 PM</b>
 * Description   : Functional Test Script
 * Original Host : WinNT Version 6.1  Build 7601 (S)
 * 
 * @since  2017/08/03
 * @author JH - VC
 * @param CXX QA
 */
public class GeneradorCPsETE extends GeneradorCPsETEHelper
{
	int registros = 0; //cantidad de lineas del dp GDP, como aún no tiene se inicia en 0
	int registrosPasosCC = 0; //cantidad de lineas del dp CC, como aún no tiene se inicia en 0
	int ajusteIndice = 0;
	int posicionScripts = 0;
	String tramiteCasoConcreto;
	String nuevoCP;
	ArrayList<String> arrayVariableDatos = new ArrayList<String>();
	ArrayList<Integer> findData = new ArrayList<Integer>();//Condiciona las lineas ya encontradas para que no vuelvan a encontrarlas en el DP_Datos.
	ArrayList<String[]> datosEscenariosGDP = new ArrayList<String[]>();//Agrega las variables a Regresion QA
	public void testMain(Object[] args) 
	{
		nuevoCP = args[0].toString();
		String casoConcreto = leerGDP(args[0].toString());
		tramiteCasoConcreto = leerTramiteGDP(args[0].toString()); //Nuevo
		String pasoCasoConcreto[] = new String[6];
		while(!casoConcreto.equals("finalizado") && !casoConcreto.equals("error")){
			////------1
			pasoCasoConcreto = leerPasosCC(casoConcreto);
			//System.out.print("1: " + pasoCasoConcreto); //////////////////////////////////////////////////////
			while(!pasoCasoConcreto[0].equals("finalizado") && !pasoCasoConcreto[0].equals("error") && !pasoCasoConcreto[0].equals("FIN")){
				////---2
				if(!pasoCasoConcreto[2].equals("FALSE") && !pasoCasoConcreto[5].equals("NA") && !pasoCasoConcreto[0].equals("error")){
					String variablesABuscar[];
					String variablesCondicional[];
					if(!pasoCasoConcreto[1].equals("NA")){ // Si tiene variables condicionales las agrega también a la lista de variables a copiar del dp de datos
						variablesCondicional = leerVariablesPasoCondicional(pasoCasoConcreto[1]);
					}else {
						variablesCondicional = new String[1];
						variablesCondicional[0] = "NA";
					}
					String variablesBiblioteca[] = leerVariablesBiblioteca(pasoCasoConcreto[0]);
					if(variablesBiblioteca[0].equals("error")){
						pasoCasoConcreto[0] = "error";
						break;
					} else if(!variablesBiblioteca[0].equals("noEncontrado")){
						String variablesConIndice[] = actualizarIndiceVariables(variablesBiblioteca, pasoCasoConcreto[4]);
						if(variablesConIndice[0].equals("error")){
							pasoCasoConcreto[0] = "error";
							break;
						}
					
						//String variablesABuscar[];
						if(!pasoCasoConcreto[1].equals("NA") && !variablesCondicional[0].equals("NA")){ // Si tiene variables condicionales las agrega también a la lista de variables a copiar del dp de datos
							//String variablesCondicional[] = leerVariablesPasoCondicional(pasoCasoConcreto[1]);
							int posicion = 0;
							if(!variablesBiblioteca[0].equals("NA")){
								variablesABuscar = new String[ variablesConIndice.length + variablesCondicional.length ];
								System.arraycopy( variablesConIndice, 0, variablesABuscar, 0, variablesConIndice.length );
								posicion = variablesConIndice.length;
							} else{
								variablesABuscar = new String[ variablesCondicional.length ];
							}
							
							System.arraycopy( variablesCondicional, 0, variablesABuscar, posicion, variablesCondicional.length );
							/*for(int k=0; k < variablesCondicional.length; k++){
								System.err.print(variablesCondicional[k] + " | ");
							}
							System.out.println();*/
						}else if(!variablesBiblioteca[0].equals("NA")){
							variablesABuscar = variablesConIndice;
						} else{
							variablesABuscar = new String[1];
							variablesABuscar[0] = "NA";
						}
						String variablesDPDatos[][] = leerVariablesDPDatos(variablesABuscar, casoConcreto, pasoCasoConcreto[0]);
						//System.out.print(" 4: " + variablesDPDatos[0][0]);
						if(variablesDPDatos[0][0].equals("error")){
							pasoCasoConcreto[0] = "error";
							break;
						}
						///////////////////////////// Viene la parte de escribir /////////////////////////////
						System.err.println();
						System.out.println("Paso: " +pasoCasoConcreto[0] + " - " + pasoCasoConcreto[2] + " - " + pasoCasoConcreto[4] + " - " + pasoCasoConcreto[1] + " | Posición DP: " + registrosPasosCC + " - " + ajusteIndice);
						System.err.println();
						//System.out.println("Variables: ");
						boolean pasoInsertado = insertarRegistroDPDatos(variablesDPDatos, pasoCasoConcreto);
						if(!pasoInsertado){
							//System.err.println("Error al insertar el paso " +  pasoCasoConcreto[0] + " en el datapool de pasos.");
							break;
						}
						//boolean datoInsertado = insertarRegistroDPDatos(variablesDPDatos, pasoCasoConcreto);
						/*for(int i=0; i < variablesABuscar.length; i++){
							for(int j=0; j < 7; j++){
								//System.out.print(variablesDPDatos[i][j]);
								//System.out.print(" | ");
							}
							System.out.println();
						}*/
						///////////////////////////// termina la parte de escribir /////////////////////////////
					}
					posicionScripts++;
					
				}
				////---2
				registrosPasosCC++; // Paso al siguiente paso del DP de pasos del CC
				pasoCasoConcreto = leerPasosCC(casoConcreto);
			}
			////---1
			registros++; // Pasa a la siguiente linea de GDP_CPXX
			if(pasoCasoConcreto[0].equals("error")) break;
			//int posicionScripts = registrosPasosCC;
			casoConcreto = leerGDP(args[0].toString());
			if(!casoConcreto.equals("finalizado")){
				String tramiteActual = tramiteCasoConcreto;
				tramiteCasoConcreto = leerTramiteGDP(args[0].toString());
				if(!tramiteActual.equals(tramiteCasoConcreto)){
					//agrego linea con el script fNuevoTramite
					String[] fNuevoTerminal = {"fNuevoTramite", "NA", "TRUE", "Siempre", "NA", "-1", "TRUE"};
					boolean  insertado = insertarRegistroDPPasos(fNuevoTerminal, tramiteCasoConcreto);
					if(!insertado){
						System.err.println("Error al insertar linea de fNuevoTramite.");
						break;
					}
					ajusteIndice++;
					ajusteIndice+=posicionScripts; //como ya terminó un tramite los pasos siguientes van a partir de ese trámite
					posicionScripts = 0; //Se reincia porque sino se generan espacios duplicados
				}
			}
		}
		if(casoConcreto.equals("finalizado")){
			registrarDatosUSR(datosEscenariosGDP, args[1].toString());
		}
		
	}
	
	private String leerTramiteGDP(String casoPrueba) {
		findData.clear();
		String sPath_DP = getCurrentProject().getLocation();
		sPath_DP = sPath_DP.concat("\\\\ConfiguracionDatosEscenario").concat("\\\\");
		String sFile = "GDP_CP" + casoPrueba + ".rftdp"; //Toma el nombre del archivo
		File dpFile = new File(sPath_DP,sFile);
		IDatapool dp = DatapoolFactory.get().loadForEdit(dpFile, true); 
		
		int defClassIndex = dp.getDefaultEquivalenceClassIndex();
		DatapoolEquivalenceClass dpc = (DatapoolEquivalenceClass)dp.getEquivalenceClass(defClassIndex); 
		try{
			if(registros < dpc.getRecordCount()){ // Revisar si empieza en 0 o en 1 y si es menor o menor igual
				IDatapoolRecord record;
				record = (IDatapoolRecord) dpc.getRecord(registros);
				
				IDatapoolCell cell = (IDatapoolCell) record.getCell("Tramite");
				String casoConcreto = cell.getCellValue().toString();
				
				System.err.println("El CP a leer es: " + casoConcreto);
				//Saving the datapool
				DatapoolFactory.get().unload(dp);
				return casoConcreto;
			} else return "finalizado";
		} catch(Exception e){
			System.out.println("Error al leer datapool GDP_CP" + casoPrueba + ". " + e);
			DatapoolFactory.get().unload(dp);
			return "error";
		}
	}

	private String[] leerVariablesPasoCondicional(String variablesCondicionales) {
		// TODO Apéndice de método generado automáticamente
		ArrayList<String> aux = new ArrayList<String>();
		for(int i = 0; i < variablesCondicionales.length(); i++){
			int posicionInicial = variablesCondicionales.indexOf("%", i);;
			int posicionFinal = variablesCondicionales.indexOf("%", posicionInicial+1);
			if(posicionInicial!=-1 && posicionFinal!=-1){
				aux.add(variablesCondicionales.substring(posicionInicial+1, posicionFinal));
				i = posicionFinal+1;
			}else{
				break;
			}
		}
		String variables[] = new String[aux.size()];
		aux.toArray(variables);
		return variables;
	}

	/**
	 * <b>Method Name :</b> leerVariablesDPDatos.
	 * <b>Description :</b> Busca los datos correspondientes de un paso o script de un caso concreto.
	 *	
	 * @param variablesABuscar Variables en los DataPools de Datos.
	 * @param casoConcreto Paso del caso concreto.
	 * @param script Nombre del Script.
	 * 
	 * @return <b>Matriz</b> con los datos de un Script.
	 */
	private String[][] leerVariablesDPDatos(String[] variablesABuscar, String casoConcreto, String script) {
		String sPath_DP = getCurrentProject().getLocation();
		sPath_DP = sPath_DP.concat("\\\\ConfiguracionDatosEscenario").concat("\\\\");
		String sFile = casoConcreto + "_CD1_COMPLETO.rftdp"; //Toma el nombre del archivo
		File dpFile = new File(sPath_DP,sFile);
		IDatapool dp = DatapoolFactory.get().loadForEdit(dpFile, true); 

		int defClassIndex = dp.getDefaultEquivalenceClassIndex();
		DatapoolEquivalenceClass dpc = (DatapoolEquivalenceClass)dp.getEquivalenceClass(defClassIndex); 
		String variablesPasoCC[][] = new String[variablesABuscar.length][7];
		try{
			for(int j = 0; j < variablesABuscar.length; j++){
				boolean variableEncontrada = false;
				boolean repeatLine = false;
				for(int i = 0; i < dpc.getRecordCount(); i++){ // Revisar si empieza en 0 o en 1 y si es menor o menor igual
					IDatapoolRecord record;
					record = (IDatapoolRecord) dpc.getRecord(i);
					IDatapoolCell cell = (IDatapoolCell) record.getCell("Cant");
					if(Integer.parseInt(cell.getCellValue().toString()) > 1){
						int variables = Integer.parseInt(cell.getCellValue().toString());
						for(int k = 1; k <= variables; k++){
							cell = (IDatapoolCell) record.getCell("Variable"+k);
							if(cell.getCellValue().toString().equals(variablesABuscar[j])){
								variableEncontrada = true;
								repeatLine = true;
								break;
							}
						}
					} else{
						cell = (IDatapoolCell) record.getCell("Variable1");
						if(cell.getCellValue().toString().equals(variablesABuscar[j])){
							variableEncontrada = true;
						}
					}
					variablesPasoCC[j][5] = "1";
					variablesPasoCC[j][6] = variablesABuscar[j];
					if(variableEncontrada){
						int sCant = Collections.frequency(findData, i);
						if(sCant == 0 || repeatLine){
							findData.add(i);
							cell = (IDatapoolCell) record.getCell("Tipo");
							variablesPasoCC[j][0] = cell.getCellValue().toString();
							cell = (IDatapoolCell) record.getCell("Tramite");
							variablesPasoCC[j][1] = cell.getCellValue().toString();
							cell = (IDatapoolCell) record.getCell("SubTipo");
							variablesPasoCC[j][2] = cell.getCellValue().toString();
							cell = (IDatapoolCell) record.getCell("Dato");
							variablesPasoCC[j][3] = cell.getCellValue().toString();
							cell = (IDatapoolCell) record.getCell("Valor");
							variablesPasoCC[j][4] = cell.getCellValue().toString();
						}else{
							variablesPasoCC[j][0] = "noEncontrado";
						}
						break;
					} else {
						variablesPasoCC[j][0] = "noEncontrado";
					}
				}
				if(!variableEncontrada){
					//System.out.println("No se encontró la variable: " + variablesABuscar[j] + " para el script: " + script);
				}
			} 
			DatapoolFactory.get().unload(dp);
			return variablesPasoCC;
		} catch(Exception e){
			System.out.println("Error al leer variables del datapool DP_Datos_" + casoConcreto + "_CD1_COMPLETO.rftdp" + ". " + e);
			variablesPasoCC[0][0] = "error";
			DatapoolFactory.get().unload(dp);
			return variablesPasoCC;
		}
	}

	/**
	 * <b>Method Name :</b> actualizarIndiceVariables.
	 * <b>Description :</b> Actualiza los indices de las variables de un Script
	 *	
	 * @param variablesBiblioteca Variables de un Script.
	 * @param paramCC Param del caso concreto.
	 * 
	 * @return <b>String[]</b> con los indices actualizados.
	 */
	private String[] actualizarIndiceVariables(String[] variablesBiblioteca, String paramCC) {
		for(int i = 0; i < variablesBiblioteca.length; i++){
			variablesBiblioteca[i] = variablesBiblioteca[i].replace("(i)", paramCC);
		}
		return variablesBiblioteca;
	}

	/**
	 * <b>Method Name :</b> leerGDP.
	 * <b>Description :</b> Busca el Caso Concreto de un paso Prueba.
	 *	
	 * @param casoPrueba Nombre del Caso de Prueba.
	 * 
	 * @return <b>Nombre</b> del paso concreto.
	 */
	private String leerGDP(String casoPrueba){
		findData.clear();
		String sPath_DP = getCurrentProject().getLocation();
		sPath_DP = sPath_DP.concat("\\\\ConfiguracionDatosEscenario").concat("\\\\");
		String sFile = "GDP_CP" + casoPrueba + ".rftdp"; //Toma el nombre del archivo
		File dpFile = new File(sPath_DP,sFile);
		IDatapool dp = DatapoolFactory.get().loadForEdit(dpFile, true); 

		int defClassIndex = dp.getDefaultEquivalenceClassIndex();	
		DatapoolEquivalenceClass dpc = (DatapoolEquivalenceClass)dp.getEquivalenceClass(defClassIndex); 		
		try{
			if(registros < dpc.getRecordCount()){ // Revisar si empieza en 0 o en 1 y si es menor o menor igual
				IDatapoolRecord record;
				record = (IDatapoolRecord) dpc.getRecord(registros);
				
				IDatapoolCell cell = (IDatapoolCell) record.getCell("CasoConcreto");
				String casoConcreto = cell.getCellValue().toString();
				
				System.err.println("El CP a leer es: " + casoConcreto);
				//Saving the datapool
				DatapoolFactory.get().unload(dp);
				registrosPasosCC = 0;
				return casoConcreto;
			} else return "finalizado";
		} catch(Exception e){
			System.out.println("Error al leer datapool GDP_CP" + casoPrueba + ". " + e);
			DatapoolFactory.get().unload(dp);
			return "error";
		}
	}

	/**
	 * <b>Method Name :</b> leerPasosCC.
	 * <b>Description :</b> Busca los datos de un paso concreto.
	 *	
	 * @param casoConcreto Nombre del Caso concreto.
	 * 
	 * @return <b>String[]</b> con los datos el paso concreto.
	 */
	private String[] leerPasosCC(String casoConcreto){
		String pasoCC[] = new String[7];
		String sPath_DP = getCurrentProject().getLocation();
		sPath_DP = sPath_DP.concat("\\\\ConfiguracionPasosEscenarios").concat("\\\\");
		String sFile = "DP_Pasos_" + casoConcreto + ".rftdp"; //Toma el nombre del archivo

		File dpFile = new File(sPath_DP,sFile);
		IDatapool dp = DatapoolFactory.get().loadForEdit(dpFile, true); 	
		int defClassIndex = dp.getDefaultEquivalenceClassIndex();
		DatapoolEquivalenceClass dpc = (DatapoolEquivalenceClass)dp.getEquivalenceClass(defClassIndex); 
		try{
			if(registrosPasosCC < dpc.getRecordCount()){ // Revisar si empieza en 0 o en 1 y si es menor o menor igual
				IDatapoolRecord record;
				record = (IDatapoolRecord) dpc.getRecord(registrosPasosCC);
				
				IDatapoolCell cell = (IDatapoolCell) record.getCell(casoConcreto + "Pasos");
				pasoCC[0] = cell.getCellValue().toString();
				
				if(pasoCC[0].equals("FIN")){
					pasoCC[0] = "finalizado";
					return pasoCC;
				}
				
				cell = (IDatapoolCell) record.getCell(casoConcreto + "Cond");
				pasoCC[1] = cell.getCellValue().toString();
				
				cell = (IDatapoolCell) record.getCell(casoConcreto + "Ejecuta");
				pasoCC[2] = cell.getCellValue().toString();
				
				cell = (IDatapoolCell) record.getCell(casoConcreto + "ReEjec");
				pasoCC[3] = cell.getCellValue().toString();
				
				cell = (IDatapoolCell) record.getCell(casoConcreto + "Param");
				pasoCC[4] = cell.getCellValue().toString();
				
				cell = (IDatapoolCell) record.getCell(casoConcreto + "Index" + tramiteCasoConcreto);
			    pasoCC[5] = cell.getCellValue().toString();
			    
				cell = (IDatapoolCell) record.getCell(casoConcreto + "ErrorStop");
				pasoCC[6] = cell.getCellValue().toString();
				
				//Saving the datapool
				DatapoolFactory.get().unload(dp);	
				return pasoCC;
			} else{
				pasoCC[0] = "finalizado";
				return pasoCC;
			}
		} catch(Exception e){
			System.out.println("Error al leer datapool DP_Pasos_" + casoConcreto + ". " + e);
			pasoCC[0] = "error";
			DatapoolFactory.get().unload(dp);
			return pasoCC;
		}
	}
	
	/**
	 * <b>Method Name :</b> leerVariablesBiblioteca
	 * <b>Description :</b> Busca las variables de un script en la Biblioteca.
	 *	
	 * @param scriptABuscar Nombre del Script.
	 * 
	 * @return <b>String[]</b> con las varibles del Script
	 */
	private String[] leerVariablesBiblioteca(String scriptABuscar){
		System.out.println("Script: " + scriptABuscar);
		String variables[] = new String[1];
		String sPath_DP = getCurrentProject().getLocation();
		sPath_DP = sPath_DP.concat("\\\\ConfiguracionDatosEscenario").concat("\\\\");
		//sPath_DP = sPath_DP.concat("\\\\Anteriores").concat("\\\\");
		//String sFile = "DatosDePrueba.rftdp"; //argu[1].toString()  + ".rftdp";
		String sFile = "Biblioteca.rftdp"; //Toma el nombre del archivo
		
		File dpFile = new File(sPath_DP,sFile);
		IDatapool dp = DatapoolFactory.get().loadForEdit(dpFile, true); 
		int defClassIndex = dp.getDefaultEquivalenceClassIndex();
		DatapoolEquivalenceClass dpc = (DatapoolEquivalenceClass)dp.getEquivalenceClass(defClassIndex); 
		boolean encontrado = false;
		try{
			for(int i = 0; i < dpc.getRecordCount(); i++){ // Recorre el dp buscando el script
				IDatapoolRecord record;
				record = (IDatapoolRecord) dpc.getRecord(i);
				IDatapoolCell cell = (IDatapoolCell) record.getCell("Script");
				if(cell.getCellValue().toString().equals(scriptABuscar)){
					cell = (IDatapoolCell) record.getCell("Variables");
					variables = cell.getCellValue().toString().split("/");
					encontrado = true;					
					break;
				}
			}
			if(!encontrado){
				variables[0] = "noEncontrado";
			}
			DatapoolFactory.get().unload(dp);
			return variables;		
		} catch(Exception e){
			System.out.println("Error al leer la biblioteca. " + e);
			variables[0] = "error";
			DatapoolFactory.get().unload(dp);
			return variables;
		}
	}
	
	/**
	 * <b>Method Name :</b> insertarRegistroDPPasos
	 * <b>Description :</b> Actualiza los Template de DataPools, agrega los datos de los pasos concretos segun su indice.
	 *	
	 * @param pasoCasoConcreto Vector con los datos del paso concreto.
	 * @param sParam Parametro de "CPXXParam".
	 * 
	 * @return <b>Boolean</b> confirmando el estado de la ejecución.
	 */
	private boolean insertarRegistroDPPasos(String[] pasoCasoConcreto, String sParam){
		if(!pasoCasoConcreto[5].equals("-1")) pasoCasoConcreto[5] = "" + (Integer.parseInt(pasoCasoConcreto[5]) + ajusteIndice); ///Nuevo
		boolean filaInsertada = false;
		String sPath_DP = getCurrentProject().getLocation();
		sPath_DP = sPath_DP.concat("\\\\ConfiguracionPasosEscenarios").concat("\\\\"); //Ruta del archivo
		String nameFile = "DP_Pasos_CP" + nuevoCP + ".rftdp"; //Toma el nombre del archivo del Template del DP de Pasos.
		try {
		    filaInsertada = agregarLineaDP(Integer.parseInt(pasoCasoConcreto[5]), sPath_DP, nameFile);
		    if (!filaInsertada) {
		        System.out.println("Error al añadir linea en el dp de datos!");
		        return false;
		    } else {
		        File dpFile = new File(sPath_DP, nameFile);
		        IDatapool dp = DatapoolFactory.get().loadForEdit(dpFile, true);
		        dp = DatapoolFactory.get().loadForEdit(dpFile, true); // Se vuelve a cargar el DP
		        int defClassIndex = dp.getDefaultEquivalenceClassIndex();
		        DatapoolEquivalenceClass dpc = (DatapoolEquivalenceClass) dp.getEquivalenceClass(defClassIndex);
		        try {
		        	int filaActualizar;
		            if(Integer.parseInt(pasoCasoConcreto[5]) != -1) filaActualizar = Integer.parseInt(pasoCasoConcreto[5].toString()) - 1;
		            else filaActualizar = dpc.getRecordCount()-1; // sin -1?
		            
		            IDatapoolRecord rec = dpc.constructRecord(); // Creamos un constructor vacio.

		            IDatapoolCell cell = (IDatapoolCell) rec.getCell("CP" + nuevoCP + "Pasos");
		            cell.setCellValue(pasoCasoConcreto[0]);

		            cell = (IDatapoolCell) rec.getCell("CP" + nuevoCP + "Cond");
		            cell.setCellValue(pasoCasoConcreto[1]);

		            cell = (IDatapoolCell) rec.getCell("CP" + nuevoCP + "Ejecuta");
		            cell.setCellValue(pasoCasoConcreto[2]);
	
		            cell = (IDatapoolCell) rec.getCell("CP" + nuevoCP + "ReEjec");
		            cell.setCellValue(pasoCasoConcreto[3]);
	
		            cell = (IDatapoolCell) rec.getCell("CP" + nuevoCP + "Param");
		            cell.setCellValue(sParam);

		            cell = (IDatapoolCell) rec.getCell("CP" + nuevoCP + "ErrorStop");
		            cell.setCellValue(pasoCasoConcreto[6]);

		            dpc.replaceRecord(rec, filaActualizar); // Replazamos una fila especifica con nuevos datos.
		            ((org.eclipse.hyades.edit.datapool.IDatapool) dp).appendEquivalenceClass(dpc);

		            //Saving the datapool
		            DatapoolFactory.get().save((org.eclipse.hyades.edit.datapool.IDatapool) dp);
		            DatapoolFactory.get().unload(dp);
		            return true;
		        } catch (Exception e) {
		            System.out.println("Error al agregar el registro en datos salida. " + e);
		            return false;
		        }
		    }
		} catch (Exception e) {
		    System.out.println("Error al añadir linea en el dp de pasos. Error: " + e);
		}
		return false;
	} 
	
	/**
	 * <b>Method Name :</b> agregarLineaDP
	 * <b>Description :</b> Inserta lineas en los Template de DataPools segón la posición que le enviamos.
	 * Ejemplo: -1 = agrega la linea secuencial, 10 = agrega las linea que faltan para la posicion 10 del DataPool.
	 * 
	 * @param sPosicion Posición en la cual se va insertar la linea.
	 * @param sPath_DP URL del archivo.
	 * @param nameFile Nombre del archivo.
	 * 
	 * @return <b>Boolean</b> confirmando el estado de la ejecución.
	 */
	private boolean agregarLineaDP(int sPosicion, String sPath_DP, String nameFile){
		java.io.File dpFile_DP = new java.io.File(sPath_DP, nameFile);
		IDatapool dp_DatosVar =dpFactory().load(dpFile_DP, true);
		IDatapoolIterator dp_DatosVarI = dpFactory().open(dp_DatosVar, null); //Sirve para moverse en el datapool
		dp_DatosVarI.dpInitialize(dp_DatosVar);
		
		File dpFile = new File(sPath_DP, nameFile); 
		IDatapool dp = DatapoolFactory.get().loadForEdit(dpFile, true); 

		int defClassIndex = dp.getDefaultEquivalenceClassIndex();
		DatapoolEquivalenceClass dpc = (DatapoolEquivalenceClass)dp.getEquivalenceClass(defClassIndex); 
		IDatapoolRecord rec = dpc.constructRecord(null);
		try{
			if(sPosicion != -1){
				if(sPosicion > dpc.getRecordCount()){				
					for(int i = dpc.getRecordCount(); i < sPosicion; i++){
						dpc.appendRecord(rec);
					}
					((org.eclipse.hyades.edit.datapool.IDatapool) dp).appendEquivalenceClass(dpc);
					//Saving the datapool
					DatapoolFactory.get().save((org.eclipse.hyades.edit.datapool.IDatapool) dp);
				}
			}else{
				dpc.appendRecord(rec);
				DatapoolFactory.get().save((org.eclipse.hyades.edit.datapool.IDatapool) dp);
			}
			DatapoolFactory.get().unload(dp);
			//System.out.println("Se insertó la linea en el datapool " + nameFile + ".");
			return true;
		}catch(Exception e){
			System.out.println("Error al insertar linea en el datapool " + nameFile + ". " + e);
			return false;
		}
	}

	/**
	 * <b>Method Name :</b> insertarRegistroDPDatos
	 * <b>Description :</b> Completa un DataPool Template que se encuentra ubicado en ConfiguracionDatosEscenario, 
	 * con los datos claves de un paso concreto.
	 * 
	 * @param variablesDPDatos Matriz con la información para llenar el DP de Datos de ConfiguracionDatosEscenario.
	 * @param pasoCasoConcreto Vector con los datos del paso concreto.
	 * 
	 * @return <b>Boolean</b> confirmando el estado de la ejecución.
	 */
	private boolean insertarRegistroDPDatos(String[][] variablesDPDatos, String[] pasoCasoConcreto) {
		String sParam = pasoCasoConcreto[4].toString();
	    String sPath_DP = getCurrentProject().getLocation();
	    sPath_DP = sPath_DP.concat("\\\\ConfiguracionDatosEscenario").concat("\\\\"); //Ruta del archivo
	    String nameFile = "CP" + nuevoCP + "_CD1_COMPLETO.rftdp"; //Toma el nombre del archivo del Template del DP de Pasos.
	    try {
	        File dpFile = new File(sPath_DP, nameFile);
	        IDatapool dp = DatapoolFactory.get().loadForEdit(dpFile, true);

	        dp = DatapoolFactory.get().loadForEdit(dpFile, true); // Se vuelve a cargar el DP
	        int defClassIndex = dp.getDefaultEquivalenceClassIndex();
	        DatapoolEquivalenceClass dpc = (DatapoolEquivalenceClass) dp.getEquivalenceClass(defClassIndex);
	        for (int i = 0; i < variablesDPDatos.length; i++) {
	            if (!variablesDPDatos[i][0].trim().equals("noEncontrado")) { //Si no encuentra nada, no graba en dp.	            		
	                if (agregarLineaDP(-1, sPath_DP, nameFile)) {
	                	
	                    IDatapoolRecord record;
	                    record = (IDatapoolRecord) dpc.getRecord(dpc.getRecordCount() - 1);

	                    IDatapoolCell cell = (IDatapoolCell) record.getCell("Tipo");
	                    cell.setCellValue(variablesDPDatos[i][0]);

	                    cell = (IDatapoolCell) record.getCell("Tramite");
	                    cell.setCellValue(tramiteCasoConcreto);
	                    
	                    cell = (IDatapoolCell) record.getCell("SubTipo");
	                    cell.setCellValue(variablesDPDatos[i][2]);	            

	                    cell = (IDatapoolCell) record.getCell("Dato");
	                    cell.setCellValue(pasoCasoConcreto[5].toString() + "- " + pasoCasoConcreto[0].toString());
	                    
	                    cell = (IDatapoolCell) record.getCell("Valor");
	                    cell.setCellValue(variablesDPDatos[i][4]);

	                    cell = (IDatapoolCell) record.getCell("Cant");
	                    cell.setCellValue("1");

	                    /*String[] sDatos = new String[2];
	                    sDatos = indexVariable(variablesDPDatos[i][6].toString(), pasoCasoConcreto[4]);
	                    sParam = sDatos[1];	*/

	                    cell = (IDatapoolCell) record.getCell("Variable1");
	                    cell.setCellValue(variablesDPDatos[i][6].toString()); //sDatos[0]
	                    
	                    if(variablesDPDatos[i][2].trim().equals("Variable")){//Ingresamos al arrayList las "Variables".
	                        String[] datosEsc  = new String[7];
	                        datosEsc[0] = variablesDPDatos[i][0];
	                        datosEsc[1] = tramiteCasoConcreto;
	                        datosEsc[2] = variablesDPDatos[i][2];     
	                        datosEsc[3] = pasoCasoConcreto[5].toString() + "- " + pasoCasoConcreto[0].toString();
	                        datosEsc[4] = variablesDPDatos[i][4];
	                        datosEsc[5] = "1";
	                        datosEsc[6] = variablesDPDatos[i][6].toString();
	                                       
	                        datosEscenariosGDP.add(datosEsc);   
	                    }
	                    ((org.eclipse.hyades.edit.datapool.IDatapool) dp).appendEquivalenceClass(dpc);

	                    //Saving the datapool
	                    DatapoolFactory.get().save((org.eclipse.hyades.edit.datapool.IDatapool) dp);
	                    DatapoolFactory.get().unload(dp);
	                }
	            }
	        }
	        if (insertarRegistroDPPasos(pasoCasoConcreto, sParam)) // sParam
	            return true;
	    } catch (Exception e) {
	    	System.out.println("Error al agregar el registro en " + nameFile + " -- Tipo de Error: " + e);
	        return false;
	    }
	    return false;
	}
	
	/**
	 * <b>Method Name :</b> registrarDatosUSR.
	 * <b>Description :</b> Completa un DataPool Template que se encuentra ubicado en el proyecto de USR, 
	 * todos los datos que sean Variables en DP de Datos de ConfiguracionDatosEscenario.
	 * 
	 * @param sDatosUSR ArrayList con la información para llenar el DP de Datos de Regresión.
	 * @param sAmbiente Ambiente.
	 * 
	 * @return <b>Boolean</b> confirmando el estado de la ejecución.
	 */
	private boolean registrarDatosUSR(ArrayList<String[]> sDatosUSR, String sAmbiente){
	    String sPath_DP = getCurrentProject().getLocation();
	    sPath_DP = sPath_DP.concat("_USR\\\\Regresion").concat("\\\\").concat(sAmbiente).concat("\\\\"); //Ruta del archivo
	    String nameFile = "CP" + nuevoCP + "_CD1.rftdp"; //Toma el nombre del archivo del Template del DP de Pasos.
	    try {
	        File dpFile = new File(sPath_DP, nameFile);
	        IDatapool dp = DatapoolFactory.get().loadForEdit(dpFile, true);

	        dp = DatapoolFactory.get().loadForEdit(dpFile, true); // Se vuelve a cargar el DP
	        int defClassIndex = dp.getDefaultEquivalenceClassIndex();
	        DatapoolEquivalenceClass dpc = (DatapoolEquivalenceClass) dp.getEquivalenceClass(defClassIndex);
	        for(int i = 0; i < sDatosUSR.size(); i++) {
	        	String[] sDato = new String[7];
	        	sDato = sDatosUSR.get(i); // Casting de String[] del ArrayList
	        	if (agregarLineaDP(-1, sPath_DP, nameFile)) {
	        		
                    IDatapoolRecord record;
                    record = (IDatapoolRecord) dpc.getRecord(i);

                    IDatapoolCell cell = (IDatapoolCell) record.getCell("Tipo");
                    cell.setCellValue(sDato[0]);

                    cell = (IDatapoolCell) record.getCell("Tramite");
                    cell.setCellValue(sDato[1]);
                    
                    cell = (IDatapoolCell) record.getCell("SubTipo");
                    cell.setCellValue(sDato[2]);

                    cell = (IDatapoolCell) record.getCell("Dato");
                    cell.setCellValue(sDato[3]);
                    
                    cell = (IDatapoolCell) record.getCell("Valor");
                    cell.setCellValue(sDato[4]);

                    cell = (IDatapoolCell) record.getCell("Cant");
                    cell.setCellValue(sDato[5]);

                    cell = (IDatapoolCell) record.getCell("Variable1");
                    cell.setCellValue(sDato[6]);
                    //Saving the datapool
                    DatapoolFactory.get().save((org.eclipse.hyades.edit.datapool.IDatapool) dp);
                    DatapoolFactory.get().unload(dp);
                }
	        }
	        return true;
	    } catch (Exception e) {
	    	System.out.println("Error al agregar el registro en " + nameFile + " -- Tipo de Error: " + e);
	        return false;
	    }
	}
	
	private String[] indexVariable(String sVariable, String sData){
		String[] sDatos = new String[2];
		if(sVariable.lastIndexOf(sData) > -1 && !sVariable.trim().equals(sData.trim())){
			String sNewName = sVariable.substring(0, sVariable.lastIndexOf(sData));
			int sCant = Collections.frequency(arrayVariableDatos, sNewName);
			if(sCant != 0){
				sDatos[0] = sNewName + String.valueOf(sCant+1);
				sDatos[1] = String.valueOf(sCant + 1);
				arrayVariableDatos.add(sNewName);
			}else{
				arrayVariableDatos.add(sNewName);
				sDatos[0] = sNewName + "1";
				sDatos[1] = "1";
			}
		}else{
			sDatos[0] = sVariable;
			sDatos[1] = sData;
		}
		return sDatos;
	}
}

