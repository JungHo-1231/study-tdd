package secondReading.chapter08.payInfo;

import lombok.Data;

@Data
public class PayInfo {
    private final String datetime;
    private final String trNum;
    private final int amounts;
}
