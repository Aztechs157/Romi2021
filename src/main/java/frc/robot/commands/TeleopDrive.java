// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TeleopDrive extends CommandBase {

    private final SendableChooser<Runnable> chooser = new SendableChooser<>();

    public TeleopDrive(Drivetrain drivetrain, Joystick controller) {
        addRequirements(drivetrain);

        chooser.setDefaultOption("Arcade Drive",
                () -> drivetrain.arcadeDrive(-controller.getRawAxis(1) * 0.95, controller.getRawAxis(4) * 0.95));
        chooser.addOption("Tank Drive",
                () -> drivetrain.tankDrive(-controller.getRawAxis(1) * 0.95, -controller.getRawAxis(5) * 0.95));
        SmartDashboard.putData(chooser);

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        chooser.getSelected().run();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
