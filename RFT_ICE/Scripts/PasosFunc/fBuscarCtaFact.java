package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fBuscarCtaFactHelper;
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
 * Script Name   : <b>fBuscarCtaFact</b>
 * Description   : Permite hacer drilldown al detalle del activo desde la vista 360 de la cuenta
 * @since  2015/12/28
 * @author SS
 * ult modif ss 28 02 2017 se modifica la grabacion de la variable global para el framework MT
 * 	ss 25/4/2017 se agrega opcion en argumento 1 de ir a buscar el nro de servicio al DP
 * argum 1 NA o DP
 */
public class fBuscarCtaFact extends fBuscarCtaFactHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];
		
		String sScriptName=getScriptName().toString(); // 22/11/2016
		
		String[] CtaFact;
		CtaFact = new String[3];
		
		String[] DatoSalida;
		DatoSalida = new String[5];
		// 0) OK/NOK 1) Nro CP 2) Ambiente 3) Nombre variable 4)valor variable

		if (args[1].toString().equals("DP"))
		{
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
					dpString("Ambiente").equals(args[2]))) {
				dpNext(); 
			} 	
			CtaFact[0]= dpString("NumeroServicio");			
		}
		else 
			CtaFact[0]= getNroServicio();
		
		System.out.println("NroServicio: " + CtaFact[0]);
 
		callScript("Scripts.Comun.BuscarCtaFact", CtaFact);

		if (CtaFact[1].toString().equals("NOK")) {
			//MensError[0] = "Activo Detalle no encontrado";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
		
		setNroCtaFact(CtaFact[2].toString());
		
		int sLong = args[0].toString().length(); // nro caso CPnn_CDi_Tj
		DatoSalida[1]= args[0].toString().substring(0, sLong-3);
		DatoSalida[2]=args[2].toString(); // nro ambiente
		DatoSalida[3]="T"+ getUltimoTramite()+"_NroCtaFact"; // Nombre variable
		DatoSalida[4]=CtaFact[2].toString(); // Valor de la variable

		callScript("Scripts.Comun.GrabarDatosSalida",DatoSalida);
		
		System.out.println("NroCtaFact: " + getNroCtaFact() );
		logInfo("NroCtaFact: " + getNroCtaFact() );
		infoPaso(sScriptName, Tipo.DATO,"NroCtaFact",getNroCtaFact());
	}
}

