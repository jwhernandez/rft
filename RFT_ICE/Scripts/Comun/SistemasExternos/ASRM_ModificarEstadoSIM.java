package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ASRM_ModificarEstadoSIMHelper;
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
 * Description   : Modifica el estado de una SIM en ASRM
 * @author SS
 * Script Name   : <b>ASRM_ModificarEstadoSIM</b>
 * Parametros: 0) Resultado = OK/NOK 1) SIM 2) estadodeseado (ACTIVO / DISPONIBLE)
 * ej  res  89506010810210375663 DISPONIBLE
 * ej res 89506010810210375697 ACTIVO
 * @since  2017/02/08
 */
public class ASRM_ModificarEstadoSIM extends ASRM_ModificarEstadoSIMHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{

		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";

		String sEstadoLeido=null;
		String sNroLeido=null;

		Tabs().ensureObjectIsVisible();

		Tabs().click(atName("Recurso Tarjetas") );
		NroSIM().waitForExistence();
		NroSIM().setProperty(".value", argu[1].toString());

		if (Informacion().exists()) okbutton().click(); // El nro no se encuentra
		else 
		{
			sNroLeido = NroSIMConsultado().getProperty(".value").toString();
			sEstadoLeido = Estado().getProperty(".value").toString();

			if (sNroLeido.equals(argu[1].toString()) )
			{
				if(!sEstadoLeido.equals(argu[2].toString()))
				{
					System.out.println("Se cambiara el estado actual que es  " + sEstadoLeido + "a  " );	
					openbutton().click();

					switch (argu[2].toString()) 
					{
					case "ACTIVO":
						ActualizarWindow().inputKeys("^{ExtPgUp}");
						break;
					case "DISPONIBLE":
						ActualizarWindow().inputKeys("^{ExtPgDn}");
						ActualizarWindow().inputKeys("{ExtUp}");
						break;
					case "RESERVADO":
						ActualizarWindow().inputKeys("^{ExtPgDn}");
						break;
					default:
						break;
					}
					ActualizarWindow().maximize();

					//Hay que hacer el click dos veces
					ModificarNumero().click(atPoint(320,48));
					sleep(2);
					ModificarNumero().click(atPoint(320,48));
					sleep(2);
					okbutton().waitForExistence();

					okbutton().click();

					argu[0] = "OK";
				} else 			argu[0] = "OK"; 
			}
		}
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);

	}
}

