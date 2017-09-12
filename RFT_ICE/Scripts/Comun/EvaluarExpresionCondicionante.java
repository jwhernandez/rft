package Scripts.Comun;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.Scripts.Comun.EvaluarExpresionCondicionanteHelper;
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
 * Description   : Evalúa la expresión condicionante de un paso
 * @author SS
 * Script Name   : <b>EvaluarExpresionCondicionante</b>
 * @since  2017/05/10
 * 0) OK/NOK 1) Expresion a evaluar 2) ambiente 3) CPnn_CD1 3)Resultado
 * ej: res "%x%*%y%/100" QA CPX1_CD1 res
 */
public class EvaluarExpresionCondicionante extends EvaluarExpresionCondicionanteHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine interprete = manager.getEngineByName("js");

		//String expresion = "x*y/100";
		String expresion = argu[1].toString();
		Boolean salir = false;
		int inicio;
		int fin;
		String filename="DPC_" + argu[3].toString();
		System.out.println("FileName=" + filename);

		// Leer data pool individual de datos
		String sPath_DP = getCurrentProject().getLocation();
		sPath_DP = sPath_DP.concat("\\\\Datos").concat("\\\\Condicionantes\\\\").concat(argu[2].toString()).concat("\\\\");
		String sFile =filename  + ".rftdp";

		System.out.println("Path DP Condicionates = " + sPath_DP+
				" File de Entrada = " + sFile);

		java.io.File dpFile_DP = new java.io.File(sPath_DP,sFile);
		IDatapool dp_DatosVar =dpFactory().load(dpFile_DP, true);
		IDatapoolIterator dp_DatosVarI = dpFactory().open(dp_DatosVar, null);
		dp_DatosVarI.dpInitialize(dp_DatosVar);

		String VarCondicionantes = expresion;
		String VarName=null ; 
		String Valor=null ;
		argu[0]="OK";

		while (!salir) {
			inicio = VarCondicionantes.indexOf("%"); 
			System.out.println("Inicio=" + inicio);
			if (inicio >= 0)
			{
				VarCondicionantes = VarCondicionantes.substring(inicio+1);
				System.out.println("Resto Expresion=" + VarCondicionantes);
				fin = VarCondicionantes.indexOf("%");
				System.out.println("fin=" + fin);
				VarName=VarCondicionantes.substring(0,fin);
				System.out.println("VarName=" + VarName);
				VarCondicionantes = VarCondicionantes.substring(fin+1);
				System.out.println("Resto Expresion=" + VarCondicionantes);

				// Buscar en el DP el nombre de la variable condicionante
				dp_DatosVarI.dpReset();
				Boolean encontrada = false;
				while (!dp_DatosVarI.dpDone() && !encontrada)
				{ 	
					//System.out.println("Variable=" + dp_DatosVarI.dpString("Variable"));
					//System.out.println("Valor=" + dp_DatosVarI.dpString("Valor"));
					if (dp_DatosVarI.dpString("Variable").equals(VarName))
					{
						System.out.println("La variable " + VarName + " fue encontrada en el DP");		
						Valor = dp_DatosVarI.dpString("Valor");
						encontrada = true;
					}
					dp_DatosVarI.dpNext(); 
				} 
				if(encontrada)
				{
					// se reemplaza en la expresión la variable condicionante
					expresion = expresion.replace("%"+VarName+"%","\""+ Valor + "\"" );
					//expresion = expresion.replace("%"+VarName+"%",VarName );
					//interprete.put(VarName,Valor);
					System.out.println("Expresion " + expresion);	
				} else
				{
					System.out.println("La variable " + VarName + " no fue encontrada en el DP");
					salir=true;
					argu[0]="NOK";
				}
			} else salir=true;
		}
		// Reemplezar los valores de la variables condicionates en la expresion
		if (argu[0].toString()=="OK"){
			try 
			{
				String resultado = interprete.eval(expresion.toString()).toString();
				System.out.println("Resultado=" + resultado);
				argu[4]=resultado;

			} catch (ScriptException se) {
				se.printStackTrace();
			}
		}
	}
}

