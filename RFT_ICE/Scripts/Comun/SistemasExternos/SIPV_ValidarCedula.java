package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.SIPV_ValidarCedulaHelper;
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
import java.awt.Rectangle;
/**
 * Description   : Valida la cedula en el sistema SIPV
 * Script Name   : <b>SIPV_ValidarCedula</b>
 * @since  2016/08/29
 * Parametros: 0) Resultado = OK/NOK 1) Msj Error 2) IMEI  3) cedula
 * res res 352214043683136 res
 * �ltima modificaci�n: VC 20170331
 * Ultima modificacion VC 20170405 Se cambia la forma en la que se obtiene la c�dula
 */
public class SIPV_ValidarCedula extends SIPV_ValidarCedulaHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		argu[1] = "Error desconocido";
		Rectangle area = new Rectangle();
		String path = "C:\\"; // cambiar por Resultados/CP0x/ obtener de variable global
		String filename = null;
		
		MenuRebajos().hover();
		sleep(1);
		ConsultaIMEI().click();
		IMEI().waitForExistence();
		if (IMEI().exists())
		{
			IMEI().setProperty("Text", argu[2].toString());
			Busqueda().click();
			
			//argu [3] = Cedula().getProperty(".defaultValue");
			//String sCedulaSIPV  = Cedula().getProperty(".defaultValue").toString();
			String sCedulaSIPV  = Cedula().getProperty(".value").toString(); // VC 20170405 Se cambia la forma en la que se obtiene la c�dula
			System.out.println("La cedula en siebel es " + argu[3] );
			if(sCedulaSIPV.equals(argu[3])){ // VC 20170331 Se valida que la c�dula coincida entre siebel y sipv
				argu[0] = "OK";
				argu[1] = "Resultado exitoso";
				TablaIMEI().ensureObjectIsVisible();
				area =TablaIMEI().getClippedScreenRectangle();
				// Generar una variable filename
				filename = path + "IMEI.jpg";
				CapturarPantalla(filename, area);
			} else System.out.println("Error al obtener la c�dula Siebel: " + argu[3] + " | SIPV: " + sCedulaSIPV);
			
		} else argu[1] = "IMEI no encontrado";
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

