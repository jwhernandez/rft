package Scripts.Comun;
import libreria.utileria.Tipo;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.Scripts.Comun.LeerDatosSalidaExternoHelper;
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
 * argumentos y los guarda como variables globales.
 * Script Name   : <b>LeerDatosSalida</b>
 * @since  2016/05/18
 * @author SS
 * @Param 0) OK/NOK 1) Nro CP 2) Ambiente 
 * Solo procesa los datos del tipo salida. No procesa los datos del tipo entrada.
 * ej res CP46 PREQA 
 */
public class LeerDatosSalidaExterno extends LeerDatosSalidaExternoHelper
{

	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		String[] Path;
		Path = new String[2];
		String sScriptName=getScriptName().toString(); // 22/11/2016
		
		/** lee el path del DP e incia la lectura*/
		callScript("Scripts.Comun.LeerPath", Path);
		String sPath_DP= Path[1].toString().concat("Regresion").concat("\\\\").concat(dpString("Ambiente")).concat("\\\\");
		String sFile = dpString("NumeroCaso") + ".rftdp";
		System.out.println("Path DP Entrada = " + sPath_DP+ " File de Entrada = " + sFile);
					
		java.io.File dpFile_DP = new java.io.File(sPath_DP,sFile);
		IDatapool dp_DatosVar =dpFactory().load(dpFile_DP, true);
		IDatapoolIterator dp_DatosVarI = dpFactory().open(dp_DatosVar, null);
		dp_DatosVarI.dpInitialize(dp_DatosVar);
		
		dp_DatosVarI.dpReset();
 
		while (!dp_DatosVarI.dpDone() && 
				dp_DatosVarI.dpString("Tipo").equals("Salida") &&
				!(dp_DatosVarI.dpString("Dato").equals(argu[3].toString()))) 
		{

			String sDato = dp_DatosVarI.dpString("Variable1");
			String sValor = dp_DatosVarI.dpString("Valor");
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Dato = " + sDato);
			System.out.println("Valor = " + sValor);

			/**
			 * Itera el data pools de variables gloables para buscar la row correcta
			 */
			
			dpReset();
			while (!dpDone() &&  !(dpString("NombreDP").equals(sDato))) {
				dpNext(); 
			} 
			
			if (!dpDone() &&  (dpString("NombreDP").equals(sDato))){
				System.out.println("Se procesa variable"  + dpString("NombreDP")
						+ dpString("VariableGlobal"));
				
				// setea las variables globales
				switch (dpString("VariableGlobal")) 
				{
				case "NroPedido":
					setNroPedido(sValor);
					infoPaso(sScriptName, Tipo.DATO,"NroPedido",getNroPedido());
					System.out.println("NroPedido"+getNroPedido());			
					break;
				case "NroServicio":
					setNroServicio(sValor);
					infoPaso(sScriptName, Tipo.DATO,"NroServicio",getNroServicio());
					System.out.println("NroServicio"+getNroServicio());			
					break;
				case "NroCtaFact":
					setNroCtaFact(sValor); 
					infoPaso(sScriptName, Tipo.DATO,"NroCtaFact",getNroCtaFact());
					System.out.println("NroCtaFact"+getNroCtaFact());		
					break;
				case "NomListaEspecial":
					setNomListaEspecial(sValor); 
					infoPaso(sScriptName, Tipo.DATO,"NomListaEspecial",getNomListaEspecial());
					System.out.println("NomListaEspecial"+getNomListaEspecial());
					break;
				case "UsuarioALT":
					setUsuarioAlt(sValor); 
					infoPaso(sScriptName, Tipo.DATO,"Usuario Alternativo",getUsuarioAlt());
					System.out.println("Usuario Alternativo"+getUsuarioAlt());
					break;
				case "ClaveALT":
					setClaveAlt(sValor); 
					infoPaso(sScriptName, Tipo.DATO,"Usuario Alternativo",getClaveAlt());
					System.out.println("Usuario Alternativo"+getClaveAlt());
					break;
				case "FechaCM":
					setFechaCM(sValor); 
					infoPaso(sScriptName, Tipo.DATO,"Fecha CM",getFechaCM());
					System.out.println("Fecha CM"+getFechaCM());
					break;
				case "TaskIdCM":
					setTaskIdCM(sValor); 
					infoPaso(sScriptName, Tipo.DATO,"Id Task",getTaskIdCM());
					System.out.println("Id Task"+getTaskIdCM());
					break;
				default:
					System.out.println("Opcion seleccionada no valida");
					break;
				} // fin del switch

				} // fin de si encontro la variable en el DP de datos externos
			dp_DatosVarI.dpNext(); 
		} // cierre del loop de lectura de datos de salida

		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

