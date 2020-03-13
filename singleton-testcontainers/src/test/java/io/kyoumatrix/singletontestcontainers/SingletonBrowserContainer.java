package io.kyoumatrix.singletontestcontainers;

import org.testcontainers.containers.BrowserWebDriverContainer;

public enum SingletonBrowserContainer {

    INSTANCE("Initial class info");


    private BrowserWebDriverContainer BROWSER_CONTAINER;

    SingletonBrowserContainer(String s) {


    }

    public static SingletonBrowserContainer getInstance() {
        return INSTANCE;
    }


    public BrowserWebDriverContainer getBROWSER_CONTAINER() {

        return BROWSER_CONTAINER;
    }

    public void setBROWSER_CONTAINER(BrowserWebDriverContainer BROWSER_CONTAINER) {
        this.BROWSER_CONTAINER = BROWSER_CONTAINER;
    }
}
