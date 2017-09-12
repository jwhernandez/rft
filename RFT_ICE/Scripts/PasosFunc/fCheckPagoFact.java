package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fCheckPagoFactHelper;
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
 * Descripcion   : Setea o valida el campo factura en tildado u no según se indique
 * Script Name   : <b>fCheckPagoFact</b>
 * @Param  0) nombre del caso 1) true / false / ValidaTrue / ValidaFalse / Ninguno 2) IN Ambiente 3) IN ErrorStop (Si / No)
 * ej CP46 true/false/Todos/Ninguno  PREQA true 20
 * Ej CP46 Ninguno PREQA true 20
 * CP46 false PREQA true 20
 * true (habilitado y solo un producto) false (inhabilitado y solo un producto)
 * Ninguno (Dejar todos los productos del nodo servicio con tilde en no) 
 * ValidaTrue (Valida si habilitado) ValidaFalse (Valida si inhabilitado)
 * Todos no está implementado, no se sabe si se usará
 * @since  2016/05/04 modificado el 2/10/16 para agregar el validar
 * @author SS
 */
public class fCheckPagoFact extends fCheckPagoFactHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] aFactura;
		aFactura = new String[4];
		//0) OK/NOK 1) true o false 2) NA o Todos 3) Servicio o NA
		//Todo implica sacar todos los tildes  
		//Se dimensiona de 2 porque no se usan los dos ultimos parametros
		
		String[] MensError;
		MensError = new String[4];
		
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		boolean bProcesa = true;
		
		System.out.println("Se procesa la opción: " + args[1].toString());

		switch (args[1].toString().toLowerCase().trim()) {
		case ("true"):
		case ("false"):
			aFactura[1]=args[1].toString().toLowerCase();
			aFactura[2]="SeteoSimple";
			aFactura[3]="NA";
			break;
		case ("ninguno"):
			aFactura[1]="false";
			aFactura[2]="SeteoNinguno";
			aFactura[3]=dpString("Servicio");
			break;
		case ("validafalse"):
			aFactura[1]="false";
			aFactura[2]="Valida";
			aFactura[3]="NA";
			break;
		case ("validatrue"):
			aFactura[1]="true";
			aFactura[2]="Valida";
			aFactura[3]="NA";
			break;

		default:
			System.out.println("La opción es incorrecta");
			bProcesa = false;
			aFactura[0] = "NOK";
			break;
		}

		if (bProcesa) 
			callScript("Scripts.Comun.CheckPagoFact", aFactura);
		
		if  (aFactura[0].toString().equals("NOK")){
			//MensError[0] = "Chequeo de tilde a factura falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

