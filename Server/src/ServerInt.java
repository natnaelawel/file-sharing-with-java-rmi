import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface ServerInt extends Remote {

    List<String> getCategories(ClientInt client) throws RemoteException;
    void addClient(ClientModel clientModel) throws RemoteException;
    void removeClient(String clientName) throws RemoteException;
    Set<ClientModel> getClients() throws RemoteException;
    ClientModel getOwner(String clientName) throws RemoteException;
    boolean isFileOwnerOnline(String username) throws RemoteException;
    void registerFiles(ClientModel clientModel, String filename)throws RemoteException;
    List<CustomFile> search(String filename)throws RemoteException;
    void calculateAvgResponseTime(String fileName, String peerId,String portNo, String srcDir) throws RemoteException;


}
