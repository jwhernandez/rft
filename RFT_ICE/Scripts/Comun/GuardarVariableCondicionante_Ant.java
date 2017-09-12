package Scripts.Comun;
import org.eclipse.hyades.edit.datapool.IDatapoolCell;
import org.eclipse.hyades.edit.datapool.IDatapoolEquivalenceClass;
import org.eclipse.hyades.edit.datapool.IDatapoolRecord;
import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.Scripts.Comun.GuardarVariableCondicionante_AntHelper;
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
 * Description   : Graba el resultado (true / false) de la ejecución de un paso
 * @author SS
 * Script Name   : <b>GuardarVariableCondicionante</b>
 * @since  2017/05/11
 * 0) OK/NOK 1) Expresion a evaluar 2) ambiente 3) CPnn_CD1 4) resultado 
 * ej res "Condicionante: CategoriaCrediticia" QA CPX1_CD1 pipi
 */ 
public class GuardarVariableCondicionante_Ant extends GuardarVariableCondicionante_AntHelper
{
 	public void testMain(Object[] argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		
		String sVariable = argu[1].toString();
		// Extraer nombre de la variable
		sVariable = sVariable.replace("Condicionante:","").trim();
		String sValor = argu[4].toString();
		
		String sVariable_DP = null;
		String sValor_DP = null;
//		IDatapoolCell cellVariable_DP=null;
//		IDatapoolCell cellValor_DP=null;
		boolean Encontro = false; 
		
		String filename="DPC_" + argu[3].toString();
		System.out.println("FileName=" + filename);

		String sPath_DP = getCurrentProject().getLocation();
		sPath_DP = sPath_DP.concat("\\\\Datos\\\\Condicionantes\\\\QA\\\\");
		//sPath_DP = sPath_DP.concat("\\\\Datos").concat("\\\\Condicionantes\\\\").concat(argu[2].toString()).concat("\\\\");
		//String sFile =filename  + ".rftdp";
		String sFile = "DPC_CPPC_CD1" + ".rftdp";
		System.out.println("Path DP Condicionates = " + sPath_DP+ 
				" File de Entrada = " + sFile);
		
		// Itera el data pools de datos del caso para buscar la row correcta CPnn_CDi
		java.io.File dpFile_DP = new java.io.File(sPath_DP,filename);
		
		DatapoolFactory dpFactory = DatapoolFactory.get();
		IDatapool dataPool = dpFactory.loadForEdit(dpFile_DP, true);
		
//		IDatapoolIterator dataPoolI = dpFactory().open(dataPool, null);
//		int equivalenceClassIndex = dataPool.getDefaultEquivalenceClassIndex();
//		IDatapoolEquivalenceClass equivalenceClass = (IDatapoolEquivalenceClass)
//				dataPool.getEquivalenceClass(equivalenceClassIndex); 
//		dataPoolI.dpInitialize(dataPool);
//		dataPoolI.dpReset();
//
//		int nReg = 0;
//		IDatapoolRecord record = null;
//		while (!dataPoolI.dpDone() &&  !Encontro) 
//		{
//			record = (IDatapoolRecord) equivalenceClass.getRecord(nReg);
//			sVariable_DP = record.getCell(0).getCellValue().toString();
//			sValor_DP = record.getCell(1).getCellValue().toString();
//			
//
//			if (sVariable_DP.equals(sVariable)) 
//			{
//				System.out.println("Se lee: Variable=" +sVariable+"-Valor Anterior="+sValor_DP);
//				Encontro = true;
//				System.out.println("Se actualizara con: " +sValor);
//				cellValor_DP =  (IDatapoolCell) record.getCell(1);
//				cellValor_DP.setCellValue(sValor);
//			}
//			nReg++;
//			dataPoolI.dpNext(); 
//		} 
//		if (!Encontro)
//		{
////			// Crear un registro en el DP y dar de alta la variable
////			IDatapoolRecord rec=equivalenceClass.constructRecord(null);
////			equivalenceClass.appendRecord(rec);
////			
////			System.out.println("Se Crear: Variable=" +sVariable+"- con Valor="+sValor);		
////			cellVariable_DP =  (IDatapoolCell) record.getCell(0);
////			cellVariable_DP.setCellValue(sValor);
////			cellValor_DP =  (IDatapoolCell) record.getCell(1);
////			cellValor_DP.setCellValue(sValor);
////			
////			((org.eclipse.hyades.edit.datapool.IDatapool) dataPool).appendEquivalenceClass(equivalenceClass);
//		}
//		//save changes:
//		dpFactory.save((org.eclipse.hyades.edit.datapool.IDatapool) dataPool);
//		dpFactory.unload(dataPool);
//		
//		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

