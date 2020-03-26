# Task 1 - Team and Project

There is no denying that this has become a very different work situation for all
of us. The coronavirus and the shutdown of universities, among other things,
means that we all have to adapt to a new everyday life.

Our group used to meet twice a week. We would meet in the group session and have
a meeting in a group room at school. There we would update what each of us had
done in-between meetings. Most of the time some of us would use a big screen to
show some of the work they had done, and we would review and discuss it. This
process might bring forth further issues we had to discuss or lead to a group
program session, and on the meeting went. Towards the end of the meeting, we
would plan the road ahead and distributed tasks. This is no longer possible, or
rather this must be done in other ways.

For our first online group session, we used Zoom, but we switched over to
discord so the TA could be included. Since some of us use desktops and some are
uncomfortable with the camera, we did not use it. This, of course, removes one
aspect of communication and is not recommended, but that was how we did it. Not
all of the microphones worked optimally, which made it difficult to hear each
other at times. However, screen sharing worked fine as a replacement for
connecting to a projector.

Nevertheless, it is clear that we are not used to working and cooperating in
this way, this is something we must learn together. On one side this is a golden
opportunity to challenge ourselves, on the other, is it extremely frustrating.
We cannot remove people from their home so that we can work undisturbed, or work
in other rooms when those do not exist. These are matters we must be aware of
and take into account as we continue to work together. This is a new situation
for us all, and we do what we can to adapt.

The most important thing now is to find good solutions so that we can cooperate
as best as possible. First, Marcus did a great thing for our first meeting when
he made an agenda of things he wanted us to review. The rest of the group should
also try to plan things they would like to discuss so that the meeting will be
relevant and meaningful for us all. This is especially important now that we
cannot meet in person, and it can quickly feel as if someone is not present at
the meeting unless they want to discuss something.

Secondly, the meetings have become quite different in form but also in length.
Since we are not confined to a room together we are rather fast in calling the
meeting over when we are through our agenda. On one side, long needless meeting
are a waste of everyone time, on the other side as we all are stationed on our
computers we might keep the channel up as each work on our part. As such we
would be able to keep up the work pace. Also, if an issue or question should
arise we would all be there to discuss it. Maybe 4 hours a week we could keep
such a work session.

Lastly, to become even better at updating and using the project board. The
project board is a great way to make the other group members aware of what each
of us is working on, but it is also a good place to post ideas you get as you
work. Perhaps you realize that you need some methods or a feature to complete
what you were working on or might need it in the future, or that your work might
be used on more ways that were intended. Questions that are pressing should be
posted in the slack, but questions we could review in a group session could be
posted as an issue. Whatever the reason, we should keep focusing on creating
issues and using the project board.

The roles we made at the start of the project has not been changed but how and
what we work on has. In other words, the roles are not perfect for our group.
Sondre still practices the role of group leader and responsible for git. He
keeps himself updated on what everyone is working on, both by following the
project board and checking in on the different branches. As secretary, Marcus
has continued to dutifully write meeting minutes, but he has also had a very
active role in the development of the game. As a customer contact, Silje has
made sure that the game we are developing follows Roborally's rules of play, and
made us aware of any deviations. Otherwise, she worked on the development of the
game and participated in the work on the written part of the assignment. Tor
Kristian has been working on developing a GUI for the game, both a setup page
and a page where the gameboard and relevant information for the player will
appear. Tobias has worked on developing animated sprites for the game and looked
at how some of the game elements can be implemented. Although some of these
tasks are deviations from the assigned roles, this has worked well. We have then
been able to focus on the tasks that need to be done and find someone to do
them, rather than to assign them to a specific person.

## Development


Due to the corona virus and the university closing the campus we've changed to
be a fully remote development team, like the rest of the course. This has of
course been a fairly dramatic change for the team and has had large
reprecussions on how we do development. We quickly settled on using the official
Discord server after a short stint using Zoom, and have had two meetings a week
regularly since with a third one here and there when needed.

## Group dynamics and communication

Because of the aforementioned changes in development things have all things
considered gone well. There have been regular feedback on pull requests and
we've filed issues for things we have questions about, issues and enhancements.
As such our project board has been more active now than before because frequent
in-person meetings allowed us to figure these things out in person. In general
the transition has gone well and Discord has been a nice tool with very quick
and easy screen sharing and voice chatting.

## Retrospective

One of the biggest issues we've had this "sprint" has been one of being able to
work with features in isolation while trying not to accidentally do the same
work as someone else on a different feature; i.e. the person working on belts
creating a duplicate set of functions for moving the robot as the person working
on the program cards.

