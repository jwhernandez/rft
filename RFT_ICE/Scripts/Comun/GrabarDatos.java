package Scripts.Comun;
import org.eclipse.hyades.edit.datapool.IDatapoolCell;
import org.eclipse.hyades.edit.datapool.IDatapoolEquivalenceClass;
import org.eclipse.hyades.edit.datapool.IDatapoolRecord;
import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.Scripts.Comun.GrabarDatosHelper;
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
 * @author Sandra
 */
public class GrabarDatos extends GrabarDatosHelper
{
	/**
	 * Script Name   : <b>GrabarDatos</b>
	 * Generated     : <b>15/12/2016 13:48:19</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/12/15
	 * @author Sandra
	 */
	public void testMain(Object[] argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		
		String sDato=null;
		String sDatoAReemp=null;
		String sIterable=null;
		boolean bEncontrado=false;
		String output;
		IDatapoolCell cellValue=null;
		
		String sPath_USR = getCurrentProject().getLocation();
		sPath_USR = sPath_USR.replace("RFT_ICE","RFT_ICE_USR");
		sPath_USR = sPath_USR.concat("\\\\Regresion").concat("\\\\").concat(argu[2].toString()).concat("\\\\");


		// Path del DP en el que va a grabar
		//String sFile_USR_Hasta = argu[3].toString().concat("_").concat(argu[4].toString()) + ".rftdp";
		String sFile_USR_Hasta = argu[3].toString().concat("_").concat(argu[4].toString())+"_All" + ".rftdp";

		System.out.println("DP Hasta=" + sPath_USR + "-" +sFile_USR_Hasta);



		// Leer y escribir dp hasta
		java.io.File dpFile_DP_H = new java.io.File(sPath_USR,sFile_USR_Hasta);
		DatapoolFactory dpFactory = DatapoolFactory.get(); 
		IDatapool dataPoolH = dpFactory.loadForEdit(dpFile_DP_H, true);
		IDatapoolIterator dataPoolIH = dpFactory().open(dataPoolH, null);
		int equivalenceClassIndexH = dataPoolH.getDefaultEquivalenceClassIndex();
		IDatapoolEquivalenceClass equivalenceClassH = (IDatapoolEquivalenceClass)
				dataPoolH.getEquivalenceClass(equivalenceClassIndexH); 
		dataPoolIH.dpInitialize(dataPoolH);
		IDatapoolRecord recordH;

		// Resetear el data pool hasta
		int nRegH = 0;
		dataPoolIH.dpReset();
		// Buscar el dato a reemplazar en el data pool hasta
		bEncontrado = false;
		while (!dataPoolIH.dpDone()  ) 	
		{
			recordH = (IDatapoolRecord) equivalenceClassH.getRecord(nRegH);
			sIterable = recordH.getCell(2).getCellValue().toString();
			sDatoAReemp = recordH.getCell(5).getCellValue().toString();
			if (!sIterable.toLowerCase().equals("no"))
			{
				output = String.format ("%1$50s %2$30s %3$30s"
						, sIterable , sDatoAReemp , sDato);
				System.out.println(output);

				// se reemplaza el contenido del data pool hasta
				cellValue =  (IDatapoolCell) recordH.getCell(0);
				cellValue.setCellValue("1");	
				String sPrueba = cellValue.getCellValue().toString();
				System.out.println("Valor Grabado" + sPrueba);
				//save changes in data pool hasta
				System.out.println("Se graban los cambios");
				dpFactory.save((org.eclipse.hyades.edit.datapool.IDatapool) dataPoolH);
			}
			nRegH++;
			dataPoolIH.dpNext(); 
		}
		//dpFactory.unload(dataPoolH);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

