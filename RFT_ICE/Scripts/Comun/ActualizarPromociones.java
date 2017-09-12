package Scripts.Comun;
import resources.Scripts.Comun.ActualizarPromocionesHelper;
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
 * Descripcion Generar un nuevo cambio de plan desde la vista 360
* Script Name   : <b>CrearPedidoCP</b>
* @since  2016/01/15
* @Param 0) Nombre plan destino 1) Encontrado / No Encontrado 2)Fila en la que fue encontrado 3)nropedido
* Ej: "PLAN CONECTADO 2 24 M 4GLTE" res res
* @author Sandra
* Precondicion estar en la vista 360 en la linea del activo
* Ultima modificacion: 20/3/2017
 */
public class ActualizarPromociones extends ActualizarPromocionesHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		
		argu[0] = "NOK"; 
		
		SiebMenu().select(atPath("CmdMgr1"));

		sleep(3);
		
		argu[0] = "OK"; 
		
		System.out.println("Resultado: " + argu[0]);
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString() );
	}
}

