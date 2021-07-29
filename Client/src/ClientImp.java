import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ClientImp extends UnicastRemoteObject implements ClientInt {

    private ClientModel client;
    private String downloadPath = "";

    public ClientImp(ClientModel client) throws RemoteException {
        super();
        this.client = client;
    }

    public byte[] downloadFile(String fileName){
        try {
            File file = new File(client.srcDirectory+"/"+fileName);
            byte buffer[] = new byte[(int)file.length()];
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(client.srcDirectory+"//"+fileName));
            input.read(buffer,0,buffer.length);
            input.close();
            return(buffer);
        } catch(Exception e){
            System.out.println("FileImpl: "+e.getMessage());
            e.printStackTrace();
            return(null);
        }
    }

    @Override
    public void downloadFromPeer(ClientModel clientModel, ArrayList<String> fileNames) throws NotBoundException, MalformedURLException, RemoteException {

        ClientImp pServer = (ClientImp) Naming.lookup("rmi://"+clientModel.getIpAddress().getHost() + "/PeerServer");
        try {
            for(String fileName: fileNames){
                byte[] fileData =  pServer.downloadFile(fileName);
                Files.write(Paths.get(downloadPath+"/"+fileName), fileData);
                System.out.println("download complete");
            }
        }catch (Exception e){
            System.out.println("There is an exception");
            e.printStackTrace();
        }
    }
}
