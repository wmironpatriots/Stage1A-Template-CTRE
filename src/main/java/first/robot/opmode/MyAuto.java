/*
 * Copyright 2026 FRCSoftware
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package first.robot.opmode;

import first.robot.Robot;

import org.wpilib.system.Timer;//import java.util.Timer;

import org.wpilib.opmode.Autonomous;
import org.wpilib.opmode.PeriodicOpMode;

@Autonomous(name = "My Auto", group = "Group 1")
public class MyAuto extends PeriodicOpMode {
  private final Robot robot;
  private final Timer autoTimer = new Timer();
  /** The Robot instance is passed into the opmode via the constructor. */
  public MyAuto(Robot robot) {
    this.robot = robot;
  }

  @Override
  public void start() {
    autoTimer.restart(); // Reset the timer to zero at the start of auto/* Called once when the robot is enabled. */
  }

  /*
   * This method runs periodically, using the same period as the Robot instance.
   *
   * Additional periodic methods may be configured with addPeriodic(),
   * which can have periods that differ from the main Robot instance.
   */
  @Override
  public void periodic() {
    if (autoTimer.hasElapsed(4.0)) { // Drive for 4 seconds after the start of auto
      robot.drivetrain.arcadeDrive(0.0, 0.0); // Stop the drivetrain after 4 seconds
      robot.intakeLauncher.setThrottle(0.0);
      robot.feeder.setThrottle(0.0);
 // } 
  //else if (autoTimer.hasElapsed(6.0)) {
    robot.drivetrain.arcadeDrive(1.0, 0.0); // Drive forward at half speed with no rotation
  //}
  //else if (autoTimer.hasElapsed(4.0)) {
    robot.drivetrain.arcadeDrive(0.0, -0.5); // Clockwise
  }
  else {
    robot.drivetrain.arcadeDrive(0.5, 0.0); // Counter clockwise
    robot.intakeLauncher.setThrottle(0.9);
    robot.feeder.setThrottle(0.75);
  }

}
}