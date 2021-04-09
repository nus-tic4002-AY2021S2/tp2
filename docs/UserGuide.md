---
layout: page
title: Addressbook NS Edition (ABNS) 
---

Addressbook NS Edition (ABNS) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ABNS can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ABNS.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:


* **`help`** : Shows a message explaining how to access the help page.

* **`show`** : Shows a list of all groups  in the address book.

* **`create`**`g/GROUP_NAME` : Creates a new group to the address book.

* **`listfromgrp`**`g/GROUP_NAME` : Lists all persons in a particular group.

* **`deletegrp`**`GROUP_INDEX` : Deletes the specified group from the address book.

* **`assignptg`**`n/PERSON_NAME g/GROUP_NAME` : Assign a person by using his name to the specified group.

* **`rename`**`GROUP_INDEX g/GROUP_NAME` : Renaming the existing group at the index to another name.

* **`list`** : Lists all contacts.

* **`add`**n/NAME p/CONTACT_NUMBER e/EMAIL a/ADDRESS [t/TAG]... [g/GROUP] : Adds a contact named to the Address Book.
* E.g_1: `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #1-01 g/A`
* E.g_2: `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #1-01 t/B`

* **`delete`**`3` : Deletes the 3rd contact shown in the current list.

* **`clear`** : Deletes all contacts.

* **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​ [g/GROUP]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
A person who is created will have have default group created. The group shall be "N/A".
If a person is created with a group, then it can be pre-assigned with a user given group.
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`
* * `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal g/GroupA` 

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​[g/GROUP]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* Edit group, eg: `edit 1 g/A1` is allowed but make sure the group does exist. Otherwise there's nothing will happen. 

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Creating a group: `create`

Creates a new group to the Address book NS Edition

Format: `create g/<GROUP NAME>`

Note: 

* Any white spaces that comes before or after the group name will be removed.

* The create group will accept all characters (except names that consist only of spaces) as a valid name for the group. This includes special characters. Group name such as "!!!" or ".?" and etc. are considered valid group name. The purpose is to allow for flexibility such as the use of symbol in representing Groups. (e.g.) "!!!" could be a non-direct internal term or lingo for people in the military to refer to a group for something Highly Important.

* The group name is also case sensitive. 'A' is not the same as 'a'.

Examples:
*  `create g/FITNESS` Creates a group called Fitness
*  `create g/COMBAT` Creates a group called COMBAT

### Listing all groups: `show`

Lists all groups in the Address book NS Edition

Format: `show`

Examples:
*  `show` Lists all groups

### Rename the group: `rename`

Rename an existing group to another name.

Format: `rename <INDEX> g/<GROUP NAME>`

* Rename the group at the specified INDEX
* The index refers to the index number shown in the displayed group list.
* The index must be a positive integer 1, 2, 3, …
* Existing values will be updated to the input values.
* Invalid index and command will trigger a message to tell the correct command format 

Examples:
*  `rename 1 g/PERFORMANCE ` Rename the group with index 1 into  PERFORMANCE.

### Assign a person to group: `assignptg`

Assign a person to a specified group by using  person's name.

Format: `assignptg n/<PERSON NAME> g/<GROUP NAME>`

* Using the person's name to assign
* Both person and group should exist.
* Invalid command or format will trigger an error message

Examples:
*  `assignptg n/Alice g/TEST ` Assign Alice to the group TEST.

### List all persons in a group: `listfromgrp`

Lists all persons in a particular group.

Format: `listfromgrp g/<GROUP NAME>`

* The group name  is case-insensitive. e.g IPPT will match ippt

Examples:
*  `listfromgrp g/PERFORMANCE ` Lists all persons in PERFORMANCE.


### Deleting a group: `deletegrp`

Deletes the specified group from the address book.

Format: `deletegrp INDEX`

* Deletes the group at the specified INDEX.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
*  `show` followed by `delete 2` deletes the 2nd group in the address book.
*  `List all` followed by `delete 1` deletes the 1st group in the results of the `find` command.


### Deleting every person from a group: `deletepsngrp`

Deletes every person from that group in the addressbook.

Format: `deletepsngrp g/<GROUP NAME>`

* Deletes every person currently assigned to that group.
* If there is no person to delete, then nothing will happen.


### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [g/GROUP]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`<br>`add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #1-01 g/A`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**create** | `create g/GROUP`<br> e.g.`create g/Testing A`
**show** | `show`<br> e.g.`show`
**assignptg** | `assignptg n/NAME g/GROUP`<br> e.g.`assignptg n/John g/AA`
**rename** | `rename INDEX g/GROUP`<br> e.g.`rename 1 g/testing`
**deletegrp** | `deletegrp INDEX`<br> e.g.`deletegrp 1`
**deletepsngrp** | `deletepsngrp g/GROUP`<br> e.g.`deletepsngrp g/BB`
**listfromgrp** | `listfromgrp g/GROUP`<br> e.g.`listfromgrp g/CC`
