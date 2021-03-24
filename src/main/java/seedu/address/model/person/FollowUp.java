package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class FollowUp {

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param followUp A valid remark.
     */
    public FollowUp(String followUp) {
        requireNonNull(followUp);
        value = followUp;
    }

    @Override
    public String toString() {
        System.out.println("i am here follow up");
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FollowUp // instanceof handles nulls
                && value.equals(((FollowUp) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
