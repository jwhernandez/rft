package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fEsperaEventoCMHelper;
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
 * Script Name   : <b>fEsperaEventoCM</b>
 * Descripcion   : Espera en el monitor de ordenes que se cumpla el evento pasado como parametro
 * @author SS
 * @Param 0)IN nombre del caso 1) Opcion 2) IN Ambiente 3) IN ErrorStop (Si / No) 4)nro paso opc
 * @since  2016/04/28
 * param1 = opcion (TramiteVenta Completo / RED Pagado / tramitedesc Completo
 * CP20_CD1_T1 "tramitedesc Completo" QA NA NA
 */
public class fEsperaEventoCM extends fEsperaEventoCMHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] EsperaEstado;
		EsperaEstado = new String[2];
		//0) OK/NOK 1) Evento (TramiteVenta Completo / tramiteventa completo / RED Pagado / )

		EsperaEstado[1] = args[1].toString();
		callScript("Scripts.Comun.EsperaEventoCM", EsperaEstado);
		
		if (EsperaEstado[0].toString().equals("NOK")) {	
			//MensError[0] = "Evento esperado falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

