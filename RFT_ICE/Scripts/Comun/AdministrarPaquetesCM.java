package Scripts.Comun;
import resources.Scripts.Comun.AdministrarPaquetesCMHelper;
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
 * Description   : Selección de paquete de Voz o SMS en CM
 * @author SS
 * Script Name   : <b>AdministrarPaqueteVozCM</b>
 * @since  2017/02/08
 * @Argumentos 0) OK/NOK  1) Opcion (Voz / SMS) 2) NombrePaquete 3) Encender / Apagar / Validar 4) EstadoaValidar
 * 4)EstadoDeseado (Encendido / Apagado)
 * ej res Voz "Plan Paquete 100 Minutos" Encender NA
 * res SMS "Plan Paquete 2000 SMS" Encender NA
 * ej res Voz "Plan Paquete 100 Minutos" Validar Encendido
 * ej res Voz "Plan Paquete 100 Minutos" Validar Apagado
 * res Voz "Plan Paquete 30 Minutos" Validar Apagado
 */
public class AdministrarPaquetesCM extends AdministrarPaquetesCMHelper
{
	public void testMain(Object[] argu) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		String sDato=null;
		boolean bEncendido = false;
		int iCantRows = 0;
		String sEstadoDeseado = argu[4].toString().toLowerCase();
		
		switch (argu[1].toString().toLowerCase()) {
		case "sms":
			System.out.println("Se procesa opción SMS");
			iCantRows = Integer.parseInt(ListaSMS().getProperty("RowsCount").toString());
			System.out.println("Cant filas=" + iCantRows);
			for (int i = 0; i < iCantRows; i++) {
				sDato= ListaSMS().getCellText("Name", i).toString();
				System.out.println(sDato);
				if (sDato.equals(argu[2].toString())) 
				{
					System.out.println("Coincide");
					ListaSMS().activateRow(i);
					switch (argu[3].toString().toLowerCase()) {
					case "encender":
						SMSFlag().setOn();
						argu[0]="OK";
						i = iCantRows;
						break;
					case "apagar":
						SMSFlag().setOff();
						argu[0]="OK";
						i = iCantRows;
						break;
					case "validar":
						bEncendido = Boolean.parseBoolean(SMSFlag().getProperty("IsChecked").toString().toLowerCase());
						System.out.println("EstadoDeseado=" + sEstadoDeseado + " EstadoSBL=" + bEncendido);
						if (bEncendido && sEstadoDeseado.equals("encendido")
								|| !bEncendido && sEstadoDeseado.equals("apagado"))
												argu[0]="OK";
						i = iCantRows;
						break;
					default:
						break;
					}
				}
			}
			break;
		case "voz":
			System.out.println("Se procesa opción Voz");
			iCantRows = Integer.parseInt(ListaVoz().getProperty("RowsCount").toString());
			System.out.println("Cant filas=" + iCantRows);
			for (int i = 0; i < iCantRows; i++) {
				sDato= ListaVoz().getCellText("Name", i).toString();
				System.out.println(sDato);
				if (sDato.equals(argu[2].toString())) 
				{
					System.out.println("Coincide");
					ListaVoz().activateRow(i);
					
					switch (argu[3].toString().toLowerCase()) {
					case "encender":
						VozFlag().setOn();
						argu[0]="OK";
						i = iCantRows;
						break;
					case "apagar":
						VozFlag().setOff();
						argu[0]="OK";
						i = iCantRows;
						break;
					case "validar":
						bEncendido = Boolean.parseBoolean(VozFlag().getProperty("IsChecked").toString().toLowerCase());
						System.out.println("EstadoDeseado=" + sEstadoDeseado + " EstadoSBL=" + bEncendido);
						if (bEncendido && sEstadoDeseado.equals("encendido")
								|| !bEncendido && sEstadoDeseado.equals("apagado"))
												argu[0]="OK";
						i = iCantRows;
						break;
					default:
						break;
					}
				}
			}
			break;
		default:
			break;
		}
		
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

