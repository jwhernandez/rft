package Scripts.Comun;
import resources.Scripts.Comun.LeerDatosEntradaHelper;
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
import java.io.File;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
import org.eclipse.hyades.execution.runtime.datapool.*;

/**
 * Description   : Lee el data pool de datos variables de entrada del caso y ambiente 
 * indicados en los argumentos y los graba en el data pool de datos de entrada global.
 * Script Name   : <b>LeerDatosEntrada</b>
 * @since  2016/05/05
 * Modificaciones: 
 * 		- 13 sep // Se agrega multi-tramite / multi- CD (Conjunto de datos)
 * @author SS
 * @Param 0) OK/NOK 1) Nro CP 2) Ambiente 3) Opción (Dato o Todo) Se puede indicar un dato
 * en particular o usar Todo para indicar que se graben todos los datos de dicho data pool. 
 * Solo procesa los datos del tipo entrada. No procesa los datos del tipo Salida.
 * ej res CP32_CD1 QA Todo
 * ej res CPPC_CD1 QA Todo
 * ult modif ss 13 7 2017 se vuelve a nombre rigido de proyecto temporariamente hasta resolver bat
 *           ss 14 8 2017 se vuelve a nombre flexible el nombre del proyecto con prueba para bat
 */
public class LeerDatosEntrada extends LeerDatosEntradaHelper
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
		System.out.println("Path DP Entrada = " + getCurrentProject().getName());
		String Name =  getCurrentProject().getName();
		sPath_DP = sPath_DP.replace(Name,Name+"_USR"); // ss feb 2017 se cambia el nombre para poder aceptar cualquier nombre de proyecto
		
		System.out.println("Path DP Entrada = " + sPath_DP);
		// sPath_DP = sPath_DP.replace("RFT_ICE","RFT_ICE_USR"); // ss feb 2017 se cambia el nombre para poder aceptar cualquier nombre de proyecto

		sPath_DP = sPath_DP.concat("\\\\Regresion").concat("\\\\").concat(argu[2].toString()).concat("\\\\");

		//sPath_DP = sPath_DP.replace("RFT_ICE","RFT_ICE_USR"); // ss feb 2017 se cambia el nombre para poder aceptar cualquier nombre de proyecto
		//sPath_DP = sPath_DP.concat("_USR\\\\Regresion").concat("\\\\").concat(argu[2].toString()).concat("\\\\");
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

