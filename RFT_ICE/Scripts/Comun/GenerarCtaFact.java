package Scripts.Comun;
import resources.Scripts.Comun.GenerarCtaFactHelper;
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
* Descripción: Genera cta fact
* Parámetros: devuelve el resultado
* SS Nov 2015
*/
public class GenerarCtaFact extends GenerarCtaFactHelper
{
	public void testMain(Object[] argum) throws RationalTestException
	{
		String[] Validaciones;
		Validaciones = new String[3];
		
		GenCtaFact().performAction();
		argum[0] = "OK";

		Validaciones[0]="DPM0004";
		callScript("Scripts.Comun.ValidarMensaje", Validaciones);

		// Validar que sea el mensaje deseado 
		  if (Validaciones[1].toString() == "false") {
		     System.out.println("Terminar Script"+ Validaciones[2].toString());
				argum[0] = "NOK";
		  }
	}
}

