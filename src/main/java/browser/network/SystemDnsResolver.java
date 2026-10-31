package browser.network;

import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

public class SystemDnsResolver implements DnsResolver {

    @Override
    public List<InetAddress> resolve(String host) throws UnknownHostException {
       if(host == null || host.isEmpty() || host.isBlank()){
            throw new UnknownHostException("Please enter a valid host");
       }
        InetAddress[] address = InetAddress.getAllByName(host);

        return Arrays.asList(address);
        
    }

}

