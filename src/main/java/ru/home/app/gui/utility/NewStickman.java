package ru.home.app.gui.utility;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class NewStickman {
    private static final int STICKMAN_SIZE = 52;
    private final Group stickmanGroup;
    private double x;
    private double y;
    private Point2D velocity;

    private static Color generateColor(String input) {
        int hashCode = input.hashCode();
        int red = (hashCode & 0xFF0000) >> 16;
        int green = (hashCode & 0x00FF00) >> 8;
        int blue = hashCode & 0x0000FF;
        return Color.rgb(red, green, blue);
    }


    public NewStickman(String username) {
        stickmanGroup = new Group();
        Color color = generateColor(username);
        this.x = 0;
        this.y = 0;
        this.velocity = new Point2D(0, 0);

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

        double armAngle = 50.0;
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

        stickmanGroup.getChildren().addAll(head, body, leftArm, rightArm, leftLeg, rightLeg);
    }

    public void update() {
        x += velocity.getX();
        y += velocity.getY();
    }

    public void addToPane(Pane pane) {
        pane.getChildren().add(stickmanGroup);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        stickmanGroup.setLayoutX(x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        stickmanGroup.setLayoutY(y);
    }

    public static int getSize() {
        return STICKMAN_SIZE;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }
}
