# Manual testing

**NOTE:** These tests are run by moving the robot using `WASD` and interacting
with the environment with `<space>` or `<enter>`, you can also rotate the robot
with the right and left arrow.

## Testing movement

- Move the robot onto conveyor belts and verify that the robots moves in the
  direction that the convery belt points.
- Verify that moving off the grid moves the robot to its initial spawn unless
  the archive marker has moved.
- Ensure that attempting to move across blocked cells (i.e. there are one or
  more walls between the intial and target location) do not work in any
  direction.

## Testing movement with cards

**NOTE:** It might be a bit hard to keep track of where your robot moves during
the cards as it currently simply teleports to the correct location...

- Add cards to the robots register and see that it ends up in the correct
  location.
- Add cards such that the robot walks off the map and verify that it respawns on
  the correct archive marker.
- Add cards such that the robot falls into a hole and verify that it respawns on
  the correct archive marker.
- Add cards such that the robot ends up on one or more conveyor belts and verify
  that the robot was moved in a way that is consistent with interacting with the
  environment and the cards.

## Testing interactions

- Ensure that moving off the grid returns you to your archive marker.
- Moving over a hole and interacting with it will respawn the robot.

## Testing menu screens

- The amount of players selected in the `player select` matches the amount of
  controllable robots spawned into the game. There will always be 1 or more AI
  spawned, which is somewhere between `randint(numPlayers(), 8)`.
- Verify that winning a game renders the `EndGameScreen` menu with the correct
  winners name displayed.
