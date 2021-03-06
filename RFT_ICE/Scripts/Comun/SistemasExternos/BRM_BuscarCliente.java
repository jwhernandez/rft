package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.BRM_BuscarClienteHelper;
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
 * Description   : Busca una cuenta en BRM  
 * Script Name   : <b>BRM_BuscarCliente</b>
 * @since   2016/11/14
 * Parametros: 0) Resultado = OK/NOK 1) cliente
 * ej res 101753755688
 * @author SS
 * 
	 * Ult Modif
	 * SS 24-11-2016 se cambia objeto summary - se hace expresión regular
 */
public class BRM_BuscarCliente extends BRM_BuscarClienteHelper
{
	public void testMain(Object[] argu) throws RationalTestException
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		argu[0] = "NOK";
		
		MenuBar().drag(atPath("File"));
		MenuBar().click(atPath("File->Close"));
		
		MenuBar().click(atPath("File"));
		MenuBar().drag(atPath("File->Search"));
		
		NroCuenta().setText(argu[1].toString());
		
		search().click();
		System.out.println(ItemsFound().getProperty("text"));
		if (!ItemsFound().getProperty("text").toString().trim().equals("No Items Found"))
		{
			int rows = Integer.parseInt(ListaCuentas().getProperty("componentCount").toString());
			if (rows!=1) 
				System.out.println("Cantidad de cuentas erronea " + ListaCuentas().getProperty("componentCount"));
			else
		
		// Frame: Customer Center
		customerCenter().inputKeys("{LeftAlt}");
		MenuBar().click(atPath("File"));
		MenuBar().click(atPath("File->Search"));
		
		// 
			{
				sleep(3);
				ListaCuentas().click(atCell(atRow(0), atColumn("Company")));
				open().click();
				summary().waitForExistence();
				argu[0] = "OK";
			}
		} else 	System.out.println("La cuenta no existe");
 
		ImpreResultadoScript(getScriptName( ).toString(), "Resultado " + argu[0]);
	}
}

