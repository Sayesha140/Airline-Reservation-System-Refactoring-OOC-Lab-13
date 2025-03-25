import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlightTimeCalculator {

    static Flight flight;

    public FlightTimeCalculator(Flight flight){
       FlightTimeCalculator.flight =flight;
    }
    private static final double SPEED_MPH = 450.0;

    public static String calculateFlightTime(double distanceInMiles) {
        double time = distanceInMiles / SPEED_MPH;
        int hours = (int) time;
        int minutes = (int) ((time - hours) * 60);
        return String.format("%02d:%02d", hours, minutes);
    }

    public static LocalDateTime getNearestHourQuarter(LocalDateTime datetime) {
        int minutes = datetime.getMinute();
        int mod = minutes % 15;
        return (mod < 8) ? datetime.minusMinutes(mod) : datetime.plusMinutes(15 - mod);
    }

    public static String fetchArrivalTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm a ");
        LocalDateTime departureTime = LocalDateTime.parse(flight.getFlightSchedule(), formatter);

        String[] flightTime = flight.getFlightTime().split(":");
        int hours = Integer.parseInt(flightTime[0]);
        int minutes = Integer.parseInt(flightTime[1]);

        return departureTime.plusHours(hours).plusMinutes(minutes)
                .format(DateTimeFormatter.ofPattern("EE, dd-MM-yyyy HH:mm a"));
    }
}
