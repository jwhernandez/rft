package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fEsperaEstadoREDHelper;
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
 * Script Name   : <b>fEsperaEstadoRED</b>
 * Descripcion   : EPermite esperar un tiempo parametrico a que se produzca un cambio 
 * de estado del RED. Adem�s de esperar que el estado sea igual al deseado, el nro de 
 * RED debe ser distinto de blanco, caso contrario sigue esperando que el red aparezca.
 * El n�mero de RED se puede guardar si se agrega la opci�n +REDValido. Caso contrario el red
 * no se guarda en la variable global.
 * Esto es para permitir reversar un red que no tiene la opcion +REDValido y luego comparar
 * el numero de red generado con la reversi�n con el que est� en la variable global.
 * @author SS
 * @Param 0)IN nombre del caso 1) Estado a esperar 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * @since  2016/05/23
 * Ej CP03 "Por pagar+REDValido" QA true
 * Precondiciones Estar en la pantalla del pedido 
 */
public class fEsperaEstadoRED extends fEsperaEstadoREDHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
		String sScriptName=getScriptName().toString(); // 22/11/2016

		String[] EsperaEstadoRED;
		EsperaEstadoRED = new String[4];
		//0)OK / NOK 1) Estado esperado 2)Tramite 3) NroRED o NA
		
		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable
		
		String sEstado = null;
		String sTipoRED = "RED";
		int iEstado = args[1].toString().indexOf("+");
		if (iEstado !=-1) 
		{
			sEstado = args[1].toString().substring(0, iEstado).trim();
			System.out.println("Estado a esperar=*" + sEstado +"*");
			sTipoRED = args[1].toString().substring(iEstado+1).trim();
			System.out.println("Tipo RED:*" + sTipoRED +"*");
		} else 
		{
			sEstado = args[1].toString().trim();
			System.out.println("Estado a esperar=*" + sEstado +"*");
			System.out.println("Tipo RED:*" + sTipoRED +"*");
		}

		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		EsperaEstadoRED[1] = sEstado;
		EsperaEstadoRED[2] = dpString("Tramite"); 
		callScript("Scripts.Comun.EsperaEstadoRED", EsperaEstadoRED);
		
		if (EsperaEstadoRED[0].toString().equals("NOK")) {	
			//MensError[0] = "Problema en el cambio de estado del RED";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		} 
		if (EsperaEstadoRED[1].toString().toLowerCase().equals("por pagar")) 
		{
			if (sTipoRED.toLowerCase().equals("redvalido")) 
			{
				// grabar el nro de red recibido
				setNroRED(EsperaEstadoRED[3].toString());

				int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj  / CPnn_CDi_Tj  ss modif feb 2017 para adaptarlo al nuevo framework mulitramite 
				DatoSalida[1]= args[0].toString().substring(0, sLong-3);
				DatoSalida[2]=args[2].toString(); // nro ambiente
				DatoSalida[3]="T"+ getUltimoTramite()+"_NroRED"; // Nombre variable
				DatoSalida[4]=EsperaEstadoRED[3].toString(); // Valor de la variable
				System.out.println("El escenario tiene su red por pagar");
				callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
			}
			infoPaso(sScriptName, Tipo.DATO,"NroRED",EsperaEstadoRED[3].toString());
		}
		if (EsperaEstadoRED[1].toString().equals("Pagado")) 
		{
			if (sTipoRED.toLowerCase().equals("redvalido")) 
			{
				// grabar el nro de red recibido
				setNroRED(EsperaEstadoRED[3].toString());

				int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj  / CPnn_CDi_Tj  ss modif feb 2017 para adaptarlo al nuevo framework mulitramite 
				DatoSalida[1]= args[0].toString().substring(0, sLong-3);
				DatoSalida[2]=args[2].toString(); // nro ambiente
				DatoSalida[3]="T"+ getUltimoTramite()+"_NroRED"; // Nombre variable
				DatoSalida[4]=EsperaEstadoRED[3].toString(); // Valor de la- variable
				System.out.println("El escenario tiene su red pagada");
				callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
				System.out.println("Se ha llamado al script");
			}
			infoPaso(sScriptName, Tipo.DATO,"NroRED",EsperaEstadoRED[3].toString());
		}
	}
}

