import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {

    public boolean isValidDate(String inputDate) {
        String dateFormat = "dd-MM-yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        try {
            LocalDate date = LocalDate.parse(inputDate, formatter);
            return true; // If parsing succeeds, the date is valid
        } catch (DateTimeParseException e) {
            return false; // If parsing fails, the date is invalid
        }
    }

//    public static void main(String[] args) {
//        String inputDate = "2023-12-01"; // Replace this with your input date
//        String dateFormat = "yyyy-MM-dd"; // Define the expected date format
//
//        if (isValidDate(inputDate, dateFormat)) {
//            System.out.println("Valid date!");
//        } else {
//            System.out.println("Invalid date!");
//        }
//    }
}
