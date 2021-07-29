import java.io.Serializable;

public class CustomFile implements Serializable {
    String fileName;
    ClientModel owner;
    long fileSize;

    public CustomFile(String fileName, ClientModel owner) {
        this.fileName = fileName;
        this.owner = owner;
        this.fileSize = (long) 10.3;
    }
}
