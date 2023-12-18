package util;

import model.MobilModel;

public class Link {

    public MobilModel data;
    public Link next;

    public Link(MobilModel data) {
        this.data = data;
    }

    public void displayLink() {
        System.out.println(data.toString());
    }
}