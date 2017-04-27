/*
 * Copyright (c) 2017 Falko Schumann
 * Released under the terms of the MIT License.
 */

package de.muspellheim.javafx.viewcontroller;

import javafx.fxml.*;
import javafx.scene.*;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Provides the infrastructure for managing the views of your JavaFX app.
 * <p> Give location of a FXML file in constructor or override {@link #loadView()} to create view
 * manually and set it with {@link #setView(Parent)}.</p>
 * <p>React to view events by override the methods</p>
 * <ul>
 * <li>{@link #viewDidLoad()} called after the view is loaded or created by {@link #loadView()}.</li>
 * <li>{@link #viewWillAppear()} called before the view is added to the view hierarchy.</li>
 * <li>{@link #viewDidAppear()} called after the view is added to the view hierarchy.</li>
 * <li>{@link #viewWillDisappear()} called before the view is removed to the view hierarchy.</li>
 * <li>{@link #viewDidDisappear()} called after the view is removed to the view hierarchy.</li>
 * </ul>
 */
public class ViewController {

    private final URL location;
    private final ResourceBundle resources;

    private Parent view;

    public ViewController() {
        this(null, null);
    }

    public ViewController(URL location) {
        this(location, null);
    }

    public ViewController(URL location, ResourceBundle resources) {
        this.location = location;
        this.resources = resources;
    }

    public URL getLocation() {
        return location;
    }

    public Parent getView() {
        loadViewIfNeeded();
        return view;
    }

    public void setView(Parent view) {
        this.view = view;
    }

    public void loadViewIfNeeded() {
        if (!isViewLoaded()) {
            loadView();
            viewDidLoad();
        }
    }

    public boolean isViewLoaded() {
        return view != null;
    }

    public void loadView() {
        try {
            view = FXMLLoader.load(location, resources);
        } catch (IOException ex) {
            throw new IllegalStateException("Can not load view from location " + location + ".", ex);
        }
    }

    protected void viewDidLoad() {
    }

    protected void viewWillAppear() {
    }

    protected void viewDidAppear() {
    }

    protected void viewWillDisappear() {
    }

    protected void viewDidDisappear() {
    }

}
