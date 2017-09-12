package Scripts.Comun;
import java.io.File;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;

import resources.Scripts.Comun.ResetVariablesCondicionantesHelper;
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
 * Description   : Functional Test Script
 * Script Name   : <b>ResetVariablesCondicionantes</b>
 * 0) OK/NOK 1) CPnn_CD1  2) ambiente 
 * ej res  CP56_CD1 QA
 * https://www.mkyong.com/java/how-to-delete-file-in-java/
 * https://www.tutorialspoint.com/java/io/file_delete.htm
 */
public class ResetVariablesCondicionantes extends ResetVariablesCondicionantesHelper
{
	public void testMain(Object[]  argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		String filename="DPC_" + argu[1].toString();
		System.out.println("FileName=" + filename);
		
		argu[0]="NOK";

		String sPath_DP = getCurrentProject().getLocation();
		sPath_DP = sPath_DP.concat("\\\\Datos").concat("\\\\Condicionantes\\\\").concat(argu[2].toString()).concat("\\\\");
		String sFileCaso =filename  + ".rftdp";
		System.out.println("Path DP Condicionates = " + sPath_DP+ 
				" File de Entrada = " + sFileCaso);
		// Se elimina el file del caso
    	try{

    		File file = new File(sPath_DP+sFileCaso);

    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}

    	}catch(Exception e){ e.printStackTrace();}
		
		// dp modelo que se usa para reiniciar los data pools de cada caso
		String sFileModelo = "DPC_Modelo.rftdp";
		
		java.io.File dpFile_Modelo = new java.io.File(sPath_DP,sFileModelo);
		java.io.File dpFile_Caso = new java.io.File(sPath_DP,sFileCaso);
		
		DatapoolFactory dpFactory = DatapoolFactory.get();
		IDatapool dataPool = dpFactory.loadForEdit(dpFile_Modelo, true);
		
		DatapoolFactory.get().saveAs((org.eclipse.hyades.edit.datapool.IDatapool) dataPool, dpFile_Caso); 
		dpFactory.unload(dataPool);

		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}
