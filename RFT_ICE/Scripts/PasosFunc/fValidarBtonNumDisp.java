package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarBtonNumDispHelper;
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
 * Script Name   : <b>fValidarBtonNumDisp</b>
 * Description   : Validar que el botón "Obtener primer número disponible" esté deshabilitado
 * @Param CP50 true PREQA true 20
 * @Param caso param1 ambiente true/false nropaso
 * @since  2016/04/25
 * @author Victor Cordero
 *  ult modif ss 23.3.2017 agregado de port.in
 */
public class fValidarBtonNumDisp extends fValidarBtonNumDispHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
			ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
			
			String[] Validac;
			Validac = new String[3];
			// 0)OUT res OK/NOK 1) IN estado deseado true (Habilitado) / false (Deshabilitado)
			// 2) Tramite
			
			String[] MensError;
			MensError = new String[4];
			
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
					dpString("Ambiente").equals(args[2]))) {
				dpNext(); 
			} 	
			
			Validac[1]=args[1].toString();
			Validac[2]=dpString ("Tramite");
			callScript("Scripts.Comun.ValidarBtonNumDisp",Validac);
			
			System.out.println("Resultado Recibido por fValidarBtonNumDisp: "+ Validac[1] );

			if  ((Validac[0].toString().equals("NOK"))){
				//MensError[0] = "Validacion de Obtener primer número disponible fallo";
				MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
	}
}

