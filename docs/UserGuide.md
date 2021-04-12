---
layout: page
title: User Guide - PatientBook
---

PatientBook is a **desktop app for managing contacts and appointments. It also has an interactive Graphical User Interface (GUI) for users to enter command.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `patientBook.jar` from [here](https://github.com/AY2021S2-TIC4002-F18-4/tp2/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your PatientBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com

    a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the patient book.

   * **`edit`**`3 n/John Doe e/johnd@example.com a/John street, block 123, #01-01` : Edit `Charlotte` contact to `John Doe` to the PatientBook.

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

* Do note that for viewApp and viewMed related commands, 2nd index is referring to the patient list and 3rd index is referring to the "appointment" or "medical history" list.
  e.g if you want to delete the 2nd appointment from the first person in the list `deleteApp 1 2`. The parameter is **FIXED**
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a patient: `add`

Adds a patient to the patient book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [app/APPOINTMENT]...​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A patient can have any number of tags (including 0) </div>
<div>A patient can have any number of appointments.</div>
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal app/health check up at 21/03/2021`

### Listing all persons : `list`

Shows a list of all persons in the patient book.

Format: `list`

### Editing a Patient : `edit`

Edits an existing person in the patient book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the patient at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the patient will be removed i.e adding of tags is not cumulative.
* You can remove all the patient’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds patient whose names contain any of the given keywords.

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

### Deleting a Patient : `delete`

Deletes the specified patient from the Patient book.

Format: `delete INDEX`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd patient in the patient book.
* `find Betsy` followed by `delete 1` deletes the 1st patient in the results of the `find` command.

### Viewing Appointment of Patient : `viewApp`

View appointment of a specified patient from the Patient book.

Format: `viewApp INDEX`

* View appointment of the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `viewApp 2` display all the appointment of 2nd patient in the patient book.
* `find Betsy` followed by `viewApp 1` display appointments of the 1st patient in the results of the `find` command.

### Adding Appointment to Patient : `addApp`

Adds an appointment to specified patient from the Patient book.

Format: `addApp INDEX /d DESCRIPTION /t DATETIME`

* Add appointment with timing `DATEIME` to patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list
* Datetime input **must be in specified format** 2021-03-18 10:00:00
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `addApp 2 /d Check-up /t 2021-03-18 10:00:00` add checkup appointment on datetime to the 2nd patient in the patient book.

### Deleting Appointment from Patient : `deleteApp`

Deletes the specified appointment from specified patient from the Patient book.

Format: `deleteApp USERINDEX APPOINTMENTINDEX`

* Deletes an appointment at specified `APPOINTMENT_INDEX` from specified patient at the specified `USER_INDEX`.
* Delete will only be valid after viewing the specified patient from the Patient Book.
* The userIndex refers to the index number shown in the displayed patient list.
* The appointmentIndex refers to the index number of the appointment of the specified patient.
* The userIndex and appointmentIndex **must be a positive integer** 1, 2, 3, …​

Examples:
* `find Betsy` followed by `deleteApp 1 3` deletes the 3rd appointment from 1st patient in the results of the `find` command.

### Viewing Medical Record of Patient : `viewMed`

View medical records of a specified patient from the Patient book.

Format: `viewMed INDEX`

* View medical records of the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `viewMed 2` display all medical records of 2nd patient in the patient book.
* `find Betsy` followed by `viewMed 1` display all medical records of the 1st patient in the results of the `find` command.

### Adding Medical Record to Patient : `addMed`

Add a medical record to specified patient from the Patient book.

Format: `addMed INDEX /d DESCRIPTION`

* Add medical records with content `DESCRIPTION` to patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `addMed 2 /d Surgery` add medical records to 2nd patient in the patient book.
* `find Betsy` followed by `addMed 1 /d Surgery` add medical records to 1st patient in the results of the `find` command.

### Deleting Medical Record from Patient : `deleteMed`

Delete the specified medical records from specified patient from the Patient book.

Format: `deleteMed USERINDEX RECORDINDEX`

* Deletes a medical records at specified `RECORDINDEX` from specified patient at the specified `USERINDEX`.
* The userIndex refers to the index number shown in the displayed patient list.
* The appointmentIndex refers to the index number of the medical records of the specified patient.
* The userIndex and recordIndex **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `deleteMed 2 1` deletes the 1st medical record of the 2nd patient in the patient book.
* `view Betsy` followed by `deleteMed 1 3` deletes the 3rd medical records from 1st patient in the results of the `view` command.

### View of Notification on Today : `appNotification`

View all appointments in the Patient Book that are scheduled for today.

Format: `appNotification`

* The display will first show all appointments which are scheduled for today once the program loaded.
* User can call `appNotification` to check for any appointments that are scheduled for today.

### Clearing all entries : `clear`

Clears all entries from the patient book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data
PatientBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

###FAQ
**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.
### Commands Table

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**viewApp** | `viewApp INDEX` <br> e.g., `viewApp 1`
**addApp** | `addApp INDEX /d DESCRIPTION /t DATETIME` <br> e.g., `addApp 2 /d Check-up /t 2021-03-18 10:00:00`
**deleteApp** | `deleteApp USERINDEX APPOINTMENTINDEX` <br> e.g., `deleteApp 1 3`
**viewMed** | `viewMed INDEX` <br> e.g., `viewMed 1`
**addMed** | `addMed INDEX /d DESCRIPTION` <br> e.g., `addMed 1 /d Surgery`
**deleteMed** | `deleteMed USERINDEX RECORDINDEX` <br> e.g., `deleteMed 1 3`
**appNotification** | `appNotification` <br> e.g., `appNotification`
**Help** | `help`

