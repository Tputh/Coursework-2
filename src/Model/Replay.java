package Model;

import java.time.LocalDateTime;

public interface Replay {
    LocalDateTime getTimeNextType(LocalDateTime dateTime);
}
