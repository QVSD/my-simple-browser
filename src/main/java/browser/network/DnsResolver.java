package browser.network;

import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.List;

/** {@code DnsResolver}
* Interface implemented by other DnsResolvers can be either SystemDnsResolver
* or an ManualDnsResolver
*/
public interface DnsResolver{
 /** Resolves hostnames into IP's.
 * @param host hostnames resolved
 * @return list of {@code InetAddress} with the associated IP's
 * @throws UnknownHostException if the hostname is either empty, or invalid
 */
    List<InetAddress> resolve(String host) throws UnknownHostException;
    
}