We also decided this cycle to redo the underlying structure of our grid, which
cost us a bit of development time as we had to put some work on hold for a
couple of days while we sketched out a new solution. The underlying issue was
that we wanted to have a generic grid where we'd read from the game map and
populate it with `IElements` and query positions on that grid. This however
quickly became fairly complex and people felt that making it generic was not a
good solution. We decided that Sondre and Marcus would attempt to tackle the
problem in their own way and ended up using Sondre's solution for managing the
grid and Marcus' solution for managing movement.

In the end this cost us a fair bit of time but allowed us to much more rapidly
work on the remaining features this sprint; we've decided to work on many
features to get a minimum viable product rather than spending much time on a few
making them perfect. This will be the next deliverable where we work on
polishing.

For the next sprint we will focus on polishing what we're working on, ensuring
that we get proper testing of the game loop without doing manual testing and
getting networking to work.

# Task 2 - Specifications
For an overview of issues from GitHub done/worked on this sprint you can look at
the [milestone](https://github.com/inf112-v20/biblioteket/milestone/3) we've
created to track mandatory assignments. To get a complete overview of the
project we have a [kanban
board](https://github.com/inf112-v20/biblioteket/projects/1) tracking the total
progress towards the final deliverable.

## User stories

- As a user I expect there to be an overview of my current health and flags etc.
  - The game should have a (simple) GUI giving the player an overview of their
    current health, their robot's hitpoints etc.
  - **Criteria:** The game should have an intuitive HUD that displays the
    currents player health, their picked up flags etc. Care needs to be taken
    that we don't display invalid data, e.g. health below or above certain
    thresholds. We need to ensure that the data is up-to-date according to the
    game loop and that we only display the current players information.
- As a user I expect there to be program cards that I can see, select and use.
  - The game needs to implement a register and the required program cards that
    the player uses to move the robot around.
  - **Criteria:** The game needs to have a working register that the player
    select `n` cards into and uses to control the robot. A player can only
    select a certain amount of cards each round, determined by their health.
    Furthmore, robots interact with one another so handling interactions between
    robots and the environment needs to work.
- As a user I expect the cards to be distinct and easy to see at a glance.
  - The game should have graphics that are easy to understand for players.
  - **Criteria:** The game needs visually distinct cards that shows the player
    what each individual card does without having to look them up in a manual.
- As a user I expect there to be a main menu screen where I can select to play
  the game or quit.
  - The game should have a main menu as the current solution with a simple black
    screen saying `Click anywhere` is not good UX.
  - **Criteria:** The game need a main menu where the player can easily start or
    exit the game with interactive buttons.
- As a player I expect that my robot interacts with the environment in expected
  ways.
  - A robot should not be able to cross walls, move along on conveyor belts,
    should not jump across pits and should "gracefully" handle rolling of the
    board.
  - **Criteria:** Once a user has selected his or hers program cards the robot
    should follow their commands in an expected way. It should not be able to
    move from one tile to another if it is blocked by a wall, if it falls of the
    board it should return to its archive marker. If it moves across a conveyor
    belt it should be moved in the direction and speed that the belt shows and
    if it happens that the robot moves across a hole in the ground it should be
    swallowed and respawn.
- As a player I expect that my robot respawns on its archive marker.
  - Robots do not respawn on the same tile round after round, picking up flags
    and hitting repair stations changes the archive markers position for a
    robot.
  - **Criteria:** A robot should not return to the initial spawn point after
    each death, its archive marker needs to be updated once the corresponding
    actions happen.

## Developer stories

We also have a few user stories based around work that is not user facing but
either is required because of features that the user will see/interact with need
them or because of deficiencies with current design.

- **Criteria:** The game should read and use the `<name>.tmx` files and their
  layers to generate a `Board` that contains all the required information about
  each game instead of iterating over the board and creating a grid where each
  cell can have many different elements.
  - Move from having a map reader that populates a generic grid containing
    `IPositions` with lists of elements in each cell to a `IBoard` that has a
    constant set of layers: player, laser, wall, flag and ground layers.
  - Refactor out and create a new way to get a elements position and direction,
    possibly leveraging the builtin `Vector2` for `x` and `y` coordinates.
- **Criteria:** The game should handle interacting with elements on the board.
  - Since we cannot loop through a list for instantiated objects of elements in
    each cell like with the previous solution.
  - The proposed solution is to use an enum and a factory to generate the
    required elements by looking up their unique ID from the layers in the board
    file.
- **Criteria:** The game should handle keyboard and mouse inputs with a proper
  input multiplexer.
  - The current way of reading input is quite ugly and should have a proper
    separate class doing input multiplexing.
    
## Testing

The code base has quite extensive testing, we test movement and interactions and
the map reader but have relied on manual testing of interactions because it took
a while to figure out a way to launch `libgdx` headless for testing as it
couldn't load the assets without launching an `ApplicationListener`. For the
manual testing see the `ManualTests.md` file in the test folder.

## Bugs, issues etc

For an up-to-date list of issues, please see the issue board on GitHub.

