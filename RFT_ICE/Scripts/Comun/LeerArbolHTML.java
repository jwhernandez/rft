package Scripts.Comun;
import resources.Scripts.Comun.LeerArbolHTMLHelper;
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
 * Description   : Functional Test Script
 * @author Sandra
 */
public class LeerArbolHTML extends LeerArbolHTMLHelper
{
	/**
	 * Script Name   : <b>SeleccionarNivel</b>
	 * Generated     : <b>09/01/2016 09:25:57</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2016/01/09
	 * @author Sandra
	 */
	public void testMain(Object[] args) 
	{

		ITestDataTree iTreeData = (ITestDataTree) tree2A().getTestData("tree");
		//  System.out.println(LineasPedido().getTestData("tree")); no funciona sobre siebel
        ITestDataTreeNodes iNodes = iTreeData.getTreeNodes();
       System.out.println("node count:"+iNodes.getNodeCount());
		
		//System.out.println(LineasPedido().getTestData("Tree").getData());
		
	}
}

