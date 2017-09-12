package Scripts.Comun;
import resources.Scripts.Comun.BuscarPedidosCMHelper;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import libreria.utileria.Tipo;
/**
* Descripción: Busca los pedidos de CM
* Parámetros: 0) OK NOK 1) Nro de servicio 2) tipo operacion 3) fecha 4) usuario 5) IN/Out task id (opc)
* Se guarda en la variable global el task id si no viene como argumento de input.
* Se pasa este dato al script funcional para guardar el task id en el data pool de salida.
* Tipo operacion: Ej. Prepago/Postpago o Postpago/Prepago
* res 85860909 Prepago/Postpago 04/01/2016 SOIN_KRAMIREZ (Para busqueda con metodo vairos parametros)
* res NA NA NA NA 1-RZSETD (para busqueda con metodo task id)
* Script Name   : <b>BuscarPedidosCM</b>
* @since  2016/04/19
* @author SS
*/
public class BuscarPedidosCM extends BuscarPedidosCMHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";

		Pestañas().gotoScreen("ICE Control Orden Task Screen");

		sleep(3);		

		TiposdeOrdenes().goTo("ICE Control de Orden Task View All", "L2");

		sleep(3);		

		NewQuery().select(atPath("NewQuery")); 
		
		String sMetodo = "Task Id";
		if (argu.length >= 6 && !argu[5].toString().equals("NA")) { 
			// buscar por task id
			TaskId().setText(argu[5].toString());
			}
		else {
			// buscar por otros criterios

			NroServicioCM().setText(argu[1].toString()); // nro servicio
			Tipo_de_Operacion().setText(argu[2].toString());
			FechaCreacion().setText(">="+argu[3].toString());
			Usuario().setText(argu[4].toString().toUpperCase()); // la búsqueda no acepta minuscula
			Estado_Tramite().setText("<>Cancelado");
			sMetodo = "Varios Parametros";

		}
		NewQuery().select(atPath("ExecuteQuery"));
		// chequear que sean dos registros 
		int iRows = Integer.parseInt(PedidosCM().getProperty("RowsCount").toString());
		System.out.println("Cantidad de filas encontradas: " + iRows + " con metodo" + sMetodo);
		if (iRows!=2) argu[0]="NOK";
		else
		{
			// capturar el Task id que se guardara en la variable global
			if (!sMetodo.equals ("Task Id")){
				setTaskIdCM(TaskId().getProperty("Text").toString());
				System.out.println("Task Id Capturada: " + getTaskIdCM());
				argu[5]=getTaskIdCM();
			}
			argu[0]="OK";
		}
	
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());

	}
}

