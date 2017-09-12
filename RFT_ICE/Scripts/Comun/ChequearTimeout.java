package Scripts.Comun;
import resources.Scripts.Comun.ChequearTimeoutHelper;
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
 * Description   : Chequear Timeout de Siebel 
 * y ver de volver a la pantalla donde se estaba o al login
 * Script Name   : <b>ChequearTimeout</b>
 * @author SS
 * @since  2016/09/16
 * args 0) OK/NOKlabel_htmlDialogStaticSeHaExce().
 */
public class ChequearTimeout extends ChequearTimeoutHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String sMensajeTimeOut = "Se ha excedido el tiempo de espera de la sesión porque estuvo inactivo demasiado tiempo. Vuelva a iniciar sesión para reanudarla. Si tenía abierto un archivo adjunto de Siebel, puede que los cambios se hayan perdido. Guarde el archivo localmente, ciérrelo y vuelva a adjuntarlo en el registro adecuado.";
		if (TimeOut().exists()) {
			if (TimeOut().getProperty(".text").toString().equals(sMensajeTimeOut))
			Aceptar().click();
		}
			
		argu[0] = "OK";
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

