import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerThread implements Runnable
{
    public void run()
    {
        try
        {
            LocateRegistry.createRegistry(1099);
            ServerInt fileServer = new ServerImp();
            Naming.rebind("chatServer", fileServer);
            System.out.println("File Indexer Server is Running");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Server failed: " + e);
        }
    }
}