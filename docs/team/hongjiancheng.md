---
# Hong Jiancheng Project Portfolio Page

---

## Project: Addressbook NS Edition (ABNS)

ABNS is a desktop address book application used for managing National Service soldiers' performance. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Data Structure**: Created Group and GroupList data structures. [\#16](https://github.com/AY2021S2-TIC4002-F18-3/tp2/pull/16)
  * What it does: allows the user to Assign the group attribute as Group, and store a collections of Groups as GroupList.
  * Justification: These data structures are critical in adding up new features required in this project.

* **New Feature**: Add listfromgrp command. [\#32](https://github.com/AY2021S2-TIC4002-F18-3/tp2/pull/32)
* What it does: allows the user to list all the persons with in a specific group.
* Justification: When user wants to view persons from a specific group, this command comes in handy.

* **New Feature**: Load group from JSON file. [\#49](https://github.com/AY2021S2-TIC4002-F18-3/tp2/pull/49)
* What it does: when storage file is presented, the group list will be loaded from the file.
* Justification: When user wants to exit and launch the app again, the previous status of the grouplist will be loaded.

* **Code contributed**: [RepoSense link](https://nus-tic4002-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=HongJiancheng&tabRepo=AY2021S2-TIC4002-F18-3%2Ftp2%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management and Community contribution**:
  * Managed releases `v1.3` on GitHub
  * Helping in review and merging other's pull requests.
  * Identifying issues and discussing with teammates.

* **Enhancements to existing features**:
  * Updated the `edit` and `add` command and make them support g/ PREFIX to pre-assign a person to the group. [\#39](https://github.com/AY2021S2-TIC4002-F18-3/tp2/pull/39) [\#59](https://github.com/AY2021S2-TIC4002-F18-3/tp2/pull/59)
  * Modify the tests to make it support Group and GroupList data structures.

* **Documentation**:
  * User Guide:
    * Added documentations for the features `show`, `create`, `listfromgrp`, `deletegrp`, `assignptg` and `rename` [\#63](https://github.com/AY2021S2-TIC4002-F18-3/tp2/pull/63)
    * Updated the existing documentation of features `add`.
  * Developer Guide:
    * Updated the Class Diagram and Sequence Diagram [\#59](https://github.com/AY2021S2-TIC4002-F18-3/tp2/pull/59)
    * Added implementation details of the `listfromgrp` feature.
  
