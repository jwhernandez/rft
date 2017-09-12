package Scripts.Comun;
import libreria.utileria.Tipo;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.Scripts.Comun.BuscarCategoriasPlanHelper;
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
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Busca en el dp central las categorias asociadas a un plan
 * @author SS
 * @since  2017/01/16
 * ult modif 
 * ss 07/3/2017 se agrega la accion para poder validar que un plan no existe en una categoria
 * Para el tema de clientes morosos o de otras categorias crediticias que no veran ciertos planes

 * Script Name   : <b>BuscarCategoriasPlan</b>
 * Parámetros: 0) OK/NOK  1) plan 2) Ambiente 3) Accion 4) cant categorias 5) Categ 1 
 * 6) Categ 2 7) Categ 3  8) Categ 4 9) Categ 5
 * Ej res "PLAN CONVERSON 1 12 M" QA res res res res res res
 * ss 21 8 2017 se cambia nombre proyecto para bat
 */
public class BuscarCategoriasPlan extends BuscarCategoriasPlanHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="OK";
		boolean sEncontro=false;
		
		String sPath_USR = getCurrentProject().getLocation();
		String Name =  getCurrentProject().getName();
		sPath_USR = sPath_USR.replace(Name,Name+"_USR"); 
		//sPath_USR = sPath_USR.replace("RFT_ICE","RFT_ICE_USR");
		sPath_USR = sPath_USR.concat("\\\\Catalogo").concat("\\\\").concat(argu[2].toString()).concat("\\\\"); // modif feb 2017 para poder usar cualqiuer nombre de proyecto
		String sFile_USR = "DP_Categorias" + ".rftdp";
		System.out.println("DP Salida en USR=" + sPath_USR + "-" +sFile_USR);
		
		// Itera el data pools de datos del caso para buscar la row correcta CPnn_CDi
		java.io.File dpFile_DP = new java.io.File(sPath_USR,sFile_USR);
		IDatapool dp_DatosVar =dpFactory().load(dpFile_DP, true);
		IDatapoolIterator dp_DatosVarI = dpFactory().open(dp_DatosVar, null);
		dp_DatosVarI.dpInitialize(dp_DatosVar);
		
		dp_DatosVarI.dpReset();
		

		while (!dp_DatosVarI.dpDone() &&  !sEncontro)	
		{
			if (dp_DatosVarI.dpString("Plan").equals(argu[1])) 
			{
				argu[4]= dp_DatosVarI.dpString("CantCats"); // ss 07/03/2017 se desplaza en 1
				
				int Cats=Integer.parseInt(argu[4].toString()); // ss 07/03/2017 se desplaza en 1
				for (int i = 1; i <= Cats; i++) 
				{
					argu[i+4]=dp_DatosVarI.dpString("Cat"+i); // ss 07/03/2017 se desplaza en 1
					System.out.println("argu["+i+"]="+ argu[i+3]);
				}
				sEncontro = true;	
			}
			dp_DatosVarI.dpNext();
		} 
		// fin de si encontro el plan en el dp central
		if (!sEncontro) argu[0]="NOK";
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

