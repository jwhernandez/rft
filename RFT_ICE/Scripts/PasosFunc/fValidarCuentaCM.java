package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fValidarCuentaCMHelper;
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
 * Description   :Valida los campos de la cuenta contra el Data Pool
 * @author SS
 * Script Name   : <b>fValidarCuentaCM</b>
 * @since  2016/04/12
 * ult modf se cambia contacto x nro cta fact
 * CP20_CD1_T1 NA QA res res 
 */
public class fValidarCuentaCM extends fValidarCuentaCMHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String sScriptName=getScriptName().toString(); // 22/11/2016
		String[] MensError;
		MensError = new String[4];
		
		String[] ValidaCta;
		ValidaCta = new String[6];
		// 0) OK/NOK 1) OUT cuenta 2) tipo 3) clase 4) contacto 5) identificacion
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 
		ValidaCta[2]=dpString("TipoCuenta");
		ValidaCta[3]="Facturación";
		// ValidaCta[4]=dpString("Contacto"); // Cambio ss 08_02_2016 contacto x cta fact
		ValidaCta[4]=dpString("NroCuentaFac"); // Cambio ss 08_02_2016 contacto x cta fact
		ValidaCta[5]=dpString("Identificacion");
		
		callScript("Scripts.Comun.ValidarCuentaCM", ValidaCta);

		setNroCtaFact(ValidaCta[1].toString());
		System.out.println("NroCtaFact: " + getNroCtaFact() );
		logInfo("NroCtaFact: " + getNroCtaFact() );
		infoPaso(sScriptName, Tipo.DATO,"NroCtaFact",getNroCtaFact());

		if (ValidaCta[0].toString().equals("NOK")) {
			//MensError[0] = "Validación de cuenta falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

