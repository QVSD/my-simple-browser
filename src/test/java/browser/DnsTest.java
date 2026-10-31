package browser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import browser.network.SystemDnsResolver;
import browser.network.DnsResolver;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.List; 

public class DnsTest{

    @Test
    void shouldResolveNumericAddresses() throws UnknownHostException{
        DnsResolver resolver = new SystemDnsResolver();

        List<InetAddress> address = resolver.resolve("192.0.2.1");

        Assertions.assertEquals(1, address.size());
        Assertions.assertEquals("192.0.2.1", address.get(0).getHostAddress());
    }

    @Test
    public void shouldRejectNullHost(){
        DnsResolver resolver = new SystemDnsResolver();
        Assertions.assertThrows(UnknownHostException.class, () -> resolver.resolve(null));
    }

    @Test 
    void shouldRejectBlankStrings(){
        DnsResolver resolver = new SystemDnsResolver();
        Assertions.assertThrows(UnknownHostException.class, () -> resolver.resolve("       "));
    }

    @Test 
    void shouldResolveLocalHost() throws UnknownHostException {
        DnsResolver resolver = new SystemDnsResolver();

        List<InetAddress> addresses = resolver.resolve("localhost");

        Assertions.assertFalse(addresses.isEmpty());
    }


}
