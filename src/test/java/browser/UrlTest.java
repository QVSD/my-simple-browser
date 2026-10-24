package browser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import browser.url.Url;

public class UrlTest{

    @Test
    public void testUrlComplete(){
            
        Url url = Main.parseUrl("http://example.com:8090/test");
   
       Assertions.assertEquals("http", url.scheme);
       Assertions.assertEquals("example.com", url.host);
       Assertions.assertEquals(8090, url.port);
       Assertions.assertEquals("/test", url.path);

    }

    @Test
    public void testUrlWithNoPort() {

        Url url = Main.parseUrl("http://example.com/test");

        
        Assertions.assertEquals("http", url.scheme);
        Assertions.assertEquals("example.com", url.host);
        Assertions.assertEquals(80, url.port);
        Assertions.assertEquals("/test", url.path);
      
    }

   @Test
   public void testUrlWithNoPath(){
            
       Url url = Main.parseUrl("http://example.com:8090/");
   
       Assertions.assertEquals("http", url.scheme);
       Assertions.assertEquals("example.com", url.host);
       Assertions.assertEquals(8090, url.port);
       Assertions.assertEquals("/", url.path);

    }
    
   @Test
   public void testUrlWithWrongPort(){   
       Assertions.assertThrows(IllegalArgumentException.class,
               () -> Main.parseUrl("http://example.com:abc/"));
    }

    @Test
    public void testUrlWithSpaceInside(){
       Assertions.assertThrows(IllegalArgumentException.class,
               () -> Main.parseUrl("http://exa mple.com/test"));
    }


}
