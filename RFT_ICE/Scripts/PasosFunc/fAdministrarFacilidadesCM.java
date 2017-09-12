package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAdministrarFacilidadesCMHelper;
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
 * Description   : Valida que todas las facilides de CM sean no editables
 * @author SS
 * Script Name   : <b>fAdministrarFacilidadesCM</b>
 * @Param 0) nro caso  1) Param del data pool pasos 2) Ambiente 3) Error para ejecución? 
 * CP20 LISTAR PREQA false
 * Si recibe en el parametro 1 un LISTAR envía LISTAR como facilidad, caso contrario envía el data del data pool de entrada
 * @since  2016/04/13
 */
public class fAdministrarFacilidadesCM extends fAdministrarFacilidadesCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ComprarFac;
		ComprarFac = new String[4];
		// 0) OK/NOK 1) Facilidad a incluir o excluir 2) Accion (Futuros usos) 3)Out Posicion

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

		if (args[1].toString().equals("LISTAR")) ComprarFac[1]="LISTAR";
		else {
			int i = Integer.parseInt(args[1].toString());
			ComprarFac[1]=dpString("PROD"+i);
			ComprarFac[2]=dpString("PROD_Accion"+i);
		}
		callScript("Scripts.Comun.AdministrarFacilidadesCM", ComprarFac);

		if  (ComprarFac[0].toString().equals("NOK")){
			//MensError[0] = "CM control facilidades falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

