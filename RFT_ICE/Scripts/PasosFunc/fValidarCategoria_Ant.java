package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fValidarCategoria_AntHelper;
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
 * Description   : Valida la categoria creditica
 * Script Name   : <b>fValidarCategoria</b>
 * @author FF
 * @since  2016/12/15
 * @Param =
 */

public class fValidarCategoria_Ant extends fValidarCategoria_AntHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ValidarAtributos;
		ValidarAtributos = new String[2];
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
		
		ValidarAtributos[1]=dpString("CategoriaCrediticia");
		callScript("Scripts.Comun.ValidarCategoria", ValidarAtributos);
		
		if  ((ValidarAtributos[0].toString().equals("NOK"))){
			//MensError[0] = "Validar atributos falló";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}
