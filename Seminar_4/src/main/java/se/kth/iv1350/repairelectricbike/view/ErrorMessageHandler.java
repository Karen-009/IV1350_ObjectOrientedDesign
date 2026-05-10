package se.kth.iv1350.repairelectricbike.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for showing error messages to the user.
 */
public class ErrorMessageHandler {
    
    /**
     * Displays the specified error message to the user.
     *
     * @param msg The error message to display.
     */
    public void showErrorMsg(String msg) {
        StringBuilder builder = new StringBuilder();
        builder.append(createTime());
        builder.append(", ERROR: ");
        builder.append(msg);
        System.out.println(builder);
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
