---
# Hong Jiancheng Project Portfolio Page

---

## Project: Addressbook NS Edition (ABNS)

ABNS is a desktop address book application used for managing National Service soldiers' performance. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Data Structure**: Created Group and GroupList data structures. [\#74]()
  * What it does: allows the user to Assign the group attribute as Group, and store a collections of Groups as GroupList.
  * Justification: These data structures are critical in adding up new features required in this project.

* **New Feature**: Add listfromgrp command.
* What it does: allows the user to list all the persons with in a specific group.
* Justification: When user wants to view persons from a specific group, this command comes in handy.

* **Code contributed**: [RepoSense link](https://nus-tic4002-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=HongJiancheng&tabRepo=AY2021S2-TIC4002-F18-3%2Ftp2%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.3` on GitHub
  * Helping in review and merging other's pull request.

* **Enhancements to existing features**:
  * Updated the add command and make it support g/ PREFIX to pre-assign a person to the group.
  * Modify the tests to make it support Group and GroupList data structures.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `show`, `create`, `listfromgrp`, `deletegrp`, `assignptg` and `rename` [\#72]()
    * Updated the existing documentation of features `add`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
