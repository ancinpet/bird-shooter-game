# Bird shooter game
A small game where you shoot at birds written in Java, mainly to practice design patterns.

Controls

Forward: W

Backward: S

Left: A

Right: D

Shoot: SPACE

Mode: M

Undo: Z

Aim up: Shift

Aim down: Ctrl

Power up: UP

Power down: DOWN

Gravity up: +

Gravity down: -


There are lots of extra configs in the MvcGameConfig.java file.

Simple mode is standart mode with no extra features.
Realistic mode the player has health, score and level. Every level he gets max health. Enemies fly right to left, if they manage to get to the other side of the screen, player looses health. Score +1 for every kill. 10 health, 10 score per level, infinite levels. Every level gets harder (enemies move faster) but also player moves faster. Player dies at 0 health, score gets shown. Enemies spawn randomly (both types) and swerve up and down. Player cannot leave the play field. Enemies can only leave the play field on the left. Missiles have 300px extra skybox at the top so they can still fall down when shot above the screen.

Two undo modes (config change): Time based and command based.

Realisitic mode missiles also have spread.

Images are cached to remove the lag that came with lec5 constant image loading.

Extra template method pattern from lec5 in AbsCommand.
