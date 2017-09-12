package Scripts.Comun;
import resources.Scripts.Comun.ValidarMotivoPedidoHelper;
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
 * Description   : Verificar el motivo, que sea por ejemplo Suspension
 * Script Name   : <b>ValidarMotivo</b>
 * @author FernandaFdez
 * @since  2016/11/08
 * @Param 0) OK/NOK  1) motivo 
 * ej res "Solicitud de cliente" 
 */
public class ValidarMotivoPedido extends ValidarMotivoPedidoHelper
{
	 public void testMain(Object[] argu) throws RationalTestException 
	 {
	  ImpreEncabezadoScript(getScriptArgs(), getScriptName( ).toString());
	  

	  String sMotivoDeseado = argu[1].toString(); // no se pone lowecase a proposito
	  IFtVerificationPoint MotivoVP;
	  MotivoVP = vpManual("Motivo", sMotivoDeseado, Motivo().getProperty("Text"));
	  if (MotivoVP.performTest()) {
	   System.out.println("Motivo: " + Motivo().getProperty("Text"));
	   argu[0] = "OK";
	  }
	  
	  ImpreResultadoScript(getScriptName( ).toString(), argu[0].toString());
	 }
	}