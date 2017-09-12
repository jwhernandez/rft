package Scripts.Comun;
import resources.Scripts.Comun.PruebaEscribirDataPoolExternoHelper;
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
import org.eclipse.hyades.edit.datapool.IDatapoolCell;
import org.eclipse.hyades.edit.datapool.IDatapoolEquivalenceClass;
import org.eclipse.hyades.edit.datapool.IDatapoolRecord;
import org.eclipse.hyades.execution.runtime.datapool.*;

import com.rational.test.ft.datapool.DatapoolFactory;
import com.rational.test.ft.datapool.impl.Datapool;
/**
 * Description   : Grabar DP externo
 * Script Name   : <b>PruebaEscribirDataPoolExterno</b>
 * @since  2016/11/03
 * @author SS
 * ej res GRAL_CD1 QA UltTramite 2
 */
public class PruebaEscribirDataPoolExterno extends PruebaEscribirDataPoolExternoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		
		String sCaso = argu[1].toString();
		String sDatoaLeer = null;
		String sDatoaGrabar = argu[3].toString();
		String sValor = argu[4].toString();
	
		// Setear el path y file del dp de salida
		String sPath_USR = getCurrentProject().getLocation().concat("_USR\\\\Regresion").concat("\\\\").concat(argu[2].toString()).concat("\\\\");
		String sFile_USR = "DP_DatosSalida" + ".rftdp";
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
		while (!dataPoolI.dpDone()) 
		{
			for (int nVars = 0; nVars < dataPool.getVariableCount(); nVars++) {
				//Leer las celdas del DP en tipo IDatapoolCell
				IDatapoolRecord record = (IDatapoolRecord) equivalenceClass.getRecord(nReg);
				IDatapoolCell cell =(IDatapoolCell) record.getCell(nVars);
				String sVar = cell.getCellVariable().getName().toString();
				String sVal = cell.getCellValue().toString();
				System.out.println("cell=" +sVar+"-"+sVal);
				if (sVar == "UltTramite") cell.setCellValue("Nuevo");
				if (sVar == "UltPasoOK") cell.setCellValue("Nuevo");
			}
			nReg++;
			dataPoolI.dpNext();
			System.out.println(nReg + "-----------------");
		}
		//save changes:
		dpFactory.save((org.eclipse.hyades.edit.datapool.IDatapool) dataPool);
		dpFactory.unload(dataPool);
	}
}

