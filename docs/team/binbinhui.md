---
layout: page
title: Lin Binhui's Project Portfolio Page
---

## Objectives
A software Engineering project focusing on the implementation of a Brown Field Project with an empahsis on Team work with different team members that adopts a software iterative approach to development.

## Overview
### Project: Police address book
A Police Address Book (PAB) is a desktop app. It is meant for Police Investigation Officers, optimized for use via a Command Line Interface (CLI) with a Graphical User Interface (GUI).
This app helps to manage their investigative cases by having people’s detailed information, assisting in sending case detail emails to colleagues or call notification emails to people in the contact list and able to remind them to follow up on the case

### Summary of Contributions
* *Code contributed*: [RepoSense link](https://nus-tic4002-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs&since=&tabOpen=true&tabType=authorship&tabAuthor=binbinhui&tabRepo=AY2021S2-TIC4002-F18-1%2Ftp2%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs)

### Enhancements implemented:
* Find by field[#42](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/42)
* Advance find[#43](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/43)
* Add new send commmand[#45](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/55),[#55](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/55),[#61](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/61)
* Add follow up command[#52](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/97)
* Send email to the person in the list[#61](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/61),[#97](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/97),[#98](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/98)
* bugfixing[#135](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/135)


### Contribution to Documentation:
#### User Guide:
Creating the fork, editing the UG by replace it into a new formate, update UserGuide and aboutme, update photo. [#1](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/1),[#14](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/14),[#30](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/30),[#38](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/38),[#64](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/64)


### Contributions to team-based tasks:
* setting up the fork. 
* guiding teammate how to correctly fork and clone to individual repo.
* Maintainig the issue tracker, create issue and close issues.
* Update developer guide.
* Giving idea on the project and relevant user storys.
* Help teammate on the review and suggestion if they face any issue.


### Community:
* PRs reviewed (with non-trivial review comments): [#6](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/6),[#39](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/39),[40](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/40),[#41](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/64),[#63](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/63),[#104](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/104)

#### Find feature:
The `find` feature allows the user to add a new person and save it to the address book.

##### Implementation
Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("add n/John Doe d/22-02-2021 i/S2731125H p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 de/This man stole 3 times r/shop theft t/NeverCalled")` API call.
[AddCommandSequenceDiagram](https://github.com/linqing42/tp2/blob/master/docs/images/AddCommandSequenceDiagram.png)
<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>
<br>
The following activity diagram summarizes what happens when a user executes addCommand feature:

[AddCommandActivityDiagram](https://github.com/linqing42/tp2/blob/master/docs/images/AddCommandActivityDiagram.png)

The `add` command facilitated by `AddClaimCommand`which extends the `Command` class and the `AddCommandParser` class, which implements the `Parser` class.
This operation takes in a String input from the user that will create `Person` objects based on the following prefixes and parameters:
n/`name`, d/`date`, f/`followUp`, i/`nric`, p/`phone`, e/`email`, a/`address` , de/`description` , r/`remark` , t/`tag`.
Meanwhile, the r/`remark` and t/`tag` are not compulsory to include.
A regex validation check will be imposed upon the creation. Any checks that fails the validation would display an error message to guide the user.
Parameters will be checked whether they are valid:
* `name` uses `Name#isValidName()` to ensure that name only contain alphanumeric characters and spaces, and it should not be blank.
* `nric` uses `Nric#isValidNric()` to ensure that nric only contain a capital letter,it should start with S, T, F or G,followed by 7 numerical numbers and a capital letter with alphabetical character. It should not be blank.
* `date` uses `Date#isValidDate()` to ensure that date should follow date format 'dd-mm-yyyy', and it should be a valid calendar date.
* `phone` uses `Phone#isValidPhone()` to ensure that phone numbers should only contain numbers, and it should be 3-15 digits long.
* `email` uses `Email#isValidEmail()` to ensure correct email format.
* `followUp` uses `FollowUp#isValidFollowUp()` to ensure that FollowUp should only contain positive integers, and it should not be blank.

Upon receiving a user command that has `add` as the first word, the following object interactions will occur, resulting in the instantiation of an `AddCommand` object:

1. `MainWindow` object calls `LogicManager#execute(input)`, where `input` is the user's input string;

2. `LogicManager` object calls `AddressBookParser#parseCommand(commandText)` to parse the user command, where `commandText` is the user's input string;

3. `AddressBookParser#parseCommand()` calls `AddCommandParser#parse(arguments)`, where `arguments` are the parameters in `commandText` such as `i/NRIC`;
In this case, `AddClaimCommandParser#parse()`is being created, and the user's input will be passed in as a parameter.

4. `AddCommandParser#parse()` will do a validation check on the user's input before creating and returning a`AddCommand` object with `Person` as its attribute.calls

Next, the following object interactions will occur to save the new person information to the `Model` object;

1. `LogicManager` object calls `AddCommand#execute(model)`, where `model` is the `Model` object and checking whether there is an existing Person

2. `AddCommand#execute()` calls `Model#addPerson(toAdd)` to add the new `Person`, where `toAdd` is the `Person` object to be stored.
It will return a `CommandResult` to the `LogicManager` that will return to user.

#### Design consideration:

##### Aspect: How add command executes
* **Alternative 1 (current choice):** unique NRIC, phone number or email to the entire address book.
  * Pros: Since each person have unique NRIC, phone number or email, it can easily be retrieved from `UniquePersonList`.
  This will reduce coupling when the person is to be updated.
  * Cons: Every time we retrieve a person using its `Nric`,`phone number` or `email`, we have to search through the whole `UniquePersonList` to find
          the associated person. As the list gets bigger, this may take more time.
          
* **Alternative 2 :** unique name to the entire address book
  * Pros: Since each person have unique name, it can easily be retrieved from `UniquePersonList`.
  * Cons: There may be a person that have the same name in the world.Therefore, it will have "fake" duplicate issue.

  <br>
  We have decided to opt for the first option primarily because it reduces the number of potential bugs such as "fake" duplication issue 
  and the complexities involved. Moreover, the implementation is still fast enough for small-scale organisations to pick up our app and use it, minimising the cons.
  
##### Aspect: How delete command executes
* **Alternative 1 (current choice):** deleting by index
  * Pros: Since each person's information has a unique index, any deletion by the index is less prone to bugs and easier to implement.
  * Cons: User will have to scroll the list for the data entry and look for its index which can be inconvenient.
          
* **Alternative 2 :** deleting by NRIC
  * Pros: Since each person have unique NRIC, any deletion by the NRIC is less prone to bugs.
  * Cons: User will need to remember the specific person's NRIC, it is very inconvenient to user.

  <br>
  We have decided to opt for the first option primarily because it is more convenient to the user as compare to alternative 2. 
