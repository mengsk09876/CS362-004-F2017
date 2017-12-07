import junit.framework.TestCase;
import java.util.Random;;


public class RandomTestURLValidator extends TestCase {
	private boolean printStatus = false;
	private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

	public RandomTestURLValidator(String testName) {
	   super(testName);
	}
	
	/**
	 * generates correct port number
	 * @return port string like ":xxx". 
	 */
	private String CorrectPortGenerator() {
		Random rand = new Random();
		   
		int rd = rand.nextInt(15000);
		String ret = ":" + rd;
		return ret; 
	}
	
	/**
	 * generates incorrect port number
	 * @return port string like ":xxx". 
	 */
	private String IncorrectPortGenerator() {
		Random rand = new Random();
		String ret = "";
		int rd = rand.nextInt(15000);
		if (rand.nextBoolean()) {
			ret = ":-" + rd;
		} else {
			rd += 100000;
			ret = ":" + rd;
		}
		
		return ret; 
	}
	
	/**
	 * generate correct sites
	 * @return sites
	 */
	private String CorrectSiteGenerator() {
		String[] sites = {"www.google.com", "www.amazon.com", "www.baidu.com",
						"music.163.com"};
		Random rand = new Random();
		String ret = "";
		if (rand.nextBoolean()) {
			ret = "http://" + sites[rand.nextInt(3)];
		} else {
			ret = "https://" + sites[rand.nextInt(3)];
		}
		
		return ret;
	}
	
	/**
	 * generate incorrect sites
	 * @return sites
	 */
	private String IncorrectSiteGenerator() {
		String[] sites = {"wwww.google.com", "wwwww.amazon.cm", "www.baidu.com",
						"music.163_com"};
		Random rand = new Random();
		String ret = "";
		if (rand.nextBoolean()) {
			ret = "htttp://" + sites[rand.nextInt(3)];
		} else {
			ret = "httpss://" + sites[rand.nextInt(3)];
		}
		
		return ret;
	}
	
	/**
	 * generate correct ip address
	 * @return
	 */
	private String CorrectIPGenerator() {
		Random rand = new Random();
		String ret = "http://" + rand.nextInt(255) + "." + rand.nextInt(255) + "." + 
				rand.nextInt(255) + "." + rand.nextInt(255);
		return ret;
	}
	
	/**
	 * generate correct ip address
	 * @return
	 */
	private String IncorrectIPGenerator() {
		Random rand = new Random();
		int ip1 = 0;
		while (ip1 <= 255) {
			ip1 = rand.nextInt(1000);
		}
		String ret = ip1 + "." + rand.nextInt(1000) + "." + 
				rand.nextInt(1000) + "." + rand.nextInt(1000);
		return ret;
	}
	
	private String CorrectPathGenerator() {
		String ret = "/home/users/index.html";
		return ret;
	}
	
	private String IncorrectPathGenerator() {
		String ret = "/home//users///index.html";
		return ret;
	}

	public void testIsValid() {
		//generate correct test data.
		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		String correctUrl;
		String incorrectUrl;
		
		for (int i = 0; i < 10; i++) {
			correctUrl = CorrectSiteGenerator();
			System.out.println(correctUrl);
			assertTrue(urlVal.isValid(correctUrl));
		}
		
		for (int i = 0; i < 10; i++) {
			correctUrl = CorrectIPGenerator();
			System.out.println(correctUrl);
			assertTrue(urlVal.isValid(correctUrl));
		}
		
		for (int i = 0; i < 10; i++) {
			correctUrl = CorrectSiteGenerator() + CorrectPathGenerator();
			System.out.println(correctUrl);
			assertTrue(urlVal.isValid(correctUrl));
		}
		
		for (int i = 0; i < 10; i++) {
			correctUrl = CorrectSiteGenerator() + CorrectPortGenerator() + CorrectPathGenerator();
			System.out.println(correctUrl);
			assertTrue(urlVal.isValid(correctUrl));
		}
		
		//generate incorrect test data.
		for (int i = 0; i < 10; i++) {
			incorrectUrl = IncorrectSiteGenerator();
			System.out.println(incorrectUrl);
			assertFalse(urlVal.isValid(incorrectUrl));
		}
		
		for (int i = 0; i < 10; i++) {
			incorrectUrl = IncorrectSiteGenerator() + IncorrectPathGenerator();
			System.out.println(incorrectUrl);
			assertFalse(urlVal.isValid(incorrectUrl));
		}
		
		for (int i = 0; i < 10; i++) {
			incorrectUrl = IncorrectSiteGenerator() + IncorrectPortGenerator() + IncorrectPathGenerator();
			System.out.println(incorrectUrl);
			assertFalse(urlVal.isValid(incorrectUrl));
		}
	}
}
