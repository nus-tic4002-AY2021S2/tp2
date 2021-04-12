package seedu.address.model.group;

import java.util.ArrayList;


public class GroupList {
    private static ArrayList<Group> listOfGroup = new ArrayList<>();

    GroupList() {
        Group defaultGroup = new Group();
        defaultGroup.setGroupName("N/A");
        addGroup(defaultGroup);
        assert !listOfGroup.isEmpty();
    }

    public static void addGroup (Group group) {
        listOfGroup.add(group);
    }

    /**
     * Check if group already exist in the listOfGroup
     * @param group name to be checked
     * @return true if exist, false otherwise
     */
    public static boolean hasGroup (Group group) {

        for (int i = 0; i < listOfGroup.size(); i++) {
            if (listOfGroup.get(i).equals(group)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Delete a {@code Group}.
     *
     * @param index is the index number starting from 1.
     */
    public static void deleteGroup (int index) {
        listOfGroup.remove(index - 1);
    }

    /**
     * Get a {@code Group}.
     *
     * @param index is the index number starting from 1.
     */
    public static Group getGroup (int index) {
        return listOfGroup.get(index - 1);
    }

    /**
     * Get all {@code Group}.
     */
    public static ArrayList<Group> getGroupList () {
        return listOfGroup;
    }

    /**
     * List all {@code Groups} as String.
     *
     */
    public static String listGroups () {
        StringBuffer output = new StringBuffer();

        for (int i = 0; i < listOfGroup.size(); i++) {
            if (!listOfGroup.get(i).toString().equals("N/A")) {
                output.append(i);
                output.append(". " + listOfGroup.get(i).toString());
                output.append(System.lineSeparator());
            }
        }
        return output.toString();
    }

    public static int getGroupListSize() {
        return listOfGroup.size();
    }

}
