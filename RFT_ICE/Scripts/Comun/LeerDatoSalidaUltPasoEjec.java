package Scripts.Comun;
import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.LeerDatoSalidaUltPasoEjecHelper;
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
 * Description   : Lee el data pool de datos de salida del caso y ambiente indicados en los
 * argumentos CPnn_CDi - Lee el último paso ejecutado correctamente así como el último
 * trámite ejecutado correctamente y los guarda como variables globales.
 * Script Name   : <b>LeerDatosSalidaUltPasoEjec</b>
 * @since  2016/09/13
 * @author SS
 * @Param 0) OK/NOK 1) Nro CP (CPnn_CDi) 2) Ambiente 
 * Solo procesa los datos del tipo salida. No procesa los datos del tipo entrada.
 * ej res GRAL_CD1 QA 
 * ult modif ss 13 7 2017 se vuelve a nombre rigido de proyecto temporariamente hasta resolver bat
 *           ss 14 8 2017 se vuelve a nombre flexible el nombre del proyecto con prueba para bat
 */
public class LeerDatoSalidaUltPasoEjec extends LeerDatoSalidaUltPasoEjecHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="OK";
		String sScriptName=getScriptName().toString(); // 22/11/2016

		String sDatoaLeer = null;
		
		String sPath_USR = getCurrentProject().getLocation();
		
		String Name =  getCurrentProject().getName();
		sPath_USR = sPath_USR.replace(Name,Name+"_USR"); 
		
		System.out.println("Path DP Entrada = " + sPath_USR);
		
		//sPath_USR = sPath_USR.replace("RFT_ICE","RFT_ICE_USR"); // ss feb 2017 se cambia el nombre para poder aceptar cualquier nombre de proyecto
		sPath_USR = sPath_USR.concat("\\\\Regresion").concat("\\\\").concat(argu[2].toString()).concat("\\\\");
		String sFile_USR = "_SeguimientoEjecucion" + ".rftdp";
		System.out.println("DP Salida en USR=" + sPath_USR + "-" +sFile_USR);
		
		// Itera el data pools de datos del caso para buscar la row correcta CPnn_CDi
		java.io.File dpFile_DP = new java.io.File(sPath_USR,sFile_USR);
		IDatapool dp_DatosVar =dpFactory().load(dpFile_DP, true);
		IDatapoolIterator dp_DatosVarI = dpFactory().open(dp_DatosVar, null);
		dp_DatosVarI.dpInitialize(dp_DatosVar);
		
		dp_DatosVarI.dpReset();

		while (!dp_DatosVarI.dpDone() && 
				!(dp_DatosVarI.dpString("NumeroCaso").equals(argu[1]) && 
						dp_DatosVarI.dpString("Ambiente").equals(argu[2])))	
		{
			dp_DatosVarI.dpNext(); 
		} 
		
		
		System.out.println("NumeroCaso = " + dp_DatosVarI.dpString("NumeroCaso"));
		System.out.println("Ambiente = " + dp_DatosVarI.dpString("Ambiente"));

		if (!dp_DatosVarI.dpDone() &&  
				(dp_DatosVarI.dpString("NumeroCaso").equals(argu[1]) && 
						dp_DatosVarI.dpString("Ambiente").equals(argu[2])))	
		{
			// setea las variables globales
			sDatoaLeer= dp_DatosVarI.dpString("UltPasoOK");
			if (!sDatoaLeer.equals("NA")) {
				setUltimoPasoOk(sDatoaLeer);
				infoPaso(sScriptName, Tipo.DATO,"UltimoPasoOK",getUltimoPasoOk());
				System.out.println("UltimoPasoOK"+getUltimoPasoOk());	
			}		

			sDatoaLeer= dp_DatosVarI.dpString("UltTramite");
			if (!sDatoaLeer.equals("NA")) {
				setUltimoTramite(sDatoaLeer);
				infoPaso(sScriptName, Tipo.DATO,"UltimoTramite",getUltimoTramite());
				System.out.println("UltimoTramite"+getUltimoTramite());		
			}

		} // fin de si encontro la variable en el DP de datos externos
		else 		argu[0]="NOK";

		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

