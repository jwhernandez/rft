package Scripts.PasosFunc;
import libreria.utileria.Tipo;
import resources.Scripts.PasosFunc.fModificarCategoriaAdminHelper;
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
 * Descripcion     : Cargar la cat crediticia vía admin
 * @param 0) numero de caso 1)NA 
 * Script Name   : <b>fModificarCategoriaAdmin</b>
 * Pre-condiciones : Estar en la vista de pedidos (no en admin)
 * @since  2016/02/16
 * @author Sandra
 */
public class fModificarCategoriaAdmin extends fModificarCategoriaAdminHelper
{
	public void testMain(Object[] args) 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] ModifCat;
		ModifCat = new String[2];
		// Parámetros: 0) CatCred 1)OK/NOK
		
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
		/** 
		 * Generar Venta
		 */
			
			ModifCat[0] = dpString("CatCrediticia");
			callScript("Scripts.Comun.ModificarCategoriaAdmin", ModifCat);

			if  (ModifCat[1].toString().equals("NOK")){
				MensError[0] = "No se pudo cargar cat crediticia";
				//MensError[0] = "xDefecto";
				MensError[1] = args[3].toString();
				MensError[2] = args[0].toString();
				MensError[3] = getScriptName( );
				callScript("Scripts.Comun.TerminarCasoError", MensError);
			}
	}
}

