package Scripts.Comun;
import libreria.utileria.Tipo;
import resources.Scripts.Comun.AceptarVtnasSBLHelper;
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
import com.rational.test.ft.value.managers.ScriptNameValue;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Se procesan las posibles ventanas abiertas de SBL para clasificar el tipo 
 * de error. Se informa vía infopaso el error recibido. Es llamado por CerrarIE.
 * Script Name   : <b>AceptarVtnasSBL</b>
 * @since  2016/09/22
 * @author SS
 * Ej res
 */
public class AceptarVtnasSBL extends AceptarVtnasSBLHelper
{
	public void testMain(Object[] argu)  throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());

		argu[0]="NOK";
		String sScriptName=getScriptName().toString(); // 22/11/2016

		// Se identifica si es un mensaje de TimeOut o SW
		System.out.println("Mensaje SW_TimeOut existe?:" + SW_TimeOut().exists() );
		// Se identiifca si es un mensaje de error de SBL HTML
		System.out.println("Mensaje MensajeErrorHTMLSiebel existe?:" + MensajeErrorHTMLSiebel().exists() );
		// Se identiifca si es un mensaje de error de SBL JavaScript
		System.out.println("Mensaje MensajeErrorBrowserScript existe?:" + MensajeErrorBrowserScript().exists() );

		
		if (MensajeErrorHTMLSiebel().exists())
		{
				System.out.println("Mensaje MensajeErrorHTMLSiebel existe");
				System.out.println(MensajeErrorHTMLSiebel().getProperty(".text"));
				infoPaso(sScriptName, Tipo.ERROR, "ManejoVentanasSBL", MensajeErrorHTMLSiebel().getProperty(".text").toString());
				if (!getTipoError().equals("NA")) setTipoError("Bug");
				if (!getMensajeError().equals("NA")) setMensajeError(MensajeErrorHTMLSiebel().getProperty(".text").toString());
				infoPaso(sScriptName, Tipo.ERROR, "ManejoVentanasSBL",MensajeErrorHTMLSiebel().getProperty(".text").toString());
		} 
		if (SW_TimeOut().exists())
		{
				System.out.println("Mensaje SW_TimeOut existe");
				System.out.println(SW_TimeOut().getProperty(".text"));
				if (SW_TimeOut().getProperty(".text").toString().contains("Session"))
				{
					if (!getTipoError().equals("NA")) setTipoError("SW");
				} else
				{
					if (!getTipoError().equals("NA")) setTipoError("TimeOut");
				}
				if (!getMensajeError().equals("NA")) setMensajeError(SW_TimeOut().getProperty(".text").toString());
				infoPaso(sScriptName, Tipo.ERROR, "ManejoVentanasSBL", SW_TimeOut().getProperty(".text").toString());

		}
		if (MensajeErrorBrowserScript().exists())
		{
				System.out.println("MensajeErrorBrowserScript  existe");
				System.out.println(MensajeErrorBrowserScript().getProperty(".text"));
				if (!getTipoError().equals("NA")) setTipoError("Bug");
				if (!getMensajeError().equals("NA")) setMensajeError(MensajeErrorBrowserScript().getProperty(".text").toString());
				infoPaso(sScriptName, Tipo.ERROR, "ManejoVentanasSBL", MensajeErrorBrowserScript().getProperty(".text").toString());
		} 
		argu[0]="OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

