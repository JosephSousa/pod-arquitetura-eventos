

package ifpb.ads.pod.publisher;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Sousa
 * @mail Jsantos.te@gmail.com
 * @Since 26/07/2017 , 13:55:14
 */
public class Publisher {

    private final static String EVENTBUS_HOST = "192.168.1.104";
    private final static Integer  EVENTBUS_PORT= 10999;
    private final String identify;

    public Publisher(String identify) {
        this.identify = identify;
    }
    
    public void publish(String subscriberId, String texto){
        
        Socket socket = null;
        
        try{
            //abrir uma conexão
            socket= new Socket(EVENTBUS_HOST, EVENTBUS_PORT);
            // escrever a mensagem 
            Protocol protocol = new Protocol();
            protocol.setPublisher(identify);
            protocol.setSubscriber(subscriberId);
            protocol.setMessage(texto);
            socket.getOutputStream().write(protocol.requestData());
            //receber um OK
            byte[] b= new byte[4];
            socket.getInputStream().read(b);
            if("#OK#".equals(new String(b))){
                System.out.println("Mensagem publicada com sucesso");
            }
            else{
                System.out.println("Mensagem não publicada, tente novamente");
            }
        } catch (IOException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            //encerrar conexão
            if(socket!= null){
                try{
                    socket.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
