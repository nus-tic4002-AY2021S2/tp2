package seedu.address.model.group;

/**
 * Represents a Person's group in the address book.
 */
public class Group {
    private String groupName;

    @Override
    public String toString() {
        return groupName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Group // instanceof handles nulls
                && groupName.equals(((Group) other).groupName)); // state check
    }

    @Override
    public int hashCode() {
        return groupName.hashCode();
    }

    /**
     * Modify a {@code Group}.
     *
     * @param newGroupName A valid groupName.
     */
    public Group setGroupName(String newGroupName) {
        this.groupName = newGroupName;
        return this;
    }
}
