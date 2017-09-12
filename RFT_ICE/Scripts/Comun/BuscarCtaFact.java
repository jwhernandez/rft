package Scripts.Comun;
import resources.Scripts.Comun.BuscarCtaFactHelper;
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
* Descripción: Permite hacer drilldown al detalle del activo desde la vista 360 de la cuenta
* Parámetros: 0) Nro de servicio (E) , 1) OK / NOK (S) 2) OUT Cta de facturacion
* OK si lineas del pefil de cuenta cliente= 1 
* Pre-Condiciones: Estar en la vista360 a nivel screen y 
* haber buscando antes el activo para estar en esa linea
* SS Nov 2015
* ult modif ss 31/05/2017 se agrega el objeto NroCtaFactRO en forma adicional al existente. 
* ej: 85972121 res res 
* Última modificación VC 20170615 Se filtra por el número de servicio para asegurarse de tomar el correcto
*/
public class BuscarCtaFact extends BuscarCtaFactHelper
{
	public void testMain(Object[] argu) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		// BuscarActivo   --- SSASTRE se elimina. Se da como precondicion que debe estar en el activo
		//String[] Activo;
		//Activo = new String[3];
		//argu[1] = "NOK";
		//Activo [0] = argu[0].toString();
		//callScript("Scripts.Comun.BuscarActivo", Activo);
		//sleep(5);
		// Fin BuscarActivo   --- SSASTRE se elimina. Se da como precondicion que debe estar en el activo
		
		argu[1] = "NOK";
		
		// VC 20170615 Se filtra por el número de servicio para asegurarse de tomar el correcto
		
		Consulta().performAction();
		text_serialNumber().setText(argu[0].toString());
		button_executeQuery().performAction();
		

		// Hacer drill down en la linea del servicio al detalle del activo
		LineasActivoPlanes().drillDownColumn("Billing Account", 0);

		sleep(5);	
		int iTotal = (int) LineasActivoServicio().getProperty("RowsCount") ;
		if ( iTotal >= 2 ) {
			argu[1]="OK";			
			LineasActivoServicio().activateRow(1);
		}
		try {
			setManejoError("Controlado"); // ss se agrega objeto nuevo
			argu[2] = NroCtaFact().getProperty("Text");
		} catch (Exception e) {
			argu[2] = NroCtaFactRO().getProperty("Text"); //ss se agrega objeto
		}

		ImpreResultadoScript(getScriptName( ).toString(), argu[1].toString());
	}
}

