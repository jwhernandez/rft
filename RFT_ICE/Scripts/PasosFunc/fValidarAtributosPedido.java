package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarAtributosPedidoHelper;
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
 * Description   : Valida una cantidad (parametrizada) de atributos (set nombre - valor)
 * @author Sandra
 * Script Name   : <b>fValidarAtributosPedido</b>
 * @since  2016/11/12
 * @param 0) numero de caso 1)digito iterador en el DP 2) ambiente 
 * 3) para o no para error 4) Nro de paso
 */
public class fValidarAtributosPedido extends fValidarAtributosPedidoHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidarAtributos;
		ValidarAtributos = new String[9];
		// 0) OK/NOK 1) tramite 2) cantidad de atributos 3) Nombre 4) Valor
		// 5)Nombre 6)Valor 7)Nombre 8)Valor

		String[] MensError;
		MensError = new String[4];
		/**
		 * Itera el data pools de datos del caso para buscar la row correcta
		 */
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 	
		
		int i = Integer.parseInt(args[1].toString());
		ValidarAtributos[1]=dpString("Tramite");
		int j= Integer.parseInt(dpString("Cantidad"+i));
		ValidarAtributos[2]=dpString("Cantidad"+i);

		for (int k = 0; k < j; k++) 
		{
			int z=k*2;
			ValidarAtributos[z+3] = dpString("Nombre"+(k+1)+"_"+i); 
			//[3] = Nombre1_1,[5]=Nombre2_1;[7]=Nombre3_1
			ValidarAtributos[z+4] = dpString("Valor"+(k+1)+"_"+i); 
			// [4]=Valor1_1,[6]=Valor2_1;[7]=Valor3_1  
		}
		callScript("Scripts.Comun.ValidarAtributosPedido", ValidarAtributos);
		
		if  ((ValidarAtributos[0].toString().equals("NOK"))){
			MensError[0] = "Validar atributos falló";
			//MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

