import junit.framework.TestCase;
import java.util.Random;;




/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorRandomTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorRandomTest(String testName) {
      super(testName);
   }
   
   public void testRandomPort() {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   Random rand = new Random();
	   for (int i = 0; i < 20; i++) {
		   int rd = rand.nextInt(15000);
		   String url = "http://www.google.com:" + rd;
		   System.out.println(url);
		   assertTrue(urlVal.isValid(url));
	   }
   }
   
   public void testRandomQuery() {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   Random rand = new Random();
	   StringBuffer query = new StringBuffer();
	   
	   for (int i = 0; i < 20; i++) {
		   int rd = rand.nextInt(26);
		   char ch = (char)('a' + rd);
		   query.append(ch);
		   System.out.println(query);
		   assertTrue(urlVal.isValidQuery(query.toString()));
	   }
   }
   
}