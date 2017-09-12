package Scripts.Comun;
import java.io.File;

import org.eclipse.hyades.edit.datapool.IDatapoolCell;
import org.eclipse.hyades.edit.datapool.IDatapoolRecord;
import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;
import resources.Scripts.Comun.AgregarLineaDPHelper;
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
//import com.sun.java.util.jar.pack.Package.File;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
	/**
	 * Script Name   : <b>AgregarLineaDP</b>
	 * Generated     : <b>may. 8, 2017 4:00:11 PM</b>
	 * Description   : Agrega lienas en el datapool enviado por parámetro, puede ser al final o en el indice indicado
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * Parámetros: 0) OK/NOK 1) Ruta del DP 2) Nombre del DP 3) Posición (Número o "final")
	 * 
	 * @since  2017/05/08
	 * @author VC
	 */
public class AgregarLineaDP extends AgregarLineaDPHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "NOK";
		//String sPath_DP = getCurrentProject().getLocation();
		String sPath_DP = argu[1].toString(); //Toma la ruta
		//sPath_DP = sPath_DP.concat("\\\\Anteriores").concat("\\\\");
		//String sFile = "DatosDePrueba.rftdp"; //argu[1].toString()  + ".rftdp";
		String sFile = argu[2].toString(); //Toma el nombre del archivo

		System.out.println("Path DP Entrada = " + sPath_DP+ " File de Entrada = " + sFile);
		
		java.io.File dpFile_DP = new java.io.File(sPath_DP,sFile);
		IDatapool dp_DatosVar =dpFactory().load(dpFile_DP, true);
		IDatapoolIterator dp_DatosVarI = dpFactory().open(dp_DatosVar, null); //Sirve para moverse en el datapool
		
		dp_DatosVarI.dpInitialize(dp_DatosVar);
		
		
		File dpFile = new File(sPath_DP, sFile); 
		IDatapool dp = DatapoolFactory.get().loadForEdit(dpFile, true); 

		int defClassIndex = dp.getDefaultEquivalenceClassIndex();

	
		DatapoolEquivalenceClass dpc = (DatapoolEquivalenceClass)dp.getEquivalenceClass(defClassIndex); 
		
		IDatapoolRecord rec=dpc.constructRecord(null);
		//int index = dp.getVariableIndex("Nombre");
		//((IDatapoolCell)rec.getCell(index)).setCellValue("Nombre");
		
		String sPosicion = argu[3].toString().toLowerCase();
		
		try{
			if(sPosicion.equals("final")) dpc.appendRecord(rec);
			else dpc.insertRecord(rec, Integer.parseInt(sPosicion));
			
			((org.eclipse.hyades.edit.datapool.IDatapool) dp).appendEquivalenceClass(dpc);

			//Saving the datapool
			DatapoolFactory.get().save((org.eclipse.hyades.edit.datapool.IDatapool) dp);
			DatapoolFactory.get().unload(dp);
			System.out.println("Se insertó la linea en el datapool " + sFile + ".");
			argu[0] = "OK";
		}catch(Exception e){
			System.out.println("Error al insertar linea en el datapool " + sFile + ". " + e);
			argu[0] = "NOK";
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

