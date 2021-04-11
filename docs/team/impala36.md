---
layout: page
title: Zhenquan's Project Portfolio Page
---

## Project: Police Address Book

Police Address Book (PAB) is a desktop app meant for Police Investigation Officers, optimized for use via a Command Line Interface (CLI) with a Graphical User Interface (GUI).

It is designed to help them manage their investigation cases better by reminding them on which people to call, automatically rescheduling follow-up calls and assisting in sending case detail emails to colleagues or call notification emails to people in the contact list.

Given below are my contributions to the project.

* **New Feature**: Added the Officer's Remark field [\#6](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/6)
  * What it does: Allows additional remarks from the officer about his opinion about the case.
  * Justification: Supposedly after a follow-up call with the person, the officer develops his own opinion on how to handle the case afterwards.
  * Highlights: When no remarks are found for a case, the Officer's Remark title will not show as well.
  
* **New Feature**: Added the Description field [\#66](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/66)
  * What it does: Allows in-depth description of a case.
  * Justification: By showing a clear description of the case, the officer has no doubts about what, where and when it happened, and who were involved.
  
* **New Feature**: Call Today message and notification icon [\#87](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/87)
  * What it does: Shows a red exclamation icon with a "Call Today!" message beside it.
  * Justification: When the report date + follow-up days = today's date, it is time for the officer to make the scheduled follow-up call with the person in the case.

* **Code contributed**: [RepoSense link]()

* **Enhancements to existing features**:
  * Improved Remark to be included in the Add and Edit commands. [\#66](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/66)
  * Made tags more useful by indicating case priority and number of times called. [\#66](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/66)
  * Improved GUI display for better readability with Description of Case Report and Officer's Remark. [\#70](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/70)
  * Improved the GUI display further with police logo, new theme colours and Call Today notification icon. [\#87](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/87)
  * Improved the FollowUp command to be included in the Add and Edit commands. [\#99](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/99)
  * Added logging for Call Today and remarkTitle to be displayed when a remark exists. [\#99](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/99)
  * Fixed Find command not being able to search descriptions, dates, remarks, followUp and tags. [\#100](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/100)

* **Documentation**:
  * User Guide:
    * Renamed AddressBook to Police Address Book, updated Add, Edit, Find, Remark format and command examples. [\#48](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/48)
    * Updated the summary description for Police Address Book, updated Add command examples. [\#66](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/66)
    * Added the FollowUp field in the command examples. [\#99](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/99)
    * Added many Find command examples. [\#100](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/100)
    * Added examples for AddTag and RemoveTag. [3d8fb80](https://github.com/AY2021S2-TIC4002-F18-1/tp2/commit/3d8fb80ebd42a340d1f73551891c670ae85b2ece#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
    * Added more Remark examples. [2379c5e](https://github.com/AY2021S2-TIC4002-F18-1/tp2/commit/2379c5e4e8bb6f71a31481c96b3da5e34e76d71b#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
    * Updated the phone number requirement. [\#138](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/138)
    
  * Developer Guide:
    * Updated the target user profile, value proposition, user stories, use cases and additional information. [\#48](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/48)
    * Replaced all addressbook-level3 to team's repo. [\#66](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/66)
    * Added CallUpActivityDiagram. [\#84](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/84)
    * Updated CallUpActivityDiagram to CallTodayActivityDiagram. [\#99](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/99)
    * Updated phone number to be limited to 3-15 numbers. [\#138](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/138)

* **Team-based tasks**:
  * Assisted Yi Heng through the whole process of adding the Date field into the project. [\#50](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/50)
  * Fixed JsonSerializableAddressBookTest.java and logic of isSamePerson for Lin Qing. [\#68](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/68)
  * Made minor fixes and corrected mistakes in AddTag and RemoveTag for Yi Heng. [\#102](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/102)
  * Several commands were not catching exceptions during testing. [\#106](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/106)
  * Fixed outstanding PE-D issues. [\#136](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/136) and [\#138](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/138)
  
* **Community**:
  * PRs reviewed: [\#1](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/1), [\#12](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/12), [\#29](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/29), [\#30](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/30), [\#31](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/31), [\#33](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/33), [\#34](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/34), [\#37](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/37), [\#38](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/38), [\#42](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/42), [\#43](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/43), [\#45](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/45), [\#46](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/46), [\#47](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/47), [\#50](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/50), [\#56](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/56), [\#64](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/64), [\#65](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/65), [\#75](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/75), [\#83](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/83), [\#85](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/85), [\#91](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/91), [\#93](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/93), [\#95](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/95), [\#97](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/97), [\#98](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/98), [\#101](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/101), [\#134](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/134), [\#135](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/135), [\#137](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/137), [\#140](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/140), [\#141](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/141)
  * Reviewed the code in team members' PRs and requested for changes when necessary. Example: [\#33](https://github.com/AY2021S2-TIC4002-F18-1/tp2/pull/33)
  * Requested for help on the forum. [#11](https://github.com/nus-tic4002-AY2021S2/forum/issues/11)

* **Tools**:
  * Integrated SceneBuilder to the project for editing the GUI.
