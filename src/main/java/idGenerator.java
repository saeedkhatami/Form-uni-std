import java.util.UUID;

public class idGenerator {
    public String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
