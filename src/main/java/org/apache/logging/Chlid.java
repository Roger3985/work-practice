package org.apache.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

/**
 *
 */
public class Chlid extends Parent {

    @Override
    protected Logger getLogger() {
        return super.getLogger();
    }

    @Override
    protected void setLogger(Logger logger) {
        super.setLogger(logger);
    }

    @Override
    public void log(Marker marker) {
        super.log(marker);
    }
}