package Scripts.Comun;
import libreria.utileria.Tipo;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;
import org.eclipse.hyades.edit.*; 
import org.eclipse.hyades.edit.datapool.IDatapoolCell;

import resources.Scripts.Comun.GrabarDatosSalidaExternoHelper;
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
 * Description   : Graba en el data pool de datos de salida del caso y ambiente indicados en los
 * argumentos.
 * Script Name   : <b>GrabarDatoSalida</b>
 * @since  2016/05/18
 * @author SS
 * @Param 0) OK/NOK 1) Nro CP 2) Ambiente 3) Variable a grabar
 * Solo procesa los datos del tipo salida. No procesa los datos del tipo entrada.
 * ej res CP46 PREQA 
 */
public class GrabarDatosSalidaExterno extends GrabarDatosSalidaExternoHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		String[] Path;
		Path = new String[2];
		String sCaso=argu[1].toString();
		String sAmbiente=argu[2].toString();
		String sDatoaGrabar = argu[3].toString();
		String sValor = null;
		
		/** lee la variabel gloabal */
		switch (sDatoaGrabar) 
		{
		case "NroPedido":
			sValor = getNroPedido();
			break;
		case "NroServicio":
			sValor = getNroServicio();
			break;
		case "NroCtaFact":
			sValor = getNroCtaFact();
			break;
		case "NomListaEspecial":
			sValor = getNomListaEspecial();
			break;
		case "UsuarioALT":
			sValor = getUsuarioAlt();
			break;
		case "ClaveALT":
			sValor = getClaveAlt();
			break;
		case "FechaCM":
			sValor = getFechaCM();
			break;
		case "TaskIdCM":
			sValor = getTaskIdCM();
			break;
		default:
			System.out.println("Opcion seleccionada no valida");
			break;
		} // fin del switch
		
		System.out.println("Opcion= " + sValor);
		
//		/**
//		 * Itera el data pools de datos del caso para buscar la row correcta
//		 */
//		
//		dpReset();
//		while (!dpDone() &&  !(dpString("NumeroCaso").equals(argu[1]) && 
//				dpString("Ambiente").equals(argu[2]))) {
//			dpNext(); 
//		} 
//		
//		System.out.println("NumeroCaso = " + dpString("NumeroCaso"));
//		System.out.println("Ambiente = " + dpString("Ambiente"));
//		// "C:\\Users\\Sandra\\IBM\\rationalsdp\\workspace\\RFT_ICE_DATOS\\
		
		/** lee el path del dp a grabar e inicializa el dp */
		callScript("Scripts.Comun.LeerPath", Path);
		String sPath_DP= Path[1].toString().concat("Regresion").concat("\\\\").concat(sAmbiente).concat("\\\\");
		String sFile = sCaso + ".rftdp";
		
		System.out.println("Path DP Entrada = " + sPath_DP+ " File de Entrada = " + sFile);
					
		java.io.File dpFile_DP = new java.io.File(sPath_DP,sFile);
		IDatapool dp_DatosVar =dpFactory().load(dpFile_DP, true);
		IDatapoolIterator dp_DatosVarI = dpFactory().open(dp_DatosVar, null);
		dp_DatosVarI.dpInitialize(dp_DatosVar);
		
		dp_DatosVarI.dpReset();
 
		while (!dp_DatosVarI.dpDone() && 
				dp_DatosVarI.dpString("Tipo").equals("Salida") &&
				!(dp_DatosVarI.dpString("Variable1").equals(sDatoaGrabar))) 
		{

			String sDato = dp_DatosVarI.dpString("Dato");
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Dato = " + sDato);

			// grabo en data pool el dato de salida
			IDatapoolCell aCell = (IDatapoolCell)dp_DatosVarI.
                    dpCurrent().getCell(Valor[i]);
			dp_DatosVarI., arg1), arg1); 
			dp_DatosVarI.storeDatapool();
			// Control 
			String sDatoControl = dpString(sDato);
			System.out.println("Dato " + sDatoControl + " leido del dp = "+ sDatoControl);
			if (!sDatoControl.equals(sValor))
			{
				setDatapool(sDato, sValor ); 
				storeDatapool();
			}
			String sValor = dp_DatosVarI.dpString("Valor");
			System.out.println("Valor = " + sValor);

			}
			dp_DatosVarI.dpNext(); 
		} 
//		if(dp_DatosVarI.dpString("Dato").equals(argu[1].toString())) {
//			String sDato = dp_DatosVarI.dpString("Dato");
//			System.out.println("El valor del data pool encontrado es: " + sDato);
//			System.out.println("El valor del data pool encontrado es: " + iCantVar);
//			
//			for (int j=1; j<=iCantVar; j++) {
//				sDato = dp_DatosVarI.dpString("Variable"+j);
//				System.out.println("El valor del data pool encontrado es: " + sDato);
//			}
//		}
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

