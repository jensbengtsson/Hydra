package com.findwise.hydra.stage;

import com.findwise.hydra.local.LocalDocument;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/** 
 * @author Roar Granevang & Joel Westberg
 */
public class RemoveFieldsTest {

    @Test
    public void testProcess() throws Exception {
        LocalDocument doc = new LocalDocument();
        doc.putContentField("title", "Supertitle");
        doc.putContentField("type", "Supertype");
        doc.putContentField("some_numeric_3", "");
        
        LinkedList<String> removeFields = new LinkedList<String>();
        removeFields.add("title");
        removeFields.add("otherObject");
        removeFields.add("field_not_existing");
        removeFields.add(".*[0-9]+");
        
        assertNotNull("Should not have been removed yet", doc.getContentField("title"));
        
        RemoveFields stage = new RemoveFields();
        stage.setRemoveFields(removeFields);
        stage.init();
        stage.process(doc);
        
        assertTrue("Should not have been removed", doc.hasContentField("type"));
        
        assertFalse("Should have been removed", doc.hasContentField("title"));
        assertFalse("Should have been removed", doc.hasContentField("some_numeric_3"));
    }

}
