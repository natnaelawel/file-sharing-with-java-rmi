import java.io.Serializable;
import java.net.URL;

public class ClientModel implements Serializable {
    int cId;
    String email;
    URL ipAddress;
    String srcDirectory;

    public ClientModel(int cId, String email, URL ipAddress, String srcDirectory) {
        this.cId = cId;
        this.email = email;
        this.ipAddress = ipAddress;
        this.srcDirectory = srcDirectory;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public URL getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(URL ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSrcDirectory() {
        return srcDirectory;
    }

    public void setSrcDirectory(String srcDirectory) {
        this.srcDirectory = srcDirectory;
    }
}
