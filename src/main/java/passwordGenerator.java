import java.util.UUID;

public class passwordGenerator {
    public String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
