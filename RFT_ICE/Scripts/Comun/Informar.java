package Scripts.Comun;
import resources.Scripts.Comun.InformarHelper;
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
 * Descripción: informa - en loginfo ... pantalla ... 
 * Autor: Sastre
 */
public class Informar extends InformarHelper
{
	/**
	 * Descripción:Se llama enviar datos de debug
	 * Parametros: 1) Mensaje a Transmitir de error 
	 */
	public void testMain(Object[] argu) 
	{
		logInfo("Mensaje: **" + argu[0] + "**" );
		System.out.println("Mensaje: **" + argu[0] + "**" );

	}
}

