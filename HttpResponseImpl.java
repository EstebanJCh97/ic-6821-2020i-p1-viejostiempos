import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


class HttpResponseImpl implements HttpResponse {

    private String version;
    private int statusCode;
    private String statusMessage;
    private Map<String, String> header;
    private OutputStream body;
    private OutputStream bodyAux;

    public HttpResponseImpl() {
        this.header = new HashMap<String, String>();
    }
    
    
    public void setMessageBody(InputStream bodyAux) throws IOException {


        OutputStream body = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = bodyAux.read(data, 0, data.length)) != -1) {
            body.write(data, 0, nRead);
        }

        this.body = body;


    }

    public void setVersion(String version) {
        this.version = version;
    }


    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;

    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;

    }


    public void appendHeader(String llave, String valor) {
        this.header.put(llave,valor);
    }


    public OutputStream getOutputStream() {


        String statusMessage2 = this.statusMessage;
        char statusCode2 = (char) (this.statusCode + '0');
        char[] header2 = getAllHash().toCharArray();
        String version2 = this.version;
        String body2 = statusCode2 + statusMessage2 + header2 + version2;
        char[] body3 = body2.toCharArray();
        byte[] body4 = Charset.forName("UTF-8").encode(CharBuffer.wrap(body3)).array();

        byte[] body5 = ((ByteArrayOutputStream) body).toByteArray();

        OutputStream ObjetosGuardados = convertir_unir(body4, body5);

        return ObjetosGuardados;
    }


    private OutputStream convertir_unir(byte[] primero, byte[] segundo) {

        ByteArrayOutputStream variableUnion = new ByteArrayOutputStream();
        OutputStream conversion = new ByteArrayOutputStream();

        try {
            variableUnion.write(primero);
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        try {
            variableUnion.write(segundo);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        byte[] unidas = variableUnion.toByteArray();

        try {
            conversion.write(unidas);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conversion;

    }

    private String getAllHash() {

        String union = null;
        for (String i : this.header.keySet()) {
            String llaveAux = i;
            String valorAux = this.header.get(i);
            union = union + llaveAux + valorAux;
        }
        return union;
    }
}
