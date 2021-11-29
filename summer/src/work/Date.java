package work;
import java.time.*;

public class Date {

    private LocalDate date;

    public Date(){
        date = LocalDate.now();
    }

    public LocalDate getDate(){
        return date;
    }

}
