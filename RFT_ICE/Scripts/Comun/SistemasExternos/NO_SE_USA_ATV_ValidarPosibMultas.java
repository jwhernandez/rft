package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.NO_SE_USA_ATV_ValidarPosibMultasHelper;
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
 * Description   : Captura las posibles multas de un servicio
 * Script Name   :  <b>ATV_ValidarPosibMultas</b>
 * @since  2016/10/18
 * Parametros: 0) Resultado = OK/NOK 1) nro servicio 2)Nombre plan 3 
 * 3) Nombre terminal o PLAN SIN TERMINAL
 * 
 * res 1-1735047671  "PLAN PROFESIONAL 1 24 M"  "NOKIA LUMIA 625 LTE NEGRO"  o / SIN TERMINAL
 */
public class NO_SE_USA_ATV_ValidarPosibMultas extends NO_SE_USA_ATV_ValidarPosibMultasHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

