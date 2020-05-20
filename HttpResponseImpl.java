import java.io.OutputStream;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse{
    private String version;
    private int StatusCode;
    private String StatusMessage;
    private Map<String,String> header;
    private OutputStream body;


    
    public void HttpResponseImpl(OutputStream body) {
        this.body = body;

    }
 

    public void setVersion(String version) {
        this.version = version;
    }


    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;

    }

    public void setStatusMessage(String StatusMessage) {
        this.StatusMessage = StatusMessage;

    }

    
    public void appendHeader(String llave,String valor) {
        header.put(llave,valor);
    }


    public OutputStream getData(){
        return body;
    }
}
