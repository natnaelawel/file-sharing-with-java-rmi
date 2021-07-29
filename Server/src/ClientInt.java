import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInt extends Remote {
    byte[] downloadFile(String fileName) throws RemoteException;
    void downloadFromPeer(ClientModel clientModel, ArrayList<String> fileNames) throws NotBoundException, MalformedURLException, RemoteException;


}
