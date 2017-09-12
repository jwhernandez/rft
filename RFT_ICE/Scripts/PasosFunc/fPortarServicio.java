package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fPortarServicioHelper;
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
 * Script Name   : <b>fPortarServicio</b>
 * Descripcion   :Ingresa el nro de servicio y presiona portar
 * @Param  0) nombre del caso 1) NA  2) IN Ambiente 3) IN ErrorStop (Si / No)
 * ej CP26_CD1_T1 1 QA true 20
 * @since  2017/03/16
 * @author SS
 */
public class fPortarServicio extends fPortarServicioHelper
{
	public void testMain(Object[] args) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] Portar;
		Portar = new String[2];
		//@Param 0)ok/nok 1)NroServicio  
		
		String[] MensError;
		MensError = new String[4];
		
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	

		int i = Integer.parseInt(args[1].toString());
		System.out.println(dpString("NroServicio" + i));
		Portar[1]=dpString("NroServicio" + i);
		callScript("Scripts.Comun.PortarServicio", Portar);

		if  (Portar[0].toString().equals("NOK")){
			//MensError[0] = "Portar servicio falló;
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

