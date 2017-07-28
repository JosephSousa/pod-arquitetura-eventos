

package ifpb.ads.pod.publisher;

/**
 *
 * @author Joseph Sousa
 * @mail Jsantos.te@gmail.com
 * @Since 26/07/2017 , 14:06:58
 */
public class Main {
    
    public static void main(String[] args) {
        String id="Joseph@gmail.com";
        Publisher publisher= new Publisher(id);
        publisher.publish("Joseph-subscriber@gmail.com", "hello world");
    }
}
