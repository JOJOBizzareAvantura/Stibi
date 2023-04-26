package view;

import java.sql.Date;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.geometry.*;

public class ChosenData {
    // attributes

    // getters and setters for fields in the GUI
    public void setNewFood() {
        // setting new food that we don't have in database in yet
    }

    public String getNewFood() {
        // getting food that we don't have in database'
        return null;
    }

    public void setExistingFood() {
        // setting food that is already in database
    }

    public String getExistingFood() {
        // getting food that is already in the database
        return null;
    }

    public void setFoodDateAdded() {
        // setting the date we are adding the food in the program
    }

    public Date getFoodDateAdded() {
        // getting the date we are adding the food in the program
        return null;
    }

    public void setUserWeight() {
        // setting the weight user has inputed
    }

    public float getUserWeight() {
        // getting the weight user has inputed
        return 0.0f;
    }

    public void setCaloryLimit() {
        // setting the calory limit
    }

    public float getCaloryLimit() {
        return 0.0f;
    }

}
