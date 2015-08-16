# RESTing
REST API testing framework

### Usage example:

###### Build your own test case:
```java
package it.caldesi.resting.sample;

import it.caldesi.resting.TestCase;
import it.caldesi.resting.net.RequestMethod;
import it.caldesi.resting.net.Response;

import java.util.HashMap;

/**
 * @author Domenico Rosario Caldesi, <d.r.caldesi@gmail.com>
 */
public class SampleTestCase extends TestCase {

    @Override
    public void setUp() {

    }

    @Override
    public void cleanUp() {

    }

    public void testCasePass() {
        assertTrue(true);
    }

    public void testCaseFail() {
        assertTrue(false);
    }

    public void testRestApi1(){
        String url = "http://localhost/uitracker/webservices/trackevent.php";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("category", "WEB_EVENT");
        params.put("action", "A_CLICK");
        Response response = doRequest(url, params, RequestMethod.GET);
        assertEqual(response.getJSONResponse().getString("status"), "ok");
    }

}
```

###### Run test case (in TestLauncher class):
```java
    // EXAMPLE MAIN
    public static void main(String[] args) throws Exception {
        executeTest(SampleTestCase.class);
    }
```