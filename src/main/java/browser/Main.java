package browser;

import java.lang.Integer;
import browser.url.Url;
/**
 *
 * http://example.com
 * http://example.com/
 * http://example.com/test
 * http://example.com:8080/test
 *
 * trebuie sa parsam aceste url uri si in final sa producem :
 * scheme = http
 * host = example.com
 * port = 80 / 8080
 * path = /
 *
 **/

public class Main {
    enum State{
        SCHEME,
        FIRST_SLASH,
        SECOND_SLASH,
        HOST, 
        PORT,
        PATH
    }

    public static void main(String[] args){
        if(args.length < 1){
            System.out.println("Invalid input");
            return;
        }

        String url = args[0];
        
        try {  
          Url parsedUrl = parseUrl(url);
          System.out.println("Path : " + url);
          parsedUrl.printUrlComponents();
        }
        catch (IllegalArgumentException e){
            System.out.println("Reverifica integritatea linkului: " + e.getMessage());

        }
    }

    public static Url parseUrl(String urlToParse){
        State state = State.SCHEME;

        Url resultedUrl = new Url();
        
        StringBuilder scheme = new StringBuilder();
        StringBuilder host = new StringBuilder();
        StringBuilder port = new StringBuilder();
        StringBuilder path = new StringBuilder();
        
        for(int i = 0; i < urlToParse.length(); i++){
            char c = urlToParse.charAt(i);
            
            if(c == ' ' 
                    || c == '\r'
                    || c == '\f'
                    || c == '\n'
                    || c == '\t'
                    || c == '\u000B'){
                throw new IllegalArgumentException("Whitespace in the link");
            }
            switch(state){
                case SCHEME: 
                    if(c != ':'){
                        scheme.append(c);
                    } else{
                        state = State.FIRST_SLASH;
                    }
                    break;
                case FIRST_SLASH:
                    if(c != '/'){
                        throw new IllegalArgumentException("Error: Invalid URL");
                    } else {
                        state = State.SECOND_SLASH;
                    }
                    break;
                case SECOND_SLASH:
                    if(c != '/'){
                        throw new IllegalArgumentException("Error: Invalid URL");
                    } else {
                        state = State.HOST;
                    }
                    break;
                case HOST:
                    if(c == ':'){
                        state = State.PORT;
                    } else {
                        if(c == '/'){
                            state = State.PATH;
                            path.append(c);
                        } else {
                            host.append(c);
                        }
                    }
                    break;
                case PORT:
                    if(Character.isDigit(c)){
                        port.append(c);
                        if(Integer.parseInt(port.toString()) > 65535){
                            throw new IllegalArgumentException("URL is wrong. Port out of range");
                        }
                    } else {
                        if(c == '/'){
                            state = State.PATH;
                            path.append(c);
                        } else {
                            throw new IllegalArgumentException("URL is wrong");
                        }
                    }
                    break;
                case PATH:
                    path.append(c);
                    break;
            }
        }
        if(scheme.length() == 0 || host.length() == 0){
            throw new IllegalArgumentException("Schema or host are missing");
        }
        resultedUrl.scheme = scheme.toString();
        resultedUrl.host = host.toString();
       
        if(!port.toString().isEmpty()){
            resultedUrl.port = Integer.parseInt(port.toString());
        }
        resultedUrl.path = path.toString(); 
        if(resultedUrl.path.isEmpty()){
            resultedUrl.path = "/";
        }
       

        return resultedUrl;
    }
}


