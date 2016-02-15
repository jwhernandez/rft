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
 * Description   : Functional Test Script
 * @since  2015/12/28
 * @author Sandra
 */
public class fBuscarCtaFact extends fBuscarCtaFactHelper
{
	public void testMain(Object[] args) 
	{
		String[] MensError;
		MensError = new String[4];
		
		String[] CtaFact;
		CtaFact = new String[3];

		CtaFact[0]= getNroServicio();
		System.out.println("NroServicio: " + getNroServicio() );
		logInfo("NroServicio: " + getNroServicio() );
		
		callScript("Scripts.Comun.BuscarCtaFact", CtaFact);
		setNroCtaFact(CtaFact[2].toString());
		System.out.println("NroCtaFact: " + getNroCtaFact() );
		logInfo("NroCtaFact: " + getNroCtaFact() );
		infoPaso(args[0].toString(), Tipo.DATO,"NroCtaFact",getNroCtaFact());

		if (CtaFact[1].toString().equals("NOK")) {
			//MensError[0] = "Activo Detalle no encontrado";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

