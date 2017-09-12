package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fEsperaEstadoHelper;
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
 * Script Name   : <b>fEsperaEstado</b>
 * Descripcion   : Permite esperar un tiempo parametrico a que se produzca un cambio 
 * de estado del pedido
 * @author SS
 * @Param 0)IN nombre del caso 1) Estado a esperar 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * @since  2015/12/27
 * Precondiciones Estar en la pantalla del pedido 
 */
public class fEsperaEstado extends fEsperaEstadoHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] EsperaEstado;
		EsperaEstado = new String[3];
		//0)Estado esperado 1)OK / NOK 2)Tramite

		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		EsperaEstado[0] = args[1].toString();
		EsperaEstado[2] = dpString("Tramite"); 
		callScript("Scripts.Comun.EsperaEstado", EsperaEstado);
		
		if (EsperaEstado[1].toString().equals("NOK")) {	
			//MensError[0] = "Problema en el cambio de estado";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

