package Scripts.Comun.SistemasExternos;
import java.awt.Rectangle;
import java.awt.Rectangle;
import resources.Scripts.Comun.SistemasExternos.SIPV_ValidarRebajaIMEIHelper;
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
 * Description   : Valida la rebaja del IMEI por la venta
 * Script Name   : <b>SIPV_ValidarRebajaIMEI</b>
 * @since  2016/08/30
 * Parametros: 0) Resultado = OK/NOK 1) Msj Error 2) IMEI  3) nroPedido
 * res res 352214043683136 res
 * �ltima modificaci�n: VC 20170331 Se agrega la l�gica para validar que el n�mero de pedido de siebel coincida con el de SIPV en el IMEI deseado
 */
public class SIPV_ValidarRebajaIMEI extends SIPV_ValidarRebajaIMEIHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		argu[1] = "Error desconocido";
		String sNumeroPedido = argu[3].toString();
		Rectangle area = new Rectangle();
		
		String path = "C:\\"; // cambiar por Resultados/CP0x/ obtener de variable global
		String filename = null;
		
		Reportes().hover();
		//ConsultaIMEI().waitForExistence();
		sleep(3);
		
		link_consultaIMEI().hover();
		//link_consultaIMEI().waitForExistence();
		sleep(3);
		
		link_imeiEstado().click();
		//link_imeiEstado().waitForExistence();
		sleep(3);
		IMEI().setProperty("Text", argu[2].toString());
		
		Btn_Busqueda().click();
		sleep(5);

		System.out.println(TablaVenta().exists());
		if (TablaVenta().exists())
		{
			//Leer el RED y sus datos para confirmar su pago
			ITestDataTable iDataTable = (ITestDataTable) TablaVenta().getTestData("contents");
			int rows = iDataTable.getRowCount();
			int cols = iDataTable.getColumnCount();
			System.out.println("Filas=" + rows + " Columnas"+ cols);
			// Solo se espera que haya un red con el numero pasado como parametro
			if (rows==1){
				// Se convierte el monto a numerico
				String sNroPed = iDataTable.getCell(0, 6).toString();
				String sReemp = iDataTable.getCell(0, 5).toString();
				System.out.println("IMEI Reeplazo: " + sReemp + " | N�mero de pedido SIPV" + sNroPed + " - N�mero de pedido en Siebel: " + sNumeroPedido);	
				
				if(sNumeroPedido.equals(sNroPed)) argu[0] = "OK";
				else System.out.println("Los n�meros de pedido no coinciden");

				area =TablaVenta().getClippedScreenRectangle();
				TablaVenta().ensureObjectIsVisible();
				// Generar una variable filename
				filename = path + "DatosVta.jpg";
				CapturarPantalla(filename, area);

			} else argu[1] = "Datos venta m�s de uno";
		} else argu[1] = "Datos venta no encontrado";

			ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
		}
	}

