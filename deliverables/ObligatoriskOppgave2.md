# Assignment 2 - Biblioteket

# Task 1 - Project and Project Structure

## Team roles

How did the team roles function?
* **First compulsory → roles worked**
* **Second compulsory → roles changed**
  * The team roles had to be swapped for this assignment, but this is to be expected, as the further our development 
goes, the necessity of different roles will shift as the requirements become more clear.

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

Regarding the list of high level requirements from task 1
* Allthough we wanted to start working on the visual part of the game and get something that is playable, we quickly realized that we need to create a good back-end part of the game first. This is what we ended up working on in this iteration.

## Commits 

During this assignment, Tor Kristian and Tobias will have less commits because of the teams decision to give them the secretary role and to do the written assignment together. 


 # Task 2 - Requirements 

## User Stories 

We generally went light on user stories for this release as most of the implementation is back-end related and as such has very little visibility to any users.
* As a user I expect the game does not allow me to do illegal moves
  * The game should handle collisions between players or a user moving outside the bounds of the game without crashing or throwing errors.
  * **Criteria:** Once the game has initialized and we have created a set amount of players, attempting to move a robot of the grid itself should not work. Neither should players be able to move onto tiles already occupied unless they have cards that can for example push other robots away.
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

We are continuing to work with the principles of test driven development
* In the first iteration our codebase really only consisted of what we learnt in the guide posted on MittUiB. As the game UI actually needed to run to test anything, we couldnt really write any tests.
In this iteration we have prioritized tests and followed the principles of test driven development. We currently have 24 working tests. 

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

We can keep the priority list of requirements from the first assignment, but make them a little more specific.
* Show a game board that is connected to the current backend of the game
* Show elements like the player and flag that are connected to the current backend game

## List of system requirements based on the high-level requirements

### Make sure the visuals work.

- Show the game board.
- Show the player.
- Show a flag.

### Be able to move around the board

- Handing out program cards.
- The player can choose program cards.
- The player can approve their choice of cards.
- Reveal program cards for each register in turn.
- A robot will move according to how it was programmed.

### Visiting flags and winning the game

- The robots are able to visit the flags.
- The game keeps track of which flags each robot has visited. (Later also show it).
- The game knows which order the flags have to be visited.
- Winning the game by visiting all the flags in the correct order.
- The game finishes when someone wins (later be prompted if they want to continue to play).

### Death

- The players can die.
- The player will die if they move out of the board.
- When all players are dead the game finishes.

### Damage

- Robots can receive damage.
- Robots die if they receive 10 damage.

### Lasers - way to take damage

- A robot being hit by a laser causes damage.
- Show board lasers.
- Board can fire lasers in a specific direction. (I think some fire two lasers)
- Robot fire lasers in the direction they are turned.

### Walls and things that stop lasers

- Show walls, they are placed between tiles.
- Walls stop robots from moving, they are not able to pass through a wall.
- Walls stop lasers.
- Robots stop lasers.

### Multiple lives and respawn

- Robots have lives. (might be adjustable at the start of the game, how many they have?)
- Robots will lose a life when they die.
- When a robot dies all the damage they have received is removed.
- Show where each robot’s archive is.
- The Archive will at first be where the robot starts the game.
- When a robot dies but still has lives left it will come back; it will respawn at its archive.
- When a robot visits a flag their archive will be moved to that flag. ( Remember to later add that it will be moved to wrench)
- Robots are allowed to share archive tile, but the robots cannot share tile.
- Robots will re-enter in the order they were destroyed.
- Repair damage - Wrenches and power down
- Show single-wrench (Cross wrench will do the same until option cards are added).
- When a robot ends a register at a wrench it places their archive there.
- If a robot ends their LAST register at single-wrench it will repair one damage.
- A player can announce their intention to go into power down the NEXT round.
- When the NEXT round starts the player will not receive any program cards, but all the damage will be removed.
- A robot in power down can still receive damage.
- At the start of the following round after a power down, the player can declare that they will stay in power down. If so \* the damage is again removed. Otherwise, they get cards.

### Locked registers

- Damaged robots get one fewer Program card for each Damage token they have.
- If a robot has 5 or more Damage tokens, its registers begin to lock up, from register 5 all the way down to register 1.
- Once a register is locked, the Program card in that register stays there until the damage locking the register is repaired.
- Being repaired will reverse the locked registers.

### Moving elements on board

- Show express conveyor belts.
- Express conveyor belts move robots two steps.
- Show normal conveyor belts.
- Normal conveyor belts move robots one step.
- Some conveyor belts make the robot rotate 90 degrees.
- Show pushers.
- Pushers shove the robots away.
- Show gears.
- Gears rotate 90 degrees.

### Adding more players and pushing robots

- Show multiple players.
- The different Program cards have a priority number.
- Higher priority number means faster movement.
- Robots can be pushed by other robots when they collide.
- Robots cannot share tile.
- Robots cannot be pushed through walls.
- Conveyor belts do not make robots push each other.

### Timer

- When the second to last player has placed their Program cards a timer goes off and the last player has 30 seconds to choose Program cards.
- Random cards are assigned the registers without Program cards.
- If all but one player is in power down then the players have 3x30 = 90 seconds to choose cards.

### Option cards

- Show cross-wrench
- Add Option cards
- If a robot ends the last register at a cross-wrench then one damage is repaired and they are assigned an Option card.
- A destroyed robot immediately loses an Option card of the player’s choice.
- a robot with an Option card can discard it to avoid receiving a Damage token
* Allthough we wanted to start working on the visual part of the game and get something that is playable, we quickly realized that we need to create a good back-end part of the game first. This is what we ended up working on in this iteration.

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


# Task 3 - Code 



Dere må dokumentere hvordan prosjektet bygger, testes og kjøres, slik at det er lett for gruppelderne å bygge, teste og kjøre koden deres. Under vurdering kommer koden også til å brukertestes.
Prosjektet skal kunne bygge, testes og kjøres på Linux, Windows og OSX.
Kodekvalitet og testdekning vektlegges. Merk at testene dere skriver skal brukes i produktet. Det kan være smart å skrive manuelle tester for å teste det som er grafisk.
Utførte oppgaver skal være ferdige.
Lever klassediagram. (Hvis det er veldig mange klasser, lager dere for de viktigste.)
Hvis dere tester manuelt: lever beskrivelser av hvordan testen foregår, slik at gruppeleder kan utføre testen selv.

## Transcripts

All transcripts are listed by date at our wiki.

* [Transcripts Thursday 13.02](https://github.com/inf112-v20/biblioteket/wiki/Transcript-13.02)
* [Transcripts Thursday 20.02](https://github.com/inf112-v20/biblioteket/wiki/Transcript-20.02)
* [Transcripts Tuesday 25.02](https://github.com/inf112-v20/biblioteket/wiki/Transcript-25.02)
* [Transcripts Thursday 27.02](https://github.com/inf112-v20/biblioteket/wiki/Transcript-27.02)
