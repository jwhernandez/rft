package Scripts.Comun;
import resources.Scripts.Comun.ValidarLiberarNumeroHelper;
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
 * Description   : Chequea si liberar numero está habilitado o no y devuelve habilitado o deshabilitado
 * Script Name   : <b>ValidarLiberarNumero</b>
 * @Param 0)OUT Habilitado / Deshabilitado  
 * @since  2016/01/21
 * @author Sandra
 */
public class ValidarLiberarNumero extends ValidarLiberarNumeroHelper
{
	public void testMain(Object[] argu) 
	{
		if (LiberarTodo().getProperty("IsEnabled").toString().equals("false")) {
			argu[0] ="Deshabilitado";
		} else {argu[0] ="Habilitado";}
		
		System.out.println("Resultado enviado por ValidarLiberarNumero: "+ argu[0] );
	}
}

