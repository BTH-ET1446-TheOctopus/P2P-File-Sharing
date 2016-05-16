package sql;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import org.sqlite.core.DB;
//
//import backend.rest.RESTStartUp;
//import sql.sqlconnector;
//import sql.insertsample;
///*
//
// * starts the rest server in a new thread and adds a shutdown hook 
// * that is executed when the program exits
// * 
// */
//
//public class Main {
//	/*
//	 * #@param args
//	 */
//
//	private static final Logger LOG = Logger.getLogger(Main.class.getName());
//
//	public static void main(String[] args) {
//
//		/*The below command line is calling a method/class "RESTStartUp"
//				 where the bootstrap is initiated, interrupted and terminated  */
//
//		//Creates Server DB on Runtime
//		sqlconnector test=new sqlconnector("mysql");
//
//		//Creates Server DB on Runtime
//		test.createclientdb();
//
//		//Insert Sample Data in Server DB
//		insertsample insample = new insertsample();
//
//		insample.insertcdb();
//
//		//test.closeconnect();
//
//	}
//
//
//}
//
