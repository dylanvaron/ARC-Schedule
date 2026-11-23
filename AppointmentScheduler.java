import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * STUDENT WORK: complete five marked methods below.
 * TreeSet is backed by a balanced BST. It orders appointments by start time.
 */
public class AppointmentScheduler {
    public static final int OPEN_MINUTES = 8 * 60;
    public static final int CLOSE_MINUTES = 17 * 60;
    public static final String FILE_HEADER = "BST-SCHEDULE-V1";
    private final TreeSet<Appointment> schedule = new TreeSet<>();

    public int size() { return schedule.size(); }

    /**
     * TODO 1: Book the appointment if it is within 08:00-17:00 AND does not
     * overlap any existing appointment. Back-to-back bookings are allowed.
     * Use TreeSet.floor(requested) and TreeSet.ceiling(requested) to inspect
     * just the adjacent appointments. Return true only if added.
     */
    public boolean book(Appointment requested) {
        if(requested.getStartMinutes() < OPEN_MINUTES || requested.getStartMinutes()+requested.getDurationMinutes() > CLOSE_MINUTES) {
            return false;
        }
        
        Appointment floored = schedule.floor(requested);
        if(floored != null) {
            if(requested.getStartMinutes() >= floored.getStartMinutes() && requested.getStartMinutes() < floored.getStartMinutes()+floored.getDurationMinutes()) {
                return false;
            }
        }

        Appointment ceilinged = schedule.ceiling(requested);
        if(ceilinged != null) {
            if(requested.getStartMinutes()+requested.getDurationMinutes() > ceilinged.getStartMinutes()) {
                return false;
            }
        }

        schedule.add(requested);
        return true;
    }

    public void printSchedule() {
        if(size() == 0) {
            System.out.println("The schedule is empty");
        }
        else {
            for(Appointment a : schedule) {
                System.out.println(a.toString());
            }
        }
    }

    public boolean cancel(int startMinutes) {
        return schedule.remove(Appointment.keyAt(startMinutes));
    }

    public Appointment nextAtOrAfter(int minutes) {
        return schedule.ceiling(Appointment.keyAt(minutes));
    }

    public void write(PrintWriter out) {
        out.println(FILE_HEADER);
        for(Appointment a : schedule) {
            out.println(a.toFileLine());
        }
    }

    /** PROVIDED: loading is deliberately independent of unfinished student methods. */
    public void load(Scanner input) {
        if (!input.hasNextLine() || !FILE_HEADER.equals(input.nextLine().trim())) {
            throw new IllegalArgumentException("Missing or incorrect schedule header.");
        }
        TreeSet<Appointment> incoming = new TreeSet<>();
        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (line.isEmpty()) continue;
            Appointment item = Appointment.fromFileLine(line);
            if (item.getStartMinutes() < OPEN_MINUTES || item.getEndMinutes() > CLOSE_MINUTES ||
                    !incoming.add(item)) {
                throw new IllegalArgumentException("Invalid saved appointment: " + line);
            }
        }
        Appointment previous = null;
        for (Appointment item : incoming) {
            if (previous != null && previous.getEndMinutes() > item.getStartMinutes()) {
                throw new IllegalArgumentException("Overlapping appointments in saved file.");
            }
            previous = item;
        }
        schedule.clear();
        schedule.addAll(incoming);
    }
}
