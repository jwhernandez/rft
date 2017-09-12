package Scripts.Comun.SistemasExternos;
import resources.Scripts.Comun.SistemasExternos.ConsultaREDporIDREDATVHelper;
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
 * Script Name   : <b>ConsultaREDporIDREDATV</b>
 * Generated     : <b>jul. 10, 2017 3:55:14 PM</b>
 * Description   : Functional Test Script
 * Original Host : WinNT Version 6.1  Build 7601 (S)
 * 
 * @since  2017/07/26
 * @author VC
 */
public class ConsultaREDporIDREDATV extends ConsultaREDporIDREDATVHelper
{
	public void testMain(Object[] args) 
	{
		args[0] = "NOK";
		
		startApp("ConsultaREDQA");
		
		sleep(2);

		try{
			ConsultaREDATV().inputChars(args[1].toString());
			sleep(2);
			if(jTextField().getText().equals(args[1].toString())){
				consulta().click();
				//etiquetaNroRed().waitForExistence();
				sleep(15);
				System.out.println(numeroRED().getText());
				String resultado = numeroRED().getText();
				int aux = resultado.indexOf("==>");
				if(aux > -1){
					System.out.println("El pedido tiene multas. " + resultado);
					String numeroRED[] = resultado.split("==>");
					args[2] = numeroRED[1];
					args[0] = "OK";
				}else{
					System.out.println("El pedido no tiene multas");
					args[0] = "OK";
				}
			} else{
				System.out.println("Error al ingresar texto.");
				args[0] = "NOK";
			}
		} catch(Exception e){
			System.out.println("No se pudo ingresar el número de pedido en la aplicación. " + e);
			args[0] = "NOK";
		}
		
		salir(ANY,MAY_EXIT).click();
	}
}

