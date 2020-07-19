import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

/**
 * @author chenqian091
 * @date 2020-07-11
 */
public class Test {

    public static void main (){
        String springVersion = SpringVersion.getVersion();
        String springBootVersion = SpringBootVersion.getVersion();

        System.out.println(springBootVersion+"   "+springVersion);
    }
}
