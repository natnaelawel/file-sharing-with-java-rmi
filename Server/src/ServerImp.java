import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServerImp extends UnicastRemoteObject implements ServerInt {
    public Set<CustomFile> files;
    public Set<ClientModel> clientModels;

    protected ServerImp() throws RemoteException {
        super();
        this.files =  new HashSet<>();
        this.clientModels = new HashSet<>();
    }



    @Override
    public List<String> getCategories(ClientInt client) throws RemoteException {
        List<String> directoryList = new ArrayList<>();
//        try {
//            File[] directories = new File("./uploads/").listFiles(File::isDirectory);
//            for(int i = 0; i < directories.length; i++){
//                directoryList.add(directories[i].getName());
//                System.out.println(directories[i].getName());
//            }
//        }catch (Exception e){
//            System.out.println("there is an exception ");
//            e.printStackTrace();
//        }
        return directoryList;
    }

    @Override
    public void addClient(ClientModel newClient) throws RemoteException {
        for(ClientModel client: clientModels){
            if(client.getEmail().equalsIgnoreCase(newClient.getEmail())){
                clientModels.remove(client);
            };
        }
        clientModels.add(newClient);
        System.out.println("Connected clients "+clientModels.toString());
    }

    @Override
    public void removeClient(String clientName) throws RemoteException {
        clientModels.remove(clientName);
    }

    @Override
    public Set<ClientModel> getClients() throws RemoteException {
        return clientModels;
    }

    @Override
    public ClientModel getOwner(String clientEmail) throws RemoteException {
        for(ClientModel client: clientModels){
            if(client.getEmail().equalsIgnoreCase(clientEmail)){
                return client;
            }
        }
        return null;
    }

    @Override
    public boolean isFileOwnerOnline(String clientEmail) throws RemoteException {
        for(ClientModel client: clientModels){
            if(client.getEmail().equalsIgnoreCase(clientEmail)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void registerFiles(ClientModel clientModel, String filename) throws RemoteException {
        System.out.println("files size are "+ this.files.size());
        CustomFile cf = new CustomFile(filename, clientModel);
        this.files.add(cf);
        System.out.println("File name"+" "+cf.fileName+" registered with peerID"+" "+cf.owner.getcId()+" on port number"+cf.owner.getIpAddress().getPort()+" and the directory is"+cf.owner.srcDirectory);
    }

    @Override
    public List<CustomFile> search(String filename) throws RemoteException {
        ArrayList<CustomFile> searchResults= new ArrayList<>();

        if(filename.equalsIgnoreCase("*")){
            searchResults.addAll(files);
            return searchResults;
        }
        for(CustomFile customFile: files)
        {
            if(filename.equalsIgnoreCase(customFile.fileName))
            {
                searchResults.add(customFile);
            }
        }
        return searchResults;
    }

    @Override
    public void calculateAvgResponseTime(String fileName, String peerId, String portNo, String srcDir) throws RemoteException {

    }
}

//@Override
//    public void download(ClientInt client, String category, String fileName, int fileSize) throws RemoteException {
//        try {
//            Path path = Paths.get(rootPath+category+"/"+fileName);
//            if (Files.exists(path)) {
//                byte[] fileData = Files.readAllBytes(path);
//                System.out.println("there is a file");
//                client.download(fileData, fileName , fileSize);
//            }else{
//                System.out.println("there is an error");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("There is an exception");
//            e.printStackTrace();
//        }
//    }