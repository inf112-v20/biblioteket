# Assignment 2 - Biblioteket

# Task 1 - Project and Project Structure

## Team roles

The roles we had assigned during the first assignment proved effective at first, however, we had to change the roles for the second assignment. Tor Kristian and Tobias weren't in Bergen for most of the assignment. As a result of this, the team then made the decision that they should share the secretary role, and focus less on the technical aspects and more on the documentation aspects of the project. The rest of the group was in Bergen and therefore able to meet up for a coding session, this meant that Sondre, Silje and Marcus, completed the programming aspects of the assignment. Our assigned team-leader, Sondre, had to travel the 26th of February. Due to this, “...” was appointed as substitute team leader during the Thursday group meeting. 

Sondre and Silje will keep their roles as team-leader and customer contact, likewise Marcus and Tobias will also keep their current roles. Although as we move further with the project and the group as a whole gains more libGDX experience, we may not need an exclusive libGDX role. 
Tor Kristian will then get a new role, this role is to be determined once our group gets a feel for what is necessary.. 

Team leader: The team leader had to be someone who is able to coordinate and can handle extra responsibility, as mentioned in the previous assignment, Sondre’s primary responsibility as our team leader is maintaining the overall quality of the software and even delves into coordinating meetings. Sondre’s role as team leader means he often assigns issues (tasks) for group members to do, based on their roles.

Customer contact: As our customer contact, Silje is in charge of making sure what we develop fulfills the provided requirements and that our own specifications and set goals are met within periods of time. She was also assigned the task of presenting the rules of the game for the rest of the group, and is therefore to some extent also responsible for correct implementation as well as prioritizing which features to implement first other than what is specified in the assignments. 

Secretary: As our secretary, Marcus’ main objective is to document and fulfill the written part of the assignments, as well as supervising that it satisfies the correction form to ensure that every requirement has been met. Marcus also transcribes the meetings.

libGDX developer: As our libGDX developer, Tor Kristians role was to familiarize himself with libGDX, and present it for the group in the early stages. 


Test manager: Tobias is our test manager, and therefore he is responsible for maintaining a set standard of coding with TDD via JUnit testing in Java. This is to help minimize software errors and bugs.


## Software Development 

To enforce a high standard for our project, we selected Kanban to plan. It allows for great flexibility while also holding true to discipline. As a result of this we are left with a good overview of; tasks that need completion, who’s working on what, and what’s already been completed.

When we are given the opportunity by our in person meetings, we try to use pair-programming which offers different opinions and could result in more efficient ways of developing as we get the chance to discuss, and perform real time peer review while programming.

## Group dynamics and communication

One primary disadvantage of working as a group, is the difficulty that comes when attempting to distribute the workload is such a manner that everyone has the same amount to do. To combat this disadvantage, we try to make sure that everyone is satisfied or “okay” with the amount of work they are doing relative to the group, we have yet to have a conflict arise from this disadvantage. We are working well together with the chosen methods and are attempting to include everyone to as much of an extent as possible. When there’s a disagreement, everyone presents their case, and we vote on what to do.

Communication via slack has allowed for near seem less planning, discussing, questions/answers, and even the scheduling of extra meetings. Additionally, Slack has made it much easier to get a hold of group leaders or the lecturer if any questions are raised regarding the obligatory assignments or anything else related to the course.


## Retrospective and improvements

In retrospect regarding the first two assignments, things are being handled in a timely and professional manner. Even though we are faced with different time schedules in terms of lectures and extra curricular activities, we counter the increased difficulty of scheduling by having an extra meeting every tuesday if necessary, and try to plan have this meeting slot open. Luckily the team is somewhat flexible when it comes to schedules.




Throughout this second assignment, some of the roles were swapped, because Tor Kristian and Tobias were away, they were employed to the secretary role and handled the written prompts for this assignment. This was to make things easier, to combat the fact that it would be hard for them to partake in pair-programming via an online call, as they were with family and unsure of when they could work. Additionally, when Tor Kristian and Tobias had any questions, they asked via Slack.

Prior to the release of the second assignment, we as a group already had made plans of what to do next. We believe that staying ahead a bit will prove to be a smart investment of time and benefit us in future assignments. If we continue development between assignments, the workload when the actual assignment is released is diminished. Even if what we develop isn’t a part of the next assignment, it will still be useful for the final product.

Although the team were flexible during this assignment, Tor Kristian and Tobias will have to make up for their absence with developing in the future, as having them do the written assignments would be unfair to the rest of the team.


We should also improve at making sure everyone is working and committing their work to gitHub as it might look like someone is not contributing or contributing less to the project. It's a group project, and everyone should contribute somewhat equally. 


## Commits 

During this assignment, Tor Kristian and Tobias will have less commits because of the teams decision to give them the secretary role and to do the written assignment together. 


 # Task 2 - Requirements 

## User Stories 

