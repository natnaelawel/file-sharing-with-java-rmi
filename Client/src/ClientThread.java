import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ClientThread implements Runnable {
    ClientModel clientModel = null;

    ClientThread(ClientModel clientModel) {
        this.clientModel = clientModel;
    }

    public void run() {
        try {
            ServerInt serverImp = (ServerInt) Naming.lookup("chatServer");
            serverImp.addClient(this.clientModel);
            try{
                File directoryPath = new File(this.clientModel.srcDirectory);
                File[] filesList = directoryPath.listFiles();
                assert filesList != null;
                for(File f : filesList) {
                    serverImp.registerFiles(this.clientModel, f.getName());
                }
            }
            catch (NullPointerException e){
                System.out.println("There is an error");
            }
            if(this.clientModel.getEmail().equalsIgnoreCase("natnael.awel@gmail.com")){
                List<CustomFile> searchResults =  serverImp.search("*");

                for(CustomFile file: searchResults){
                    ArrayList<String> filenames = new ArrayList<>();
                    filenames.add(file.fileName);
                    downloadFromPeer(file.owner, filenames);
                }
            }

        } catch (Exception e) {
            System.out.println("Client Thread exception: " + e);
        }
    }

    public void downloadFromPeer(ClientModel clientModel, ArrayList<String> fileNames) throws NotBoundException, MalformedURLException, RemoteException {
        String address = "rmi://"+clientModel.getIpAddress().getHost()+":"+ clientModel.getIpAddress().getPort() + "/PeerServer";
        System.out.println("the remote address is "+ address);
        ClientInt pServer = (ClientInt) Naming.lookup(address);
        try {
            for(String fileName: fileNames){
                byte[] fileData =  pServer.downloadFile(fileName);

                Files.write(Paths.get(this.clientModel.getSrcDirectory()+"/"+fileName), fileData);
                System.out.println("download complete");
            }
        }catch (Exception e){
            System.out.println("There is an exception");
            e.printStackTrace();
        }
    }
}



//            // method to search for the file
//
//            ArrayList<CustomFile> arr;
//            System.out.println("Enter the file name to be searched");
//            //String fileTobeSearched=null;
//            while ((fileTobeSearched = br.readLine()) != null) {
//                arr = serverImp.search(fileTobeSearched);
//                //System.out.println("Peer ID's having the given file are"+arr.get(na));
//                for (int i = 0; i < arr.size(); i++) {
//                    System.out.println("Peer ID's having the given file are" + arr.get(i).owner.getcId());
//                }
//                System.out.println("Enter the peerID of the peer you want to connect?");
//                peerID = br.readLine();
//                downloadFromPeer(peerID, arr);
//                break;
//            }


//    public void downloadFromPeer(ClientModel clientModel, ArrayList<String> fileNames) throws NotBoundException, RemoteException, MalformedURLException, IOException {
//        String downloadPath = "";
//        ClientImp pServer = (ClientImp) Naming.lookup("rmi://"+clientModel.getIpAddress().getHost() + "/PeerServer");
//        try {
//            Path path = Paths.get(downloadPath);
//            for(String fileName: fileNames){
//                byte[] fileData =  pServer.downloadFile(fileName);
//                Files.write(Paths.get(downloadPath+"/"+fileName), fileData);
//                System.out.println("download complete");
//            }
//        }catch (Exception e){
//            System.out.println("There is an exception");
//            e.printStackTrace();
//        }
//    }
