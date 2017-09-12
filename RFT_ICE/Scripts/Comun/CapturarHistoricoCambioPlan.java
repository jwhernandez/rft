package Scripts.Comun;
import resources.Scripts.Comun.CapturarHistoricoCambioPlanHelper;

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
 * Script Name   : <b>CapturarHistoricoCambioPlan</b>
 * Generated     : <b>jul. 12, 2017 10:08:59 AM</b>
 * Description   : Captura el historico de los cambios de planes que se realizaron para un servicio
 * Original Host : WinNT Version 6.1  Build 7601 (S)
 * @Param 0) OK / NOK 1) NroServicio 2) movimiento 3) NroPedido 4) IMEI
 * @since  2017/07/12
 * @author VC
 * 
 * res 89774114 Cambio de plan 1-1770261530 351526061615042
 * res 89774114 Cambio 1-1770261530 355799055684169
 */
public class CapturarHistoricoCambioPlan extends CapturarHistoricoCambioPlanHelper
{
	
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0]="NOK";

		try{
		Pestañas().gotoScreen("Accounts Screen");
		sleep(5);
		Pestañas().gotoView("ICE Consultas View");
		
		Servicio().setText(argu[1].toString());
		button_consulta().performAction();
		
		int irows=(int) list_siebList().getProperty("RowsCount");
		
		boolean encontrado = false;
		for(int i = 0; i < irows; i++){			
			String agencia = list_siebList().getCellText("ICE Agencia CP", i);
			String fechaCreacion = list_siebList().getCellText("ICE Fecha Creacion CP", i);
			String movimiento = list_siebList().getCellText("ICE Movimiento CP", i);
			String nroPedido = list_siebList().getCellText("ICE Numero Pedido CP", i);
			String nombreTerminal = list_siebList().getCellText("ICE Nombre Terminal", i);
			String imei = list_siebList().getCellText("ICE IMEI", i);
			System.out.println("Agencia: " + agencia + " Fecha de creación: " + fechaCreacion + " NroPedido: " + nroPedido + " Movimiento: " + movimiento + " Terminal: " + nombreTerminal + " IMEI: " + imei);
			if(movimiento.equals(argu[2].toString()) && nroPedido.equals(argu[3].toString()) && imei.equals(argu[4].toString())){
				System.out.println("Pedido encontrado.");
				encontrado = true;
				break;
			}
		}
		
		if(encontrado) argu[0]="OK";
		else System.out.println("Pedido no encontrado.");
		
		} catch(Exception e){
			System.out.println("Error al capturar los cambios de plan asociados al servicio " + argu[1].toString() + " Error: " + e);
		}
		ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	}
}

