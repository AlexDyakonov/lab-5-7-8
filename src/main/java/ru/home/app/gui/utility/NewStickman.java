package ru.home.app.gui.utility;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Random;

public class NewStickman {
    private static final double STICKMAN_SIZE = 40;
    double x;
    double y;
    double speedX;
    double speedY;
    Group stickmanGroup;

    private static Color generateColor(String input) {
        int hashCode = input.hashCode();
        int red = (hashCode & 0xFF0000) >> 16;
        int green = (hashCode & 0x00FF00) >> 8;
        int blue = hashCode & 0x0000FF;
        return Color.rgb(red, green, blue);
    }


    public NewStickman(double x, double y, double speedX, double speedY, String username) {
        Color color = generateColor(username);
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;

        double headRadius = STICKMAN_SIZE * 0.15;
        double bodyLength = STICKMAN_SIZE * 0.4;
        double limbLength = STICKMAN_SIZE * 0.3;
        double limbOffset = STICKMAN_SIZE * 0.2;

        Circle head = new Circle(headRadius);
        head.setCenterX(headRadius);
        head.setCenterY(headRadius);
        head.setFill(color);

        Line body = new Line(headRadius, headRadius * 2, headRadius, headRadius * 2 + bodyLength);
        body.setStroke(color);

        double armAngle = 45.0;
        double armLength = limbLength / Math.cos(Math.toRadians(armAngle)) - 5;
        double armOffset = limbOffset + limbLength * Math.sin(Math.toRadians(armAngle));

        Line leftArm = new Line(headRadius, headRadius * 2 + limbOffset, headRadius - armLength, headRadius * 2 + armOffset);
        leftArm.setStroke(color);

        Line rightArm = new Line(headRadius, headRadius * 2 + limbOffset, headRadius + armLength, headRadius * 2 + armOffset);
        rightArm.setStroke(color);

        Line leftLeg = new Line(headRadius, headRadius * 2 + bodyLength, headRadius - limbLength, headRadius * 2 + bodyLength + limbLength);
        leftLeg.setStroke(color);

        Line rightLeg = new Line(headRadius, headRadius * 2 + bodyLength, headRadius + limbLength, headRadius * 2 + bodyLength + limbLength);
        rightLeg.setStroke(color);

        this.stickmanGroup = new Group(head, body, leftArm, rightArm, leftLeg, rightLeg);
    }

    public void move(double mouseX, double mouseY, double WIDTH, double HEIGHT) {
        Random random = new Random();

        double dx = x - mouseX;
        double dy = y - mouseY;
        double angle = Math.atan2(dy, dx);
        double newX = x + 5 * Math.cos(angle);
        double newY = y + 5 * Math.sin(angle);

        double distanceTravelled = Math.hypot(this.x - mouseX, this.y - mouseY);
        if (distanceTravelled < 50) {
            this.speedX = (1 + random.nextDouble() * 3) * Math.signum(this.speedX);
            this.speedY = (1 + random.nextDouble() * 3) * Math.signum(this.speedY);
            if (!(newX - STICKMAN_SIZE < 0 || newX + STICKMAN_SIZE > WIDTH)) {
                x = newX;
            }
            if (!(newY - STICKMAN_SIZE < 0 || newY + STICKMAN_SIZE > HEIGHT)) {
                y = newY;
            }
        }

        this.x += this.speedX;
        this.y += this.speedY;

        if (distanceTravelled > 10) {
            this.speedX *= 0.9;
            this.speedY *= 0.9;
        }

        double minSpeed = 0.5;
        if (Math.abs(this.speedX) < minSpeed) {
            this.speedX = Math.copySign(minSpeed, this.speedX);
        }
        if (Math.abs(this.speedY) < minSpeed) {
            this.speedY = Math.copySign(minSpeed, this.speedY);
        }

        this.stickmanGroup.setTranslateX(this.x);
        this.stickmanGroup.setTranslateY(this.y);

        if (this.x < 3 || this.x + STICKMAN_SIZE > WIDTH - 7) {
            this.speedX *= -1.05;
        }
        if (this.y < 3 || this.y + STICKMAN_SIZE > HEIGHT - 7) {
            this.speedY *= -1.05;
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public Group getStickmanGroup() {
        return stickmanGroup;
    }

    public void setStickmanGroup(Group stickmanGroup) {
        this.stickmanGroup = stickmanGroup;
    }

    public static double getStickmanSize() {
        return STICKMAN_SIZE;
    }
}
