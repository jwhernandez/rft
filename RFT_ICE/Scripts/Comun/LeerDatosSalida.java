package Scripts.Comun;
import libreria.utileria.Tipo;

import org.eclipse.hyades.execution.runtime.datapool.IDatapool;
import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

import resources.Scripts.Comun.LeerDatosSalidaHelper;
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
 * @Param 0) OK/NOK 1) Nro CP (CPnn_CDi) 2) Ambiente 3) Tramite = Tj  (Ejemplo T1)
 * Solo procesa los datos del tipo salida. No procesa los datos del tipo entrada.
 * ej res CP46 PREQA 	
 * ej res CPVta2_CDX PREQA T1
 * res GRAL_CD2 QA T1
 *  ult modif ss 18-5-2017 se agrega 2nda lista especial
 */
public class LeerDatosSalida extends LeerDatosSalidaHelper
{
	
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="OK";
		String sScriptName=getScriptName().toString(); // 22/11/2016

		String sDatoaLeer = null;

		// Lee el trámite para saber que conjunto de datos leer.
		String sTramite = argu[3].toString();

		// Itera el data pools de datos del caso para buscar la row correcta CPnn_CDi
		try
		{
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(argu[1]) && 
					dpString("Ambiente").equals(argu[2]))) {
				dpNext(); 
			} 

			System.out.println("NumeroCaso = " + dpString("NumeroCaso"));
			System.out.println("Ambiente = " + dpString("Ambiente"));

			if (!dpDone() &&  (dpString("NumeroCaso").equals(argu[1]) && 
					dpString("Ambiente").equals(argu[2])))
			{
				// setea las variables globales

				sDatoaLeer = dpString(sTramite +"_"+ "NroPedido");
				if (!sDatoaLeer.equals("NA")) {
					setNroPedido(sDatoaLeer);
					infoPaso(sScriptName, Tipo.DATO,sTramite +"_"+"NroPedido",getNroPedido());
					System.out.println(sTramite +"_"+"NroPedido="+getNroPedido());	}		
				
				if (!sTramite.equals("T1"))
				{
					sDatoaLeer = dpString(sTramite +"_"+ "NroPedidoAnt");
					if (!sDatoaLeer.equals("NA")) {
						setNroPedidoAnt(sDatoaLeer);
						infoPaso(sScriptName, Tipo.DATO,sTramite +"_"+"NroPedidoAnt",getNroPedidoAnt());
						System.out.println(sTramite +"_"+"NroPedidoAnt="+getNroPedidoAnt());	}	
				}
				
				if (!sTramite.equals("T1") && !sTramite.equals("T2")) // Agregado el 27/10/16
				{
					sDatoaLeer = dpString(sTramite +"_"+ "NroPedidoAntAnt");
					if (!sDatoaLeer.equals("NA")) {
						setNroPedidoAntAnt(sDatoaLeer);
						infoPaso(sScriptName, Tipo.DATO,sTramite +"_"+"NroPedidoAntAnt",getNroPedidoAntAnt());
						System.out.println(sTramite +"_"+"NroPedidoAntAnt="+getNroPedidoAntAnt());	}	
				}


				sDatoaLeer = dpString(sTramite +"_"+ "NroServicio");
				if (!sDatoaLeer.equals("NA")) {
					setNroServicio(sDatoaLeer);
					infoPaso(sScriptName, Tipo.DATO,"NroServicio",getNroServicio());
					System.out.println(sTramite +"_"+"NroServicio="+getNroServicio());		
				}

				sDatoaLeer = dpString(sTramite +"_"+ "NroCtaFact");
				if (!sDatoaLeer.equals("NA")) {
					setNroCtaFact(sDatoaLeer); 
					infoPaso(sScriptName, Tipo.DATO,sTramite +"_"+"NroCtaFact",getNroCtaFact());
					System.out.println(sTramite +"_"+"NroCtaFact="+getNroCtaFact());		
				}

				sDatoaLeer = dpString(sTramite +"_"+ "NroRED");
				if (!sDatoaLeer.equals("NA")) {
					setNroRED(sDatoaLeer); 
					infoPaso(sScriptName, Tipo.DATO,sTramite +"_"+"NroRED",getNroRED());
					System.out.println(sTramite +"_"+"NroRED="+getNroRED());}

				sDatoaLeer = dpString(sTramite +"_"+ "NomListaEspecial");
				if (!sDatoaLeer.equals("NA")) {
					setNomListaEspecial(sDatoaLeer); 
					infoPaso(sScriptName, Tipo.DATO,sTramite +"_"+"NomListaEspecial",getNomListaEspecial());
					System.out.println(sTramite +"_"+"NomListaEspecial="+getNomListaEspecial()); }

				sDatoaLeer = dpString(sTramite +"_"+ "NomListaEspecial_SMS");
				if (!sDatoaLeer.equals("NA")) {
					setNomListaEspecial_SMS(sDatoaLeer); 
					infoPaso(sScriptName, Tipo.DATO,sTramite +"_"+"NomListaEspecial_SMS",getNomListaEspecial_SMS());
					System.out.println(sTramite +"_"+"NomListaEspecial="+getNomListaEspecial_SMS()); }

				
				sDatoaLeer = dpString(sTramite +"_"+ "FechaCM");
				if (!sDatoaLeer.equals("NA")) {
					setFechaCM(sDatoaLeer); 
					infoPaso(sScriptName, Tipo.DATO,sTramite +"_"+"Fecha CM=",getFechaCM());
					System.out.println(sTramite +"_"+"Fecha CM"+getFechaCM()); }
				
				sDatoaLeer = dpString(sTramite +"_"+ "TaskIdCM");
				if (!sDatoaLeer.equals("NA")) {
					setTaskIdCM(sDatoaLeer); 
					infoPaso(sScriptName, Tipo.DATO,sTramite +"_"+"Id Task",getTaskIdCM());
					System.out.println(sTramite +"_"+"Id Task="+getTaskIdCM());}
				
			} // fin de si encontro la variable en el DP de datos externos
			else 		argu[0]="NOK";

		} // end del try
		catch (Exception e){
			logInfo("Mensaje de excepción: "+e.getMessage());
			System.out.println("Mensaje de excepción: "+e.getMessage());
			if (e.getMessage().contains("DatapoolException"))
				System.out.println("Error en el DP");
			setTipoError("Error en el DP");
			setMensajeError(e.getMessage());
			argu[0]="NOK";
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

