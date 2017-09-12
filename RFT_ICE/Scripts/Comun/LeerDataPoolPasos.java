package Scripts.Comun;
import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.Scripts.Comun.LeerDataPoolPasosHelper;

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
 * Description   : Lee el data pool de pasos individuales y lo inserta en el dp de pasos maestros
 * @author rft
 * Script Name   : <b>LeerDataPoolPasos</b>
 * @since  2017/04/24
 * ej res CPPC QA 
 */
public class LeerDataPoolPasos extends LeerDataPoolPasosHelper
{
	public void testMain(Object[] argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="NOK";
		String sTramite = "1";
		String sClave = argu[1].toString() +"_T" + sTramite; // CPnn_CDi_Tj
		boolean bMasTramites=true;

		// Leer data pool individual de datos
		String sPath_DP = getCurrentProject().getLocation();
		//sPath_DP = sPath_DP.replace("RFT_ICE","RFT_ICE_USR"); // ss feb 2017 se cambia el nombre para poder aceptar cualquier nombre de proyecto
		sPath_DP = sPath_DP.concat("_USR\\\\Regresion").concat("\\\\").concat(argu[2].toString()).concat("\\\\");
		String sFile =argu[1].toString()  + ".rftdp";

		System.out.println("Path DP Entrada = " + sPath_DP+
				" File de Entrada = " + sFile);

		java.io.File dpFile_DP = new java.io.File(sPath_DP,sFile);
		IDatapool dp_DatosVar =dpFactory().load(dpFile_DP, true);
		IDatapoolIterator dp_DatosVarI = dpFactory().open(dp_DatosVar, null);
		dp_DatosVarI.dpInitialize(dp_DatosVar);
		
		dp_DatosVarI.dpReset();
		while (bMasTramites){
			sClave = argu[1].toString() +"_T" + sTramite; // CPnn_CDi_Tj
			// Buscar en el data pool maestro el registro para el caso y tramite
			System.out.println("***********************************************************");
			System.out.println("Tramite=" + sTramite);
			// Itera el data pools de datos del caso para buscar la row correcta que tenga CPnn_CDi_Tj
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(sClave) && 
					dpString("Ambiente").equals(argu[2]))) {
				dpNext(); 	} 
			System.out.println("NumeroCaso_ConjuntoDatos_Tramite = " + dpString("NumeroCaso"));
			System.out.println("Ambiente = " + dpString("Ambiente"));
			System.out.println("***********************************************************");
		
			int iCantVar=0;
			while (!dp_DatosVarI.dpDone() && 
					dp_DatosVarI.dpString("Tipo").equals("Entrada") &&
					!(dp_DatosVarI.dpString("Dato").equals(argu[3].toString()))
					&& dp_DatosVarI.dpString("Tramite").equals(sTramite)
					) {

				iCantVar = Integer.parseInt(dp_DatosVarI.dpString("Cant"));
				String sDato = dp_DatosVarI.dpString("Dato");
				String sValor = dp_DatosVarI.dpString("Valor");
				System.out.println("--------------------------------------------------------------------");
				System.out.println("Dato = " + sDato);
				System.out.println("Cantidad Variables = " + iCantVar);
				System.out.println("Valor = " + sValor);
				for (int j=1; j<=iCantVar; j++) {
					sDato = dp_DatosVarI.dpString("Variable"+j);
					System.out.println("Variable" + j + "= " + sDato);

					// grabo en data pool maestro
					setDatapool(sDato, sValor ); 
					storeDatapool();
					// Control 
					String sDatoControl = dpString(sDato);
					System.out.println("Dato " + sDatoControl + " leido del dp = "+ sDatoControl);
					if (!sDatoControl.equals(sValor))
					{
						setDatapool(sDato, sValor ); 
						storeDatapool();
					}
				}
				dp_DatosVarI.dpNext(); 
			} // fin iteración de un tramite
			
			if(!dp_DatosVarI.dpDone() && !dp_DatosVarI.dpString("Tramite").equals(sTramite))
					sTramite = dp_DatosVarI.dpString("Tramite"); 
			else 	bMasTramites = false;
		}
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

