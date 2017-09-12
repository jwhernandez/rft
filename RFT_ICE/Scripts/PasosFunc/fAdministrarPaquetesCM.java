package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAdministrarPaquetesCMHelper;
import Scripts.Comun.AdministrarPaquetesCM;
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
 * Description   : Selección de paquete de Voz o SMS en CM
 * @author SS
 * Script Name   : <b>fAdministrarPaquetesCM</b>
 * @since  2017/02/08
 * ej CP20_CD1_T1 1 QA res res 
 */
public class fAdministrarPaquetesCM extends fAdministrarPaquetesCMHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] Paquetes;
		Paquetes = new String[5];
		// @Argumentos 0) OK/NOK  1) Opcion (Voz / SMS) 2) NombrePaquete 3) Encender / Apagar / Validar  4) EstadoaValidar
		// 4)EstadoDeseado (Encendido / Apagado)
		 
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		
		int i = Integer.parseInt(args[1].toString());
		System.out.println(i);
		Paquetes[1] = dpString("PaqOpcion"+i); 
		Paquetes[2] = dpString("Paquete"+i); 
		Paquetes[3] = dpString("PaqAccion"+i); 
		Paquetes[4] = dpString("PaqEstado"+i); 
		System.out.println(Paquetes);

		callScript(new AdministrarPaquetesCM(), Paquetes);
		

		if  ((Paquetes[0].toString().equals("NOK"))){
			//MensError[0] = "xDefecto";// ""Problemas con administración de la Temporada"
			MensError[0] = "Problemas con administración de los paquetes en CM";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

