package browser.url;



public class Url {

    public String scheme;
    public String host;
    public int port;
    public String path;


    public Url(){
        this.scheme = null;
        this.host = null;
        this.port = 80;
        this.path = null;
    }
    public Url(String scheme, String host, int port, String path){
        this.scheme = scheme;
        this.host = host;
        this.port = port;
        this.path = path;
    }

    public void printUrlComponents(){
        System.out.println(
                " scheme : " + this.scheme +
                "\n host: " + this.host +
                "\n port: " + this.port +
                "\n path: " + this.path
                );
    }
}
