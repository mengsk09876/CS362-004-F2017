/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import junit.framework.TestCase;





/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   System.out.println(urlVal.isValid("http://www.google.com:15000"));
	   System.out.println(urlVal.isValid("http://www.amazon.com"));
	   System.out.println(urlVal.isValid("http:///www.amazon.com"));
	   System.out.println(urlVal.isValid("http://www.google.com"));
	   System.out.println(urlVal.isValid("http:/www.baidu.com"));
	   System.out.println(urlVal.isValid("http://www.baidu.com"));
	   System.out.println(urlVal.isValid("https://www.baidu.com"));
   }
   
   
   public void testYourFirstPartition()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   StringBuffer url = new StringBuffer();
	   url.append("http://www.");
	   for (int i = 0; i < 26; i++) {
		   StringBuffer url1 = new StringBuffer(url.toString());
		   char ch1 = (char)('a' + i);
		   url1.append(ch1);
		   for (int j = 0; j < 26; j++) {
			   StringBuffer url2 = new StringBuffer(url1.toString());
			   char ch2 = (char)('a' + j);
			   url2.append(ch2);
			   for (int k = 0; k < 26; k++) {
				   StringBuffer url3 = new StringBuffer(url2.toString());
				   StringBuffer url4 = new StringBuffer(url2.toString());
				   char ch3 = (char)('a' + k);
				   url3.append(ch3);
				   url3.append(".com");
				   url4.append(ch3);
				   url4.append(".cn");
				   //url4.append();
				   //System.out.println(url4.toString());
				   assertTrue(urlVal.isValid(url3.toString()));
				   assertTrue(urlVal.isValid(url4.toString()));
			   }
			   url2.deleteCharAt(url1.length()-1);
		   }
		   url1.deleteCharAt(url1.length()-1);
	   }
   }
   
   public void testYourSecondPartition(){
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   StringBuffer url = new StringBuffer();
	   url.append("http://www.");
	   for (int i = 0; i < 26; i++) {
		   StringBuffer url1 = new StringBuffer(url.toString());
		   char ch1 = (char)('a' + i);
		   url1.append(ch1);
		   for (int j = 0; j < 26; j++) {
			   StringBuffer url2 = new StringBuffer(url1.toString());
			   char ch2 = (char)('a' + j);
			   url2.append(ch2);
			   for (int k = 0; k < 26; k++) {
				   StringBuffer url3 = new StringBuffer(url2.toString());
				   StringBuffer url4 = new StringBuffer(url2.toString());
				   char ch3 = (char)('a' + k);
				   url3.append(ch3);
				   url3.append(".comaa");
				   url4.append(ch3);
				   url4.append("//");
				   //url4.append();
				   //System.out.println(url4.toString());
				   assertFalse(urlVal.isValid(url3.toString()));
				   assertFalse(urlVal.isValid(url4.toString()));
			   }
			   url2.deleteCharAt(url1.length()-1);
		   }
		   url1.deleteCharAt(url1.length()-1);
	   }
   }
   
   public void testIsValid()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   StringBuffer url = new StringBuffer();
	   url.append("http://www.google.com:");
	   for (int i = 0; i < 10000; i++) {
		   url.append(i);
		   //System.out.println(url.toString());
		   //There is a bug!
		   assertTrue(urlVal.isValid(url.toString()));
		   url.deleteCharAt(url.length()-1);
	   }
   }
   
   public void testAnyOtherUnitTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String authority1 = "www.google.com:50";
	   String authority2 = "www.google.com:15000";	
	   String authority3 = "www.google.com:000";	
	   assertTrue(urlVal.isValidAuthority(authority1));
	   assertTrue(urlVal.isValidAuthority(authority2));
	   assertFalse(urlVal.isValidAuthority(authority3));
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */
   public void testUrlArrays() {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   String[] testWebsites = {"http://www.google.com", "www.baidu.com", "https://www.amazon.com"};
	   String[] testAuthorities = {":100", "1000", "10000"};
	   String[] testPaths = {"/home", "/home/users/", "/home/", ""};
	   
	   for (int i = 0; i < testWebsites.length; i++) {
		   	for (int j = 0; j < testAuthorities.length; j++) {
		   		for (int k = 0; k < testPaths.length; k++) {
		   			String cur = testWebsites[i] + testAuthorities[j] + testPaths[k];
		   			System.out.println(urlVal.isValid(cur) + "\t" + cur);
		   		}
		   	}
	   }
   }


}
