package Scripts.Comun;
import resources.Scripts.Comun.SetEnvioInfoHelper;
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
 * Description   : Administra la casilla de envio de informacion.
 * Si la accion es setear, setea a true o false.
 * Si es consulta, valida que sea true o false
 * @author SS
 * Param 0) NOK/OK 1) Setear True o Consultar False o cualquier combinacion 2)Tramite
 * Script Name   : <b>SetEnvioInfo</b>
 * @since  2017/03/27
 * ej res "Setear True" 
 */
public class SetEnvioInfo extends SetEnvioInfoHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0]="NOK";
		Boolean bHabilitado = null;
		String sTramite = argu[2].toString();
		

			try {
				setManejoError("Controlado");
				if (sTramite.equals("PortIn"))
					bHabilitado = Boolean.valueOf(EnvioInformacion().getProperty("IsChecked").toString());
				else System.out.println("Opcion no implementada");
			} catch (Exception e) {
				setManejoError("NA");
				if (sTramite.equals("PortIn")) {
					button_toggleLayout().performAction();
					bHabilitado = Boolean.valueOf(EnvioInformacion().getProperty("IsChecked").toString());
				}
			}

			String sOpcion = argu[1].toString().toLowerCase();
			switch (sOpcion) {
			case "setear true":
				if (!bHabilitado)
				{
					if (sTramite.equals("PortIn")) {
						EnvioInformacion().setOn();	
						if (Boolean.valueOf(EnvioInformacion().getProperty("IsChecked").toString()))
							argu[0]="OK";
					}
				} else argu[0]="OK";
				break;
			case "setear false":
				if (bHabilitado)
				{
					if (sTramite.equals("PortIn")) {
						EnvioInformacion().setOff();
						if (!Boolean.valueOf(EnvioInformacion().getProperty("IsChecked").toString()))
							argu[0]="OK";
					}
				} else argu[0]="OK";
				break;
			case "consultar true":
				if (bHabilitado) argu[0]="OK";
				break;
			case "consultar false":
				if (!bHabilitado) argu[0]="OK";
				break;
			default:
				System.out.println("Opcion no implementada");
				break;
			}
			// Se vueve a dejar el panel superior igual
			if (sTramite.equals("PortIn")) 
				button_toggleLayout().performAction();
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

