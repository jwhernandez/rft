package Scripts.Comun;
import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.Scripts.Comun.IngresarDataPoolPasosHelper;

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
 * Description   : Lee un data pool de pasos individual de un caso y lo agrega al data pool general de pasos.
 * La idea es usarlo para release management.
 * @author SS
 * Script Name   : <b>IngresarDataPoolPasos</b>
 * @since  2016/05/27
 * @Param 0) OK/NOK 1)nro de caso
 * Ej res CP03
 * Última modificación VC 20170528 Se añade lógica para que agregue las lineas en el dp de forma automática en caso de que se acaben 
 */
public class IngresarDataPoolPasos extends IngresarDataPoolPasosHelper
{
	public void testMain(Object[] argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";

		String sCaso = argu[1].toString();
		System.out.println("NumeroCaso = " + sCaso); // nro de caso

		String sPath_DP= getOption(IOptionName.DATASTORE).toString().concat("\\\\").concat("ConfiguracionPasosEscenarios");
		String sFile = "DP_Pasos_"+ sCaso + ".rftdp";
		
		System.out.println("Path DP Entrada = " + sPath_DP+
							" File de Entrada = " + sFile);
		
		java.io.File dpFile_DP = new java.io.File(sPath_DP,sFile);
		IDatapool dp_DatosVar =dpFactory().load(dpFile_DP, true);
		IDatapoolIterator dp_DatosVarI = dpFactory().open(dp_DatosVar, null);
		dp_DatosVarI.dpInitialize(dp_DatosVar);
		
		dp_DatosVarI.dpReset();
		
		String sCasoPasos = sCaso + "Pasos" ;
		String sCasoParam = sCaso + "Param" ;
		String sCasoEjecuta = sCaso + "Ejecuta" ;
		String sCasoErrorStop = sCaso + "ErrorStop" ;
		String sCasoCond = sCaso + "Cond" ;
		String sCasoReEjec = sCaso + "ReEjec" ;
		
		/**
		 * Itera el data pools de datos global para grabar cada row
		 * Itera el data pool específico para leer cada row
		 */
		
		dpReset();
		System.out.println(dpDone());
		System.out.println(dp_DatosVarI.dpDone());
		while (!dpDone() && !dp_DatosVarI.dpDone()) {
			
			String sCasoPasosValor = dp_DatosVarI.dpString(sCasoPasos);
			String sCasoParamValor = dp_DatosVarI.dpString(sCasoParam);
			String sCasoEjecutaValor = dp_DatosVarI.dpString(sCasoEjecuta);
			String sCasoCondValor = dp_DatosVarI.dpString(sCasoCond);
			String sCasoErrorStopValor = dp_DatosVarI.dpString(sCasoErrorStop);
			String sCasoReEjecValor = dp_DatosVarI.dpString(sCasoReEjec);
			
			System.out.println("--------------------------------------------------------------------");
			System.out.println(sCasoPasos+"="+sCasoPasosValor);
			System.out.println(sCasoParam + "=" +sCasoParamValor);
			System.out.println(sCasoEjecuta+ "=" +sCasoEjecutaValor);
			System.out.println(sCasoCond+ "=" +sCasoCondValor);
			System.out.println(sCasoErrorStop+ "=" +sCasoErrorStopValor);
			System.out.println(sCasoReEjec+ "=" +sCasoReEjecValor);

			// grabo en data pool genérico
			setDatapool(sCasoPasos, sCasoPasosValor ); 
			setDatapool(sCasoParam, sCasoParamValor ); 
			setDatapool(sCasoEjecuta, sCasoEjecutaValor ); 
			setDatapool(sCasoCond, sCasoCondValor ); 
			setDatapool(sCasoErrorStop, sCasoErrorStopValor ); 
			setDatapool(sCasoReEjec, sCasoReEjecValor ); 
			try {
				storeDatapool();
			} catch (Exception e) {
				try {
					storeDatapool();
				} catch (Exception e2) {
					System.out.println("No se puedo generar el data pool. Volver a intentar");
				}
			}
			dp_DatosVarI.dpNext(); 
			dpNext(); 
			
			// VC 20170528 Se añade lógica para que agregue las lineas en el dp de forma automática en caso de que se acaben 
			if(dpDone() && !dp_DatosVarI.dpDone()){
				String[] agregarLinea = new String[4];
				agregarLinea[1] = getCurrentProject().getLocation().concat("\\\\Datos").concat("\\\\");
				agregarLinea[2] = "DP_Pasos.rftdp";
				agregarLinea[3] = "final";
				try{
					callScript("Scripts.Comun.AgregarLineaDP", agregarLinea);
				} catch(Exception e) {
					System.out.println("Error al añadir linea en el dp de pasos. Error: " + e);
				}
				if(agregarLinea[0].endsWith("NOK")) System.out.println("Error al añadir linea en el dp de pasos.");
				else System.out.println("Se añadió un nuevo registro el dp de pasos.");
			}
		} 
		
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

