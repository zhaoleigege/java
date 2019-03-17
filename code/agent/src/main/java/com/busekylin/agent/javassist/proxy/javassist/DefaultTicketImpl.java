package com.busekylin.agent.javassist.proxy.javassist;

public class DefaultTicketImpl implements Ticket {
    @Override
    public void sell() {
        System.out.println("火车站售票");
    }
}
