package org.bpm.engine.runtime;

import static org.assertj.core.api.Assertions.*;

import org.activiti.engine.impl.ServiceImpl;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.bpm.engine.AbstractBaseTest;
import org.bpm.engine.definition.BpmDefinition;
import org.bpm.engine.impl.activiti.ActivitiBaseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

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
