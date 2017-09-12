package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fBRM_ValidarApartadoCuentaHelper;
import Scripts.Comun.SistemasExternos.BRM_ValidarApartadoCuenta;
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
 * Description   : Busca un apartado postal en BRM  
 * Script Name   : <b>fBRM_ValidarApartadoCuenta</b>
 * @since   2017/02/15
 * Parametros: 0) Resultado = OK/NOK 1) valordeseado
 * ej CP20_CD1_T1 (DP o NA) QA NA NA 
 * param1 = DP para ir buscar el dato en el dp en Apartado. NA para usar la info de la variable global generada por un paso previo.
 * @author SS
 */
public class fBRM_ValidarApartadoCuenta extends fBRM_ValidarApartadoCuentaHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Validar;
		Validar = new String[2];
		// 0) Resultado = OK/NOK 1) valordeseado
		String[] MensError;
		MensError = new String[4];


		if (args[1].toString().equals("DP"))
		{
			dpReset();
			while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
					dpString("Ambiente").equals(args[2]))) {
				dpNext(); 
			} 		
			Validar[1] = dpString("Apartado");
		} 
		else {
			//setApartado("111-2525");
			Validar[1]=getApartado();
			System.out.println(Validar[1]);
		}

		callScript(new BRM_ValidarApartadoCuenta(), Validar);
		

		if  (Validar[0].toString().equals("NOK")) {
			MensError[0] = "Problema al buscaren el apartado a BRM";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

