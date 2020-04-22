package cz.rsvoboda.getting.started;

import io.quarkus.test.junit.QuarkusTest;
import org.jboss.byteman.contrib.bmunit.BMRule;
import org.jboss.byteman.contrib.bmunit.BMRules;
import org.jboss.byteman.contrib.bmunit.BMUnitConfig;
import org.jboss.byteman.contrib.bmunit.WithByteman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

@WithByteman
@BMUnitConfig(verbose = false, bmunitVerbose = false)
//@QuarkusTest
public class FooTest {
    @Test
    @BMRules(rules = {
            @BMRule(name = "GreetingResource action",
                    targetClass = "cz.rsvoboda.getting.started.GreetingResource",
                    targetMethod = "hello",
                    action = "return \"ahoj\""
            )
    })
    void create() {
        Assertions.assertEquals("ahoj", new GreetingResource().hello());
    }
}
