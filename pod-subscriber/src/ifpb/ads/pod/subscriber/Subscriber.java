

package ifpb.ads.pod.subscriber;

import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;

/**
 *
 * @author Joseph Sousa
 * @mail Jsantos.te@gmail.com
 * @Since 26/07/2017 , 14:09:46
 */
public class Subscriber {

    private final static String EVENTBUS_HOST="192.168.1.104";
    private final static Integer EVENTBUS_PORT=10998;
    private final static String TOKEN= "---123456---";
    private final static String ID= "Joseph@gmail.com";

    private Socket connect() throws UnknownHostException, IOException{
        Socket socket = new Socket(EVENTBUS_HOST,EVENTBUS_PORT);
        return socket;
    }
    
    private void send(Socket socket) throws IOException{
        StringBuilder sb= new StringBuilder();
        sb.append(TOKEN);
        sb.append(ID);
        sb.append(TOKEN);
        socket.getOutputStream().write(sb.toString().getBytes());
    }
    
    private String wait(Socket socket) throws IOException{
        byte [] b= new byte[1024];
        socket.getInputStream().read(b);
        return new String(b).trim();
    }
    
    private String[] convert(String msg){
        return msg.replaceAll(TOKEN,"").split("\\|");
    }
    
    public void subscribe(){
        try{
            //conect server
            Socket socket = connect();
            //register
            send(socket);
            //wait messages
            boolean waiting = true;
            while(waiting){
                String msg = wait(socket);
                String[] data= convert(msg);
                notify(data[0],data[1]);
            }
            // encerrar
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void notify(String pubId, String msg){
        // processamento em um subsistema
        System.out.println(String.format("Mensagem recebida de %s: %s", pubId,msg));
    }
}
