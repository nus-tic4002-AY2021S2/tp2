package seedu.address.model.group;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class GroupList {
    private ArrayList<Group> listOfGroup= new ArrayList<>();

    public void addGroup (Group group) {
        listOfGroup.add(group);
    }

    /**
     * Delete a {@code Group}.
     *
     * @param index is the index number starting from 1.
     */
    public void deleteGroup (int index) {
        listOfGroup.remove(index - 1);
    }

    /**
     * Get a {@code Group}.
     *
     * @param index is the index number starting from 1.
     */
    public Group getGroup (int index) {
        return listOfGroup.get(index - 1);
    }

    public String listGroups () {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < listOfGroup.size(); i++) {
            output.append(i + 1);
            output.append(". " + listOfGroup.get(i).toString());
            output.append(System.lineSeparator());
        }
        return output.toString();
    }

}
