package Scripts.Comun;
import org.eclipse.hyades.edit.datapool.IDatapoolCell;
import org.eclipse.hyades.edit.datapool.IDatapoolEquivalenceClass;
import org.eclipse.hyades.edit.datapool.IDatapoolRecord;
import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.Scripts.Comun.GrabarDatoSalidaUltPasoEjecHelper;
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
 * Description   : Graba el data pool de datos de salida del caso y ambiente indicados en los
 * argumentos CPnn_CDi - Graba el último paso ejecutado correctamente así como el último
 * trámite ejecutado correctamente y los guarda como variables globales.
 * Script Name   : <b>GrabarDatoSalidaUltPasoEjec</b>
 * @since  2016/11/03
 * @author SS
 * @Param 0) OK/NOK 1) Nro CP 2) Ambiente 3) Ultimo tramite o NA  4) ultimo paso o NA 
 * NA Indica no actualizar ese dato
 * ej res GRAL_CD1 QA 1 45
 * res CP20_CD1 QA NA 1
 * ult modif  ss 14 8 2017 se vuelve a nombre flexible el nombre del proyecto con prueba para bat
 */
public class GrabarDatoSalidaUltPasoEjec extends GrabarDatoSalidaUltPasoEjecHelper
{
	public void testMain(Object[]  argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		
		String sCaso = argu[1].toString();
		String sAmbiente = argu[2].toString();
		String sUltTram = argu[3].toString();
		String sUltPaso = argu[4].toString();
		boolean Encontro = false; 
		String sCasoDP = null;
		String sAmbienteDP=null;
		String sUltTramDP=null;
		String sUltPasoDP=null;
		
		String sPath_USR = getCurrentProject().getLocation();
		String Name =  getCurrentProject().getName();
		sPath_USR = sPath_USR.replace(Name,Name+"_USR"); 

 		sPath_USR = sPath_USR.concat("\\\\Regresion").concat("\\\\").concat(argu[2].toString()).concat("\\\\");
 		String sFile_USR = "_SeguimientoEjecucion" + ".rftdp";
		System.out.println("DP Salida en USR=" + sPath_USR + "-" +sFile_USR);
		
		// Itera el data pools de datos del caso para buscar la row correcta CPnn_CDi
		java.io.File dpFile_DP = new java.io.File(sPath_USR,sFile_USR);
		
		DatapoolFactory dpFactory = DatapoolFactory.get();
		IDatapool dataPool = dpFactory.loadForEdit(dpFile_DP, true);
		IDatapoolIterator dataPoolI = dpFactory().open(dataPool, null);
		int equivalenceClassIndex = dataPool.getDefaultEquivalenceClassIndex();
		IDatapoolEquivalenceClass equivalenceClass = (IDatapoolEquivalenceClass)
				dataPool.getEquivalenceClass(equivalenceClassIndex); 
		dataPoolI.dpInitialize(dataPool);
		dataPoolI.dpReset();

		int nReg = 0;
		IDatapoolRecord record;
		IDatapoolCell cellUltTram=null;
		IDatapoolCell cellUltPaso=null;
		while (!dataPoolI.dpDone() &&  !Encontro) 
		{
			record = (IDatapoolRecord) equivalenceClass.getRecord(nReg);
			//sCasoDPVar = record.getCell(0).getCellVariable().getName().toString();
			sCasoDP = record.getCell(0).getCellValue().toString();
			//sAmbienteDP = record.getCell(1).getCellVariable().getName().toString();
			sAmbienteDP = record.getCell(1).getCellValue().toString();
			cellUltTram =  (IDatapoolCell) record.getCell(2);
			//sVarDP = record.getCell(2).getCellVariable().getName().toString();
			sUltTramDP = record.getCell(2).getCellValue().toString();
			cellUltPaso =  (IDatapoolCell) record.getCell(3);
			sUltPasoDP = record.getCell(3).getCellValue().toString();
			if (sCasoDP.equals(sCaso) && sAmbienteDP.equals(sAmbiente)) 
			{
				System.out.println("NumeroCaso = " + sCasoDP );
				System.out.println("Ambiente = "  + sAmbienteDP );
				System.out.println("Se lee: Ult Tramite=" +sUltTramDP+"-Ult Paso="+sUltPasoDP);
				Encontro = true;
				System.out.println("Se actualizara con: Ult Tramite=" +sUltTram+"-Ult Paso="+sUltPaso);
			}
			nReg++;
			dataPoolI.dpNext(); 
		} 
		if (!Encontro) argu[0]="NOK";
		else 
		{ 
			// actualiza y comprueba
			if (!sUltTram.equals("NA"))
				cellUltTram.setCellValue(sUltTram);
			if (!sUltPaso.equals("NA"))
				cellUltPaso.setCellValue(sUltPaso);		

			sUltTramDP = cellUltTram.getCellValue().toString();
			sUltPasoDP = cellUltPaso.getCellValue().toString();
			
			if ((sUltTram.equals("NA") || sUltTramDP.equals(sUltTram)) && 
					((sUltPaso.equals("NA") ||sUltPasoDP.equals(sUltPaso))))
						argu[0]="OK"; 
			else 		argu[0]="NOK";
		}
		
		//save changes:
		dpFactory.save((org.eclipse.hyades.edit.datapool.IDatapool) dataPool);
		dpFactory.unload(dataPool);
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

