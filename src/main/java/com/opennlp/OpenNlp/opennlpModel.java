package com.opennlp.OpenNlp;

import java.net.URL;

/**
 * Class containing the URL of the model.
 *
 * @author Hugo “m09” Mougard
 */
public class opennlpModel {

    /**
     * URL of the model.
     */
    static final public URL url = opennlpModel.class.getClassLoader()
            .getResource(
            "com/opennlp/OpenNlp/test");
}
