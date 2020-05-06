# Task 1 - Team and Project

## Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamleader eller kundekontakt?

As stated in the previous deliverable, coronavirus has pushed all of us into
somewhat of a strange "virtual learning" environment. As all of us in the group
are accustomed to attending physical education, it has been tricky to adapt to
these bizarre current norms.

Our group meets online at least twice a week. As there is no face to face
interaction anymore, it can be somewhat harder to follow what is going on, or to
explain something. Screen share serves as somewhat of a solution, although
sometimes a faulty internet connection can limit even that. Due to a lack of
face to face interaction, the meetings online must be much more structured and
are more difficult to maintain an adecquate flow, as this can easily be
interrupted by an unwelcomed noise from someone's microphone, or a disconnection
of internet. If there is a group consensus that we are lacking in some sense for
the project, this can be solved "easily" by scheduling a work period in which we
all sit on discord and work, allowing for live support from other group members
(as close as it can get to live support anyway), we also have in the past
scheduled more meetings in one week, in the case that we might be accomplishing
what we are working on in one meeting rather quickly and would like the group's
input.

Our groups adaptation to the COVID crisis, while slow and grueling (and somewhat
of a trial and error process, seeing what works and what doesn't), has swiftly
improved via our improved meeting structure, our open communication on slack and
discord, and our flexibility and willingness to work outside our assigned roles.
The reason we must be open to versatilitile tasks regarding the project, is
because we weren't sure exactly what roles were going to be in demand when we
started.

We have kept the same roles as for the previous turn in, and we are approaching
our roles with flexibility, meaning people who have a set role in one department
can also provide key developments elsewhere. Sondre is still our teamleader,
while simultaneously staying on top of github, this also means maintaining an
overview of the branches and the general progress on the project board. Marcus
still takes notes at the meetings, but also has been working effectively to
implement features, in parts like rendering of robots. Silje is still our
customer contact, while simultaneously taking on the role of somewhat of a
"judge", as she has mastered the rules of the game, she is able to determine
whether or not there are any rule violations happening, Silje is also
responsible for multiple positive implementations on the code. Tor Kristian has
been continuing his work on the GUI for the game, making sure that the player
has all necessary information visible to them during the gameplay (e.g.
damagetokens). For this part of the project, Tobias has undertaken the process
of drawing the playermodels, and using libGDX to pack the textures so that they
function as intended, as this wasn't very a necessarily difficult task, Tobias
has also been assigned the role of writing the written part of the delivery.

- Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er
  verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke,
  hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?
  - Unsure.
- Gjør et retrospektiv hvor dere vurderer prosjektet har gått. Hva har dere
  gjort bra, hva hadde dere gjort annerledes hvis dere begynte på nytt?
  - Considering the current impact of the COVID-19 outbreak, we have adapted
    quickly, with a near frictionless switch to online meetings rather than our
    prior face to face meetings. Another practice we maintained that has
    benefitted us greatly, is having diverse roles for the team, while also
    being flexible about what we work on. Also, we have had good communication
    on Slack and Discord, which heavily furthered our ability to eliminate
    problems we had during development. One thing we agreed we would do
    differently if we could start over with the knowledge we have now, is to
    find a good solution for the grid earlier on.

##Legg ved skjermdump av project board ved innlevering. Sørg for at det er oppdatert med siste status ved innlevering.
Screenshot of project board (when turning in)

##Hvordan fungerer gruppedynamikken og kommunikasjonen nå i forhold til i starten? Hvordan påvirket karantene og nedstengning teamet og fremdriften?
The group dynamic now is significantly improved relative to the one we had at
the start, as we all have roles which complement eachother, as we have had time
to try different roles. Our communication has been good, although it was
somewhat deterred when we could no longer have meetings on campus, our discord
meetings are still progressive towards our completion of the project, as we all
try to compile an agenda, with questions, comments, and concerns prior to each
meeting. During the actual meetings, we take turns one by one to explain what
we've been working on, the status of it, and if there are any problems regarding
it. The impact of the quarantine/lockdown initially seemed as though it would
reduce the pace at which the game was developing, it has had a considerably
smaller impact than expected. Our first online group meeting was on zoom, but we
were quick to switch to discord, so that we might include a teacher's assistant
in the meetings. We also have adapted to cooperating without face to face
interaction entirely, as some group members don't have access to a suitable
webcam or are uncomfortable with using it. If push came to shove, and we felt
our progress rate was sub par, there was always the option of potentially
setting up a work time of a few hours to cooperate on discord so that problems
may be brought up with the group with instantaneous available assistance, rather
than waiting for a slack response or a meeting.

- Our group dynamic has significantly improved, as we all have assigned roles
  which complement each other but are also flexible enough with our roles to
  take on issues and challenges outside of our roles field of work.
- Communication: Although we are no longer meeting face to face because of
  coronavirus, our discord meetings are still proactive, as people try to
  prepare questions, concerns, and ideas into a list before the meetings, and
  then we go one by one to discuss what we’re working on, how it’s going with
  that, and what we are planning.
- Quarantine/lockdown and the impact thereof on the team.
  - Already specified in last obligatorisk innlevering
  - Our first online group session was zoom
  - Switched to discord to include TA
  - Must adapt to cooperation without face to face interaction, as some group
    members do not have an accessible webcam or are uncomfortable video calling.
- Adapting to quarantine:
  - Preparing personal agendas for discord meetings
  - Potentially setting up a work time of a few hours to cooperate on discord so
    that problems may be brought up to the group instantly, rather than having
    to wait on a meeting either tuesday or thursday. _touch up on stuff
    mentioned in previous md._

##Class Diagram

# Deloppgave 2: Krav

- Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva
  dere har gjort siden forrige gang.
  - https://github.com/inf112-v20/biblioteket/milestone/4
- As a user I expect to be able to see card priority upon selection
  - The game should have a GUI in which a player is given an overview of all the
    information they may need when processing how to go about their turn.
  - Criteria: Each card should be given a priority so that players are able to
    determine its most efficient use.
- As a user I expect the game to maintain it’s current functionality when
  resizing the window.
  - The game should be resizable, and maintain all functionality no matter what
    resolution the window is resized to.
  - Criteria: The player’s mouse clicks should register based on where the mouse
    is regarding the new window, rather than the old window.
- As a user I expect my robot to fall down holes or off the map if a move I have
  made pushes them off.
  - Robots should be able to fall through holes and off the map.
  - Criteria: A player should fall through a hole or off the map if their robots
    movements lead them to do so in theory.
- As a player I expect to be able to play with my friends, either on the same
  computer, or on a connection (lan)
  - Players should be met with a selection screen to select multi/single player.
  - Criteria: The game needs a second screen behind the original main menu
    screen where a player may specify their desired play conditions. Dev
    stories???

## Testing

The code base has quite extensive testing, we test movement and interactions and
the map reader but have relied on manual testing of interactions because it took
a while to figure out a way to launch libgdx headless for testing as it couldn't
load the assets without launching an ApplicationListener. For the manual testing
see the ManualTests.md file in the test folder.

Bugs/issues/etc.
No known bugs.
