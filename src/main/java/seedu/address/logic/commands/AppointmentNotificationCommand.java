package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.appointment.Appointment;






public class AppointmentNotificationCommand extends Command {
    public static final String COMMAND_WORD = "appNotification";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": add medical history to this person";


    public AppointmentNotificationCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder initMessage = new StringBuilder();
        Integer appCount = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Person person : lastShownList) {
            List<Appointment> appointments = new ArrayList<>(person.getAppointments());
            for (Appointment appointment : appointments) {
                String dateString = simpleDateFormat.format(new Date());
                String appDateString = appointment.getDate().substring(0, 10);
                if (dateString.trim().equals(appDateString.trim())) {
                    appCount++;
                    stringBuilder.append(appCount + ". " + person.getName() + " has appointment on "
                            + appointment.appointmentDescription + " at " + appointment.getDate() + "\n");
                }
            }
        }

        if (appCount == 0) {
            initMessage.append("Welcome, today has no appointment for anyone.");
        } else {
            initMessage.append("There are " + appCount + " appointment(s) today : \n");
            initMessage.append(stringBuilder);
        }


        return new CommandResult(initMessage.toString());
    }

}