We generally went light on user stories for this release as most of the implementation is back-end related and as such has very little visibility to any users.
As a user I expect the game does not allow me to do illegal moves
The game should handle collisions between players or a user moving outside the bounds of the game without crashing or throwing errors.
Criteria: Once the game has initialized and we have created a set amount of players, attempting to move a robot of the grid itself should not work. Neither should players be able to move onto tiles already occupied unless they have cards that can for example push other robots away.
## Developer Stories 

Because of how little the user will see of the work this sprint we also did a few acceptance criteria specifically for us developers.
* **Criteria**: The game should abstract away locations and the underlying grid and use that to represent elements on the board, not the other way around.
  * We need a way to keep track of the position of elements, and a grid that matches the entire board.
* **Criteria**: The game should have a clear hierarchy of inheritance for elements, with a single root interface that is inherited down through the specific elements placed on the grid.
  * A robot is an element, and so is a laser or a flag. As such they should inherit a common interface exposing the minimally required methods for an element.
* **Criteria**: A player should control one and only one robot.
  * A player has HP and a robot takes damage. We need to ensure that a player can never control more than one robot at a time and that damage inflicted (and subsequently the death of a robot) affects his or hers HP.


## Testing 

We have made several test classes with several test methods, to ensure that what we have developed is functioning properly. `DirectionTest` checks if every direction to the left, right and the opposite is correct. `GridTest` checks if the proper coordinates are returned

One for directions, the grid, position, player, robotest



For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester ✔️

Dersom dere har oppgaver som dere skal til å starte med, hvor dere har oversikt over både brukerhistorie, akseptansekriterier og arbeidsoppgaver, kan dere ta med disse i innleveringen også. ✔️

Forklar kort hvordan dere har prioritert oppgavene fremover 
* In order to ensure we complete the assignment in a timely and effective manner, we prioritized the tasks as follows;
  * At this moment, the backend and the frontend parts of the game live in different worlds. As we have finished a lot of the backend that we consider necessary, the goal now will be to connect the map with the backend.
    * Get the map connected to the current back end
    * Create a proper GUI for the game.
    * Program cards
    * We will be prioritizing this before working on the logic for the actual game.

Forklar kort hvilke hovedkrav dere anser som en del av MVP og hvorfor. Hvis det er gjort endringer i rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?
* We believe the following hovedkrav are a part of MVP;
  * Krav 
    * Reasoning
* Det blir (/blir ikkje) gjort endringer i rekkefølge utfra hva som er gitt fra kunde
  * Grunn

Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.

* Since last time we have:
  * Added locations and directions
    * These are absolutely necessary in order for a playable finished product, each entity on the board must be able to determine their location and which direction they are facing.
  * Made a grid
  * Worked on grid and position for grid
  * Implemented finishing touches to Player and Robot
  * Refactored grid
  * Merged ILocation in IPosition
  * Updated Robot and Player to meet feedback
* We prioritized:
  * Primarily we worked on the backend of the game, and implementing features to the backend that will prove useful later on. 
  * We did not work too much on the Player or board objects, as it would be nonsensical to program this without making sure the board is functional to as full of an extent as possible.


Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs). ✔️
* Currently known bugs:
  * Player is unmovable
    * This isn’t as much of a bug as it is just unfinished, this will be fixed as development proceeds.
  * Player/Robot not completely synchronised with board.

Kravlista er lang, men det er ikke nødvendig å levere på alle kravene hvis det ikke er realistisk. Det er viktigere at de oppgavene som er utført holder høy kvalitet. Utførte oppgaver skal være ferdige.



How did the team roles function?
* **First compulsory → roles worked**
* **Second compulsory → roles changed**
  * The team roles had to be swapped for this assignment, but this is to be expected, as the further our development 
goes, the necessity of different roles will shift as the requirements become more clear.


# Task 3 - Code 



Dere må dokumentere hvordan prosjektet bygger, testes og kjøres, slik at det er lett for gruppelderne å bygge, teste og kjøre koden deres. Under vurdering kommer koden også til å brukertestes.
Prosjektet skal kunne bygge, testes og kjøres på Linux, Windows og OSX.
Kodekvalitet og testdekning vektlegges. Merk at testene dere skriver skal brukes i produktet. Det kan være smart å skrive manuelle tester for å teste det som er grafisk.
Utførte oppgaver skal være ferdige.
Lever klassediagram. (Hvis det er veldig mange klasser, lager dere for de viktigste.)
Hvis dere tester manuelt: lever beskrivelser av hvordan testen foregår, slik at gruppeleder kan utføre testen selv.

## Transcripts

All transcripts are listed by date at our wiki.
* Transcript Thursday 13.02
  * https://github.com/inf112-v20/biblioteket/wiki/Transcript-13.02
* Transcript Thursday 20.02 
  * https://github.com/inf112-v20/biblioteket/wiki/Transcripts-20.02
* Transcript Tuesday 25.02
  * https://github.com/inf112-v20/biblioteket/wiki/Transcript-25.02
