package Scripts.Comun;
import resources.Scripts.Comun.SeleccionaInformeHelper;
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
 * Description   : Selecciona la opción del menú del contrato a imprimir 
 * según parámetro recibido
 * Parametros 0)OK/NOK 1) Prepago - Postpago
 * ej: res "Contrato de planes moviles pospago"
 * Script Name   : <b>SeleccionaInforme</b>
 * @author Sandra
 * @since  2016/01/29
 */
public class SeleccionaInforme extends SeleccionaInformeHelper
{
	public void testMain(Object[] argu) 
	{
		argu[0]="NOK";
		Botón_Informes().click();
		sleep(2);

		Menu_reportes().select(atPath(argu[1].toString()));

		sleep(2);
		Enviar().performAction();
		
		argu[0]="OK";
		

	}
}

