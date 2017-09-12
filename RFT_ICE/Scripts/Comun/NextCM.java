package Scripts.Comun;
import java.text.SimpleDateFormat;
import java.util.Date;
import resources.Scripts.Comun.NextCMHelper;
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
 * Description   : Presiona Next en la task de nombre pasado como parametro
 * @author SS
 * Script Name   : <b>NextCM</b>
 * @since  2016/04/18
 * @Param 0)OK/NOK 1)Pantalla (cuenta, plan, paquetes, imei, contrato, terminal, finalizar, facilidades) 2)fechaCM o NA
 * res IMEI
 * res contrato
 * Ult Modif ss 08/12/2017 se agregan opciones de cuenta .
 * Última modificación VC 20170509 Cierra la ventana de tareas una vez finalizado el cm.. 
 */
public class NextCM extends NextCMHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK" ;
		argu[2]="NA";
		
		switch (argu[1].toString().toLowerCase()) {
		case "cuenta":
			NextCuenta().performAction();
			argu[0] = "OK";
			break;
		case "plan":
			NextPlan().performAction();
			argu[0] = "OK";
			break;
		case "paquetes":
			NextPaqueteVoz().performAction();
			argu[0] = "OK";
			break;
		case "imei":
			NextIMEI().performAction();
			argu[0] = "OK";
			break;
		case "contrato":
			NextContrato().performAction();
			argu[0] = "OK";
			break;
		case "red":
			NextRED().performAction();
			argu[0] = "OK";
			break;
		case "terminal":
			NextTerminal().performAction();
			argu[0] = "OK";
			break;
		case "finalizar":
			Finalizar().performAction();
			sleep(5);
			if(IndependenciaNumericaTaskUI().exists()) Tareas().click(); // VC 20170509 Cierra la ventana de tareas
			argu[0] = "OK";
			break;
		case "facilidades":
			NextFacilidades().performAction();
			argu[0] = "OK";
			break;
		default:  
			break;
		} // end del switch
		
		if (!argu[1].toString().toLowerCase().equals("cuenta") && getFechaCM() ==null ) 
		{
			Date now = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Format 1:   " + dateFormatter.format(now));
			setFechaCM(dateFormatter.format(now));
			System.out.println("Variable Fecha:   " + getFechaCM());
			argu[2]=getFechaCM().toString();
		}

		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

