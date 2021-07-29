import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Starter {

    public static void main(String[] args) throws IOException {

//        ClientModel clientModel = new ClientModel(1, "email@email.com", new URL("http://localhost:5500"), "C://Users/Desktop");
        ClientModel clientModel = new ClientModel(Integer.parseInt(args[0]), args[1], new URL("http://"+args[2]), args[3]);

        try {
            LocateRegistry.createRegistry(clientModel.getIpAddress().getPort());
            ClientInt clImp = new ClientImp(clientModel);
            System.out.println("Directory name" + clientModel.srcDirectory);
            Naming.rebind("rmi://"+clientModel.getIpAddress().getHost()+ ":" +clientModel.getIpAddress().getPort() + "/PeerServer", clImp);
        } catch (Exception e) {
            System.err.println("Peer FileServer exception: " + e.getMessage());
            e.printStackTrace();
        }
        new ClientThread(clientModel).run();


    }
}
