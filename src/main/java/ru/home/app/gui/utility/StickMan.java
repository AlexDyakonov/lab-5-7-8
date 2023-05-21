package ru.home.app.gui.utility;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StickMan {
    private double x;
    private double y;
    private double size;

    private final List<Shape> shapes;

    public StickMan() {
        this.shapes = new ArrayList<>();
    }

    public void generate(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;

        Random rand = new Random();
        Color color = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        Circle head = new Circle(x, y - 50 * size, 20 * size);
        Line body = new Line(x, y - 30 * size, x, y + 30 * size);
        Line leftArm = new Line(x, y - 20 * size, x - 20 * size, y);
        Line rightArm = new Line(x, y - 20 * size, x + 20 * size, y);
        Line leftLeg = new Line(x, y + 30 * size, x - 10 * size, y + 60 * size);
        Line rightLeg = new Line(x, y + 30 * size, x + 10 * size, y + 60 * size);
        Rectangle hitbox = new Rectangle(x - 30 * size, y - 70 * size, 60 * size, 130 * size);
        hitbox.setFill(null);
        hitbox.setStroke(Color.BLACK);
        head.setFill(color);
        body.setStroke(color);
        leftArm.setStroke(color);
        rightArm.setStroke(color);
        leftLeg.setStroke(color);
        rightLeg.setStroke(color);

        this.shapes.add(head);
        this.shapes.add(body);
        this.shapes.add(leftArm);
        this.shapes.add(rightArm);
        this.shapes.add(leftLeg);
        this.shapes.add(rightLeg);
        this.shapes.add(hitbox);
    }

    public void move(double x, double y, double width, double height) {
        double dx = x - this.x;
        double dy = y - this.y;

        if (checkCollision(x, y, width, height)) {
        } else {
            changePosition(dx, dy);
            this.x = x;
            this.y = y;
        }
    }

    private void changePosition(double dx, double dy) {
        for (Shape shape : this.shapes) {
            if (shape instanceof Circle circle) {
                circle.setCenterX(circle.getCenterX() + dx);
                circle.setCenterY(circle.getCenterY() + dy);
            } else if (shape instanceof Line line) {
                line.setStartX(line.getStartX() + dx);
                line.setStartY(line.getStartY() + dy);
                line.setEndX(line.getEndX() + dx);
                line.setEndY(line.getEndY() + dy);
            } else if (shape instanceof Rectangle rectangle) {
                rectangle.setX(rectangle.getX() + dx);
                rectangle.setY(rectangle.getY() + dy);
            }
        }
    }

    private boolean checkCollision(double x, double y, double width, double height) {
        for (Shape shape : this.shapes) {
            if (shape instanceof Rectangle hitbox) {
                double rectLeft = hitbox.getX();
                double rectTop = hitbox.getY();
                double rectRight = hitbox.getX() + hitbox.getWidth();
                double rectBottom = hitbox.getY() + hitbox.getHeight();
                return rectLeft < 0 || rectRight > width || rectTop < 0 || rectBottom > height; // collision detected
            }
        }
        return false;
    }

    public Rectangle2D getHitbox() {
        double buffer = 20 * this.size;
        double x = this.x - buffer + this.size / 2;
        double y = this.y - buffer + this.size / 2;
        double width = 2 * buffer - this.size;
        double height = 2 * buffer - this.size;
        return new Rectangle2D(x, y, width, height);
    }


    public List<Shape> getShapes() {
        return this.shapes;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getSize() {
        return this.size;
    }
}