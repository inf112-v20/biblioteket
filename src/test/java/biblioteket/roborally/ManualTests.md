# Manual testing

**NOTE:** These tests are run by moving the robot using `WASD` and interacting
with the environment with `<space>`.

## Testing movement

- Move the robot onto conveyor belts and verify that the robots moves in the
  direction that the convery belt points.
- Verify that moving off the grid moves the robot to its initial spawn unless
  the archive marker has moved.
- Ensure that attempting to move across blocked cells (i.e. there are one or
  more walls between the intial and target location) do not work in any
  direction.

## Testing interactions

- Ensure that moving off the grid returns you to your archive marker.
- Moving over a hole and interacting with it will respawn the robot.
