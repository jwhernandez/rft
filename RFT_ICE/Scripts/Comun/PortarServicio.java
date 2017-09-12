package Scripts.Comun;
import resources.Scripts.Comun.PortarServicioHelper;
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
 * Script Name   : <b>PortarServicio</b>
 * Descripcion   :Ingresa el nro de servicio y presiona portar
 * Realiza esta operación sobre la vista de todas las ordenes
 * @Param 0)  ok/nok 1)NroServicio  
 * ej  res 89840888
 * @since  2017/03/16
 * @author SS
 */
public class PortarServicio extends PortarServicioHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";
		
		Pestañas().goTo("ICE Order Entry - All Orders View (Port)", "L2");
		sleep(5);
		
		NroServicioPortabilidadAllOrders().setText(argu[1].toString()); // Se unifica y se usa el mismo objeto en qa a partir feb/2017 
		PortarAllOrders().performAction(); // Se unifica y se usa el mismo objeto en qa a partir feb/2017 

		argu[0]="OK";
	}
}

