

package ifpb.ads.pod.publisher;

/**
 *
 * @author Joseph Sousa
 * @mail Jsantos.te@gmail.com
 * @Since 26/07/2017 , 13:45:56
 */
public class Protocol {
    
    private final static String TOKEN= "---123456---";
    
    private String pub;
    private String msg;
    private String sub;
    
    public void setPublisher(String publisher){
        this.pub = publisher;
    }
    
    public void setMessage(String message){
        this.msg =  message;
    }
    
    public void setSubscriber(String subscriber){
        this.sub = subscriber;
    }
    
    public byte[] requestData(){
        StringBuilder sb = new StringBuilder();
        sb.append(TOKEN);
        sb.append(pub);
        sb.append("|");
        sb.append(pub);
        sb.append("|");
        sb.append(msg);
        sb.append(TOKEN);
        return sb.toString().getBytes();
    }

}
