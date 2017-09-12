package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidaCambioPlanFactHelper;
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
 * Description   : Recibe si debe estar habilitado o no la opción del menu y realiza la validación
 * @author SS
 * Script Name   : <b>fValidaCambioPlanFact</b>
 * @since  2016/05/02
 * @Param 0)CP 1) Opciones (ej "Actualizar promoción=false" 2) ambiente 3) true/false 4)nro paso
 * ej CP50 "Actualizar promoción=false" PREQA true 20
 */
public class fValidaCambioPlanFact extends fValidaCambioPlanFactHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidaCP;
		ValidaCP = new String[3];
		// 0)OK/NOK 1)opcion 2) estado

		String[] MensError;
		MensError = new String[4];

		int iOpcion = args[1].toString().indexOf("=");
		String sOpcion = args[1].toString().substring(0, iOpcion).trim();
		System.out.println("Opcion a ejeuctar:*" + sOpcion +"*");
		String sEstadoEsperado = args[1].toString().substring(iOpcion+1).trim();
		System.out.println("Estado a Esperar a ejeuctar:*" + sEstadoEsperado +"*");

		ValidaCP[1]=sOpcion;
		ValidaCP[2]=sEstadoEsperado;
		callScript("Scripts.Comun.ValidaCambioPlanFact",ValidaCP);


		if  (ValidaCP[0].toString().equals("NOK")){
			//MensError[0] = "Validacion Cambio de plan en cta facturación falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);

		}
	}
}

