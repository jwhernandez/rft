package Scripts.PasosFunc;
import resources.Scripts.PasosFunc.fAsociarListaEspecialHelper;
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
 * Script Name   : <b>fAsociarListaEspecial</b>
 * Description   : Asocia la lista especial a un pedido
 * @param 0) numero de caso 1)un digito indicando el numero de variable en el dp
 * @since  2015/12/27
 * @author SS
 * ult modif 12/4/2017 se agrega opcion portin
 * ult modif ss 18-5-2017 se agrega 2nda lista especial via param 1 = SMS o VOZ
 */
public class fAsociarListaEspecial extends fAsociarListaEspecialHelper
{
	public void testMain(Object[] args) throws RationalTestException 
	{
		ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
		String[] MensError;
		MensError = new String[4];

		String[] ListaEsp;
		ListaEsp = new String[4];
		//  @param Parámetros: 0) NOK / OK 1) Producto al cual asociar 2) Nombre de la lista 3)Tramite
 
		dpReset();
		while (!dpDone() &&  !(dpString("NumeroCaso").equals(args[0]) && 
				dpString("Ambiente").equals(args[2]))) {
			dpNext(); 
		} 

		String sParam1=args[1].toString().toLowerCase();
		if (sParam1.equals("na"))
		{
				ListaEsp[1] = dpString("ProductoFAVNum");
				ListaEsp[2] = getNomListaEspecial(); 
		}
		else
		{
			if (sParam1.equals("sms"))
			{
			ListaEsp[1] = dpString("ProductoFAVNum_SMS");
			ListaEsp[2] = getNomListaEspecial_SMS(); 	
			}
			if (sParam1.equals("voz"))
			{
			ListaEsp[1] = dpString("ProductoFAVNum");
			ListaEsp[2] = getNomListaEspecial(); 	
			}
		}
		ListaEsp[3] = dpString("Tramite"); 
		callScript("Scripts.Comun.AsociarListaEspecial", ListaEsp);

		if  ((ListaEsp[0].toString().equals("NOK"))){
			//MensError[0] = "Asignación de Lista Especial tuvo error";
			MensError[0] = "xDefecto";
			MensError[1] = args[3].toString();
			MensError[2] = args[0].toString();
			MensError[3] = getScriptName( );
			callScript("Scripts.Comun.TerminarCasoError", MensError);
		}
	}
}

