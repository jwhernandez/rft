package Scripts.Comun;
import resources.Scripts.Comun.Script3Helper;
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
 * Description   : Descripción de lo que hace el script
 * Script Name   : <b>Script3</b>
 * @Param 0) 1)
 * ej: 
 * Precondiciones de re-ejecución
 * @author SSASTRE
 * @since  2016/01/27
 */
public class Script3 extends Script3Helper
{
	public void testMain(Object[] args) 
	{
		System.out.println(Cantidad().getProperties());
		System.out.println(Cantidad().getProperty("Text"));
		IMEI().setText("352214043682617");
		System.out.println(IMEI().getProperty("Text"));
		
		System.out.println(Producto().getProperty("Text"));
		
		for (int i=0; i<=8; i++) {
			LineasPedido().activateRow(i);
			System.out.println(Producto().getProperty("Text"));
		}

	}
}

