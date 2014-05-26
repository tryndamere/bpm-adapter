package org.bpm.engine.runtime;

import org.bpm.engine.AbstractBaseTest;
import org.bpm.engine.definition.BpmDefinition;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izerui.com on 14-5-26.
 */
public class FollowActivityTest extends AbstractBaseTest{


    @Autowired
    BpmRuntime bpmRuntime;

    @Autowired
    BpmDefinition bpmDefinition;

    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();


    @Before
    public void init() throws IOException {
        InputStream inputStream = resolver.getResource("/diagrams/followActivity.zip").getInputStream();
        bpmDefinition.deploy(null,new ZipInputStream(inputStream),null);
    }

    @Test
    public void testFollowActivitys(){
        String taskId = bpmRuntime.startProcess(null, "nextTask", null, null, null);

        List<ActivityDefinition> nextActivitys = bpmRuntime.nextActivities(null, taskId, null);
        assertThat(nextActivitys).isNotEmpty();

    }



}
