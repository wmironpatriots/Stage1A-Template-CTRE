/*
 * Copyright 2026 FRCSoftware
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package first.robot;

import org.wpilib.drive.DifferentialDrive;
import org.wpilib.framework.OpModeRobot;
import org.wpilib.hardware.bus.CANPort;
import org.wpilib.hardware.imu.OnboardIMU;
import org.wpilib.hardware.imu.OnboardIMU.MountOrientation;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;





/**
 * The methods in this class are called automatically as described in the OpModeRobot documentation.
 * OpMode classes anywhere in the package (or sub-packages) where this class is located are
 * automatically registered to display in the Driver Station. If you change the name of this class
 * or the package after creating this project, you must also update the Main.java file in the
 * project.
 */
public class Robot extends OpModeRobot {

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  private final TalonFX leftLeader = new TalonFX(0, new CANBus(CANPort.CAN_S0));
  private final TalonFX leftFollower = new TalonFX(1, new CANBus(CANPort.CAN_S0));
  private final TalonFX rightLeader = new TalonFX(2, new CANBus(CANPort.CAN_S0));
  private final TalonFX rightFollower = new TalonFX(3, new CANBus(CANPort.CAN_S0));

  

  public final DifferentialDrive drivetrain =
    new DifferentialDrive(leftLeader::setThrottle, rightLeader::setThrottle);
  
  private final OnboardIMU imu = new OnboardIMU(MountOrientation.FLAT);

  public Robot() {
    TalonFXConfiguration leftConfig = new TalonFXConfiguration();
    leftConfig.MotorOutput.withInverted(InvertedValue.Clockwise_Positive);
    leftLeader.getConfigurator().apply(leftConfig);
    leftFollower.getConfigurator().apply(leftConfig);
    leftFollower.setControl(new Follower(leftLeader.getDeviceID(), MotorAlignmentValue.Aligned));

    TalonFXConfiguration rightConfig = new TalonFXConfiguration();
    rightConfig.MotorOutput.withInverted(InvertedValue.CounterClockwise_Positive);
    rightLeader.getConfigurator().apply(rightConfig);
    rightFollower.getConfigurator().apply(rightConfig);
    rightFollower.setControl(new Follower(rightLeader.getDeviceID(), MotorAlignmentValue.Aligned));
  }

  @Override
  public void simulationPeriodic() {}
}
