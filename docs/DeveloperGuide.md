---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-TIC4002-F18-4/tp2/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S2-TIC4002-F18-4/tp2/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-TIC4002-F18-4/tp2/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `MedicalRecordDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-TIC4002-F18-4/tp2/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-TIC4002-F18-4/tp2/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-TIC4002-F18-4/tp2/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("addApp /d Heart Checkup /t 2021-04-12 10:00:00")` API call.

![Interactions Inside the Logic Component for the `execute("addApp /d Heart Checkup /t 2021-04-12 10:00:00")` Command](images/AddAppointmentSequenceDiagram.png)

Given below is the Object Diagram for the interactions and parameters within the `Logic` component for the `execute("addApp 2 /d Check-up /t 2021-03-18 10:00:00")` API call.

![Interactions Inside the Logic Component for the `execute("addApp 2 /d Check-up /t 2021-03-18 10:00:00")` Command](images/AddAppointmentObjectDiagram.png)

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S2-TIC4002-F18-4/tp2/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the patient book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `PatientBook`, which `Person` references. This allows `PatientBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S2-TIC4002-F18-4/tp2/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the patient book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current patient book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous patient book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone patient book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial patient book state, and the `currentStatePointer` pointing to that single patient book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the patient book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the patient book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted patient book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified patient book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the patient book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous patient book state, and restores the patient book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the patient book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest patient book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the patient book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all patient book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire patient book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                   | So that I can…​                                                           |
| -------- | ------------------------------------------ | --------------------------------- | -----------------------------------------------------------------------------|
| `* * *`  | new user                                   | see usage instructions            | refer to instructions when I forget how to use the App                       |
| `* * *`  | user                                       | add a new patient                 | insert patient entries into the PatientBook                                  |
| `* * *`  | user                                       | view the lists of patients        | shows a list of all patients in the PatientBook                              |
| `* * *`  | user                                       | edit a patient personal details   | modify entries in the PatientBook                                            |
| `* * *`  | user                                       | delete a patient                  | remove entries that I no longer need                                         |
| `* * *`  | user                                       | find a patient by keyword         | locate details of patient without having to go through the entire list       |
| `* * *`  | user                                       | add tags onto a patient's data    | search using the tag                                                         |
| `* * *`  | user                                       | add a new appointment             | insert appointment entries into the PatientBook                              |
| `* * *`  | user                                       | view the lists of appointments    | shows a list of all appointments a patient has in the PatientBook            |
| `* * *`  | user                                       | delete appointments               | delete appointments a patient has in the PatientBook                         |
| `* * *`  | user                                       | add a new medical records         | insert medical records entries into the PatientBook                          |
| `* * *`  | user                                       | view the lists of medical records | shows a list of medical records a patient has in the PatientBook             |
| `* * *`  | user                                       | delete medical records            | delete medical records of a patient has in the PatientBook                   |
| `* * *`  | user                                       | view all appointments today       | view appointments of all patient in the PatientBook that schedule for today  |
| `* * *`  | user                                       | find a appointment by date        | view and reference information related to the appointment in the PatientBook |
| `* *`    | user                                       | hide private contact details      | minimize chance of someone else seeing them by accident                      |
| `* *`    | user                                       | view patient appointment          | track patient appointment and give patient notification calls                |
| `*`      | user with many patients in the patient book| sort patients by name             | locate a patient easily                                                      |

### Use cases

(For all use cases below, the **System** is the `PatientBook` and the **Actor** is the `Staff`, unless specified otherwise)

###### Use Case Diagram
![PatientBook - UC](images/UseCaseDiagram.PNG)

---

**Use case: View appointment in PatientBook**

**MSS**

1.  User requests to list all Patients
2.  User view appointment by patients name
3.  PatientBook show all appointment of the patient

Use case ends.

**Extensions**

* 1a. View a non existing perons's appointment.
    Use case go back to step1 and restart by key in the correct name
  
  2a. No appointment in this patient

Use case ends.

![PatientBook - AD](images/ViewAppActivityDiagram.PNG)

---

**Use case: List all patients**

**MSS**

1.  User requests to list patients
2.  PatientBook shows a list of patients

Use case ends.

**Extensions**

* 2a. The PatientBook is empty.

Use case ends.

**Use case: Find a patient**

**MSS**

1.  User will search patient records with keywords
2.  PatientBook will list the patient with keywords in the name

**Extensions**

* 1a. The PatientBook is empty.
  2a. The PatientBook will not list any records.
  Use case ends.

* 1a. The keywords do not match any patient.
  2a. The PatientBook will not list any records. Return to step 1 to enter new keywords.

Use case ends.

---

*Use case: Delete a patient*

*MSS*

1.  User requests to list patient
2.  PatientBook shows a list of patients
3.  Staff requests to delete a specific patient in the list
4.  PatientBook deletes the patient successfully.


*Extensions*

* 2a. The PatientBook is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PatientBook shows an error message.

      Use case resumes at step 2.

Use case ends.

---

*Use case: Edit patient details in PatientBook*

*MSS*

1.  User requests to list all Patients
2.  User enter the index of patient and all parameters user want to modify
3.  PatientBook will update the patients details
4.  Show successfully updated

    Use case ends.

*Extensions*

* 1a. Edit a non existing perons's appointment.
  Use case go back to step1 and restart by key in the correct index

  2a. No patient in the list
  Use case end.

    23. Edit parameter is not formated correctly
        Use case go back to step1 and restart by key in the correct information

  Use case ends.

---

*Use case: Delete an appointment*

*MSS*

1.  User requests to list patient
2.  PatientBook shows a list of patients
3.  Staff requests to delete a specific appointment from specified patient in the list
4.  PatientBook deletes the appointment of the patient successfully.

*Extensions*

* 2a. The PatientBook is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PatientBook shows an error message.

      Use case resumes at step 2.

Use case ends.

---

**Use case: View medical records in PatientBook**

**MSS**

1.  User requests to list all Patients
2.  User view medical records by patients index
3.  PatientBook show all medical records of the patient

Use case ends.

**Extensions**

* 1a. View a non existing perons's appointment.
  Use case go back to step1 and restart by key in the correct name

  2a. No appointment in this patient

Use case ends.

---

*Use case: Delete medical records in PatientBook*

*MSS*

1.  User requests to list patient
2.  PatientBook shows a list of patients
3.  Staff requests to delete a specific medical record from specified patient in the list
4.  PatientBook deletes the medical record of the patient successfully.

*Extensions*

* 2a. The PatientBook is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PatientBook shows an error message.

      Use case resumes at step 2.

Use case ends.

Given below is the Object Diagram for the interactions and parameters within the `Logic` component for the `execute("deleteMed 1 1")` API call and delete medical records use case.

![PatientBook - AD](images/DeleteMedicalHistoryObjectDiagram.png)


Given below is the Sequence Diagram for the interactions of deleting a Medical Records within the `Logic` component for the `execute("deleteMed 1 1")` API call and delete medical records use case.

![PatientBook - AD](images/DeleteMedicalHistorySequenceDiagram.png)

---

*Use case: Add medical records in PatientBook*

*MSS*

1.  User requests to list patient
2.  PatientBook shows a list of patients
3.  Staff requests to add a specific medical record to a specified patient in the list
4.  PatientBook added the medical record to the patient successfully.

*Extensions*

* 2a. The PatientBook is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. PatientBook shows an error message.

      Use case resumes at step 2.

Use case ends.

Given below is the Activity Diagram for interactions within Patient Book for the Add Medical records Use case.

![Add medical records in PatientBook](images/AddMedicalHistoryActivityDiagram.png)

---

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The application shouldn't take more than 10s to start.


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **JDK** - Java Development Kit - [Java SE](https://www.oracle.com/sg/java/technologies/javase-downloads.html "Java SE Downloads")
* **Gradle** - Gradle Build Tool - [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html "Build Automation Tool - Docs")
* **Intellij / IDE** - Intellij Integrated Development Environment - [IntelliJ IDEA](https://www.jetbrains.com/idea/ "JVM IDE")
* **Plugin** - IDE Plugins - [Intellij IDEA Plugins](https://www.jetbrains.com/help/idea/managing-plugins.html "Manage plugins")

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a patient while all patients are shown

   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact will be deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar will be updated.

   1. Test case: `delete 0`<br>
      Expected: No patient will be deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

## **Appendix: Effort**


### Difficulty level
We felt the difficulty level was sharply increased compared to our previous TP project where we designed, developed and tested our own project implementation.


### Challenges faced
1. Even with the guides and tutorial for AB-3's codebase, we still found it quite daunting and hard to fully trace and follow the code in order to understand when and where to apply our modifications.

2. Planning the contribution and allocation of workload among team members was also quite tough as we found most of the codebase to be tightly coupled, therefore requiring team members to find time to code together and check each other's contributions.
   We often had to hold many adhoc sessions in order to explain and show each other on how to modify the codebase to fit our use cases.


### Effort required
1. The process of adding new classes, modifying the UI and adding new variables was very tedious and required much manual work(IDE refactoring was not very helpful).

2. Adding test cases was also difficult as the tests would often break across PRs and contributions if they were overly complex.


### Achievements
1. We managed to hit a final code coverage of 72%. 

2. We also were able to add and modify some of the diagrams in the developer guide to reflect the modifications made.

3. We managed to add more than 3 additional functions on top of the existing AB-3 Codebase.
